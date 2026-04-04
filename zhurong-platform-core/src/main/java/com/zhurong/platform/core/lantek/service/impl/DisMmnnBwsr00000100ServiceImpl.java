package com.zhurong.platform.core.lantek.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.core.lantek.convert.DisMmnnBwsr00000100Convert;
import com.zhurong.platform.core.lantek.dto.DisMmnnBwsr00000100DTO;
import com.zhurong.platform.core.lantek.entity.DisMmnnBwsr00000100;
import com.zhurong.platform.core.lantek.mapper.DisMmnnBwsr00000100Mapper;
import com.zhurong.platform.core.lantek.service.IDisMmnnBwsr00000100Service;
import com.zhurong.platform.core.lantek.vo.DisMmnnBwsr00000100VO;
import com.zhurong.platform.core.lantek.vo.JobBrowserTreeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class DisMmnnBwsr00000100ServiceImpl
        extends ServiceImpl<DisMmnnBwsr00000100Mapper, DisMmnnBwsr00000100>
        implements IDisMmnnBwsr00000100Service {

    private final DisMmnnBwsr00000100Convert convert;
    private final DisMmnnBwsr00000100Mapper disMmnnBwsr00000100Mapper;



    @Override
    public DisMmnnBwsr00000100VO getVOById(Long id) {
        DisMmnnBwsr00000100 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(DisMmnnBwsr00000100DTO dto) {
        DisMmnnBwsr00000100 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, DisMmnnBwsr00000100DTO dto) {
        DisMmnnBwsr00000100 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }

    @Override
    public List<JobBrowserTreeVO> getJobBrowserTree() {
        List<DisMmnnBwsr00000100VO> jobTreeList = disMmnnBwsr00000100Mapper.selectJobTreeList();
        List<JobBrowserTreeVO> tree = buildTree(jobTreeList);
        return tree;
    }

    private List<JobBrowserTreeVO> buildTree(List<DisMmnnBwsr00000100VO> list) {

        Map<String, JobBrowserTreeVO> nodeMap = new HashMap<>();

        for (DisMmnnBwsr00000100VO item : list) {
            JobBrowserTreeVO node = new JobBrowserTreeVO();
            node.setId(item.getNodeID());
            node.setParentId(
                    item.getParentID() == null ? null : String.valueOf(item.getParentID())
            );
            node.setLabel(item.getNodeName());
            node.setIsFolder(item.getRecState() == null || item.getRecState() != 1);
            node.setChildren(new ArrayList<>());

            nodeMap.put(node.getId(), node);
        }

        List<JobBrowserTreeVO> rootList = new ArrayList<>();

        for (JobBrowserTreeVO node : nodeMap.values()) {
            String parentId = node.getParentId();

            // 根节点（关键判断）
            if (parentId == null || parentId.equals("-1") || !nodeMap.containsKey(parentId)) {
                rootList.add(node);
            } else {
                JobBrowserTreeVO parent = nodeMap.get(parentId);
                parent.getChildren().add(node);
            }
        }

        return rootList;
    }
}
