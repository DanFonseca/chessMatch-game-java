package br.com.daniel.chessMatch.chess.Pieces;

import br.com.daniel.chessMatch.boardgame.Board;
import br.com.daniel.chessMatch.boardgame.Position;
import br.com.daniel.chessMatch.chess.ChessPiece;
import br.com.daniel.chessMatch.chess.Color;

public class Bishop extends ChessPiece {
    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        Position  p = new Position(0,0);
        boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];


        //NorthEast
        p.setValues(position.getRow()-1, position.getColum()+1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
            p.setValues(p.getRow()-1, p.getColum()+1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
        }

        //NorthWest
        p.setValues(position.getRow()-1, position.getColum()-1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
            p.setValues(p.getRow()-1, p.getColum()-1);
        }

        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getRow()] = true;
        }

        //SouthEast
        p.setValues(position.getRow()+1, position.getColum()+1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
            p.setValues(p.getRow()+1, p.getColum()+1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
        }

        //SouthEast
        p.setValues(position.getRow()+1, position.getColum()-1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
            p.setValues(p.getRow()+1, p.getColum()-1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
        }

        return mat;
    }

    @Override
    public String toString() {
        return "B";
    }
}
