package com.poly.mapper;

import com.poly.dto.request.AttributeRequest;
import com.poly.dto.response.product.AttributeResponse;
import com.poly.entity.Attribute;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttributeMapper {
    Attribute toAttribute(AttributeRequest request);

    AttributeResponse toAttributeResponse(Attribute attribute);

    List<AttributeResponse> toAttributeResponseList(List<Attribute> attributes);
}
