package com.tak.gateway.api.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum ApplicationName {
    Gateway("gateway", "api-gateway"), // main-web
    ;

    @Getter
    private final String code;

    @Getter
    private final String name;

    public static ApplicationName fromCode(String _code) {
        return Arrays.stream(ApplicationName.values()).filter(t -> t.getCode().equals(_code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Code - " + _code));
    }

    public static ApplicationName fromName(String _name) {
        return Arrays.stream(ApplicationName.values()).filter(t -> t.getName().equals(_name)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Name - " + _name));
    }
}
