package com.wneely.dao;

import com.wneely.entity.TbMessage;
import org.springframework.stereotype.Component;

@Component
public interface TbMessageDao {

    void insert(TbMessage message);
}
