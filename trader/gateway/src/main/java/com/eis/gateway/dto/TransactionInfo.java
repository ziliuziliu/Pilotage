package com.eis.gateway.dto;

import com.eis.common.util.UserSide;
import lombok.Data;

@Data
public class TransactionInfo {
    String tradeId;
    String orderId;
    String product;
    Integer price;
    Integer quantity;
    String sellName;
    String sellCompany;
    String buyName;
    String buyCompany;
    UserSide initSide;
}
