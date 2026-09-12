package com.settleflow.merchant.application;

/**
 * CreateMerchantCommand
 */
public record CreateMerchantCommand(
    String businessName,
    String email,
    String settlementCurrency
) {
}