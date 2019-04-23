package com.github.yiyan1992.carloan.service.sys;

import com.github.yiyan1992.carloan.dao.sys.MenuDao;
import com.github.yiyan1992.carloan.entity.sys.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    public void save(Menu menu) {
        menuDao.save(menu);
    }
}
