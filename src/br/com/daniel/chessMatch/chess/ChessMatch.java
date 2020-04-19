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

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePiece (sourcePosition);

        Piece capturedPiece = makeMovie (source, target);
        System.out.println(capturedPiece);


        //downcast ↓ (ChessPiece ← Piece)
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMovie(Position source, Position target) {
        Piece capturedPiece = board.removePiece(target);
        Piece sourcePiece =  board.removePiece(source);

        System.out.println("Target: " + capturedPiece);
        System.out.println("Source"+ sourcePiece);

        board.placePiece(sourcePiece, target);

        return  capturedPiece;
    }

    private void validateSourcePiece(ChessPosition sourcePosition) {
        if(!board.thereIsAPiece(sourcePosition.toPosition())){
            throw new  ChessException("There is no a Piece on this Position");
        }
    }

}