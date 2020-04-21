package br.com.daniel.chessMatch.boardgame;

import br.com.daniel.chessMatch.exceptions.BoardException;

//Representação de um tabuleiro.

public class Board {
    private int rows;
    private int columns;

    private Piece pieces [][];

    //@Board retorna um tabuleiro.
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

    //Retorna uma peça de uma determinada @Position
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

    //Remove uma peça do tabuleiro em uma determinada @Position.
    public Piece removePiece(Position position){
        positionExists(position);
        if(!thereIsAPiece(position)) return null;

        Piece aux = this.getPiece(position);
        aux.position = null;
        pieces[position.getRow()] [position.getColum()] = null;

        return aux;
    }

    //Verifica se em uma determinada @Position existe uma posiçao válida.
    public boolean positionExists (Position position){
        return positionExists(position.getRow(), position.getColum());
    }

    public boolean positionExists (int row, int colum){
        return (row >=0 && colum >=0) && (row < rows && colum < columns);
    }

    //Verifica se em uma determinada @Position existe uma peça.
    public boolean thereIsAPiece (Position position){
        if(!positionExists(position)){
            throw new BoardException("The position not Exist. " + position);
        }
        return  getPiece(position) != null;
    }

    /**
    * @Piece: Recebe uma peça para ser alocada
    * @Position: Recepe uma Posiçao na qual a peça será alocada.
    * @placePiece: método que monta um tabuleiro.
     **/
    public void placePiece (Piece piece, Position position){
        /**
            Se houve uma peça, retorna um erro, pois não pode haver
            uma mesma peça na mesma posiçao no momendo da montagem do tabuleiro.
         **/

        if(thereIsAPiece(position)){
            throw new BoardException(" Error: Already exists this Piece on this position: " + position);
        }

        this.pieces[position.getRow()] [position.getColum()] = piece;
        piece.position = position;
    }
}
