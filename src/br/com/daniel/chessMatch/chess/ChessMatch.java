package br.com.daniel.chessMatch.chess;

import br.com.daniel.chessMatch.boardgame.Board;
import br.com.daniel.chessMatch.boardgame.Position;
import br.com.daniel.chessMatch.chess.Pieces.King;
import br.com.daniel.chessMatch.chess.Pieces.Rook;

public class ChessMatch {
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMatch;
    private ChessPiece enPassartVulnereble;
    private ChessPiece promoted;

    private Board board;



    public ChessMatch() {
        board = new Board(8, 8);
        InitialSetup ();
    }

    public Board getBoard() {
        return board;
    }


    public ChessPiece[][] getPieces() {

        ChessPiece[][] chessPieces = new ChessPiece[board.getRows()][board.getColumns()];

        for (int i = 0; i < chessPieces.length; i++) {
            for (int j=0; j < chessPieces[i].length; j++) {
                chessPieces[i][j] = (ChessPiece) board.getPiece(i, j);
            }
        }
        return chessPieces;
    }

    public void InitialSetup (){
        board.placePiece(new Rook(board, Color.BLACK), new Position(0,2));
        board.placePiece(new King(board, Color.WHITE), new Position(0,1));
        board.placePiece(new King(board, Color.WHITE), new Position(0,1));

    }

}