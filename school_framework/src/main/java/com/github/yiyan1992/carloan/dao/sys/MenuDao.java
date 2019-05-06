package com.github.yiyan1992.carloan.dao.sys;

import com.github.yiyan1992.carloan.entity.sys.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MenuDao extends JpaRepository<Menu, Integer> {

    List<Menu> findParentList();

    List<Menu> findChildrenList(int id);

    Set<Menu> findMenuByRole(String roleName);
}
