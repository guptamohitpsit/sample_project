package com.demo.project.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMode {
	NET_BANKING("NetBanking"),
    CHEQUE("Cheque"),
    CASH("Cash"),
    PAYTM("Paytm"),
    RECIEVED("Cash"),
    TRANSFER("Cash");
    
    String name;
}
