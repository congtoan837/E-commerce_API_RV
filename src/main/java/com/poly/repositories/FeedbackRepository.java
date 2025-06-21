package com.poly.repositories;

import java.util.UUID;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {

    Page<Feedback> findAllByProductId(UUID productId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Feedback f SET f.isDeleted = true WHERE f.id = :id")
    int softDelete(UUID id);
}
