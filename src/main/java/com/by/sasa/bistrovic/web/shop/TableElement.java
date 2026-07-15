package com.by.sasa.bistrovic.web.shop;

import jakarta.persistence.*;
import java.util.List;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "table_element_item")
public class TableElement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ElementCollection
    private List<String> columns;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<List<String>> rows;

    public TableElement() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public List<String> getColumns() { return columns; }
    public void setColumns(List<String> columns) { this.columns = columns; }

    public List<List<String>> getRows() { return rows; }
    public void setRows(List<List<String>> rows) { this.rows = rows; }
}
