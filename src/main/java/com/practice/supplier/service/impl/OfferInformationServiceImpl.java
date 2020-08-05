package com.practice.supplier.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.dao.PurchaseInformationMapper;
import com.practice.supplier.dao.QualificationsMapper;
import com.practice.supplier.manage.UserManage;
import com.practice.supplier.model.entity.MarginChange;
import com.practice.supplier.model.entity.OfferInformation;
import com.practice.supplier.dao.OfferInformationMapper;
import com.practice.supplier.model.entity.PurchaseInformation;
import com.practice.supplier.model.entity.Qualifications;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.IMarginChangeService;
import com.practice.supplier.service.IOfferInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    private QualificationsMapper qualificationsMapper;

    @Autowired
    private PurchaseInformationMapper purchaseInformationMapper;

    @Autowired
    private IMarginChangeService iMarginChangeService;


    @Override
    public ServerResponse judgment(String companyName) {
        QueryWrapper<Qualifications> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",UserManage.getUserId())
                .eq("registered_object",companyName)
                .eq("status",2);
        if(qualificationsMapper.selectList(queryWrapper).isEmpty()){
            return ServerResponse.createByErrorMessage("不具备该公司的供应资格");
        }
        return ServerResponse.createBySuccess();
    }

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
            if(offerInformation.getStatus() == 2){
                QueryWrapper<OfferInformation> offerInformationQueryWrapper = new QueryWrapper<>();
                offerInformationQueryWrapper.eq("purchase_order",offerInformation.getPurchaseOrder())
                        .eq("status",0);
                List<OfferInformation> offerInformationList = baseMapper.selectList(offerInformationQueryWrapper);
                List<OfferInformation> updateList = new ArrayList<>();
                for(OfferInformation offerInformation1 : offerInformationList){
                    offerInformation1.setUpdateTime(LocalDateTime.now());
                    offerInformation1.setStatus(1);
                    updateList.add(offerInformation1);
                }
                if(!updateList.isEmpty()){
                    this.saveOrUpdateBatch(updateList);
                }

                retreatMargin(offerInformation);
            }
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
    public ServerResponse getOfferInformationByStatusByUser(Pagination pagination) {
        QueryWrapper<OfferInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",pagination.getStatus())
            .eq("user_id",UserManage.getUserId());
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<OfferInformation> offerInformationList = baseMapper.selectList(queryWrapper);
        PageInfo<OfferInformation> pageInfo= new PageInfo<>(offerInformationList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getOfferInformationByPurchaseOrder(Pagination pagination) {
        QueryWrapper<OfferInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("purchase_order",pagination.getOthers())
                .eq("user_id",UserManage.getUserId());
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<OfferInformation> offerInformationList = baseMapper.selectList(queryWrapper);
        PageInfo<OfferInformation> pageInfo= new PageInfo<>(offerInformationList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getOfferInformationByPurchaseOrder1(Pagination pagination) {
        QueryWrapper<OfferInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("purchase_order",pagination.getOthers());
        PageHelper.startPage(pagination.getPageNum(),pagination.getPageSize());
        List<OfferInformation> offerInformationList = baseMapper.selectList(queryWrapper);
        PageInfo<OfferInformation> pageInfo= new PageInfo<>(offerInformationList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    //退还保证金
    private void retreatMargin(OfferInformation offerInformation){
        QueryWrapper<PurchaseInformation> purchaseInformationQueryWrapper = new QueryWrapper<>();
        purchaseInformationQueryWrapper.eq("purchase_order",offerInformation.getPurchaseOrder());
        PurchaseInformation purchaseInformation = purchaseInformationMapper.selectOne(purchaseInformationQueryWrapper);
        if(purchaseInformation==null) return;
        QueryWrapper<OfferInformation> offerInformationQueryWrapper = new QueryWrapper<>();
        offerInformationQueryWrapper.eq("purchase_order",offerInformation.getPurchaseOrder());
        List<OfferInformation> offerInformationList = baseMapper.selectList(offerInformationQueryWrapper);
        for(OfferInformation offerInformation1 : offerInformationList){
            MarginChange marginChange =new MarginChange();
            marginChange.setUserId(offerInformation1.getUserId());
            marginChange.setUpdateAmount(purchaseInformation.getMargin());
            iMarginChangeService.addIMarginChangeByMargin(marginChange);
        }

    }

}
