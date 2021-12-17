package com.jw.ums.controller;

import com.jw.common.result.Res;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.jw.ums.entity.RoleNode;
import com.jw.ums.service.RoleNodeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("roleNode")
public class RoleNodeController {

    @Resource
    private RoleNodeService roleNodeService;

    /**
     * 根据ID查询
     *
     * @param id
     */
    @PostMapping("select")
    public Res select(@RequestParam Long id) {
        return new Res(roleNodeService.select(id));
    }

    /**
     * 查询全部
     */
    @PostMapping("selectAll")
    public Res selectAll() {
        return new Res(roleNodeService.selectAll());
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
        return new Res(PageSerializable.of(roleNodeService.selectAll()));
    }

    /**
     * 新增
     *
     * @param roleNode
     */
    @PostMapping("insert")
    public Res insert(@RequestBody @Valid RoleNode roleNode) {
        return new Res(roleNodeService.insert(roleNode));
    }

    /**
     * 修改
     *
     * @param roleNode
     */
    @PostMapping("update")
    public Res update(@RequestBody @Valid RoleNode roleNode) {
        return new Res(roleNodeService.update(roleNode));
    }

    /**
     * 删除
     *
     * @param id
     */
    @PostMapping("delete")
    public Res delete(@RequestParam Long id) {
        return new Res(roleNodeService.delete(id));
    }

}

