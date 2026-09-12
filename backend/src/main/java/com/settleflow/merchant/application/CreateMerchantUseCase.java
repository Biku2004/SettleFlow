package com.settleflow.merchant.application;

import java.time.Clock;
import java.time.Instant;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.settleflow.merchant.domain.Merchant;
import com.settleflow.merchant.infrastructure.persistence.MerchantJpaEntity;

import jakarta.transaction.Transactional;

@Service 
public class CreateMerchantUseCase {
    
    private final MerchantJpaEntity merchantRepository;
    private final Clock clock;

    public CreateMerchantUseCase(
        MerchantJpaEntity merchantRepository,
        Clock clock
    ){
        this.merchantRepository = Objects.requireNonNull(merchantRepository);
    
        this.clock = Objects.requireNonNull(clock);
    }

    @Transactional 
    public Merchant execute(CreateMerchantCommand command){
        Objects.requireNonNull(command,"command must not be null");
    
        Instant now = clock.instant();
    
        Merchant merchant = Merchant.create(command.businessName(), command.email(), command.settlementCurrency(), now);


        return merchantRepository.save(merchant);
    }


}
