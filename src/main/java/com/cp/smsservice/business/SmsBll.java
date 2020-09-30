package com.cp.smsservice.business;

import com.cp.smsservice.business.IImpl.SmsMqHelper;
import com.cp.smsservice.dao.SmsInfoMapper;
import com.cp.smsservice.model.SmsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class SmsBll {
    @Autowired
    private SmsInfoMapper smsInfoMapper;

    @Autowired
    private SmsMqHelper smsMqHelper;

    /**
     * 插入短信记录，同时异步发送短信后再更新短信记录
     *
     * @param smsInfo
     * @return
     */
    public Boolean sendSms(SmsInfo smsInfo) {
        Boolean result = smsInfoMapper.insert(smsInfo) > 0;

        //// 异步发起调用短信接口，推消息（利用死信队列进行消息补偿）


        return result;
    }

    /**
     * 只插入短信记录，稍后再由定时任务进行短信的发送
     *
     * @param smsInfo
     * @return
     */
    public Boolean addSms(SmsInfo smsInfo) {
        if (smsInfo.getExcuteTime() == null) {
            smsInfo.setExcuteTime(new Date());
        }

        return smsInfoMapper.insert(smsInfo) > 0;
    }

    public Boolean sendMsg(String json) {
        String msgId = "";
        boolean flag = false;
        //// 插入消息表

        flag = smsMqHelper.sendMsg(json, msgId);

        return flag;
    }
}
