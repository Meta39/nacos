package com.jw.ums.controller;

import com.jw.common.entity.ums.Node;
import com.jw.common.result.Res;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.jw.ums.service.NodeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 权限表
 */
@RestController
@RequestMapping("node")
public class NodeController {

    @Resource
    private NodeService nodeService;

    /**
     * 根据ID查询
     *
     * @param id
     */
    @PostMapping("select")
    public Res select(@RequestParam Integer id) {
        return new Res(nodeService.select(id));
    }

    /**
     * 查询全部
     */
    @PostMapping("selectAll")
    public Res selectAll() {
        return new Res(nodeService.selectAll());
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
        return new Res(PageSerializable.of(nodeService.selectAll()));
    }

    /**
     * 新增
     *
     * @param node
     */
    @PostMapping("insert")
    public Res insert(@RequestBody @Valid Node node) {
        return new Res(nodeService.insert(node));
    }

    /**
     * 修改
     *
     * @param node
     */
    @PostMapping("update")
    public Res update(@RequestBody @Valid Node node) {
        return new Res(node.getId() == null ? nodeService.insert(node) : nodeService.update(node));
    }

    /**
     * 删除
     *
     * @param id
     */
    @PostMapping("delete")
    public Res delete(@RequestParam Integer id) {
        return new Res(nodeService.delete(id));
    }

}

