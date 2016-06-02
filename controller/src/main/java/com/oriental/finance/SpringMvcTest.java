package com.oriental.finance;

import com.alibaba.fastjson.JSONObject;
import com.oriental.finance.dto.Datagrid;
import com.oriental.finance.dto.Node;
import com.oriental.finance.dto.UserDto;
import com.oriental.finance.model.User;
import com.oriental.finance.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/14 0014.
 */
@Controller
public class SpringMvcTest {

    private Logger logger = LoggerFactory.getLogger(SpringMvcTest.class);

    @Autowired
    private DemoService demoService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("integral")
    public String integral() {
        return "integral";
    }


    @RequestMapping("init")
    @ResponseBody
    public Datagrid init() {
        UserDto user1=new UserDto();
        user1.setLoginId("xiao_dingo");
        user1.setName("吴文昌");
        user1.setTime("2106-05-29");
        user1.setChannel("APP");

        UserDto user2=new UserDto();
        user2.setLoginId("wufan");
        user2.setName("吴繁");
        user2.setTime("2106-05-29");
        user2.setChannel("APP");

        List<UserDto> users=new ArrayList();
        users.add(user1);
        users.add(user2);
        Datagrid datagrid=new Datagrid(users,true);
        datagrid.setStatus("succeed");
        datagrid.setTotal(users.size());
        return datagrid;
    }


    @RequestMapping("tree/init")
    @ResponseBody
    public List<Node> treeInit(String id) {
        System.out.println(id);
        if(StringUtils.isEmpty(id)){
            String node_id="-1";
        }
        Node node1=new Node("1","-1","parent1","url","false",false);
        node1.setFolder("1");
        List<Node> list=new ArrayList();
        list.add(node1);
        return list;

    }
}
