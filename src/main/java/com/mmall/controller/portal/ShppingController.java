package com.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Shipping;
import com.mmall.pojo.User;
import com.mmall.service.IShippingService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhang on 2018/3/24.
 */

@Controller
@RequestMapping("/shipping/")
public class ShppingController {

    @Autowired
    private IShippingService iShippingService;


    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session, HttpServletRequest request,  Shipping shipping){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);

        //二期
        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
        }
        User user = JsonUtil.string2Obj(RedisPoolUtil.get(loginToken), User.class);

        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.add(user.getId(), shipping);
    }


    @RequestMapping("del.do")
    @ResponseBody
    public ServerResponse del(HttpSession session, HttpServletRequest request, Integer shippingId){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);

        //二期
        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
        }
        User user = JsonUtil.string2Obj(RedisPoolUtil.get(loginToken), User.class);


        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.del(user.getId(), shippingId);
    }

    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse update(HttpSession session, HttpServletRequest request, Shipping shipping){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);

        //二期
        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
        }
        User user = JsonUtil.string2Obj(RedisPoolUtil.get(loginToken), User.class);

        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.update(user.getId(), shipping);
    }


    @RequestMapping("select.do")
    @ResponseBody
    public ServerResponse<Shipping> select(HttpSession session, HttpServletRequest request, Integer shippingId){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);

        //二期
        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
        }
        User user = JsonUtil.string2Obj(RedisPoolUtil.get(loginToken), User.class);

        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.select(user.getId(), shippingId);
    }


    //分页

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum" ,defaultValue = "1") int pagNum,
                                         @RequestParam(value = "pageSize" ,defaultValue = "10")int pageSize,
                                         HttpSession session, HttpServletRequest request){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);

        //二期
        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
        }
        User user = JsonUtil.string2Obj(RedisPoolUtil.get(loginToken), User.class);

        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iShippingService.list(user.getId(), pagNum, pageSize);
    }

}
