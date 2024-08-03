package com.seahere.backend.outgoing.service;

import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.inventory.entity.InventoryEntity;
import com.seahere.backend.inventory.exception.InventoryNotFoundException;
import com.seahere.backend.inventory.repository.InventoryJpaRepository;
import com.seahere.backend.outgoing.entity.OutgoingDetailEntity;
import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.entity.OutgoingState;
import com.seahere.backend.outgoing.exception.LackInventoryException;
import com.seahere.backend.outgoing.exception.OutgoingNotFoundException;
import com.seahere.backend.outgoing.repository.OutgoingJpaRepository;
import com.seahere.backend.outgoing.repository.OutgoingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OutgoingService {

    private final OutgoingJpaRepository outgoingJpaRepository;
    private final InventoryJpaRepository inventoryJpaRepository;
    private final OutgoingRepository outgoingRepository;

    public Slice<OutgoingEntity> findByOutgoingStateIsPending(Long companyId, Pageable pageable, LocalDate startDate, LocalDate endDate, String search){
        return outgoingRepository.findByOutgoingStateIsPending(companyId,pageable, startDate, endDate, search);
    }
    @Transactional
    public void save(OutgoingEntity outgoingEntity){
        outgoingJpaRepository.save(outgoingEntity);
    }
    @Transactional
    public OutgoingEntity changeOutgoingState(Long outgoingId, OutgoingState state){
        if(OutgoingState.READY.equals(state)){
            OutgoingEntity outgoingCall = acceptOutgoingCall(outgoingId);
            outgoingCall.changeState(state);
            return outgoingCall;
        }
        OutgoingEntity outgoingCall = outgoingJpaRepository.findById(outgoingId).orElseThrow(OutgoingNotFoundException::new);
        outgoingCall.changeState(state);
        return outgoingCall;
    }

    private OutgoingEntity acceptOutgoingCall(Long outgoingId){
        OutgoingEntity outgoingCall = outgoingJpaRepository.findByIdFetchCompany(outgoingId).orElseThrow(OutgoingNotFoundException::new);
        CompanyEntity company = outgoingCall.getCompany();

        for(OutgoingDetailEntity detail : outgoingCall.getOutgoingDetails()){
             InventoryEntity inventory= inventoryJpaRepository.findByCategoryAndProductNameAndCompanyIdAndNaturalStatusAndCountry(detail.getCategory(), detail.getProduct().getProductName(), company.getId(), detail.getNaturalStatus(), detail.getCountry())
                    .orElseThrow(InventoryNotFoundException::new);
             if(detail.isLackInventory(inventory.getQuantity())) throw new LackInventoryException();
             inventory.minusQuantity(detail.getQuantity());
        }
        return outgoingCall;
    }
}
