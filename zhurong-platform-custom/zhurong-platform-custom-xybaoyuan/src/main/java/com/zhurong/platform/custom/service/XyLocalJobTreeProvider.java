package com.zhurong.platform.custom.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.zhurong.platform.core.lantek.vo.JobBrowserTreeVO;
import com.zhurong.platform.custom.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Windows 客户端直接读取本机 Lantek 作业浏览表。
 * MQ 消费线程没有网页登录令牌，因此创建作业前的目录、重名校验不能再通过 Feign 查询 core。
 */
@Component
@DS("lantek")
@ConditionalOnClientCommunicationEnabled
public class XyLocalJobTreeProvider implements XyJobTreeProvider {

    private final JdbcTemplate jdbcTemplate;

    public XyLocalJobTreeProvider(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<JobBrowserTreeVO> getTree() {
        List<JobBrowserTreeVO> nodes = jdbcTemplate.query(
                "SELECT NodeName, NodeID, ParentID, RecState "
                        + "FROM DIS_MMNN_BWSR_00000100 ORDER BY NodeID",
                (resultSet, rowNumber) -> {
                    JobBrowserTreeVO node = new JobBrowserTreeVO();
                    node.setId(String.valueOf(resultSet.getInt("NodeID")));
                    int parentId = resultSet.getInt("ParentID");
                    node.setParentId(resultSet.wasNull() ? null : String.valueOf(parentId));
                    node.setLabel(resultSet.getString("NodeName"));
                    int recordState = resultSet.getInt("RecState");
                    node.setIsFolder(resultSet.wasNull() || recordState != 1);
                    node.setChildren(new ArrayList<>());
                    return node;
                }
        );
        return buildTree(nodes);
    }

    private static List<JobBrowserTreeVO> buildTree(List<JobBrowserTreeVO> nodes) {
        Map<String, JobBrowserTreeVO> byId = new LinkedHashMap<>();
        nodes.forEach(node -> byId.put(node.getId(), node));

        List<JobBrowserTreeVO> roots = new ArrayList<>();
        for (JobBrowserTreeVO node : nodes) {
            JobBrowserTreeVO parent = byId.get(node.getParentId());
            if (parent == null || "-1".equals(node.getParentId())) {
                roots.add(node);
            } else {
                parent.getChildren().add(node);
            }
        }
        return roots;
    }
}
