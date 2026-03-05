package com.ao.platform.core.lantek.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NestStateChangedEvent {

    private String eventId;

    private Integer recID;

    private Integer oldState;

    private Integer newState;

    private LocalDateTime lastDate;
    private LocalDateTime eventTime;

}