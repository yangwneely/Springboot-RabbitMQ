package com.wneely.controller;

import com.wneely.dao.TbContextDao;
import com.wneely.entity.TbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/object")
@RestController
public class ObjectController {

    @Autowired
    private TbContextDao tbContextDao;

    @GetMapping("/insert")
    public void insertContext(){
        TbContext context = new TbContext();
        context.setId(UUID.randomUUID().toString().replaceAll("-",""));
        context.setContext("insert demo");
        tbContextDao.insert(context);
    }

}
