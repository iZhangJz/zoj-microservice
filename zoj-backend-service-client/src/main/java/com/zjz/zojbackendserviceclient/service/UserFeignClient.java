package com.zjz.zojbackendserviceclient.service;

import com.zjz.model.entity.User;
import com.zjz.model.enums.UserRoleEnum;
import com.zjz.model.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

import static com.zjz.common.constant.UserConstant.USER_LOGIN_STATE;


/**
 * 用户服务
 *
 */
@FeignClient(name = "zoj-user-service",path = "/api/user/inner")
public interface UserFeignClient {


    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    @GetMapping("/get")
    User getById(@RequestParam("id") long userId);

    /**
     * 根据用户id集合获取用户信息
     * @param ids
     * @return
     */
    @GetMapping("/list")
    List<User> listByIds(@RequestParam("ids") Collection<Long> ids);

    /**
     * 获取当前登录用户
     *
     * @param sessionId
     * @return
     */
    @GetMapping("/get/login")
    User getLoginUser(@RequestParam("sessionId") String sessionId);


    /**
     * 是否为管理员
     *
     * @param user
     * @return
     */
    default boolean isAdmin(User user){
        return user != null && UserRoleEnum.ADMIN.getValue().equals(user.getUserRole());
    }

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    default boolean isAdmin(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return isAdmin(user);
    }

    /**
     * 获取脱敏的用户信息
     *
     * @param user
     * @return
     */
    default UserVO getUserVO(User user){
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

}
