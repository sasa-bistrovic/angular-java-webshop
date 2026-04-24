package com.by.sasa.bistrovic.web.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "filteroption_item")
public class FilterOption {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String parameter;
    private String value;
    
    @ManyToOne
    @JoinColumn(name = "product_variant_id")
    @JsonIgnore
    private ProductVariant productVariant;    

    public FilterOption() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getParameter() { return parameter; }
    public void setParameter(String parameter) { this.parameter = parameter; }
    
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }    

    public ProductVariant getProductVariant() { return productVariant; }
    public void setProductVariant(ProductVariant productVariant) { this.productVariant = productVariant; }    
}
