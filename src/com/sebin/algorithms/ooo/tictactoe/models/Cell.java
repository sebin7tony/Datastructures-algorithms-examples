package com.sebin.interview.ooo.tictactoe.models;

public class Cell {
    private int row;
    private int col;
    private String value;

    public Cell(int row,int col){
        this.col = col;
        this.row = row;
        this.value = "-";
    }

    public void setCell(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
