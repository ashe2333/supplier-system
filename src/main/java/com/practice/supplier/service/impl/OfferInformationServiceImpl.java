package com.practice.supplier.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.manage.UserManage;
import com.practice.supplier.model.entity.OfferInformation;
import com.practice.supplier.dao.OfferInformationMapper;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.IOfferInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class OfferInformationServiceImpl extends ServiceImpl<OfferInformationMapper, OfferInformation> implements IOfferInformationService {

    @Override
    public ServerResponse addOfferInformation(OfferInformation offerInformation) {
        offerInformation.setCreateTime(LocalDateTime.now());
        offerInformation.setUpdateTime(LocalDateTime.now());
        offerInformation.setUserId(UserManage.getUserId());
        if(baseMapper.insert(offerInformation)==1){
            return ServerResponse.createBySuccess();
        } else return ServerResponse.createByError();
    }

    @Override
    public ServerResponse updateOfferInformation(OfferInformation offerInformation) {
        QueryWrapper<OfferInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",offerInformation.getId());
        offerInformation.setUpdateTime(LocalDateTime.now());
        if(baseMapper.update(offerInformation,queryWrapper)==1){
            return ServerResponse.createBySuccess();
        }else return ServerResponse.createByError();
    }

    @Override
    public ServerResponse getALLOfferInformation(Pagination pagination) {
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<OfferInformation> offerInformationList = baseMapper.selectList(null);
        PageInfo<OfferInformation> pageInfo= new PageInfo<>(offerInformationList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getOfferInformationByUserId(Pagination pagination) {
        QueryWrapper<OfferInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",UserManage.getUserId());
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<OfferInformation> offerInformationList = baseMapper.selectList(queryWrapper);
        PageInfo<OfferInformation> pageInfo= new PageInfo<>(offerInformationList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getOfferInformationByStatus(Pagination pagination) {
        QueryWrapper<OfferInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",pagination.getStatus());
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<OfferInformation> offerInformationList = baseMapper.selectList(queryWrapper);
        PageInfo<OfferInformation> pageInfo= new PageInfo<>(offerInformationList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getPurchaseInformationByPurchaseOrder(Pagination pagination) {
        QueryWrapper<OfferInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("purchase_order",pagination.getOthers());
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<OfferInformation> offerInformationList = baseMapper.selectList(queryWrapper);
        PageInfo<OfferInformation> pageInfo= new PageInfo<>(offerInformationList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
