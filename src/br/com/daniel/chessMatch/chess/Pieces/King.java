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


    public boolean canMove (Position position){
           ChessPiece p = (ChessPiece)getBoard().getPiece(position);
           return p == null || p.getColor() != getColor();
    }

    public boolean testRockCastling (Position position){
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
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

        // Specialmove Castling
        if(getMoveCount() == 0 && !chessMatch.getCheck()){
            //Specialmove Castling kingside rook
            Position posT1 = new Position(position.getRow(), position.getColum()+3);
            if(testRockCastling(posT1)){
                Position P1 = new Position(position.getRow(), position.getColum() +1);
                Position P2 = new Position(position.getRow(), position.getColum()+2);
                if(getBoard().getPiece(P1) == null && getBoard().getPiece(P2) == null){
                    mat[position.getRow()][position.getColum()+2] = true;
                }
            }
        }

        // Specialmove Castling queenside rook
        if(getMoveCount() == 0 && !chessMatch.getCheck()){
            //Specialmove Castling kingside rook
            Position posT1 = new Position(position.getRow(), position.getColum()-4);
            if(testRockCastling(posT1)){
                Position P1 = new Position(position.getRow(), position.getColum() -1);
                Position P2 = new Position(position.getRow(), position.getColum()-2);
                Position P3 = new Position(position.getRow(), position.getColum()-3);
                if(getBoard().getPiece(P1) == null && getBoard().getPiece(P2) == null
                && getBoard().getPiece(P3) == null){
                    mat[position.getRow()][position.getColum()-2] = true;
                }
            }
        }





            return mat;
    }
}
