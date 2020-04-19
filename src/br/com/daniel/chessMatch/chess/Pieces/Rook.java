package br.com.daniel.chessMatch.chess.Pieces;

import br.com.daniel.chessMatch.boardgame.Board;
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

    @Override
    public boolean[][] possibleMoves() {
        return new boolean[getBoard().getRows()][getBoard().getColumns()];
    }
}
