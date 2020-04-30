package br.com.daniel.chessMatch.boardgame;

public abstract class Piece {

    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
        //posiçao de uma peça recém-criada será nulo.
        position = null;
    }

    protected Board getBoard() {
        return board;
    }



    /*
        Método abstrado para que as classes especializadas implementem.
     */
    public abstract boolean [][] possibleMoves();
    /**
        Método concreeto que recebe uma determinada @Position
        Este método irá chamar a classe abstrata, utirlizando a técnica de Hook Method
     **/
    public boolean possibleMoves(Position position) {
        return possibleMoves()[position.getRow()][position.getColum()];
    }

    /*
        Verifica os movimentos possiveis de cada Peça.
     */
    public boolean isThereAnyPossibleMove() {

        boolean mat[][] = possibleMoves();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j]) {
                    return true;
                }
            }
        }

        return false;
    }
}
