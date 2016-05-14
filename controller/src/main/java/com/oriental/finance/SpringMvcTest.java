package com.oriental.finance;

import com.oriental.finance.model.User;
import com.oriental.finance.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/5/14 0014.
 */
@Controller
public class SpringMvcTest {

    private Logger logger= LoggerFactory.getLogger(SpringMvcTest.class);

    @Autowired
    private DemoService demoService;

    @RequestMapping("/test")
    public String test() {
        logger.info("this just a test for logback");
        User user =new User();
        user.setUserName("wuwenchang");
        user.setMale("M");
        user.setAge(28);
        demoService.register(user);
        return "index";
    }

}
