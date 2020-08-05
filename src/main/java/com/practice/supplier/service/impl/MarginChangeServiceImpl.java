package com.practice.supplier.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.manage.UserManage;
import com.practice.supplier.model.entity.MarginChange;
import com.practice.supplier.dao.MarginChangeMapper;
import com.practice.supplier.model.entity.UserMargin;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.IMarginChangeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.practice.supplier.service.IUserMarginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evildoer
 * @since 2020-07-27
 */
@Service
public class MarginChangeServiceImpl extends ServiceImpl<MarginChangeMapper, MarginChange> implements IMarginChangeService {

    @Autowired
    private IUserMarginService iUserMarginService;

    @Override
    public ServerResponse addIMarginChangeByMargin(MarginChange marginChange) {
        marginChange.setCreateTime(LocalDateTime.now());
        marginChange.setUpdateTime(LocalDateTime.now());
        float updateAmount = marginChange.getUpdateAmount();
        UserMargin userMargin = iUserMarginService.getUserMarginById(marginChange.getUserId());
        float balance = userMargin.getNotFrozen() + updateAmount;
        if(balance>=0){
            userMargin.setUpdateTime(LocalDateTime.now());
            userMargin.setNotFrozen(balance);
            userMargin.setOfferFrozen(userMargin.getOfferFrozen()-updateAmount);
            marginChange.setNotFrozen(balance);
            marginChange.setDepositAmount(userMargin.getDepositAmount());
            if(iUserMarginService.updateUserMargin(userMargin).isSuccess()){
                if(baseMapper.insert(marginChange)==1){
                    return ServerResponse.createBySuccess();
                } else return ServerResponse.createByError();
            }else return ServerResponse.createByErrorMessage("更新失败，请稍后重试");
        }else return ServerResponse.createByErrorMessage("余额不足");
    }

    @Override
    public ServerResponse addIMarginChangeByDeposit(MarginChange marginChange) {
        marginChange.setCreateTime(LocalDateTime.now());
        marginChange.setUpdateTime(LocalDateTime.now());
        float updateAmount = marginChange.getUpdateAmount();
        UserMargin userMargin = iUserMarginService.getUserMarginById(marginChange.getUserId());
        float depositAmount = userMargin.getDepositAmount() + updateAmount;
        float balance = userMargin.getNotFrozen() + updateAmount;
        userMargin.setUpdateTime(LocalDateTime.now());
        userMargin.setDepositAmount(depositAmount);
        userMargin.setNotFrozen(balance);
        marginChange.setDepositAmount(depositAmount);
        marginChange.setNotFrozen(balance);
        if(iUserMarginService.updateUserMargin(userMargin).isSuccess()){
            if(baseMapper.insert(marginChange)==1){
                return ServerResponse.createBySuccess();
            } else return ServerResponse.createByError();
        }else return ServerResponse.createByErrorMessage("更新失败，请稍后重试");
    }


    @Override
    public ServerResponse getMarginChangeByUserId(Pagination pagination) {
        QueryWrapper<MarginChange> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",UserManage.getUserId());
        List<MarginChange> marginChangeList = baseMapper.selectList(queryWrapper);
        PageInfo<MarginChange> pageInfo= new PageInfo<>(marginChangeList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
