package br.com.daniel.chessMatch.chess.Pieces;

import br.com.daniel.chessMatch.boardgame.Board;
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
}
