package com.github.yiyan1992.carloan.entity.sys;

import com.github.yiyan1992.carloan.entity.request.Request;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 */
@SqlResultSetMapping(name = "menus", classes = {@ConstructorResult(targetClass = Menu.class,
        columns = {
                @ColumnResult(name = "id"),
                @ColumnResult(name = "name"),
                @ColumnResult(name = "type"),
                @ColumnResult(name = "url"),
                @ColumnResult(name = "permission"),
                @ColumnResult(name = "count", type = Integer.class)
        }
)})
@NamedNativeQuery(name = "Menu.findParentList", query = "SELECT m.*, ( SELECT count(1) FROM s_menu WHERE menu_id = m.id ) `count` FROM s_menu m WHERE ISNULL(m.menu_id)", resultSetMapping = "menus")
@NamedNativeQuery(name = "Menu.findChildrenList", query = "SELECT m.*,(SELECT count( 1 ) FROM s_menu WHERE menu_id = m.id ) as `count` FROM s_menu m WHERE m.menu_id=:id", resultSetMapping = "menus")
@NamedNativeQuery(name = "Menu.findMenuByRole", query = "SELECT m.*,0 'count' FROM s_menu m WHERE m.id in (select menu_id from s_role_menu WHERE role_name=:roleName)", resultSetMapping = "menus")
@Data
@Entity
@Table(name = "s_menu")
@NoArgsConstructor
public class Menu extends Request<Menu> implements Serializable {

    public Menu(Integer id, String name, Integer type, String url, String permission, Integer count) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
        this.permission = permission;
        this.count = count;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", length = 11)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    /**
     * 0 菜单 1:按钮
     */
    @Column(name = "type", length = 1)
    private Integer type;

    @Column(name = "url", length = 50)
    private String url;

    @Column(name = "permission", length = 20)
    private String permission;

    @OneToOne
    private Menu menu;

    @Transient
    private boolean hasChildren;

    @Transient
    private List<Menu> children;

    @Transient
    private int count;

    @Override
    public Example<Menu> getPageExample() {
        return Example.of(this,
                ExampleMatcher.matching()
                        .withMatcher("menuName", matcher -> matcher.contains()));
    }

    public boolean isHasChildren() {
        return count > 0;
    }
}