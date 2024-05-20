package com.meossg.mall.model.dao;

import com.meossg.mall.model.dto.MallOrderDTO;

import java.util.List;

public interface OrderMapper {
    List<MallOrderDTO> getAllOrderList();

    List<MallOrderDTO> getAllOrderListWithStatus();

    int approveMemberOrder(int orderId);

    int makeOrderDeliver(int orderId);
}
