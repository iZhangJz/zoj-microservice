package com.zjz.zojbackenduserservice.controller;

import com.zjz.common.common.ErrorCode;
import com.zjz.common.common.SpringSessionHttpSession;
import com.zjz.common.exception.BusinessException;
import com.zjz.common.exception.ThrowUtils;
import com.zjz.model.entity.User;
import com.zjz.zojbackendserviceclient.service.UserFeignClient;
import com.zjz.zojbackenduserservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

import static com.zjz.common.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户内部接口
 *
 */
@RestController
@RequestMapping("/inner")
@Slf4j
public class UserInnerController implements UserFeignClient {

    @Resource
    private UserService userService;

    @Resource
    private SessionRepository<? extends Session> sessionRepository;

    public HttpSession getSessionById(String sessionId) {
        Session session = sessionRepository.findById(sessionId);
        if (session == null) {
            return null;
        }
        return new SpringSessionHttpSession(session);
    }

    /**
     * 根据 id 获取用户
     *
     * @param id
     * @return
     */
    @Override
    public User getById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getById(id);
        ThrowUtils.throwIf(user == null, ErrorCode.NOT_FOUND_ERROR);
        return user;
    }

    /**
     * 根据 id 列表批量获取用户
     * @param ids
     * @return
     */
    @Override
    public List<User> listByIds(@RequestBody Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return userService.listByIds(ids);
    }

    @Override
    public User getLoginUser(String sessionId) {
        // 使用 sessionId 查找用户信息

        HttpSession session = getSessionById(sessionId);
        if (session == null) {
            return null;
        }
        Object userObj = session.getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            return null;
        }
        currentUser = this.getById(currentUser.getId());
        return currentUser;
    }



}
