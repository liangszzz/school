package com.github.yiyan1992.carloan.entity.query;

import com.github.yiyan1992.carloan.config.MagicValue;
import lombok.Data;

/**
 * @author admin
 */
@Data
public class LoginQuery {

    private String username;

    private String password;
    /**
     * 登录类型
     * 0:管理员
     * 1:教师
     * 2:学生
     */
    private String loginType;

    public String getUsername() {
        switch (loginType) {
            case "0":
                return MagicValue.LOGIN_TYPE_MANAGE + username;
            case "1":
                return MagicValue.LOGIN_TYPE_TEACHER + username;
            case "2":
                return MagicValue.LOGIN_TYPE_STUDENT + username;
        }
        return username;
    }
}
