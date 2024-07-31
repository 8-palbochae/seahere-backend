package com.seahere.backend.inventory.service;

import com.seahere.backend.incoming.controller.request.IncomingDataRequest;
import com.seahere.backend.incoming.entity.IncomingEntity;
import com.seahere.backend.inventory.controller.request.InventoryRequest;
import com.seahere.backend.inventory.controller.response.InventoryReqDetailDto;
import com.seahere.backend.inventory.controller.response.InventoryReqDto;
import com.seahere.backend.inventory.entity.InventoryEntity;
import com.seahere.backend.inventory.repository.InventoryJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryJpaRepository inventoryJpaRepository;

    public Page<InventoryReqDto> findPagedInventoryByCompanyId(Long companyId, Pageable pageable, String search) {
        return inventoryJpaRepository.findPagedInventoryByCompanyId(companyId, search, pageable);
    }

    public Page<InventoryReqDetailDto> findPagedProductsByCompanyId(Long companyId, String name, String category, PageRequest pageable) {
        return inventoryJpaRepository.findPagedProductsByCompanyId(companyId, name, category, pageable);
    }

    //todo 나중에 뜯어 고쳐야함
    private boolean isInventory(Long companyId, IncomingDataRequest incomingDataRequest) {
        return inventoryJpaRepository.findByCategoryAndNameAndCompanyIdAndNaturalStatusAndCountry(incomingDataRequest.getCategory(),"광어",companyId,incomingDataRequest.getNaturalStatus(),incomingDataRequest.getCountry()).isPresent();
    }

    public InventoryEntity inventoryUpdateEnroll(Long companyId, IncomingDataRequest incomingDataRequest){
        if(isInventory(companyId, incomingDataRequest)){
            return null;
        }
        else{
            InventoryRequest inventoryRequest = InventoryRequest.builder()
                    .companyId(companyId)
                    .quantity(incomingDataRequest.getQuantity())
                    .category(incomingDataRequest.getCategory())
                    .name("광어")
                    .country(incomingDataRequest.getCountry())
                    .incomingDate(LocalDate.now()) // 또는 incomingDataRequest에서 날짜를 가져오도록 수정
                    .naturalStatus(incomingDataRequest.getNaturalStatus())
                    .build();

            InventoryEntity inventoryEntity = inventoryRequest.toEntity();
            inventoryJpaRepository.save(inventoryEntity);
            return inventoryEntity;
        }
    }


}
