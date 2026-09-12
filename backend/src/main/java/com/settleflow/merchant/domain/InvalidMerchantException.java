package com.settleflow.merchant.domain;

public class InvalidMerchantException extends RuntimeException{
    
    public InvalidMerchantException(String message){
        super(message);
    }

}
