package com.zhurong.platform.core.lantek.event;

import com.zhurong.platform.core.lantek.constants.NestMqConstants;
import com.zhurong.platform.core.lantek.dto.NestStateChangedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class NestEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publish(Integer recID, Integer oldState, Integer newState, LocalDateTime lastDate) {

        NestStateChangedEvent event = NestStateChangedEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .recID(recID)
                .oldState(oldState)
                .newState(newState)
                .lastDate(lastDate)
                .eventTime(LocalDateTime.now())
                .build();
        log.info("发送套料状态变更消息,ID={}，newState={}，oldState={}", recID, newState, oldState);
        rabbitTemplate.convertAndSend(
                NestMqConstants.EXCHANGE,
                NestMqConstants.RoutingKey.STATE_CHANGED,
                event
        );
    }
}