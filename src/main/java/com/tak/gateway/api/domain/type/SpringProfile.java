package com.tak.gateway.api.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum SpringProfile {
    LOCAL("local") //
    , DEV("dev") //
    , PRD("prd") //
    ;

    @Getter
    private final String code;

    public static SpringProfile fromCode(String code) {
        return Arrays.stream(SpringProfile.values()).filter(t -> t.getCode().equals(code)).findFirst()
                .orElse(LOCAL);
    }

    public boolean isLocal() {
        return LOCAL.equals(this);
    }

    public boolean isDev() {
        return DEV.equals(this);
    }

    public boolean isPrd() {
        return (PRD.equals(this));
    }
}
