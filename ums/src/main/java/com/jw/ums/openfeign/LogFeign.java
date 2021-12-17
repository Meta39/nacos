package com.jw.ums.openfeign;

import com.jw.common.entity.log.Log;
import com.jw.common.result.Res;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="LOG")
public interface LogFeign {
    @PostMapping("log/update")
    Res update(@RequestBody Log log);
}
