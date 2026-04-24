package com.by.sasa.bistrovic.web.shop;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_section_item")
public class ProductSection {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private boolean dropdown;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ContentElement> elements;

    public ProductSection() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean getDropdown() { return dropdown; }
    public void setDropdown(boolean dropdown) { this.dropdown = dropdown; }

    public List<ContentElement> getElements() { return elements; }
    public void setElements(List<ContentElement> elements) { this.elements = elements; }
}