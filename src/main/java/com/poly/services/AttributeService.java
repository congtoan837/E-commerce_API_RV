package com.poly.services;

import com.poly.dto.request.AttributeRequest;
import com.poly.dto.response.product.AttributeResponse;
import com.poly.entity.Attribute;
import com.poly.mapper.AttributeMapper;
import com.poly.repositories.AttributeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttributeService {
    AttributeRepository attributeRepository;
    AttributeMapper attributeMapper;

    public AttributeResponse create(AttributeRequest request) {
        Attribute attribute = attributeMapper.toAttribute(request);
        return attributeMapper.toAttributeResponse(attributeRepository.save(attribute));
    }

    public List<AttributeResponse> getAll() {
        return attributeMapper.toAttributeResponseList(attributeRepository.findAll());
    }
}
