package com.example;
//Made by Amir (aka eparon)
//The pawn
//behaves like a pawn except en passant and promoting. en passant works but it doesnt remove the piece when taken as i couldnt find the method fo it
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class eparonsPawn extends Piece{

    private boolean hasMoved;
    private String imgFile ;
    public eparonsPawn(boolean isWhite, String img_file) {
        super(isWhite, img_file);
        System.out.println("a");
        
    }
    //pre condition: color is not null
    //post condition: returns a string of the color of the pawn and the word pawn
    @Override
    public String toString(){
        String c = "black";
        if(color){
            c = "white";
        }
        return "A " + c + " pawn";
    }
    
    


    

    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    //pre condition: all values are defined and all methods work, all indexes are in bounds
    //post condition: returns a list of all controlled squares by the pawn (squares it can capture into)
    public ArrayList<Square> getControlledSquares(Board b, Square start) {
        Square[][] sq = b.getSquareArray();
        int multiplyColor = -1;
        if(color){
            multiplyColor = 1;
        }
        ArrayList<Square> list = new ArrayList<>();

        // if(start.getCol() == 0 &&  sq[start.getRow() + multiplyColor][start.getCol() + 1].isOccupiedWhite() == multiplyColor * -1){
        //         list.add(sq[start.getRow() + multiplyColor][start.getCol() + 1]);
        //     }
        // else if(sq[start.getRow() + multiplyColor][start.getCol() - 1].isOccupiedWhite() == multiplyColor * -1){
        //         list.add(sq[start.getRow() + multiplyColor][start.getCol() - 1]);
        //     }
            
        if(start.getCol() != 0 && start.getCol() != 7){
                list.add(sq[start.getRow() + multiplyColor][start.getCol() - 1]);
                list.add(sq[start.getRow() + multiplyColor][start.getCol() + 1]);
        }
        else{
            if(start.getCol() == 0){
                // if(sq[start.getRow() + multiplyColor][start.getCol() + 1].isOccupiedWhite() == multiplyColor * -1 ){
                list.add(sq[start.getRow() + multiplyColor][start.getCol() + 1]);
            }
            }
            if(start.getCol() == 7){
                // if(sq[start.getRow() + multiplyColor][start.getCol() - 1].isOccupiedWhite() == multiplyColor * -1){
                    list.add(sq[start.getRow() + multiplyColor][start.getCol() - 1]);
                }
              
        
       
        return list;
        
    }
    public void promote(){
        //to be implemented


 }

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    //precondition: all variables and methods are defined, and working and all indexes are in bound
    //post condition: returns arraylist of all moves pawn can go to legally
    public ArrayList<Square> getLegalMoves(Board b, Square start){
        try {
        ArrayList<Square> list = new ArrayList<>();

        Square[][] sq = b.getSquareArray();
        int multiplyColor = -1;
        int enPassantRow = 3;
        if(getColor()){
            multiplyColor = 1;
            enPassantRow++;
        }
        if(!sq[start.getRow() + multiplyColor][start.getCol()].isOccupied()){
            if(start.getRow() != 0 && start.getRow()!= 7){
                if(start.getRow() == 6 && !getColor()){
                    list.add(sq[start.getRow() + (2 * multiplyColor)][start.getCol()]);
                }
                if(start.getRow() == 1 && getColor()){
                    list.add(sq[start.getRow() + (2 * multiplyColor)][start.getCol()]);
                }
        // else if(start.getRow() == 7 && color != true){
        //     list.add(sq[5][start.getRow()]);
        // }
            list.add(sq[start.getRow() + multiplyColor][start.getCol()]);
            
            
            
        // else if(color != true){
        //     list.add(sq[start.getCol() - 1][start.getRow()]);
        // }
          //  System.out.println("row : " + start.getRow() + " col : "+ start.getCol());
            }
        }
        if(start.getCol() == 0 && sq[start.getRow() + multiplyColor][start.getCol() + 1].isOccupied() && sq[start.getRow() + multiplyColor][start.getCol() + 1].isOccupiedWhite() == multiplyColor * -1){
                list.add(sq[start.getRow() + multiplyColor][start.getCol() + 1]);
            }
        else if(start.getCol() == 7 && sq[start.getRow() + multiplyColor][start.getCol() - 1].isOccupied() && sq[start.getRow() + multiplyColor][start.getCol() - 1].isOccupiedWhite() == multiplyColor * -1){
                list.add(sq[start.getRow() + multiplyColor][start.getCol() - 1]);
            }
            
        else if(start.getCol() != 0 && start.getCol() != 7){
            if(sq[start.getRow() + multiplyColor][start.getCol() - 1].isOccupied() && sq[start.getRow() + multiplyColor][start.getCol() - 1].isOccupiedWhite() == multiplyColor * -1 ){
                list.add(sq[start.getRow() + multiplyColor][start.getCol() - 1]);
            }
            if(sq[start.getRow() + multiplyColor][start.getCol() + 1].isOccupied() && sq[start.getRow() + multiplyColor][start.getCol() + 1].isOccupiedWhite() == multiplyColor * -1 ){
                list.add(sq[start.getRow() + multiplyColor][start.getCol() + 1]);
            }
        }
        else{
            if(start.getCol() == 0){
                if(sq[start.getRow() + multiplyColor][start.getCol() + 1].isOccupied() && sq[start.getRow() + multiplyColor][start.getCol() + 1].isOccupiedWhite() == multiplyColor * -1 ){
                list.add(sq[start.getRow() + multiplyColor][start.getCol() + 1]);
            }
            }
            if(start.getCol() == 7){
                if(sq[start.getRow() + multiplyColor][start.getCol() - 1].isOccupied() && sq[start.getRow() + multiplyColor][start.getCol() - 1].isOccupiedWhite() == multiplyColor * -1){
                    list.add(sq[start.getRow() + multiplyColor][start.getCol() - 1]);
                }
            }   
        }
        if(start.getRow() == enPassantRow){
            System.out.println("in en passant row");
            if(start.getCol() != 0 && start.getCol() != 7){
                if( b.lastMovedPiece == sq[start.getRow()][start.getCol() + 1].getOccupyingPiece() && b.lastMovedPiece.getColor() != this.color && sq[start.getRow()][start.getCol() + 1].getOccupyingPiece() != null){
                    System.out.println("in en passant row but name is wrong" + sq[start.getRow()][start.getCol() + 1].getOccupyingPiece().getPieceName().toString());
                    
                    if(sq[start.getRow()][start.getCol() + 1].getOccupyingPiece().getPieceName().contains("pawn")){
                        list.add(sq[start.getRow() + multiplyColor][start.getCol() + 1]);
                        
                        System.out.println("in en passant row and trying to add");
                    }
                    
                }
                if(b.lastMovedPiece == sq[start.getRow()][start.getCol() + multiplyColor].getOccupyingPiece() && b.lastMovedPiece.getColor() != this.color && sq[start.getRow()][start.getCol() - 1].getOccupyingPiece() != null){
                    if(sq[start.getRow()][start.getCol() - 1].getOccupyingPiece().getPieceName().contains("pawn")){
                        list.add(sq[start.getRow() + multiplyColor][start.getCol() - 1]);
                    }
                }
            }
            else if(start.getCol() == 7){
                if(b.lastMovedPiece == sq[start.getRow()][start.getCol() -1 ].getOccupyingPiece() && b.lastMovedPiece.getColor() != this.color && sq[start.getRow()][start.getCol()- 1].getOccupyingPiece() != null){
                    if(sq[start.getRow()][start.getCol() - 1].getOccupyingPiece().getPieceName().contains("pawn")){
                        list.add(sq[start.getRow() + multiplyColor][start.getCol() - 1]);
                    }
                }
            }
            else if(start.getCol() == 0){
                if( b.lastMovedPiece == sq[start.getRow()][start.getCol() + 1].getOccupyingPiece() && b.lastMovedPiece.getColor() != this.color && sq[start.getRow()][start.getCol() + 1].getOccupyingPiece() != null){
                    if(sq[start.getRow()][start.getCol() + 1].getOccupyingPiece().getPieceName().contains("pawn")){
                        list.add(sq[start.getRow() + multiplyColor][start.getCol() + 1]);
                    }
                }
            }
            else{
                System.out.println("one of them is null");
            }
        
        }
    	
        return list;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("you moved illegally" + e);
            ArrayList<Square> list = new ArrayList<>();
            return list;
        }
        catch(NullPointerException e){
            System.out.println("one besides you is null" + e);
            ArrayList<Square> list = new ArrayList<>();
            return list;
        }
    }
}