package com.sebin.interview.ooo.tictactoe;

import com.sebin.interview.ooo.tictactoe.models.Cell;
import com.sebin.interview.ooo.tictactoe.Game.*;

public class GameEngine {

    public boolean isGameOver(Cell[][] gameState,int currentRow,int currentCol,GameSymbols currentSymbol){

        boolean isRowMatch = true;
        for(int i=0;i<gameState[0].length;i++){
            if(!gameState[currentRow][i].getValue().equals(currentSymbol.getValue())){
                isRowMatch = false;
                break;
            }
        }

        if(isRowMatch)
            return true;

        boolean isColMatch = true;
        for(int i=0;i<gameState.length;i++){
            if(!gameState[i][currentCol].getValue().equals(currentSymbol.getValue())){
                isColMatch = false;
                break;
            }
        }

        if(isColMatch)
            return true;

        boolean isDiagonal = true;
        int colValue = currentCol;
        for(int i = currentRow;i>=0;i--){
            if(colValue < gameState.length && !gameState[i][colValue].getValue().equals(currentSymbol.getValue())){
                isDiagonal = false;
                break;
            }
            colValue++;
        }

        int rowValue = currentRow;
        for(int i = currentCol;i>=0;i--){
            if(rowValue < gameState.length && !gameState[rowValue][i].getValue().equals(currentSymbol.getValue())){
                isDiagonal = false;
                break;
            }
            rowValue++;
        }

        //----
        colValue = currentCol;
        for(int i = currentRow;i>=0;i--){
            if(colValue >= 0 && !gameState[i][colValue].getValue().equals(currentSymbol.getValue())){
                isDiagonal = false;
                break;
            }
            colValue--;
        }

        rowValue = currentRow;
        for(int i = currentCol;i < gameState.length;i++){
            if(rowValue < gameState.length && !gameState[rowValue][i].getValue().equals(currentSymbol.getValue())){
                isDiagonal = false;
                break;
            }
            rowValue++;
        }


        if(isDiagonal)
            return true;

        return false;
    }


    public boolean placeSymbol(Cell[][] gameState,int currentRow,int currentCol,GameSymbols currentSymbol){

        gameState[currentRow][currentCol].setCell(currentSymbol.getValue());
        if(isGameOver(gameState,currentRow,currentCol,currentSymbol)){
            return false;
        }else{
            return true;
        }
    }

}
