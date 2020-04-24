package br.com.daniel.chessMatch.chess;

import br.com.daniel.chessMatch.boardgame.Board;
import br.com.daniel.chessMatch.boardgame.Piece;
import br.com.daniel.chessMatch.boardgame.Position;

public abstract class ChessPiece extends Piece {
    private Color color;
    private int moveCount;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }
    public ChessPosition getChessPosition() {
        return  ChessPosition.fromPosition(position);
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void increaseMoveCount() {
         moveCount++;
    }

    public void decreaseMoveCount() {
        moveCount--;
    }

    public Color getColor() {
        return color;
    }

    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p != null && p.getColor() != this.color;
    }

}
