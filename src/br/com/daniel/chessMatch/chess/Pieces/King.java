package br.com.daniel.chessMatch.chess.Pieces;

import br.com.daniel.chessMatch.boardgame.Board;
import br.com.daniel.chessMatch.boardgame.Position;
import br.com.daniel.chessMatch.chess.ChessMatch;
import br.com.daniel.chessMatch.chess.ChessPiece;
import br.com.daniel.chessMatch.chess.Color;

public class King extends ChessPiece {
    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }


    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece)getBoard().getPiece(position);
        return p == null || p.getColor() != getColor();
    }

    private boolean testRookCastling(Position position) {
        ChessPiece p = (ChessPiece)getBoard().getPiece(position);
        return  p instanceof Rook && p != null && p.getColor() == getColor() && p.getMoveCount() == 0;
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



        // #specialmove castling
        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            // #specialmove castling kingside rook
            Position posT1 = new Position(position.getRow(), position.getColum() + 3);
            if (testRookCastling(posT1)) {
                Position p1 = new Position(position.getRow(), position.getColum() + 1);
                Position p2 = new Position(position.getRow(), position.getColum() + 2);
                if (getBoard().getPiece(p1) == null && getBoard().getPiece(p2) == null) {
                    mat[position.getRow()][position.getColum() + 2] = true;
                }
            }
            // #specialmove castling queenside rook
            Position posT2 = new Position(position.getRow(), position.getColum() - 4);
            if (testRookCastling(posT2)) {
                Position p1 = new Position(position.getRow(), position.getColum() - 1);
                Position p2 = new Position(position.getRow(), position.getColum() - 2);
                Position p3 = new Position(position.getRow(), position.getColum() - 3);
                if (getBoard().getPiece(p1) == null && getBoard().getPiece(p2) == null && getBoard().getPiece(p3) == null) {
                    mat[position.getRow()][position.getColum() - 2] = true;
                }
            }
        }

            return mat;
    }
}
