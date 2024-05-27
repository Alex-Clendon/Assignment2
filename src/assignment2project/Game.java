/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

/**
 *
 * @author alexa
 */
public abstract class Game {
    //Abstract class that defines a game. It has a player and a score.
    //Millionare extends game as millionare is a type of game.
    protected Player player;
    protected int score;
    
    public abstract void initialiseGame();
    public abstract void startGame();
    public abstract void endGame();
    
    public int getScore(){
        return score;
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }
}
