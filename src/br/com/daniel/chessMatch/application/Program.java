package br.com.daniel.chessMatch.application;

import br.com.daniel.chessMatch.chess.ChessMatch;
import br.com.daniel.chessMatch.chess.ChessPiece;
import br.com.daniel.chessMatch.chess.ChessPosition;
import br.com.daniel.chessMatch.exceptions.ChessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<ChessPiece> capturedPieces = new ArrayList<>();
        ChessPiece captured;
        ChessMatch chessMatch = new ChessMatch();

        while (true) {
            try {
                UI.clearScreen();
                UI.PrintMatch(chessMatch, capturedPieces);

                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);
                UI.printBoard(chessMatch.getPieces(), chessMatch.possibleMoves(source));

                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);
                captured = chessMatch.performChessMove(source, target);

                if(captured != null){
                    capturedPieces.add(captured);
                }

            } catch (ChessException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}


