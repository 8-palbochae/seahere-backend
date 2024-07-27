package com.seahere.backend.inventory.entity;

import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
@NoArgsConstructor
public class ItemEntity {
    @Id
    @Column(name = "item_id")
    private int item_id;

    private String name;
    private String item_image;
}
