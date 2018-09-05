package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.vo.OrderVo;

import java.util.Map;

/**
 * Created by zhang on 2018/3/26.
 */
public interface IOrderService {


    //前台
    ServerResponse pay(Long orderNo, Integer userId, String path);

    ServerResponse aliCallback(Map<String, String> params);

    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);

    ServerResponse createOrder(Integer userId, Integer shippingId);

    ServerResponse<String> cancelOrder(Integer userId, Long orderNo);

    ServerResponse getOrderCartProduct(Integer userId);

    ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo);

    ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize);

    ServerResponse<PageInfo> manageList(int pageNum, int pageSize);


    //后台
    ServerResponse<OrderVo> manegeDetail(Long orderNo);

    ServerResponse<PageInfo> manegeSearch(Long orderNo, int pageNum, int pageSize);

    ServerResponse<String> manegeSendGoods(Long orderNo);

    //二期定时关闭订单
    void closeOrder(int hour);

}
