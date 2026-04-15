package com.zhurong.platform.core.lantek.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.core.lantek.convert.DisMmnnMmoo00000200Convert;
import com.zhurong.platform.core.lantek.dto.DisMmnnMmoo00000200DTO;
import com.zhurong.platform.core.lantek.dto.RelationLoadPlan;
import com.zhurong.platform.core.lantek.entity.DisMmnnBwsr00000100;
import com.zhurong.platform.core.lantek.entity.DisMmnnBwsr00000200;
import com.zhurong.platform.core.lantek.entity.DisMmnnMmoo00000200;
import com.zhurong.platform.core.lantek.mapper.DisMmnnMmoo00000200Mapper;
import com.zhurong.platform.core.lantek.service.IDisMmnnBwsr00000100Service;
import com.zhurong.platform.core.lantek.service.IDisMmnnBwsr00000200Service;
import com.zhurong.platform.core.lantek.service.IDisMmnnMmoo00000200Service;
import com.zhurong.platform.core.lantek.vo.DisMmnnMmoo00000200VO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class DisMmnnMmoo00000200ServiceImpl
        extends ServiceImpl<DisMmnnMmoo00000200Mapper, DisMmnnMmoo00000200>
        implements IDisMmnnMmoo00000200Service {

    private final DisMmnnMmoo00000200Convert convert;
    private final IDisMmnnBwsr00000100Service disMmnnBwsr00000100Service;
    private final IDisMmnnBwsr00000200Service disMmnnBwsr00000200Service;

    @Override
    public List<DisMmnnMmoo00000200VO> findJobsByRefs(List<String> jobRefs, RelationLoadPlan loadPlan) {
        if (CollectionUtils.isEmpty(jobRefs)) {
            return Collections.emptyList();
        }

        List<DisMmnnMmoo00000200> jobs = this.list(
                Wrappers.lambdaQuery(DisMmnnMmoo00000200.class)
                        .in(DisMmnnMmoo00000200::getJobRef, jobRefs)
        );

        List<DisMmnnMmoo00000200VO> views = convert.toVOList(jobs);

        if (loadPlan != null && loadPlan.isIncludeJobFullPath()) {
            Map<String, String> pathMap = buildJobPathMap(jobRefs, jobs);
            views.forEach(vo -> vo.setJobFullPath(pathMap.get(vo.getJobRef())));
        }

        return views;
    }

    @Override
    public Map<String, String> buildJobPathMap(List<String> jobRefs, List<DisMmnnMmoo00000200> seedJobs) {
        Map<String, String> result = new HashMap<>();

        List<DisMmnnMmoo00000200> jobs;
        if (CollectionUtils.isEmpty(seedJobs)) {
            jobs = this.list(
                    Wrappers.lambdaQuery(DisMmnnMmoo00000200.class)
                            .in(DisMmnnMmoo00000200::getJobRef, jobRefs)
            );
        } else {
            jobs = seedJobs;
        }

        if (CollectionUtils.isEmpty(jobs)) {
            return result;
        }

        List<DisMmnnBwsr00000100> folders = disMmnnBwsr00000100Service.list();
        Map<Integer, DisMmnnBwsr00000100> folderMap = folders.stream()
                .filter(it -> it.getNodeID() != null)
                .collect(Collectors.toMap(DisMmnnBwsr00000100::getNodeID, Function.identity(), (a, b) -> a));

        List<Integer> recIds = jobs.stream()
                .map(DisMmnnMmoo00000200::getRecID)
                .filter(Objects::nonNull)
                .toList();

        List<DisMmnnBwsr00000200> links = disMmnnBwsr00000200Service.list(
                Wrappers.lambdaQuery(DisMmnnBwsr00000200.class)
                        .in(DisMmnnBwsr00000200::getRecordID, recIds)
        );

        Map<Integer, DisMmnnBwsr00000200> linkMap = links.stream()
                .filter(it -> it.getRecordID() != null)
                .collect(Collectors.toMap(DisMmnnBwsr00000200::getRecordID, Function.identity(), (a, b) -> a));

        for (DisMmnnMmoo00000200 job : jobs) {
            if (job.getJobRef() == null) {
                continue;
            }

            StringBuilder pathBuilder = new StringBuilder(
                    StringUtils.isBlank(job.getJobName()) ? "" : job.getJobName()
            );

            DisMmnnBwsr00000200 rel = linkMap.get(job.getRecID());
            if (rel != null) {
                prependFolderPath(rel.getBwsrID(), pathBuilder, folderMap);
            }

            result.put(job.getJobRef(), pathBuilder.isEmpty() ? "/" : pathBuilder.toString());
        }

        return result;
    }

    @Override
    public String resolveJobPath(String jobRef) {
        if (StringUtils.isBlank(jobRef)) {
            return "/";
        }
        return buildJobPathMap(List.of(jobRef), Collections.emptyList())
                .getOrDefault(jobRef, "/");
    }

    private void prependFolderPath(Integer parentId,
                                   StringBuilder fullPath,
                                   Map<Integer, DisMmnnBwsr00000100> folderMap) {
        if (parentId == null || parentId == 0) {
            return;
        }

        DisMmnnBwsr00000100 current = folderMap.get(parentId);
        if (current == null || current.getParentID() == null || current.getParentID() == 0) {
            return;
        }

        fullPath.insert(0, "/");
        fullPath.insert(0, Optional.ofNullable(current.getNodeName()).orElse(""));
        prependFolderPath(current.getParentID(), fullPath, folderMap);
    }

    @Override
    public DisMmnnMmoo00000200VO getVOById(Long id) {
        DisMmnnMmoo00000200 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(DisMmnnMmoo00000200DTO dto) {
        DisMmnnMmoo00000200 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, DisMmnnMmoo00000200DTO dto) {
        DisMmnnMmoo00000200 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
