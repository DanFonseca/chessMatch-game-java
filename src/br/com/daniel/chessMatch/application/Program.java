package br.com.daniel.chessMatch.application;
import br.com.daniel.chessMatch.chess.ChessMatch;

public class Program {
    public static void main(String[] args) {
        ChessMatch chessMatch = new ChessMatch();
        UI.printBoard(chessMatch.getPieces());
    }
}


