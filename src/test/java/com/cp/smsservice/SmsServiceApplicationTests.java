package com.cp.smsservice;

import com.cp.smsservice.dao.SmsInfoMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class SmsServiceApplicationTests {

    @Autowired
    private SmsInfoMapper smsInfoMapper;

    @Test
    void contextLoads() {

    }

}
