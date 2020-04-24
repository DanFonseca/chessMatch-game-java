package br.com.daniel.chessMatch.chess.Pieces;

import br.com.daniel.chessMatch.boardgame.Board;
import br.com.daniel.chessMatch.boardgame.Position;
import br.com.daniel.chessMatch.chess.ChessPiece;
import br.com.daniel.chessMatch.chess.Color;

public class Queen extends ChessPiece {
    public Queen(Board board, Color color) {
        super(board, color);
    }

    public boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p == null || p.getColor() != getColor();
    }


    @Override
    public boolean[][] possibleMoves() {
        Position p = new Position(0,0);
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        //left
        p.setValues(position.getRow(), position.getColum()-1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
            p.setColum(p.getColum()-1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
        }

        //right
        p.setValues(position.getRow(), position.getColum()+1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
            p.setColum(p.getColum()+1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
        }

        //up
        p.setValues(position.getRow()-1, position.getColum());
        while (getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColum()] = true;
            p.setRow(p.getRow()-1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
        }

        //down
        p.setValues(position.getRow()+1, position.getColum());
        while (getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColum()] = true;
            p.setRow(p.getRow()+1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
        }

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
        return "Q";
    }
}
