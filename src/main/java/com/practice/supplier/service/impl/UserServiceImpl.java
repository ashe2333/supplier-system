package com.practice.supplier.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.practice.supplier.common.authentication.JWTUtil;
import com.practice.supplier.common.domain.Const;
import com.practice.supplier.common.domain.ServerResponse;
import com.practice.supplier.common.exception.AuthenticationException;
import com.practice.supplier.model.entity.User;
import com.practice.supplier.dao.UserMapper;
import com.practice.supplier.model.entity.UserMargin;
import com.practice.supplier.model.form.Pagination;
import com.practice.supplier.service.IUserMarginService;
import com.practice.supplier.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.practice.supplier.untils.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evildoer
 * @since 2020-07-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public ServerResponse login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username)
                .eq("password", EncryptionService.encryption(password));
        User s = this.getOne(queryWrapper);
        if(s==null) throw new AuthenticationException("账号或密码错误");
        String token = JWTUtil.encryptToken(JWTUtil.sign(s.getId(),s.getIdentity()));
        Map<String,String> data = new HashMap<>();
        data.put("token",token);
        data.put("role",s.getIdentity()==1?Const.USER:Const.ADMIN);
        return ServerResponse.createBySuccess(data);
    }

    @Override
    public ServerResponse registered(User user) {
        user.setPassword(EncryptionService.encryption(user.getPassword()));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        if(baseMapper.selectList(queryWrapper).isEmpty()){
            LocalDateTime nowTime= LocalDateTime.now();
            user.setCreateTime(nowTime);
            user.setUpdateTime(nowTime);
            user.setIdentity(1);
            if (baseMapper.insert(user)==1){
                return ServerResponse.createBySuccessMessage("注册成功");
            }else return ServerResponse.createByErrorMessage("注册失败，请稍后重试");
        }
        else return ServerResponse.createByErrorMessage("用户已存在");
    }

    @Override
    public ServerResponse changePassword(User user) {
        user.setPassword(EncryptionService.encryption(user.getPassword()));
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username",user.getUsername())
                .ne("password",user.getPassword());
        user.setUpdateTime(LocalDateTime.now());
        if(baseMapper.update(user,updateWrapper)==1){
            return ServerResponse.createBySuccessMessage("密码修改成功");
        }else if(baseMapper.selectList(updateWrapper).isEmpty()){
            return ServerResponse.createByErrorMessage("新密码与旧密码相同，密码修改失败");
        }
        else return ServerResponse.createByErrorMessage("密码修改失败,请稍后重试");
    }

    @Override
    public ServerResponse changePasswordBySecret(User user) {
        user.setPassword(EncryptionService.encryption(user.getPassword()));
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username",user.getUsername())
                .eq("secret_question",user.getSecretQuestion())
                .eq("secret_answer",user.getSecretQuestion());
        user.setUpdateTime(LocalDateTime.now());
        if(baseMapper.update(user,updateWrapper)==1){
            return ServerResponse.createBySuccessMessage("密码修改成功");
        }else if(baseMapper.selectList(updateWrapper).isEmpty()){
            return ServerResponse.createByErrorMessage("密保错误，密码修改失败");
        }
        else return ServerResponse.createByErrorMessage("密码修改失败,请稍后重试");
    }

    @Override
    public ServerResponse getAllUser(Pagination pagination) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("identity", 1);
        List<User> userList = baseMapper.selectList(queryWrapper);
        return ServerResponse.createBySuccess(userList);
    }

    @Override
    public ServerResponse getUserByUserName(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userName);
        User s = this.getOne(queryWrapper);
        if(s==null){
            return  ServerResponse.createByErrorMessage("账号错误");
        }
        return ServerResponse.createBySuccess(s);
    }


    @Override
    public String getRoleByToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Const.TOKEN);
        if (StringUtils.isEmpty(token))
            throw new AuthenticationException("请登录后访问！");
        token = JWTUtil.decryptToken(token);            // 解密token
        Integer id = JWTUtil.getId(token);
        Integer role = JWTUtil.getRole(token);
        // 校验token是否合法
        if (role==null||!JWTUtil.verify(token,id,role))
            throw new AuthenticationException("token校验不通过");
        return role==1?Const.USER:Const.ADMIN;
    }
}
