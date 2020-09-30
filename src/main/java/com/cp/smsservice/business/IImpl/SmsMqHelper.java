package com.cp.smsservice.business.IImpl;

import org.springframework.stereotype.Component;

@Component
public interface SmsMqHelper {
    boolean sendMsg(String json, String msgId);
}
