package com.settleflow.merchant.domain;

import java.time.Instant;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

/**
 * Merchant
 */
public final class Merchant {

    private static final int MAX_BUSINESS_NAME_LENGTH = 150;
    private static final int MAX_EMAIL_LENGTH = 254;
    private static final String SUPPORTED_SETTLEMENT_CURRENCY = "INR";

    private final UUID merchantId;

    private String businessName;
    private String email;
    private MerchantStatus status;

    private final String settlementCurrency;

    private final Instant createdAt;
    private Instant updatedAt;


    private Merchant(
        UUID merchantId, 
        String businessName, 
        String email, 
        MerchantStatus status,
        String settlementCurrency,
        Instant createdAt,
        Instant updatedAt
    ) 
    {
        this.merchantId = Objects.requireNonNull(
            merchantId, 
            "merchantId must not be null"
        );
        
        this.businessName = validateBusinessName(businessName);
        this.email = validateAndNormalizeEmail(email);

        this.status = Objects.requireNonNull(
            status,
            "status must not be null"
        );

        // which fields to have requireNonNull ?

        this.settlementCurrency = validateAndNormalizeSettlementCurrency(settlementCurrency);
        
        this.createdAt = Objects.requireNonNull(createdAt,"createtedAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt,"updateAt must not be null");
    
        if(updatedAt.isBefore(createdAt)){
            throw new InvalidMerchantException("updatedAt cannot be before createdAt");
        }
    
    }

    public static Merchant create(
        String businessName, 
        String email, 
        String settlementCurrency, 
        Instant now
    ) {
        Objects.requireNonNull(now,"now must not be null");
        
        return new Merchant(UUID.randomUUID(), businessName, email, MerchantStatus.ACTIVE, settlementCurrency, now, now);
    
    }

    public static Merchant reconstitute(
        UUID merchantId,
        String businessName,
        String email,
        MerchantStatus status,
        String settlementCurrency,
        Instant createdAt,
        Instant updatedAt
    ){
        return new Merchant(merchantId, businessName, email, status, settlementCurrency, createdAt, updatedAt);
    }

    private static String validateBusinessName(String businessName){
        if(businessName == null){
            throw new InvalidMerchantException("Business name must not be null");
        }

        String normalized = businessName.trim();

        if(normalized.isBlank()){
            throw new InvalidMerchantException("Business name must not be blank");
        }

        if(normalized.length() > MAX_BUSINESS_NAME_LENGTH){
            throw new InvalidMerchantException(
                "Businesss name must not exceed " + MAX_BUSINESS_NAME_LENGTH + " characters"
            );
        }

        return normalized;
    }


    public static String validateAndNormalizeEmail(String email){
        if(email == null){
            throw new InvalidMerchantException("Email must not be null");
        }

        String normalized = email.trim().toLowerCase(Locale.ROOT);
    
        if(normalized.isBlank()){
            throw new InvalidMerchantException("Email must not be blank");
        }

        if(normalized.length() > MAX_EMAIL_LENGTH){
            throw new InvalidMerchantException(
                "Email must not exceed " +
                MAX_EMAIL_LENGTH + " characters"   
            );
        }

        return normalized;
    
    }

    public static String validateAndNormalizeSettlementCurrency(String settlementCurrency){

        if(settlementCurrency == null){
            throw new InvalidMerchantException("Settlement Currency cannot be null");
        }

        String normalized = settlementCurrency
            .trim()
            .toUpperCase(Locale.ROOT);

        if(!SUPPORTED_SETTLEMENT_CURRENCY.equals(normalized)){
            throw new UnsupportedSettlementCurrencyException(normalized);
        }

        return normalized;
    }

    public UUID getMerchantId() {
        return merchantId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getEmail() {
        return email;
    }

    public MerchantStatus getStatus() {
        return status;
    }

    public String getSettlementCurrency() {
        return settlementCurrency;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }    
    
}
