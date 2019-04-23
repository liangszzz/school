package com.github.yiyan1992.carloan.config.datasource;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author admin
 */
@Data
@Component
@ConfigurationProperties(prefix = "mysql.datasource")
public class MySqlEntity {

    private String drive;

    private String url;

    private String username;

    private String password;

}
