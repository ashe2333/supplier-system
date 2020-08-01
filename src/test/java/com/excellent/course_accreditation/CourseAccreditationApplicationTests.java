package com.excellent.course_accreditation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.practice.supplier.Application;
import com.practice.supplier.dao.PurchaseInformationMapper;
import com.practice.supplier.dao.UserMapper;
import com.practice.supplier.model.entity.PurchaseInformation;
import com.practice.supplier.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = {Application.class})
class CourseAccreditationApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PurchaseInformationMapper purchaseInformationMapper;


    @Test
    void test1(){
        List<User> userList  = userMapper.selectList(null);
        System.out.println(userList.toString());
    }

    @Test
    void test2(){
        User user = new User();
        user.setIdentity(1);
        user.setUsername("user");
        user.setPassword("13456");
        userMapper.insert(user);
    }

    @Test
    void test3(){

    }
}
