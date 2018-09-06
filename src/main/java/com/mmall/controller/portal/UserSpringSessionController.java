package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhang on 2018/2/25.
 */
@Controller
@RequestMapping("/user/springsession/")
public class UserSpringSessionController {

    @Autowired
    private IUserService iUserService;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session, HttpServletResponse httpServletResponse) {
        //测试全局异常
//        int i = 0;
//        int b = 2/i;

        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getDate());

            //二期 FD2E019FC885D27C1867604078785EBF
//                CookieUtil.writeLoginToken(httpServletResponse, session.getId());
//                RedisShardedPoolUtil.setEx(session.getId(), JsonUtil.obj2String(response.getDate()), Const.RedisCacheExTime.REDIS_SESSION_EXTIME);
        }

        return response;
    }

    /**
     * 用户登出
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.removeAttribute(Const.CURRENT_USER);

        //二期
//        String loginToken = CookieUtil.readLoginToken(request);
//        CookieUtil.delLoginToken(response, request);
//        RedisShardedPoolUtil.del(loginToken);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //二期
//        String loginToken = CookieUtil.readLoginToken(request);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
//        }
//        User user = JsonUtil.string2Obj(RedisShardedPoolUtil.get(loginToken), User.class);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
    }
}


