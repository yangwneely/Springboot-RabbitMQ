package com.wneely.dao;

import com.wneely.entity.TbContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TbContextDao {

    List<TbContext> list();

    TbContext selectByPrimaryKey(String id);

    int update(TbContext context);

    void insert(TbContext context);
}
