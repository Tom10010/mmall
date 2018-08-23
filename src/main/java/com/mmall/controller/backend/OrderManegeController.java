package com.mmall.controller.backend;

import com.github.pagehelper.PageInfo;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IOrderService;
import com.mmall.service.IUserService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisPoolUtil;
import com.mmall.vo.OrderVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zhang on 2018/4/1.
 */

@Controller
@RequestMapping("/manage/order")
public class OrderManegeController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IOrderService iOrderService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderList(HttpSession session, HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10")int pageSize){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);

        //二期
        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
        }
        User user = JsonUtil.string2Obj(RedisPoolUtil.get(loginToken), User.class);

        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //校验一下是否是管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //增加处理分类的逻辑代码
            return iOrderService.manageList(pageNum, pageSize);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员");
        }
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> detail(HttpSession session, HttpServletRequest request, Long orderNo){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);

        //二期
        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
        }
        User user = JsonUtil.string2Obj(RedisPoolUtil.get(loginToken), User.class);

        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //校验一下是否是管理 员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //增加处理分类的逻辑代码
            return iOrderService.manegeDetail(orderNo);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员");
        }
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderSearch(HttpSession session, HttpServletRequest request, Long orderNo, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10")int pageSize){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);

        //二期
        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
        }
        User user = JsonUtil.string2Obj(RedisPoolUtil.get(loginToken), User.class);

        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //校验一下是否是管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //增加处理分类的逻辑代码
            return iOrderService.manegeSearch(orderNo, pageNum, pageSize);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员");
        }
    }

    @RequestMapping("send_goods.do")
    @ResponseBody
    public ServerResponse<String> orderSendGoods(HttpSession session, HttpServletRequest request,  Long orderNo){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);

        //二期
        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
        }
        User user = JsonUtil.string2Obj(RedisPoolUtil.get(loginToken), User.class);

        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //校验一下是否是管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //增加处理分类的逻辑代码
            return iOrderService.manegeSendGoods(orderNo);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员");
        }
    }


}
