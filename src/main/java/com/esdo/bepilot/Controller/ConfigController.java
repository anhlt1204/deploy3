package com.esdo.bepilot.Controller;

import com.esdo.bepilot.Model.Request.ConfigRequest;
import com.esdo.bepilot.Model.Response.ConfigResponse;
import com.esdo.bepilot.Service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping
    @Transactional(rollbackFor = Exception.class)
    ConfigResponse getConfig() {
        return configService.getConfig();
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    ConfigResponse editConfig(@RequestBody ConfigRequest request) {
        return configService.editConfig(request);
    }
}
