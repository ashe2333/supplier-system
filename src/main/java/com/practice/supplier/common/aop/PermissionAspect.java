package com.practice.supplier.common.aop;

import com.practice.supplier.common.annotation.Permission;
import com.practice.supplier.common.exception.AuthenticationException;
//import com.practice.supplier.manage.UserManage;
import com.practice.supplier.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class PermissionAspect {
    @Autowired
    IUserService userService;

    /**
     * @Description: 角色权限验证
     * @Param: [join, permission]
     * @return: boolean
     * @Author: ashe
     * @Date: 2019/11/15
     */
    //com.practice.supplier.common.aop.PermissionAspect
    @Pointcut("execution(public * com.practice.supplier.controller.*.*(..)) within(com.excellent.accreditation.controller.*)")
    private void point() {
    }

    //切入点签名
    @Before(value = "point()&&@annotation(permission)")
    public void rolePermission(JoinPoint joinPoint, Permission permission) {
        printLog();
        String userRole = getRoles();             // 用户角色集合
        String[] roles = permission.roles();             // 满足条件的角色数组
        checkPermission(userRole, roles);
    }

    /**
     * @Author 安羽兮
     * @Description 判断用户是否有权限访问
     * @Date 15:06 2019/12/7
     * @Param [roles, userRoles]
     * @Return boolean
     **/
    private boolean checkPermission(String userRole, String[] roles) {
        for (String r : roles) {
            // 用户只要有一个角色满足条件即可
            if (r.equals(userRole)) {
                return true;
            }
        }
        throw new AuthenticationException("权限不足");
    }

    private String getRoles() {
        return userService.getRoleByToken();
    }

    public void printLog() {
        // Get request attribute
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(requestAttributes).getRequest();

        log.info("Request URL: [{}], URI: [{}], Request Method: [{}], IP: [{}]",
                request.getRequestURL(),
                request.getRequestURI(),
                request.getMethod(),
                getClientIp(request));
    }

    public static String getClientIp(HttpServletRequest request) {
        String ip;
        String[] headers = {"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};

        for (String header : headers) {
            ip = request.getHeader(header);
            if (checkIP(ip)) {
                return ip;
            }
        }
        ip = request.getRemoteAddr();
        return ip;
    }

    private static boolean checkIP(String ip) {
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)
                || ip.split(".").length != 4) {
            return false;
        }
        return true;
    }
}
