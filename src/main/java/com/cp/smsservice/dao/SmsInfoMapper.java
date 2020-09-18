package com.cp.smsservice.dao;

import com.cp.smsservice.model.SmsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SmsInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsInfo record);

    int insertSelective(SmsInfo record);

    SmsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsInfo record);

    int updateByPrimaryKey(SmsInfo record);
}