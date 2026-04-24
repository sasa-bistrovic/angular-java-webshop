package com.by.sasa.bistrovic.web.shop;

import jakarta.persistence.*;
import java.util.List;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "table_combobox_button_element_item")
public class TableComboBoxButtonElement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<List<String>> columns;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<List<String>> rows;

    private String comboboxName;
    private String buttonName;

    @ElementCollection
    private List<String> comboboxOptions;

    public TableComboBoxButtonElement() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public List<List<String>> getColumns() { return columns; }
    public void setColumns(List<List<String>> columns) { this.columns = columns; }

    public List<List<String>> getRows() { return rows; }
    public void setRows(List<List<String>> rows) { this.rows = rows; }

    public String getComboboxName() { return comboboxName; }
    public void setComboboxName(String comboboxName) { this.comboboxName = comboboxName; }

    public String getButtonName() { return buttonName; }
    public void setButtonName(String buttonName) { this.buttonName = buttonName; }

    public List<String> getComboboxOptions() { return comboboxOptions; }
    public void setComboboxOptions(List<String> comboboxOptions) { this.comboboxOptions = comboboxOptions; }
}
