package com.omad.lee.damo.Model.Responce;

public enum ApplicationUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    QUESTION_READ("question:read"),
    QUESTION_WRITE("question:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        System.out.println("ApplicationUSerPermission     get permission   = " +permission);
        return permission;
    }
}
