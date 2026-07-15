package com.by.sasa.bistrovic.web.shop;

import jakarta.persistence.*;

@Entity
@Table(name = "image_sub_desc_item")
public class ImageSubDesc {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String text;
    private String position;
    private String bgColor;
    private String textColor;
    private boolean isBold;
    private boolean isItalic;

    public ImageSubDesc() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getBgColor() { return bgColor; }
    public void setBgColor(String bgColor) { this.bgColor = bgColor; }

    public String getTextColor() { return textColor; }
    public void setTextColor(String textColor) { this.textColor = textColor; }

    public boolean isBold() { return isBold; }
    public void setBold(boolean bold) { isBold = bold; }

    public boolean isItalic() { return isItalic; }
    public void setItalic(boolean italic) { isItalic = italic; }
}

