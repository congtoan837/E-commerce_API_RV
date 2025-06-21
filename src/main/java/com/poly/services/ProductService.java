package com.poly.services;

import java.util.*;

import com.poly.dto.request.AttributeValueRequest;
import com.poly.entity.*;
import com.poly.repositories.AttributeRepository;
import com.poly.repositories.AttributeValueRepository;
import jakarta.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.dto.request.ProductRequest;
import com.poly.dto.request.VariantRequest;
import com.poly.dto.response.PageResponse;
import com.poly.dto.response.product.ProductResponse;
import com.poly.exception.AppException;
import com.poly.exception.ErrorCode;
import com.poly.mapper.ProductMapper;
import com.poly.mapper.VariantMapper;
import com.poly.repositories.CategoryRepository;
import com.poly.repositories.ProductRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    AttributeRepository attributeRepository;

    ProductMapper productMapper;
    VariantMapper variantMapper;

    private void processVariant(ProductRequest request, Product product) {
        long minPrice = Long.MAX_VALUE;
        long maxPrice = Long.MIN_VALUE;

        Set<Variant> currentVariants = product.getVariants();
        currentVariants.clear();

        for (VariantRequest variantRequest : request.getVariants()) {
            Variant variant = variantMapper.toVariant(variantRequest);
            variant.setProduct(product);

            Set<AttributeValue> processedAttributeValues = new HashSet<>();
            for (AttributeValueRequest avReq : variantRequest.getAttributeValues()) {
                Attribute attribute = attributeRepository.findById(avReq.getAttributeId())
                        .orElseThrow(() -> new AppException(ErrorCode.VARIANT_ATTRIBUTE_INVALID));

                AttributeValue newValue = AttributeValue.builder()
                        .attribute(attribute)
                        .value(avReq.getValue())
                        .build();
                processedAttributeValues.add(newValue);
            }

            variant.setAttributeValues(processedAttributeValues);

            // Cập nhật min/max price
            long price = variant.getPrice();
            minPrice = Math.min(minPrice, price);
            maxPrice = Math.max(maxPrice, price);
            //
            variant.setSoldQuantity(0L);

            currentVariants.add(variant);
        }

        if (currentVariants.isEmpty()) {
            product.setSoldQuantity(0L);

            product.setMinPrice(null);
            product.setMaxPrice(null);
        } else {
            product.setPrice(null);
            product.setStockQuantity(null);

            product.setMinPrice(minPrice);
            product.setMaxPrice(maxPrice);
        }

        product.setVariants(currentVariants);
    }

    @Transactional
    public ProductResponse create(ProductRequest request) {
        Product product = productMapper.toProduct(request);

        List<Category> categories = categoryRepository.findAllByNameIn(request.getCategories());
//        if (categories.size() < request.getCategories().size())
//            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        product.setCategories(new HashSet<>(categories));

        this.processVariant(request, product);

        return productMapper.toProductResponse(productRepository.save(product));
    }

    public PageResponse<ProductResponse> get(String keyword, int page, int size) {
        if (StringUtils.isNotBlank(keyword))
            keyword = StringUtils.lowerCase(keyword).trim();

        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        var pageResult = productRepository.searchByKeyword(keyword, pageable);
        return productMapper.toPageResponse(pageResult);
    }

    public ProductResponse getDetail(UUID productId) {
        var result = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return productMapper.toProductResponse(result);
    }

    @Transactional
    public ProductResponse update(ProductRequest request) {
        Product product = productRepository
                .findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

//        // Vô hiệu hóa phiên bản cũ
//        productRepository.softDelete(request.getId());

//        // Tạo sản phẩm mới
//        var newProduct = productMapper.copyProduct(product);
//        productMapper.updateProductFromProductRequest(newProduct, request);

        productMapper.updateProductFromProductRequest(product, request);

        List<Category> categories = categoryRepository.findAllByNameIn(request.getCategories());
        product.setCategories(new HashSet<>(categories));

        this.processVariant(request, product);

        return productMapper.toProductResponse(productRepository.save(product));
    }

    public void delete(UUID id) {
        if (productRepository.softDelete(id) < 1)
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
    }
}
