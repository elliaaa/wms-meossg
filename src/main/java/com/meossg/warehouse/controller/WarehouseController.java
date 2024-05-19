package com.meossg.warehouse.controller;

import com.meossg.warehouse.model.dto.InWarehouseDTO;
import com.meossg.warehouse.model.service.InWarehouseService;

import java.util.List;

public class WarehouseController {

    public static void getInWarehouse() {
        List<InWarehouseDTO> inWarehouseDTOList= InWarehouseService.getAllInWarehouseList();
        System.out.println("--------------창고--------------");
        for (InWarehouseDTO inWarehouseDTO : inWarehouseDTOList) {
            System.out.println("입고번호 : " + inWarehouseDTO.getId());
            System.out.println("상품번호 : " + inWarehouseDTO.getProductId());
            System.out.println("입고일 : " + inWarehouseDTO.getInDate());
        }
    }


}