package com.project.pickaboo.domain;

public enum PlatformEnum {

    NONE(Platform.NONE),
    KAKAO(Platform.KAKAO);
    private final String platform;

    PlatformEnum(final String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return this.platform;
    }

    public static class Platform {
        public static final String NONE = "PLATFORM_NONE";
        public static final String KAKAO = "PLATFORM_KAKAO";
    }
}
