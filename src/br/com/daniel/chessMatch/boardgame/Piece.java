package br.com.daniel.chessMatch.boardgame;

public class Piece {

    protected Position position;
    private Board board;


    public Piece(Board board) {
        this.board = board;
        //posiçao de uma peça recém-criada será nulo.
        position =  null;
    }

    protected Board getBoard() {
        return board;
    }
}
