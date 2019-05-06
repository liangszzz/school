package com.github.yiyan1992.carloan.service.sys;

import com.github.yiyan1992.carloan.dao.sys.MenuDao;
import com.github.yiyan1992.carloan.dao.sys.RoleDao;
import com.github.yiyan1992.carloan.entity.query.RoleMenu;
import com.github.yiyan1992.carloan.entity.response.Response;
import com.github.yiyan1992.carloan.entity.sys.Menu;
import com.github.yiyan1992.carloan.entity.sys.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RoleDao roleDao;

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

    public List<Menu> findChildrenList(Integer id) {
        return menuDao.findChildrenList(id);
    }

    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    public Set<Menu> findMenuByRole(String roleName) {
        return menuDao.findMenuByRole(roleName);
    }

    @Transactional
    public Response saveRoleMenu(RoleMenu roleMenu) {
        Optional<Role> role = roleDao.findById(roleMenu.getRoleName());
        if (role.isPresent()) {
            List<Menu> list = menuDao.findAllById(roleMenu.getMenus());
            Set<Menu> set = new HashSet<>();
            set.addAll(list);
            role.get().setMenus(set);
            roleDao.save(role.get());
            return Response.success("保存成功!");
        }
        return Response.error("角色不存在!");
    }
}
