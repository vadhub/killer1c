package org.example.model;

import org.simpleframework.xml.ElementList;

import java.util.List;

public class Row {
    @ElementList(inline = true, entry = "Cell", type = Cell.class, required = false)
    List<Cell> cells;
}
