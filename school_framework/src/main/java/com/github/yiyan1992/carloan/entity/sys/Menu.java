package com.github.yiyan1992.carloan.entity.sys;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author admin
 */
@Data
@Entity
@Table(name = "s_menu")
public class Menu implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id",length = 11)
    private int id;

    @Column(name = "menu_name", length = 50)
    private String menuName;

    /**
     * 0 菜单 1:按钮
     */
    @Column(name = "menu_type", length = 1)
    private int menuType = 0;

    @Column(name = "parent_id",length = 11)
    private int parentId;

    @Column(name = "url", length = 50)
    private String url;

    @Column(name = "permission", length = 20)
    private String permission;

}