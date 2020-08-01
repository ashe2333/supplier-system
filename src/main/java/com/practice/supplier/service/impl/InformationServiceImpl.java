package com.practice.supplier.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.manage.UserManage;
import com.practice.supplier.model.entity.Information;
import com.practice.supplier.dao.InformationMapper;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.IInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
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
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements IInformationService {


    @Override
    public ServerResponse registered(Information information) {
        //information.setUserId(1);
        information.setUserId(UserManage.getUserId());
        information.setCreateTime(LocalDateTime.now());
        information.setUpdateTime(LocalDateTime.now());
        if(baseMapper.insert(information)==1){
            return ServerResponse.createBySuccessMessage("添加注册信息成功");
        } else return ServerResponse.createByErrorMessage("添加注册信息失败，请稍后重试");
    }

    @Override
    public ServerResponse updateInformation(Information information) {
        UpdateWrapper<Information> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",information.getUserId());
        information.setUpdateTime(LocalDateTime.now());
        if(baseMapper.update(information,updateWrapper)==1){
            return ServerResponse.createBySuccessMessage("修改注册信息成功");
        } else return ServerResponse.createByErrorMessage("修改注册信息失败，请稍后重试");
    }

    @Override
    public ServerResponse getAllInformation(Pagination pagination) {
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<Information> informationList = baseMapper.selectList(null);
        PageInfo<Information> pageInfo= new PageInfo<>(informationList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getInformationById() {
        QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",UserManage.getUserId());
        Information information = this.getOne(queryWrapper);
        return ServerResponse.createBySuccess(information);
    }

    @Override
    public ServerResponse getInformationByStatus(Pagination pagination) {
        QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",pagination.getStatus());
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<Information> informationList = baseMapper.selectList(queryWrapper);
        PageInfo<Information> pageInfo= new PageInfo<>(informationList);
        return ServerResponse.createBySuccess(pageInfo);
    }


}
