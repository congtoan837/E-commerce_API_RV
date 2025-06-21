package com.poly.entity;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Long minPrice;

    @Column
    private Long maxPrice;

    @Column
    private Long price;

    @Column
    private Long stockQuantity;

    @Column
    private Long soldQuantity;

    @Column
    private String status;

    @Column
    private String coverImage;

    @ElementCollection
    private Set<String> images;

    @Column(nullable = false)
    private boolean isDeleted;

    @Version
    private Integer version;

    @Column(columnDefinition = "int default 5")
    private Double rating;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    @Filter(name = "categoryFilter", condition = "isDeleted = false")
    private Set<Category> categories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Variant> variants;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime modifiedLastTime;
}
