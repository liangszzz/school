package com.github.yiyan1992.carloan.config;

public class MagicValue {

    public static final String LOGIN_TYPE_MANAGE = "manage-";
    public static final String LOGIN_TYPE_TEACHER = "teacher-";
    public static final String LOGIN_TYPE_STUDENT = "student-";

    public static boolean isManage(String value) {
        return value.startsWith(LOGIN_TYPE_MANAGE);
    }

    public static boolean isTeacher(String value) {
        return value.startsWith(LOGIN_TYPE_TEACHER);
    }

    public static boolean isStudent(String value) {
        return value.startsWith(LOGIN_TYPE_STUDENT);
    }
}
