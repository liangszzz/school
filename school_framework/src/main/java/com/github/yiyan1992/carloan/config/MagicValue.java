package com.github.yiyan1992.carloan.config;

public class MagicValue {

    public static final String LOGIN_TYPE_0 = "manage-";
    public static final String LOGIN_TYPE_1 = "teacher-";
    public static final String LOGIN_TYPE_2 = "student-";

    public static boolean isManage(String username) {
        return username.startsWith(LOGIN_TYPE_0);
    }

    public static boolean isTeacher(String username) {
        return username.startsWith(LOGIN_TYPE_1);
    }

    public static boolean isStudent(String username) {
        return username.startsWith(LOGIN_TYPE_2);
    }
}
