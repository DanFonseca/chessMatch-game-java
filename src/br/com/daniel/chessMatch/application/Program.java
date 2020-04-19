package br.com.daniel.chessMatch.application;

import br.com.daniel.chessMatch.chess.ChessMatch;
import br.com.daniel.chessMatch.chess.ChessPosition;
import br.com.daniel.chessMatch.exceptions.ChessException;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ChessMatch chessMatch = new ChessMatch();
        while (true) {
            try {
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces());
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);
                chessMatch.performChessMove(source, target);
            } catch (ChessException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}


