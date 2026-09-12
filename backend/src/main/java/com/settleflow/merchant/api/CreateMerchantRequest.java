package com.settleflow.merchant.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * CreateMerchantRequest
 */
public record CreateMerchantRequest(

    @NotBlank 
    @Size(max = 150)
    String businessName,

    @NotBlank 
    @Email 
    @Size(max=254) 
    String email,
    
    @NotBlank 
    @Size(min=3,max = 3)
    String settlementCurrency

) {
}