package com.by.sasa.bistrovic.web.shop;

import jakarta.persistence.*;

@Entity
@Table(name = "bullet_point_item")
public class BulletPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String text;
    private String link;

    public BulletPoint() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}

