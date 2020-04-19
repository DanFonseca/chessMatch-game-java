package br.com.daniel.chessMatch.boardgame;

import br.com.daniel.chessMatch.exceptions.BoardException;

public class Board {
    private int rows;
    private int columns;

    private Piece pieces [][];

    public Board(int rows, int columns) {
        if(rows <0 || columns <0){
            throw new BoardException("Error creating board: there mus be least 1 row and 1 column");
        }
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

    public Piece getPiece (Position position){
        if(!positionExists(position)){
            throw new BoardException("The position not Exist. " + position);
        }
        return this.pieces[position.getRow()][position.getColum()];
    }

    public Piece getPiece (int row, int column){
        if(!positionExists(row, column)){
            throw new BoardException("The position not Exist. " + row + "," + column);
        }
        return this.pieces[row][column];
    }

    public Piece removePiece(Position position){
        positionExists(position);
        if(!thereIsAPiece(position)) return null;

        Piece aux = this.getPiece(position);
        aux.position = null;
        pieces[position.getRow()] [position.getColum()] = null;

        return aux;
    }

    public boolean positionExists (Position position){
        return positionExists(position.getRow(), position.getColum());
    }

    public boolean positionExists (int row, int colum){
        return  colum >= 0 && colum < columns && rows >=0 && row < rows;
    }

    public boolean thereIsAPiece (Position position){
        if(!positionExists(position)){
            throw new BoardException("The position not Exist. " + position);
        }
        return  getPiece(position) != null;
    }

    public void placePiece (Piece piece, Position position){
        if(thereIsAPiece(position)){
            throw new BoardException(" Error: Already exists this Piece on this position: " + position);
        }
        this.pieces[position.getRow()] [position.getColum()] = piece;
    }
}
