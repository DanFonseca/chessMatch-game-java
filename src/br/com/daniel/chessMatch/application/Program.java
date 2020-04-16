package br.com.daniel.chessMatch.application;
import br.com.daniel.chessMatch.chess.ChessMatch;
import br.com.daniel.chessMatch.chess.ChessPiece;

public class Program {

    public static void main(String[] args) {
        ChessMatch chessMatch = new ChessMatch();
        UI.prinBoard(chessMatch.getPieces());

    }
}
