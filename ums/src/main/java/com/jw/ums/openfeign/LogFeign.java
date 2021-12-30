package com.jw.ums.openfeign;

import com.jw.common.entity.log.Log;
import com.jw.common.result.Res;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * RequestParam必须写参数名称，否则Get请求调用失败。
 */
@FeignClient(name="log")//name是注册中心的name。注意区分大小写。
public interface LogFeign {
    @PostMapping("log/insert")
    Res insert(@RequestBody Log log);

    @GetMapping("log/selectFeign")
    Log selectFeign(@RequestParam("id") Long id);
}
