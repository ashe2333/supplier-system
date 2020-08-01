package com.excellent.course_accreditation;

import com.practice.supplier.Application;
import com.practice.supplier.model.entity.Qualifications;
import com.practice.supplier.service.IQualificationsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {Application.class})
public class test1 {
    @Autowired
    private IQualificationsService iQualificationsService;

    @Test
    void test1(){
        Qualifications qualifications = new Qualifications();
        qualifications.setUserId(1);
        qualifications.setStatus(1);
        qualifications.setRegisteredObject("aa");
        if(!iQualificationsService.updateQualifications(qualifications).isSuccess()){
            System.out.println("+++++++++++++++++++++");
        }
    }
}
