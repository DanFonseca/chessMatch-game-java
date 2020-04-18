package br.com.daniel.chessMatch.chess;

import br.com.daniel.chessMatch.boardgame.Board;
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

/*  O método @placeNewPiece pega as coordenadas do Xadrez(ex: b1)
    e converte (utilizando ChessPosition)   em int para que possa ser alocada no vetor (utilizando
    @board.placePiece
 */
    private void placeNewPiece (char colum, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(colum, row).toPosition());
    }

    public void InitialSetup (){
        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 3, new Rook(board, Color.BLACK));
    }

}