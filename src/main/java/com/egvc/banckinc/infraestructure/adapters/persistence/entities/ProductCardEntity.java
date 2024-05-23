package com.egvc.banckinc.infraestructure.adapters.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_card")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCardEntity {

    @Id
    private Long idProduct;

    @Column(name = "product_name")
    private String productName;




}
