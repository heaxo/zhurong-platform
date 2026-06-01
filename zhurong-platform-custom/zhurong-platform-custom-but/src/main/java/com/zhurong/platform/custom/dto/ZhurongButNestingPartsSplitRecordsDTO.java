package com.zhurong.platform.custom.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
*  DTO
*/
@Data
public class ZhurongButNestingPartsSplitRecordsDTO implements Serializable {

    private List<ZhurongButNestingPartsSplitRecordsCreateDTO> records;

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdBy;
    private Long updatedBy;
    private Integer version;
    private Boolean isDeleted;
    private Boolean isRead;
    private Boolean isReviewed;
    private String nstRef;
    private String mnoRef;
    private Integer oprId;
    private Integer quantity;
    private String remark;
    private String ordRef;
    private Integer recId;
    private String prdRef;

}
