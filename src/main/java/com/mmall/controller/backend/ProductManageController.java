package com.mmall.controller.backend;

import com.google.common.collect.Maps;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.IFileService;
import com.mmall.service.IUserService;
import com.mmall.service.IProductService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.PropertiesUtil;
import com.mmall.util.RedisShardedPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by zhang on 2018/3/16.
 */
@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IProductService iproductService;

    @Autowired
    private IFileService iFileService;


    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse productSave(HttpSession session, HttpServletRequest request, Product product){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);
        //二期
//        String loginToken = CookieUtil.readLoginToken(request);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
//        }
//        User user = JsonUtil.string2Obj(RedisShardedPoolUtil.get(loginToken), User.class);
//        if(user == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
//        }
//        if(iUserService.checkAdminRole(user).isSuccess()){
//            //填充增加产品的额逻辑代码
//            return iproductService.saveOrUpdateProduct(product);
//        }else{
//            return ServerResponse.createByErrorMessage("不是管理员，无权限");
//        }
        return iproductService.saveOrUpdateProduct(product);
    }


    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse setSaleStatus(HttpSession session, HttpServletRequest request ,Integer productId, Integer status){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);

        //二期
//        String loginToken = CookieUtil.readLoginToken(request);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
//        }
//        User user = JsonUtil.string2Obj(RedisShardedPoolUtil.get(loginToken), User.class);
//
//        if(user == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
//        }
//        if(iUserService.checkAdminRole(user).isSuccess()){
//            return iproductService.setSaleStatus(productId, status);
//        }else{
//            return ServerResponse.createByErrorMessage("不是管理员，无权限");
//        }
        return iproductService.setSaleStatus(productId, status);
    }

    //获取商品详情
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse getDetail(HttpSession session,HttpServletRequest request,  Integer productId, Integer status){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);

        //二期
//        String loginToken = CookieUtil.readLoginToken(request);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
//        }
//        User user = JsonUtil.string2Obj(RedisShardedPoolUtil.get(loginToken), User.class);
//
//        if(user == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
//        }
//        if(iUserService.checkAdminRole(user).isSuccess()){
//                //业务
//            return iproductService.manageProductDetail(productId);
//        }else{
//            return ServerResponse.createByErrorMessage("不是管理员，无权限");
//        }
        return iproductService.manageProductDetail(productId);
    }

    //获取商品列表
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session,HttpServletRequest request ,  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);

        //二期
//        String loginToken = CookieUtil.readLoginToken(request);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
//        }
//        User user = JsonUtil.string2Obj(RedisShardedPoolUtil.get(loginToken), User.class);
//
//        if(user == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
//        }
//        if(iUserService.checkAdminRole(user).isSuccess()){
//            //业务
//            return iproductService.getProductList(pageNum, pageSize);
//        }else{
//            return ServerResponse.createByErrorMessage("不是管理员，无权限");
//        }
        return iproductService.getProductList(pageNum, pageSize);

    }

    //查询商品
    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse productSearch(HttpSession session,HttpServletRequest request, String productName, Integer productId,  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);

        //二期
//        String loginToken = CookieUtil.readLoginToken(request);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
//        }
//        User user = JsonUtil.string2Obj(RedisShardedPoolUtil.get(loginToken), User.class);
//
//        if(user == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
//        }
//        if(iUserService.checkAdminRole(user).isSuccess()){
//            //业务
//            return iproductService.searchProduct(productName, productId, pageNum, pageSize);
//        }else{
//            return ServerResponse.createByErrorMessage("不是管理员，无权限");
//        }
        return iproductService.searchProduct(productName, productId, pageNum, pageSize);

    }


    //文件长传
    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upLoad(HttpSession session, @RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);

        //二期
//        String loginToken = CookieUtil.readLoginToken(request);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
//        }
//        User user = JsonUtil.string2Obj(RedisShardedPoolUtil.get(loginToken), User.class);
//
//        if(user == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
//        }
//        if(iUserService.checkAdminRole(user).isSuccess()){
//            String path = request.getSession().getServletContext().getRealPath("upload");
//            String targetFileName = iFileService.upload(file, path);
//            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
//            System.out.println(url);
//            Map fileMap = Maps.newHashMap();
//            fileMap.put("uri", targetFileName);
//            fileMap.put("url", url);
//            return ServerResponse.createBySuccess(fileMap);
//        }else{
//            return ServerResponse.createByErrorMessage("不是管理员，无权限");
//        }
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);
        return ServerResponse.createBySuccess(fileMap);
    }

    //富文本上传
    @RequestMapping("richtext_img_upload.do")
    @ResponseBody
    public Map richtextImgUpload(HttpSession session, @RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);

        Map resultMap = Maps.newHashMap();
        //二期
//        String loginToken = CookieUtil.readLoginToken(request);
//        if(StringUtils.isEmpty(loginToken)){
//            resultMap.put("success", false);
//            resultMap.put("msg", "请登录管理员");
//            return resultMap;
//        }
//        User user = JsonUtil.string2Obj(RedisShardedPoolUtil.get(loginToken), User.class);
//
//        if(user == null){
//            resultMap.put("success", false);
//            resultMap.put("msg", "请登录管理员");
//            return resultMap;
//        }
//        //富文本中对于返回值有自己的要求，使用simditor所以按照simditor的要求进行返回
//
//        if(iUserService.checkAdminRole(user).isSuccess()){
//            String path = request.getSession().getServletContext().getRealPath("upload");
//            String targetFileName = iFileService.upload(file, path);
//            if(StringUtils.isBlank(targetFileName)){
//                resultMap.put("success", false);
//                resultMap.put("msg", "上传失败");
//                return resultMap;
//            }
//            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
//            resultMap.put("success", true);
//            resultMap.put("msg", "上传成功");
//            resultMap.put("file_path", url);
//            response.addHeader("Access-Control-Allow-Headers", "X-File-Name");
//            return resultMap;
//        }else{
//            resultMap.put("success", false);
//            resultMap.put("msg", "无权限操作");
//            return resultMap;        }
//
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        if(StringUtils.isBlank(targetFileName)){
            resultMap.put("success", false);
            resultMap.put("msg", "上传失败");
            return resultMap;
        }
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
        resultMap.put("success", true);
        resultMap.put("msg", "上传成功");
        resultMap.put("file_path", url);
        response.addHeader("Access-Control-Allow-Headers", "X-File-Name");
        return resultMap;
    }







}
