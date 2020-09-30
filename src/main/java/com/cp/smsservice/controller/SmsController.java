package com.cp.smsservice.controller;

import com.cp.smsservice.business.IImpl.SmsMqHelper;
import com.cp.smsservice.business.SmsBll;
import com.cp.smsservice.model.CommonRes;
import com.cp.smsservice.model.SmsInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Api(value = "sms-service controller")
@RestController
@RequestMapping("sms")
public class SmsController {
    @Autowired
    private SmsBll bll;

    @ApiOperation(value = "获取短信详情", notes = "根据主键ID获取短信详情", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "短信ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/{id}")
    public SmsInfo getSmsInfoById(@PathVariable(value = "id") Long id) {
        return new SmsInfo();
    }

    @ApiOperation(value = "获取短信列表", notes = "获取短信列表", httpMethod = "POST")
    @PostMapping("/list")
    public List<SmsInfo> getSmsInfoList() {
        List<SmsInfo> list = new LinkedList<>();
        list.add(new SmsInfo());
        return list;
    }

    @ApiOperation(value = "添加短信", notes = "添加短信到数据库，但不是立即发送，根据指定时间发送,可能有延迟", httpMethod = "POST")
    @PostMapping("/addSms")
    public CommonRes<Boolean> addSms(@RequestBody @Validated SmsInfo smsInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new CommonRes<>(false, bindingResult.getAllErrors().get(0).getDefaultMessage(), false);
        }

        CommonRes<Boolean> res = new CommonRes(true, "", false);

        res.setResult(bll.addSms(smsInfo));

        return res;
    }

    @ApiOperation(value = "发送短信", notes = "立即发送短信，异步", httpMethod = "POST")
    @ApiImplicitParam(name = "smsInfo", value = "短信实体", required = true, dataType = "SmsInfo")
    @PostMapping("/sendSms")
    public CommonRes<Boolean> sendSms(@Validated @RequestBody SmsInfo smsInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new CommonRes<>(false, bindingResult.getAllErrors().get(0).getDefaultMessage(), false);
        }

        CommonRes<Boolean> res = new CommonRes(true, "", false);

        res.setResult(bll.sendSms(smsInfo));

        return res;
    }

    @PostMapping("/sendMsg")
    public CommonRes<Boolean> sendMsg(String json) {
        CommonRes<Boolean> res = new CommonRes(true, "", false);

        res.setResult(bll.sendMsg(json));

        return res;
    }
}
