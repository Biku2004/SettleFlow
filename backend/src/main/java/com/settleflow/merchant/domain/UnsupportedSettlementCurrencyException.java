package com.settleflow.merchant.domain;

public class UnsupportedSettlementCurrencyException extends RuntimeException{
    
    public final String currency;

    public UnsupportedSettlementCurrencyException(String currency){
        // Why no encapsulation here ? means why no validation than direct thhis.currency ?
        super("Settlemenet currency " + currency + " is not supported.");
        this.currency = currency;
    }

    public String getCurrency(){
        return currency;
    }


}
