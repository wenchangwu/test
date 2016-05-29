package com.oriental.finance.service;

import com.oriental.finance.DemoServiceFacade;
import com.oriental.finance.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/5/14 0014.
 */
@Service
public class DemoService implements DemoServiceFacade{

    public String register(User user) {
        System.out.println("this is just a demo");
        return "Hello World";
    }
}
