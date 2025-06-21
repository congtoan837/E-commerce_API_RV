package com.poly.repositories;

import com.poly.entity.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, UUID> { }
