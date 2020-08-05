package com.practice.supplier.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.practice.supplier.dao.PurchaseInformationMapper;
import com.practice.supplier.model.entity.PurchaseInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    @Autowired
    private PurchaseInformationMapper purchaseInformationMapper;
    //定时每秒执行
    @Scheduled(cron = "*/1 * * * * ?")
    private void updateStatic() {
        LocalDateTime nowTime= LocalDateTime.now();
        QueryWrapper<PurchaseInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",0);
        List<PurchaseInformation> purchaseInformationList = purchaseInformationMapper.selectList(queryWrapper);
        for (PurchaseInformation purchaseInformation: purchaseInformationList){
            Integer i = purchaseInformation.getDeadline().compareTo(nowTime);
            if(i == -1){
                UpdateWrapper<PurchaseInformation> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id",purchaseInformation.getId());
                purchaseInformation.setStatus(1);
                purchaseInformationMapper.update(purchaseInformation,queryWrapper);
            }
        }
    }
}
