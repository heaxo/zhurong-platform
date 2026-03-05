package com.ao.platform.core.lantek.event;

import com.ao.platform.core.lantek.constants.NestMqConstants;
import com.ao.platform.core.lantek.dto.NestStateChangedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
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

        rabbitTemplate.convertAndSend(
                NestMqConstants.EXCHANGE,
                NestMqConstants.RoutingKey.STATE_CHANGED,
                event
        );
    }
}