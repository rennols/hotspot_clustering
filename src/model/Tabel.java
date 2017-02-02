/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import struktur.Point;

/**
 *
 * @author Anavel
 */
public class Tabel extends AbstractTableModel {
    
    List<Point> Point = new ArrayList<Point>();

    private final String HEADER[] = {"Brightness", "Bright_T31", "FRP"};

    public Tabel(List<Point> Point) {
        this.Point = Point;
    }

    public Point getPoint(int index) {
        return Point.get(index);
    }

    public int getRowCount() {
        return Point.size();
    }

    public int getColumnCount() {
        return HEADER.length;
    }

    public String getColumnName(int column) {
        return HEADER[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Point cluster = Point.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return cluster.getBrightness();
            case 1:
                return cluster.getBright_t31();
            case 2:
                return cluster.getFrp();
            default:
                return null;
        }

    }
    
}
