package br.com.daniel.chessMatch.boardgame;

public class Board {
    private int rows;
    private int columns;

    private Piece pieces [][];

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.pieces = new Piece [rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Piece getPiece (int row, int columns){
        return this.pieces[row][columns];
    }

    public Piece getPiece (Position position){
        return this.pieces[position.getRow()][position.getColum()];
    }
    public void placePiece (Piece piece, Position position){
        this.pieces[position.getRow()] [position.getColum()] = piece;
    }
}
