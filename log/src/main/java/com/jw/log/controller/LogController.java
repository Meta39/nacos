package com.jw.log.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.jw.common.entity.log.Log;
import com.jw.log.aop.IgnoreResAnnotate;
import com.jw.log.service.LogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 日志表
 */
@RestController
@RequestMapping("log")
public class LogController {

    @Resource
    private LogService logService;

    /**
     * 根据ID查询
     *
     * @param id
     */
    @GetMapping("select")
    public Log select(@RequestParam Long id) {
        return logService.select(id);
    }

    /**
     * 查询全部
     */
    @GetMapping("selectAll")
    public List<Log> selectAll() {
        return logService.selectAll();
    }

    /**
     * 查询全部（分页）
     *
     * @param pageNum  起始页
     * @param pageSize 每页数据量
     */
    @GetMapping("selectPage")
    public PageSerializable<Log> selectPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageSerializable.of(logService.selectAll());
    }

    /**
     * 新增
     *
     * @param log
     */
    @PostMapping("insert")
    public Integer insert(@RequestBody @Valid Log log) {
        return logService.insert(log);
    }


    //openFeign

    /**
     * openFeign调用，不返回Res
     * @param id
     * @return
     */
    @IgnoreResAnnotate
    @GetMapping("selectFeign")
    public Log selectFeign(@RequestParam Long id) {
        return logService.select(id);
    }

}

