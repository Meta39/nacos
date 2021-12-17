package com.jw.ums.controller;

import com.jw.common.result.Res;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.jw.ums.entity.UserRole;
import com.jw.ums.service.UserRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("userRole")
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;

    /**
     * 根据ID查询
     *
     * @param id
     */
    @PostMapping("select")
    public Res select(@RequestParam Long id) {
        return new Res(userRoleService.select(id));
    }

    /**
     * 查询全部
     */
    @PostMapping("selectAll")
    public Res selectAll() {
        return new Res(userRoleService.selectAll());
    }

    /**
     * 查询全部（分页）
     *
     * @param pageNum  起始页
     * @param pageSize 每页数据量
     */
    @PostMapping("selectPage")
    public Res selectPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new Res(PageSerializable.of(userRoleService.selectAll()));
    }

    /**
     * 新增
     *
     * @param userRole
     */
    @PostMapping("insert")
    public Res insert(@RequestBody @Valid UserRole userRole) {
        return new Res(userRoleService.insert(userRole));
    }

    /**
     * 新增/修改
     *
     * @param userRole
     */
    @PostMapping("update")
    public Res update(@RequestBody @Valid UserRole userRole) {
        return new Res(userRoleService.update(userRole));
    }

    /**
     * 删除
     *
     * @param id
     */
    @PostMapping("delete")
    public Res delete(@RequestParam Long id) {
        return new Res(userRoleService.delete(id));
    }

}

