package com.github.yiyan1992.carloan.service.sys;

import com.github.yiyan1992.carloan.dao.sys.MenuDao;
import com.github.yiyan1992.carloan.entity.sys.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    public Menu save(Menu menu) {
        return menuDao.save(menu);
    }

    public Page<Menu> findPageList(Example<Menu> pageExample, PageRequest pageRequest) {
        return menuDao.findAll(pageExample, pageRequest);
    }

    public Optional<Menu> findById(Integer id) {
        return menuDao.findById(id);
    }

    public void deleteById(Integer id) {
        menuDao.deleteById(id);
    }

    public List<Menu> findList() {
        return menuDao.findParentList();
    }

}
