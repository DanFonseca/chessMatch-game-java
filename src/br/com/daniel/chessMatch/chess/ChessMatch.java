package br.com.daniel.chessMatch.chess;

import br.com.daniel.chessMatch.boardgame.Board;
import br.com.daniel.chessMatch.boardgame.Piece;
import br.com.daniel.chessMatch.boardgame.Position;
import br.com.daniel.chessMatch.chess.Pieces.King;
import br.com.daniel.chessMatch.chess.Pieces.Rook;
import br.com.daniel.chessMatch.exceptions.ChessException;

public class ChessMatch {
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMatch;
    private ChessPiece enPassartVulnereble;
    private ChessPiece promoted;

    private Board board;



    public ChessMatch() {
        board = new Board(8, 8);
        InitialSetup ();
    }

    public Board getBoard() {
        return board;
    }


    public ChessPiece[][] getPieces() {

        ChessPiece[][] chessPieces = new ChessPiece[board.getRows()][board.getColumns()];

        for (int i = 0; i < chessPieces.length; i++) {
            for (int j=0; j < chessPieces[i].length; j++) {
                chessPieces[i][j] = (ChessPiece) board.getPiece(i, j);
            }
        }
        return chessPieces;
    }

/*  O método @placeNewPiece pega as coordenadas do Xadrez(ex: b1)
    e converte (utilizando ChessPosition)   em int para que possa ser alocada no vetor (utilizando
    @board.placePiece
 */
    private void placeNewPiece (char colum, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(colum, row).toPosition());
    }

    /*
        montando o tabuleiro na inicilizaçao do programa.
     */
    public void InitialSetup (){
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }

    /*
        montando o tabuleiro na inicilizaçao do programa.
     */
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);
        validateTargePosition(source, target);

        Piece capturedPiece = makeMovie (source, target);

        //downcast  (ChessPiece <- Piece)
        return (ChessPiece) capturedPiece;
    }

    /**
        @validateTargePosition: Este método verifica se da posiçao de partida até a posiçao
        que a peça chegará, será válida.
     **/
    private void validateTargePosition(Position source, Position target) {
        /**
            Verificaçao pegando o objeto @source e acessando este objeto o método
            @possibleMoves passando como parametro a variavel @target
         **/
        if(!board.getPiece(source).possibleMoves(target)){
            throw new ChessException("There is no possible moves for this chosen Piece.");
        }
    }


    private Piece makeMovie(Position source, Position target) {
        Piece capturedPiece = board.removePiece(target);
        Piece sourcePiece =  board.removePiece(source);

        board.placePiece(sourcePiece, target);

        return  capturedPiece;
    }

    private void validateSourcePosition(Position sourcePosition) {

        if(!board.thereIsAPiece(sourcePosition)){
            throw new  ChessException("There is no a Piece on this Position");
        }

        if(!board.getPiece(sourcePosition).isThereAnyPossibleMove()){
            throw new  ChessException("There is no possible moves for the chosen piece");
        }
    }

    public boolean [][] possibleMoves (ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        Piece p = board.getPiece(position);
        boolean mat [][] = p.possibleMoves();

       return mat;
    }


}