package com.lly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 无问东西 on 2018/11/28 20:53
 */
@Controller
public class SettingController {
    @RequestMapping("/setting")
    @ResponseBody
    public String setting() {
        return "Setting:OK";
    }
}
