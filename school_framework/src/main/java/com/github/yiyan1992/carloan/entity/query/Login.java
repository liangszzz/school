package com.github.yiyan1992.carloan.entity.query;

import com.github.yiyan1992.carloan.config.MagicValue;
import lombok.Data;

/**
 * @author admin
 */
@Data
public class Login {

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
                return MagicValue.LOGIN_TYPE_0 + username;
            case "1":
                return MagicValue.LOGIN_TYPE_1 + username;
            case "2":
                return MagicValue.LOGIN_TYPE_2 + username;
        }
        return username;
    }
}
