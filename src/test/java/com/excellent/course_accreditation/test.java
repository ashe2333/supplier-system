package com.excellent.course_accreditation;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.practice.supplier.Application;
import com.practice.supplier.dao.PurchaseInformationMapper;
import com.practice.supplier.dao.UserMapper;
import com.practice.supplier.model.entity.User;
import com.practice.supplier.service.impl.InformationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

@SpringBootTest(classes = {Application.class})
public class test extends ServiceImpl<UserMapper, User> {


    @Test
    void test1(){
        List<User> userList  = baseMapper.selectList(null);
        System.out.println(userList.toString());
    }

    @Test
    void test2(){
        User user = new User();
        user.setIdentity(1);
        user.setUsername("user");
        user.setPassword("13456");
        baseMapper.insert(user);
    }

    @Test
    void test3(){
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("username", "user");
        User user = new User();
        //user.setIdentity(1);
        user.setUsername("user");
        user.setPassword("123456");
        System.out.println(baseMapper.update(user,userUpdateWrapper));
    }

    @Test
    void test4(){
        if(1==1){
            System.out.println("++++++++++++++++++++");
        }else System.out.println("========================");
    }
    @Test
    void test5(){
        String filePar = "d:\\test"+"\\"+"aa";// 文件夹路径
        File myPath = new File( filePar );
        if ( !myPath.exists()){//若此目录不存在，则创建之
            myPath.mkdir();
            System.out.println("创建文件夹路径为："+ filePar);
        }
    }


}
