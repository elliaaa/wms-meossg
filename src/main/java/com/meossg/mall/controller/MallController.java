package com.meossg.mall.controller;

import com.meossg.mall.model.dto.*;
import com.meossg.mall.model.service.MallService;

import java.util.*;

public class MallController {

    private static MallService mallService = new MallService();

    public static void addProduct(ProductDTO product) {
        MallService.addProduct(product);
    }

    public static void modifyProduct(ProductDTO product) {
        int result = MallService.modifyProduct(product);

        if (result == 1) {
            System.out.println("상품 정보 변경 성공!!");
        } else {
            System.out.println("상품 정보 변경 실패!!");
        }
    }

    public static void placingOrder(Map<String, Integer> map) {

        if (map != null) {
            int result = MallService.placingOrder(map);

            if (result == 1) {
                System.out.println("발주 성공!!");
            } else {
                System.out.println("발주 실패!!");
            }
        }
    }

    public static int checkProductExists(int productId) {
        return MallService.checkProductExists(productId);
    }

    public static void getAllStockList() {
        List<StockDTO> stockList = MallService.getAllStockList();
        System.out.println("---------- < 재고 > ----------");
        for (StockDTO stock : stockList) {
            System.out.print("ID : %3s,  ".formatted(stock.getId()));
            System.out.print("NAME : %8s,  ".formatted(stock.getName()));
            System.out.println("COUNT : %4s".formatted(stock.getCount()));
        }
    }

    public static void showAllProducts() {
        List<ProductDTO> allProductList = mallService.selectAllProducts();

        for (ProductDTO product : allProductList) {
            System.out.println(product.toStringWithID());
        }
    }

    public static void showAllPlacingOrder() {
        List<MallPlacingOrderDTO> placingOrderList = mallService.selectAllPlacingOrder();

        for (MallPlacingOrderDTO placingOrder : placingOrderList) {
            System.out.println(placingOrder);
        }

    }

    public static int isPlacingOrderNull(int mallPlacingOrderId) {
        return MallService.isPlacingOrderNull(mallPlacingOrderId);
    }

    public static void deletePlacingOrder(HashMap<String, Integer> placingOrderId) {

        if (placingOrderId != null) {
            int result = MallService.deletePlacingOrder(placingOrderId);

            if (result == 1) {
                System.out.println(placingOrderId.values() + "번 발주 삭제를 성공했습니다.");
            } else {
                System.out.println(placingOrderId.values() + "번 발주 삭제를 실패했습니다.");
            }
        }
    }

    public AdminDTO login(AdminDTO admin) {

        return mallService.verifyLogin(admin);
    }

    public void selectAllMember() {
        List<MemberDTO> memberList = mallService.selectAllMember();

        for (MemberDTO member : memberList) {
            System.out.println(member);
        }
    }


    public void selectMemberByName(MemberDTO findName) {
        List<MemberDTO> memberList = mallService.selectMemberByName(findName);

        if (memberList.isEmpty()) {
            System.out.println(findName.getName() + "님이 존재하지 않습니다.");
        } else {
            for (MemberDTO member : memberList) {
                System.out.println(member);
            }
        }

    }


    public void getAllOrderList() {
        List<MallOrderDTO> orderList = mallService.getAllOrderList();

        if (orderList != null) {
            for (MallOrderDTO order : orderList) {
                System.out.print("주문번호 : %3s,  ".formatted(order.getOrderId()));
                System.out.print("주문자 : %8s,  ".formatted(order.getUserName()));
                System.out.print("제품 : %4s,  ".formatted(order.getProductName()));
                System.out.print("수량 : %4s,  ".formatted(order.getCount()));
                System.out.println("총액 : %4s".formatted(order.getTotalPrice()));
            }
        } else {
            System.out.println("주문 내역이 없습니다.");
        }
    }

    public void showPostStatus() {
        List<DeliveryDTO> deliveryList = mallService.showPostStatus();
        if (!deliveryList.isEmpty()) {
            for (DeliveryDTO delivery : deliveryList) {
                System.out.println(delivery);
            }
        } else {
            System.out.println("배송 내역이 없습니다.");
        }
    }

    public void approveOrder() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------< 미 승인 주문 >-------");
        List<MallOrderDTO> orderList = mallService.getAllOrderListWithStatus();
        if (orderList == null) {
            System.out.println("미 승인 주문 내역이 없습니다.");
        } else {
            for (MallOrderDTO order : orderList) {
                System.out.print("주문번호 : %3s,  ".formatted(order.getOrderId()));
                System.out.print("주문자 : %8s,  ".formatted(order.getUserName()));
                System.out.print("상품명 : %4s,  ".formatted(order.getProductName()));
                System.out.print("수량 : %4s,  ".formatted(order.getCount()));
                System.out.println("총액 : %4s,  ".formatted(order.getTotalPrice()));
            }
            System.out.println("--------------------------");
            System.out.print("주문 번호를 입력하세요 : ");
            int orderId = sc.nextInt();
            for (MallOrderDTO order : orderList) {
                if (order.getOrderId() == orderId) {
                    mallService.approveOrder(orderId);
                    return;
                }
            }
            System.out.println("없는 주문 번호입니다.");
        }
    }

    public void updatePassword(Map<String, String> newPassword) {
        MemberDTO member = new MemberDTO();
        member.setId(newPassword.get("id"));
        member.setName(newPassword.get("name"));
        member.setPassword(newPassword.get("password"));

        if (mallService.updatePassword(member)) {
            System.out.println("회원정보 수정을 성공했습니다.");
        } else {
            System.out.println("회원정보 수정을 실패했습니다.");
        }
    }


    public void updatePhone(Map<String, String> newPhone) {
        MemberDTO member = new MemberDTO();
        member.setId(newPhone.get("id"));
        member.setName(newPhone.get("name"));
        member.setPhone(newPhone.get("phone"));

        if (mallService.updatePhone(member)) {
            System.out.println("회원정보 수정을 성공했습니다.");
        } else {
            System.out.println("회원정보 수정을 실패했습니다.");
        }
    }

    public void updateAddress(Map<String, String> newAddress) {
        MemberDTO member = new MemberDTO();
        member.setId(newAddress.get("id"));
        member.setName(newAddress.get("name"));
        member.setAddress(newAddress.get("address"));

        if (mallService.updateAddress(member)) {
            System.out.println("회원정보 수정을 성공했습니다.");
        } else {
            System.out.println("회원정보 수정을 실패했습니다.");
        }
    }


    public void updateAllInfo(Map<String, String> newAllInfo) {
        MemberDTO member = new MemberDTO();
        member.setId(newAllInfo.get("id"));
        member.setName(newAllInfo.get("name"));
        member.setPassword(newAllInfo.get("password"));
        member.setPhone(newAllInfo.get("phone"));
        member.setAddress(newAllInfo.get("address"));

        if (mallService.updateAllInfo(member)) {
            System.out.println("회원정보 수정을 성공했습니다.");
        } else {
            System.out.println("회원정보 수정을 실패했습니다.");
        }
    }

    public void getTotalProfit() {
        System.out.println("----------< 전체 기간 수익 >----------");
        System.out.println(mallService.getTotalProfit());
    }

    public void getPeriodProfit(Map<String, Integer> period) {
        if (period.isEmpty()) {
            return;
        } else {
            int total = mallService.getPeriodProfit(period);
            System.out.println(total == 0 ? "해당 기간의 수익이 없습니다." : total);
        }
    }

    public void selectTotalSalesRate() {
        List<SalesRateDTO> salesRateList;

        salesRateList = mallService.selectTotalSalesRate();

        if (!salesRateList.isEmpty()) {
            System.out.println("=================== 총 판매량 ===================");
            for (SalesRateDTO salesRate : salesRateList) {
                System.out.println(salesRate);
            }
            System.out.println("===============================================");
        } else {
            System.out.println("판매 내역이 없습니다.");
        }


    }

    public void selectSalesRateByProduct(Map<String, String> map) {
        List<SalesRateDTO> salesRateList;

        salesRateList = mallService.selectSalesRateByProduct(map);

        if (!salesRateList.isEmpty()) {
            for (SalesRateDTO salesRate : salesRateList) {
                System.out.println(salesRate);
            }
        } else {
            System.out.println("판매 내역이 없습니다.");
        }
    }

    public void profitOfEachProduct() {
        List<ProductDTO> profitList = mallService.profitOfEachProduct();

        if (!profitList.isEmpty()) {
            for (ProductDTO profit : profitList) {
                System.out.println("[ 상품번호 : " + profit.getId() +
                        " | 상품명: " + profit.getName() +
                        " | 매출: " + profit.getPrice() + " ]");
            }
        } else {
            System.out.println("상품이 없습니다.");
        }
    }
}


