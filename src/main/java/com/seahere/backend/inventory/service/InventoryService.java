package com.seahere.backend.inventory.service;

import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.company.repository.CompanyRepository;
import com.seahere.backend.incoming.controller.request.IncomingDataRequest;
import com.seahere.backend.incoming.entity.IncomingEntity;
import com.seahere.backend.inventory.controller.request.InventoryRequest;
import com.seahere.backend.inventory.controller.response.InventoryReqDetailDto;
import com.seahere.backend.inventory.controller.response.InventoryReqDto;
import com.seahere.backend.inventory.entity.InventoryEntity;
import com.seahere.backend.inventory.repository.InventoryJpaRepository;
import com.seahere.backend.product.controller.ProductController;
import com.seahere.backend.product.entity.ProductEntity;
import com.seahere.backend.product.repository.ProductRepository;
import com.seahere.backend.user.repository.UserRepository;
import com.seahere.backend.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryJpaRepository inventoryJpaRepository;
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;

    public Slice<InventoryReqDto> findPagedInventoryByCompanyId(Long companyId, Pageable pageable, String search) {
        return inventoryRepository.findPagedInventoryByCompanyId(companyId, search, pageable);
    }

    public Slice<InventoryReqDetailDto> findPagedProductsByCompanyId(Long companyId, String name, String category, PageRequest pageable) {
        return inventoryRepository.findPagedProductsByCompanyId(companyId, name, category, pageable);
    }

    //todo 나중에 뜯어 고쳐야함
    private boolean isInventory(Long companyId, IncomingDataRequest incomingDataRequest) {
        ProductEntity productEntity = productRepository.findById(incomingDataRequest.getProductId()).get();

        return inventoryJpaRepository.findByCategoryAndNameAndCompanyIdAndNaturalStatusAndCountry(incomingDataRequest.getCategory(),productEntity.getProductName(),companyId,incomingDataRequest.getNaturalStatus(),incomingDataRequest.getCountry()).isPresent();
    }

    public InventoryEntity inventoryUpdateEnroll(Long companyId, IncomingDataRequest incomingDataRequest){

        ProductEntity productEntity = productRepository.findById(incomingDataRequest.getProductId()).get();

        if(isInventory(companyId, incomingDataRequest)){
            InventoryEntity inventoryEntity = inventoryJpaRepository.findByCategoryAndNameAndCompanyIdAndNaturalStatusAndCountry(incomingDataRequest.getCategory(),productEntity.getProductName(),companyId,incomingDataRequest.getNaturalStatus(),incomingDataRequest.getCountry()).get();
            inventoryEntity.addQuantity(incomingDataRequest.getQuantity());
            return inventoryEntity;
        }
        else{
            InventoryRequest inventoryRequest = InventoryRequest.builder()
                    .companyId(companyId)
                    .quantity(incomingDataRequest.getQuantity())
                    .category(incomingDataRequest.getCategory())
                    .name(productEntity.getProductName())
                    .country(incomingDataRequest.getCountry())
                    .incomingDate(LocalDate.now())
                    .naturalStatus(incomingDataRequest.getNaturalStatus())
                    .build();

            InventoryEntity inventoryEntity = inventoryRequest.toEntity();
            CompanyEntity company = companyRepository.findById(companyId).get();
            inventoryEntity.assignCompany(company);
            inventoryJpaRepository.save(inventoryEntity);
            return inventoryEntity;
        }
    }


}
