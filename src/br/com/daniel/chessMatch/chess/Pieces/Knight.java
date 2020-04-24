package br.com.daniel.chessMatch.chess.Pieces;

import br.com.daniel.chessMatch.boardgame.Board;
import br.com.daniel.chessMatch.boardgame.Position;
import br.com.daniel.chessMatch.chess.ChessPiece;
import br.com.daniel.chessMatch.chess.Color;

public class Knight extends ChessPiece {
    public Knight(Board board, Color color) {
        super(board, color);
    }

    public boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getColumns()][getBoard().getRows()];
        Position p = new Position(0, 0);

        //left-up
        p.setValues(position.getRow() - 1, position.getColum() - 2);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColum()] = true;
        }

        //left-down
        p.setValues(position.getRow() + 1, position.getColum() - 2);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColum()] = true;
        }


        //Right-up

        p.setValues(position.getRow() - 1, position.getColum() + 2);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColum()] = true;
        }

        //Right-down

        p.setValues(position.getRow() + 1, position.getColum() + 2);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColum()] = true;
        }

        //down-left
        p.setValues(position.getRow() + 2, position.getColum() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColum()] = true;
        }


        //down-right
        p.setValues(position.getRow() + 2, position.getColum() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColum()] = true;
        }


        //up-right
        p.setValues(position.getRow() - 2, position.getColum() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColum()] = true;
        }

        //up-left
        p.setValues(position.getRow() - 2, position.getColum() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColum()] = true;
        }


        return mat;
    }

    @Override
    public String toString() {
        return "N";
    }
}
