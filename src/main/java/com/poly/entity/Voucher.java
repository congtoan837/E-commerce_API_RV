package com.poly.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.*;
import org.hibernate.annotations.Where;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vouchers")
@Where(clause = "is_deleted = false")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column
    private String code;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String coverImage;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "voucher_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "voucher_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;

    @Column
    private long value;

    @Column
    private long maxValue;

    @Column
    private boolean isPercent;

    @Column
    private Long limitQuantity;

    @Column
    private int consumedQuantity;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private String status; // active, inactive, expired

    @Column(nullable = false)
    private boolean isDeleted;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime modifiedLastTime;
}
