package com.practice.supplier.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.dao.SaveTakeMapper;
import com.practice.supplier.manage.UserManage;
import com.practice.supplier.model.entity.MarginChange;
import com.practice.supplier.model.entity.SaveTake;
import com.practice.supplier.model.entity.UserMargin;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.IMarginChangeService;
import com.practice.supplier.service.ISaveTakeService;
import com.practice.supplier.service.IUserMarginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaveTakeServiceImp extends ServiceImpl<SaveTakeMapper, SaveTake> implements ISaveTakeService {

    @Autowired
    private IUserMarginService iUserMarginService;

    @Autowired
    private IMarginChangeService iMarginChangeService;

    @Override
    public ServerResponse addSaveTake(SaveTake saveTake) {
        saveTake.setCreateTime(LocalDateTime.now());
        saveTake.setUpdateTime(LocalDateTime.now());
        if(baseMapper.insert(saveTake)==1){
            return ServerResponse.createBySuccess();
        } else return ServerResponse.createByError();
    }

    @Override
    public ServerResponse updateSaveTake(SaveTake saveTake) {
        UpdateWrapper<SaveTake> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",saveTake.getUserId());
        if(baseMapper.update(saveTake,updateWrapper)==1){
            if(saveTake.getStatus()==1){
                MarginChange marginChange = new MarginChange();
                marginChange.setCreateTime(LocalDateTime.now());
                marginChange.setUpdateTime(LocalDateTime.now());
                marginChange.setUserId(saveTake.getUserId());
                marginChange.setUpdateAmount(saveTake.getChangeAmount());
                if(saveTake.getType()==0){
                    marginChange.setUpdateType("用户汇款存入");
                }
                if(saveTake.getType()==1){
                    marginChange.setUpdateType("用户退还保证金取出");
                    marginChange.setUpdateAmount(saveTake.getChangeAmount()*(-1));
                }
                iMarginChangeService.addIMarginChangeByDeposit(marginChange);
            }
            return ServerResponse.createBySuccess();
        } else return ServerResponse.createByError();
    }

    @Override
    public ServerResponse getAllSaveTake(Pagination pagination) {
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<SaveTake> saveTakeList = baseMapper.selectList(null);
        PageInfo<SaveTake> pageInfo= new PageInfo<>(saveTakeList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getSaveTakeByUseId(Pagination pagination) {
        QueryWrapper<SaveTake> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",UserManage.getUserId());
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<SaveTake> saveTakeList = baseMapper.selectList(queryWrapper);
        PageInfo<SaveTake> pageInfo= new PageInfo<>(saveTakeList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getSaveTakeByStatus(Pagination pagination) {
        QueryWrapper<SaveTake> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",pagination.getStatus());
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<SaveTake> saveTakeList = baseMapper.selectList(queryWrapper);
        PageInfo<SaveTake> pageInfo= new PageInfo<>(saveTakeList);
        return ServerResponse.createBySuccess(pageInfo);
    }

}
