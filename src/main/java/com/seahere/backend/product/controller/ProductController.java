package com.seahere.backend.product.controller;

import com.seahere.backend.product.controller.response.IncomingSearchResponse;
import com.seahere.backend.product.dto.ProductMockDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    List<ProductMockDto> mockList;

    @PostConstruct
    void init() {
        mockList = new ArrayList<>();
        mockList.add(new ProductMockDto(1L, "광어", "qr1", "img1"));
        mockList.add(new ProductMockDto(2L, "이광수", "qr2", "img2"));
        mockList.add(new ProductMockDto(3L, "밀치", "qr3", "img3"));
        mockList.add(new ProductMockDto(4L, "새우", "qr4", "img4"));
        mockList.add(new ProductMockDto(5L, "우럭", "qr5", "img5"));
        mockList.add(new ProductMockDto(6L, "해삼", "qr6", "img6"));
        mockList.add(new ProductMockDto(7L, "멍게", "qr7", "img7"));
    }

    @GetMapping("/product-search-incoming")
    public ResponseEntity<List<IncomingSearchResponse>> getMockData() {
        List<IncomingSearchResponse> fishList = mockList.stream().map(IncomingSearchResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(fishList);
    }

}
