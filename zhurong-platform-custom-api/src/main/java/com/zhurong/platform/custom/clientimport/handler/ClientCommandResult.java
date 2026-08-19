package com.zhurong.platform.custom.clientimport.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCommandResult {

    private boolean success;

    private String message;

    private Object data;

    public static ClientCommandResult success(String message, Object data) {
        return new ClientCommandResult(true, message, data);
    }

    public static ClientCommandResult failed(String message) {
        return new ClientCommandResult(false, message, null);
    }
}
