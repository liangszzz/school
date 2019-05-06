package com.github.yiyan1992.carloan.dao.sys;

import com.github.yiyan1992.carloan.entity.sys.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuDao extends JpaRepository<Menu, Integer> {

    List<Menu> findParentList();

    List<Menu> findChildrenList(int id);
}
