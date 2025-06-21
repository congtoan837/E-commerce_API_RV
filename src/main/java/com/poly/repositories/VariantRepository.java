package com.poly.repositories;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.entity.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, UUID> {
    Set<Variant> findByProductId(UUID productId);

    Optional<Variant> findByIdAndProductId(UUID variantId, UUID productId);

    @Modifying
    @Transactional
    @Query("UPDATE Variant v SET v.isDeleted = true WHERE v.id = :id")
    int softDelete(UUID id);
}
