package br.com.daniel.chessMatch.application;

import br.com.daniel.chessMatch.boardgame.Piece;
import br.com.daniel.chessMatch.chess.ChessMatch;
import br.com.daniel.chessMatch.chess.ChessPiece;

public class UI {


    public static void prinBoard(ChessPiece[][] chessPiece) {

        for(int i=0; i < chessPiece.length; i++){
            System.out.print((8-i) + " ");
            for (int j = 0; j < chessPiece[i].length; j++){
                printPiece( chessPiece[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printPiece (ChessPiece piece){
        if(piece == null){
            System.out.print("-");
        }else {
            System.out.print(piece);
        }
        System.out.print(" ");
    }
}