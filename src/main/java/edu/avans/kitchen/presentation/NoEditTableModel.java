package edu.avans.kitchen.presentation;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bram
 */

public class NoEditTableModel extends AbstractTableModel{
    //Attributes
    private final String[] cNames;
    private final int cLength;
    private final String[][] data;
    private String status;
    
    //Constructor
    public NoEditTableModel(String[][] data, String[] colNames){
        this.data = data;
        this.cNames = colNames;
        this.cLength = cNames.length;
        this.status = "";
    }
    
    public NoEditTableModel(String[][] data, String[] colNames, String status){
        this.data = data;
        this.cNames = colNames;
        this.cLength = cNames.length;
        this.status = status;
    }
    
    //Getters
    @Override
    public int getColumnCount(){
        return cLength;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }
    
    @Override
    public String getColumnName(int col){
        return cNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    
    //Setters
    @Override
    public void setValueAt(Object val, int row, int col){
        data[row][col] = val.toString();
    }
    
    //Methods
    @Override
    public boolean isCellEditable(int row, int col){
        return "placed".equals(status) && col == 1;
    }
}
