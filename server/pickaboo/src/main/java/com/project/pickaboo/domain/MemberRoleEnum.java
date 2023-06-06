package com.project.pickaboo.domain;

public enum MemberRoleEnum {

    MEMBER(Authority.MEMBER),
    ADMIN(Authority.ADMIN);

    private final String authority;

    MemberRoleEnum(final String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    private static class Authority {
        public static final String MEMBER  = "ROLE_MEMBER";
        public static final String ADMIN = "ROLE_ADMIN";
    }

}
