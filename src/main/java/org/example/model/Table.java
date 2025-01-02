package org.example.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

public class Table extends View {
    public JTable table;
    /*
    * set header with icon
    * */
    @ElementList(inline = true, entry = "header", type = Header.class, required = false)
    public List<Header> header;
    /*
    * set header: a, b, c
    * */
    @Attribute(name = "headers", required = false)
    public String sHeader;

    @ElementList(inline = true, entry = "row", type = Row.class, required = false)
    public List<Row> rows;

    /*
    * set rows: {
    *               {a, b, c},
    *               {a1, b1, c1}
    *           }
    * */
    @Attribute(name = "rows", required = false)
    public String sRows;

    public Table(String id, String text, String width, String sHeader, List<Row> rows) {
        super(id, text, width);
        this.sHeader = sHeader;
        this.rows = rows;
    }

    public Table(String id, String text, String width, List<Header> header, List<Row> rows) {
        super(id, text, width);
        this.header = header;
        this.rows = rows;
    }

    public JTable createTable(Object[][] content, Object[] header) {
        table = new JTable(content, header);
        return table;
    }

    public Table() {
    }

    public JTable createTable() {
        Object[][] row;
        if (rows != null) {
            row = mapRows(rows);
        } else {
            row = mapStringTo2DArray(sRows);
        }

        if (sHeader != null) {
            table = new JTable(row, mapStringToArray(sHeader));
        } else {
            table = new JTable(row, header.toArray());
        }
        return table;
    }

    public static Object[][] mapRows(List<Row> rows) {
        return rows.stream().map(it -> it.cells.toArray(Object[]::new)).toArray(Object[][]::new);
    }

    public static Object[] mapStringToArray(String headers) {
        return headers.split(",");
    }

    public static String[][] mapStringTo2DArray(String rows) {
        return Arrays.stream(rows
                        .replaceAll("^\\{\\{", "")
                        .replaceAll("\\}$", "")
                        .split("\\}, \\{"))
                .map(row -> row.replaceAll("[{}]", "").split(",\\s*")).toArray(String[][]::new);
    }

    @Override
    public void setOnClickListener(Action toDo) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toDo.invoke();
            }
        });
    }
}
