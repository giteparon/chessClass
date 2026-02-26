package com.example;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img; 
    private boolean hasMoved;
    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;
         
        try {
            if (this.img == null) {
                this.img = ImageIO.read(new File(System.getProperty("user.dir")+img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }
    public String getPieceName(){
        for(int i = 0; i < this.img.toString().length(); i++){
            if(this.img.toString().charAt(i) == '.'){
                return this.img.toString().substring(0, i);
            }
        }
        return null;
    }
    

    
    public boolean getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }
    
    //precondition: g and currentSquare must be on-null valid objects.
    //postcondition: the image stored in the img property of this object is drawn to the screen.
    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
        g.drawImage(this.img, x, y, null);
    }
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
        int col = start.getCol();
        int row = start.getRow();
        int color;
        ArrayList<Square> list = new ArrayList<>();

        if(this.color == true){
            color = 1;
        }
        else{
            color = -1;
        }
        if(board[row + 1][col + color].getOccupyingPiece() != null){
            list.add(board[row + 1][col + color]);
        }
        else if(board[row - 1][col + color].getOccupyingPiece() != null){
            list.add(board[row - 1][col + color]);
        }
        
        
        
        return list;
        
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    
    public ArrayList<Square> getLegalMoves(Board b, Square start){
        try {
        ArrayList<Square> list = new ArrayList<>();
        Square[][] sq = b.getSquareArray();
        int multiplyColor = -1;
        int enPassantRow = 3;
        if(color){
            multiplyColor = 1;
            enPassantRow++;
        }
        if(!sq[start.getRow() + multiplyColor][start.getCol()].isOccupied()){
            if(start.getRow() != 0 && start.getRow()!= 7){
                if(start.getRow() == 6 && !color){
                    list.add(sq[start.getRow() + (2 * multiplyColor)][start.getCol()]);
                }
                if(start.getRow() == 1 && color){
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
            if(start.getCol != 0 && start.getCol() != 7){
                if( sq[start.getRow()][start.getCol() + 1].getOccupyingPiece().getPieceName().contains("pawn") && ){
                
                }
            }
        }
    	
        return list;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("you moved illegally" + e);
            ArrayList<Square> list = new ArrayList<>();
            return list;
        }
    }
}