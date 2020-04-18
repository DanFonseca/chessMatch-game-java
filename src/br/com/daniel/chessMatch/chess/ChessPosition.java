package br.com.daniel.chessMatch.chess;

import br.com.daniel.chessMatch.boardgame.Position;
import br.com.daniel.chessMatch.exceptions.ChessException;

public class ChessPosition {
    int row;
    char colum;

    public ChessPosition (char colum, int row){

        if(colum < 'a' || colum > 'h' || row < 1 || row > 8){
            throw new ChessException("Error instantiating ChessPosition: Position must be a1 to h8.");
        }
        this.row = row;
        this.colum = colum;
    }

    public int getRow() {
        return row;
    }

    public char getColum() {
        return colum;
    }

    protected Position toPosition (){
        return new Position(8-this.row, this.colum-'a');
    }

    protected static ChessPosition fromPosition (Position position){
        return new ChessPosition((char) position.getColum(), 8 - position.getRow());
    }

    @Override
    public String toString() {
        return " " + colum + row;
    }
}
