package com.jw.ums.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.jw.common.entity.ums.Role;
import com.jw.common.result.Res;
import com.jw.ums.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 根据ID查询
     *
     * @param id
     */
    @PostMapping("select")
    public Res select(@RequestParam Integer id) {
        return new Res(roleService.select(id));
    }

    /**
     * 查询全部
     */
    @PostMapping("selectAll")
    public Res selectAll() {
        return new Res(roleService.selectAll());
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
        return new Res(PageSerializable.of(roleService.selectAll()));
    }

    /**
     * 新增
     *
     * @param role
     */
    @PostMapping("insert")
    public Res insert(@RequestBody @Valid Role role) {
        return new Res(roleService.insert(role));
    }


    /**
     * 修改
     *
     * @param role
     */
    @PostMapping("update")
    public Res update(@RequestBody @Valid Role role) {
        return new Res(roleService.update(role));
    }

    /**
     * 删除
     *
     * @param id
     */
    @PostMapping("delete")
    public Res delete(@RequestParam Integer id) {
        return new Res(roleService.delete(id));
    }

}

