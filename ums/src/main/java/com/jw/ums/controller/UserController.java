package com.jw.ums.controller;

import com.jw.common.result.Res;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.jw.ums.aop.LogAnnotate;
import com.jw.ums.entity.User;
import com.jw.ums.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("hello")
    public Res hello(){
        return new Res("hello");
    }

    /**
     * 根据ID查询
     *
     * @param id
     */
    @GetMapping("select")
//    @PowerAnnotate("user/select")//切面实现授权
//    @LogAnnotate()//切面实现记录日志
    public Res select(@RequestParam Integer id) {
        return new Res(userService.select(id));
    }

    /**
     * 查询全部
     */
    @GetMapping("selectAll")
    public Res selectAll() {
        return new Res(userService.selectAll());
    }

    /**
     * 查询全部（分页）
     *
     * @param pageNum  起始页
     * @param pageSize 每页数据量
     */
    @GetMapping("selectPage")
    public Res selectPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new Res(PageSerializable.of(userService.selectAll()));
    }

    /**
     * 新增
     *
     * @param user
     */
    @PostMapping("insert")
    public Res insert(@RequestBody @Valid User user) {
        return new Res(userService.insert(user));
    }

    /**
     * 修改
     *
     * @param user
     */
    @PostMapping("update")
    public Res update(@RequestBody @Valid User user) {
        return new Res(userService.update(user));
    }

    /**
     * 删除
     *
     * @param id
     */
    @PostMapping("delete")
    public Res delete(@RequestParam Integer id) {
        return new Res(userService.delete(id));
    }


}

