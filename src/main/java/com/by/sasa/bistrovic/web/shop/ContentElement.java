package com.by.sasa.bistrovic.web.shop;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "content_element_item")
public class ContentElement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String type;

    private String content;
    private String alignment;
    private String bgColor;
    private String textColor;
    private Boolean isBold;
    private Boolean isItalic;

    private String imageUrl;
    private String videoUrl;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ParameterSpec> specs;

    @OneToMany(cascade = CascadeType.ALL)
    private List<BulletPoint> bullets;

    @OneToOne(cascade = CascadeType.ALL)
    private ImageSubDesc imageSubDesc;

    @OneToOne(cascade = CascadeType.ALL)
    private TableElement table;

    @OneToOne(cascade = CascadeType.ALL)
    private TableComboBoxButtonElement tableComboBoxButton;

    public ContentElement() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAlignment() { return alignment; }
    public void setAlignment(String alignment) { this.alignment = alignment; }

    public String getBgColor() { return bgColor; }
    public void setBgColor(String bgColor) { this.bgColor = bgColor; }

    public String getTextColor() { return textColor; }
    public void setTextColor(String textColor) { this.textColor = textColor; }

    public Boolean getBold() { return isBold; }
    public void setBold(Boolean bold) { isBold = bold; }

    public Boolean getItalic() { return isItalic; }
    public void setItalic(Boolean italic) { isItalic = italic; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public List<ParameterSpec> getSpecs() { return specs; }
    public void setSpecs(List<ParameterSpec> specs) { this.specs = specs; }

    public List<BulletPoint> getBullets() { return bullets; }
    public void setBullets(List<BulletPoint> bullets) { this.bullets = bullets; }

    public ImageSubDesc getImageSubDesc() { return imageSubDesc; }
    public void setImageSubDesc(ImageSubDesc imageSubDesc) { this.imageSubDesc = imageSubDesc; }

    public TableElement getTable() { return table; }
    public void setTable(TableElement table) { this.table = table; }

    public TableComboBoxButtonElement getTableComboBoxButton() { return tableComboBoxButton; }
    public void setTableComboBoxButton(TableComboBoxButtonElement tableComboBoxButton) { this.tableComboBoxButton = tableComboBoxButton; }
}
