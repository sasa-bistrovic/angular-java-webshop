package com.by.sasa.bistrovic.web.shop;

import jakarta.persistence.*;

@Entity
@Table(name = "parameter_spec_item")
public class ParameterSpec {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String key;
    private String value;
    private String link;

    public ParameterSpec() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}

