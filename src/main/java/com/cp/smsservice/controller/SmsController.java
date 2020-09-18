package com.cp.smsservice.controller;

import com.cp.smsservice.model.SmsInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Api(value = "sms-service controller")
@RestController
@RequestMapping("sms")
public class SmsController {
    @ApiOperation(value = "获取短信详情", notes = "根据主键ID获取短信详情",httpMethod = "get")
    @ApiImplicitParam(name = "id", value = "短信ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/{id}")
    public SmsInfo getSmsInfoById(@PathVariable(value = "id") Long id) {
        return new SmsInfo();
    }

    @ApiOperation(value = "获取短信列表", notes = "获取短信列表",httpMethod = "post")
    @PostMapping("/list")
    public List<SmsInfo> getSmsInfoList() {
        List<SmsInfo> list = new LinkedList<>();
        list.add(new SmsInfo());
        return list;
    }

    @ApiOperation(value = "添加短信", notes = "添加短信到数据库，但不是立即发送，根据指定时间发送",httpMethod = "post")
    @PostMapping("/addSms")
    public boolean addSms(@RequestBody SmsInfo smsInfo) {
        return true;
    }

    @ApiOperation(value = "发送短信",notes = "立即发送短信，异步",httpMethod = "post")
    @ApiImplicitParam(name = "smsInfo", value = "短信实体", required = true, dataType = "SmsInfo")
    @PostMapping("/sendSms")
    public boolean sendSms(@RequestBody SmsInfo smsInfo){
        return true;
    }
}
