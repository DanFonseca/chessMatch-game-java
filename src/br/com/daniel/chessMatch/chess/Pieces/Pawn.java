package br.com.daniel.chessMatch.chess.Pieces;

import br.com.daniel.chessMatch.boardgame.Board;
import br.com.daniel.chessMatch.boardgame.Position;
import br.com.daniel.chessMatch.chess.ChessPiece;
import br.com.daniel.chessMatch.chess.Color;

public class Pawn extends ChessPiece {

    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {

        boolean [][] mat  = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0,0);

        if(getColor() == Color.WHITE){

            p.setValues(position.getRow()-1, position.getColum());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
                mat[p.getRow()][p.getColum()] = true;
            }

            p.setValues(position.getRow()-2, position.getColum());
            Position p2 = new Position(position.getRow()-1, position.getColum());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0){
                mat[p.getRow()][p.getColum()] = true;
            }

            p.setValues(position.getRow()-1, position.getColum()-1);
            if(getBoard().positionExists(p)  && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColum()] = true;
            }

            p.setValues(position.getRow()-1, position.getColum()+1);
            if(getBoard().positionExists(p)  && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColum()] = true;
            }

        }else{
            p.setValues(position.getRow()+1, position.getColum());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
                mat[p.getRow()][p.getColum()] = true;
            }

            p.setValues(position.getRow()+2, position.getColum());
            Position p2 = new Position(position.getRow()+1, position.getColum());
            if(getBoard().positionExists(p)  && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0){
                mat[p.getRow()][p.getColum()] = true;
            }

            p.setValues(position.getRow()+1, position.getColum()-1);
            if(getBoard().positionExists(p)  && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColum()] = true;
            }

            p.setValues(position.getRow()+1, position.getColum()+1);
            if(getBoard().positionExists(p)  && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColum()] = true;
            }

        }

        return mat;
    }

    @Override
    public String toString() {
        return  "P";
    }
}
