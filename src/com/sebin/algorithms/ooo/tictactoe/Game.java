package com.sebin.interview.ooo.tictactoe;

import com.sebin.interview.ooo.tictactoe.models.Cell;
import com.sebin.interview.ooo.tictactoe.models.Player;

import java.util.Scanner;

public class Game {

    private Cell[][] gameState;
    private Player[] players;
    private int currentTurn;
    private int totalMoves = 0;
    private GameEngine gameEngine;

    public Game(int boardSize,Player player1,Player player2){
        gameState = new Cell[boardSize][boardSize];
        currentTurn = 0;
        players = new Player[2];
        initializeState();
        addPlayers(player1,player2);
        gameEngine = new GameEngine();
    }

    public Game(int boardSize,Player player){
        gameState = new Cell[boardSize][boardSize];
        initializeState();
        players = new Player[2];
        Player computer = new Player("Computer");
        addPlayers(computer,player);
        gameEngine = new GameEngine();
        currentTurn = 0;
    }

    public void startGame(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Game Started");

        while(true){
            System.out.println("Current player "+players[currentTurn].getUsername());
            System.out.println("Please add row");
            int row = scanner.nextInt();
            System.out.println("Please add col");
            int col = scanner.nextInt();

            if(!gameEngine.placeSymbol(gameState,row,col,GameSymbols.STAR.getSymbolForPlayer(currentTurn))){
                System.out.println("We have a winner "+players[currentTurn].getUsername());
                displayGameState();
                break;
            }else{
                displayGameState();
                currentTurn  = (currentTurn == 0) ? 1 : 0;
                totalMoves++;
                if(totalMoves >= gameState.length*gameState.length){
                    System.out.println("Game is draw");
                    displayGameState();
                    break;
                }
            }
        }
    }

    private void addPlayers(Player player1,Player player2){
        players[0] = player1;
        players[1] = player2;
    }

    private void initializeState(){
        for(int i=0;i<gameState.length;i++){
            for(int j=0;j<gameState[0].length;j++){
                gameState[i][j] = new Cell(i,j);
            }
        }
    }

    public void displayGameState(){
        for(int i=0;i<gameState.length;i++){
            for(int j=0;j<gameState[0].length;j++){
                System.out.print(gameState[i][j].getValue()+" ");
            }
            System.out.println("");
        }
    }

    public enum GameSymbols {
        STAR("*"),CIRCLE("O");

        private String value;

        GameSymbols(String s) {
            value = s;
        }

        public String getValue(){
            return value;
        }

        public GameSymbols getValidSymbol(String input){
            for(GameSymbols symbols : GameSymbols.values()){
                if(symbols.getValue().equals(input)){
                    return symbols;
                }
            }
            return null;
        }

        public GameSymbols getSymbolForPlayer(int playerNumber){
            return (playerNumber == 0) ?  STAR : CIRCLE;
        }
    }


    public static void main(String[] args) {
        Game game = new Game(3,new Player("Sebin"));
        game.displayGameState();
        game.startGame();
    }
}
