package br.com.daniel.chessMatch.application;

import br.com.daniel.chessMatch.chess.ChessMatch;
import br.com.daniel.chessMatch.chess.ChessPiece;
import br.com.daniel.chessMatch.chess.ChessPosition;
import br.com.daniel.chessMatch.chess.Color;
import br.com.daniel.chessMatch.exceptions.ChessException;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";


    public static ChessPosition readChessPosition(Scanner sc) {
        try {
            String s = sc.next();
            char colum = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(colum, row);
        } catch (RuntimeException e) {
            throw new ChessException("Error instantiating ChessPosition: Position must be a1 to h8.");
        }
    }

    public static void PrintMatch(ChessMatch chessMatch, List<ChessPiece> pieces) {
        printBoard(chessMatch.getPieces());
        PrintCapturedPieces(pieces);
        System.out.println("Turn: " + chessMatch.getTurn());
        System.out.println("Current Player: " + chessMatch.getCurrentPlayer());

        if (chessMatch.getCheck()) System.out.println("CHECK!");
    }

    public static void PrintCapturedPieces(List<ChessPiece> pieces) {
        ArrayList<ChessPiece> white = pieces.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<ChessPiece> black = pieces.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toCollection(ArrayList::new));
        System.out.println("Captured: ");
        System.out.println(ANSI_WHITE + "White: " + Arrays.toString(white.toArray()) + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Black: " + Arrays.toString(black.toArray()) + ANSI_RESET);
    }

    public static void printBoard(ChessPiece[][] chessPiece) {
        System.out.println("  a b c d e f g h");
        for (int i = 0; i < chessPiece.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < chessPiece[i].length; j++) {
                printPiece(chessPiece[i][j], false);
            }
            System.out.print((8 - i) + " ");
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printBoard(ChessPiece[][] chessPiece, boolean[][] possibleMoves) {
        System.out.println("  a b c d e f g h");
        for (int i = 0; i < chessPiece.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < chessPiece[i].length; j++) {
                printPiece(chessPiece[i][j], possibleMoves[i][j]);
            }
            System.out.print((8 - i) + " ");
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }


    public static void printPiece(ChessPiece piece, boolean possibleMovie) {
        if (possibleMovie) {
            System.out.print(ANSI_RED_BACKGROUND);
        }
        if (piece == null) {
            System.out.print("-" + ANSI_RESET);

        } else if (piece.getColor() == Color.WHITE) {

            System.out.print(ANSI_WHITE + piece + ANSI_RESET);

        } else {

            System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
        }
        System.out.print(" ");
    }


    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}

