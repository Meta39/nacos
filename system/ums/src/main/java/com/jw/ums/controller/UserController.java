package com.jw.ums.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.jw.ums.entity.User;
import com.jw.ums.service.UserService;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户表
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 根据ID查询
     *
     * @param id ID
     */
    @GetMapping("queryById")
    public User queryById(@RequestParam Integer id) {
        return userService.queryById(id);
    }

    /**
     * 查询全部
     */
    @GetMapping("findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    /**
     * 查询全部（分页）
     *
     * @param pageNum  起始页
     * @param pageSize 每页数据量
     */
    @GetMapping("findAllPage")
    public PageSerializable<User> findAllPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageSerializable.of(userService.findAll());
    }

    /**
     * 新增
     *
     * @param user
     */
    @PostMapping("insert")
    public Integer insert(@RequestBody @Valid User user) {
        return userService.insert(user);
    }

    /**
     * 修改
     *
     * @param user
     */
    @PostMapping("update")
    public Integer update(@RequestBody @Valid User user) {
        return userService.update(user);
    }

    /**
     * 删除
     *
     * @param id ID
     */
    @PostMapping("deleteById")
    public Integer deleteById(@RequestParam Integer id) {
        return userService.deleteById(id);
    }

}
