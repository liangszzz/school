package com.github.yiyan1992.carloan.dao.sys;

import com.github.yiyan1992.carloan.entity.sys.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuDao extends JpaRepository<Menu, Integer> {

    @Query(value = "SELECT m.*,(SELECT count( 1 ) FROM s_menu WHERE menu_id = m.id ) `count` FROM s_menu m WHERE ISNULL( m.menu_id )", nativeQuery = true)
    List<Menu> findParentList();

    @Query(value = "SELECT m.*,(SELECT count( 1 ) FROM s_menu WHERE menu_id = m.id ) count FROM s_menu m WHERE m.menu_id:id", nativeQuery = true)
    List<Menu> findChildrenList(int id);
}
