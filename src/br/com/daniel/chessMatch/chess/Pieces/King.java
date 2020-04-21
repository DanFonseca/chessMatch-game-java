package br.com.daniel.chessMatch.chess.Pieces;

import br.com.daniel.chessMatch.boardgame.Board;
import br.com.daniel.chessMatch.boardgame.Position;
import br.com.daniel.chessMatch.chess.ChessPiece;
import br.com.daniel.chessMatch.chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    public boolean canMove (Position position){
           ChessPiece p = (ChessPiece)getBoard().getPiece(position);
           return p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {

        boolean [][] mat  = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0,0);

        //above
        p.setValues(position.getRow()-1, position.getColum());
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColum()] = true;
        }

        //below
        p.setValues(position.getRow()+1, position.getColum());
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColum()]= true;
        }

        //right
        p.setValues(position.getRow(), position.getColum()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColum()] = true;
        }

        //left
        p.setValues(position.getRow(), position.getColum()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColum()] = true;
        }


        //norhtWest
        p.setValues(position.getRow()-1, position.getColum()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColum()] = true;
        }

        //northEast
        p.setValues(position.getRow()-1, position.getColum()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColum()] = true;
        }

        //south-west
        p.setValues(position.getRow()+1, position.getColum()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColum()]= true;
        }

        //south-east
        p.setValues(position.getRow()+1, position.getColum()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColum()]= true;
        }
            return mat;
    }
}
