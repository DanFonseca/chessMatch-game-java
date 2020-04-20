package br.com.daniel.chessMatch.chess.Pieces;

import br.com.daniel.chessMatch.boardgame.Board;
import br.com.daniel.chessMatch.boardgame.Position;
import br.com.daniel.chessMatch.chess.ChessPiece;
import br.com.daniel.chessMatch.chess.Color;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }
    /*
        Método @possibleMoves, representa as movimentações possóveis de uma Torre.
     */
    @Override
    public boolean[][] possibleMoves() {
        /*
         Instanciamento de um vetor auxiliar que receberá a quantidade
         de linhas e colunas da classe board.
     */
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        /*
         Instanciamento de um Position auxiliar, que servirá para contas as posiçoes possiveis.
     */
        Position p = new Position(0,0);


        //above
        p.setValues(position.getRow() -1, position.getColum());

        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
            p.setRow(p.getRow()-1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
        }

        //below
        p.setValues(position.getRow() +1, position.getColum());

        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
            p.setRow(p.getRow()+1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColum()] = true;
        }

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

        return mat;
    }


}
