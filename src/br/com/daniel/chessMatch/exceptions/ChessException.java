package br.com.daniel.chessMatch.exceptions;

import br.com.daniel.chessMatch.boardgame.Board;
import br.com.daniel.chessMatch.chess.ChessPosition;

public class ChessException extends BoardException {
    public ChessException(String msg){
        super(msg);
    }
}
