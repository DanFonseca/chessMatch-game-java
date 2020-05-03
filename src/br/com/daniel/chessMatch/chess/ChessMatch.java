package br.com.daniel.chessMatch.chess;

import br.com.daniel.chessMatch.boardgame.Board;
import br.com.daniel.chessMatch.boardgame.Piece;
import br.com.daniel.chessMatch.boardgame.Position;
import br.com.daniel.chessMatch.chess.Pieces.*;
import br.com.daniel.chessMatch.exceptions.ChessException;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;

    List<Piece> piecesOnTheBoard = new ArrayList<>();
    List<Piece> capturedPieces = new ArrayList<>();

    private Board board;


    public ChessMatch() {
        board = new Board(8, 8);
        currentPlayer = Color.WHITE;
        InitialSetup();
    }

    public ChessPiece getEnPassantVulnerable() {
        return enPassantVulnerable;
    }

    public ChessPiece getPromoted() {
        return promoted;
    }

    public Board getBoard() {
        return board;
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
    }

    public ChessPiece[][] getPieces() {

        ChessPiece[][] chessPieces = new ChessPiece[board.getRows()][board.getColumns()];

        for (int i = 0; i < chessPieces.length; i++) {
            for (int j = 0; j < chessPieces[i].length; j++) {
                chessPieces[i][j] = (ChessPiece) board.getPiece(i, j);
            }
        }
        return chessPieces;
    }

    /*  O método @placeNewPiece pega as coordenadas do Xadrez(ex: b1)
        e converte (utilizando ChessPosition)   em int para que possa ser alocada no vetor (utilizando
        @board.placePiece
     */
    private void placeNewPiece(char colum, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(colum, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    /*
        montando o tabuleiro na inicilizaçao do programa.
     */
    public void InitialSetup() {
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE, this));


        placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));

        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK, this));

    }

    /*
        montando o tabuleiro na inicilizaçao do programa.
     */
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);
        validateTargePosition(source, target);
        Piece capturedPiece = makeMovie(source, target);

        if (testeCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't Check your self");
        }

        ChessPiece movedPiece = (ChessPiece) board.getPiece(target);

        //#special move promoted
        promoted = null;
        if(movedPiece.getColor() == Color.WHITE && target.getRow() == 0 ||
        movedPiece.getColor() == Color.BLACK && target.getRow() == 7) {
            promoted = (ChessPiece) board.getPiece(target);
            promoted = replacePromotedPiece("Q");
        }

        check = testeCheck(getOpponent(currentPlayer));

        if (testCheckmate(getOpponent(currentPlayer))) {
            checkMate = true;
        } else {
            nextTurn();
        }

        // #specialMove en passant
        if (movedPiece instanceof Pawn && (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2)) {
            enPassantVulnerable = movedPiece;
        }
        else {
            enPassantVulnerable = null;
        }

        //downcast  (ChessPiece <- Piece)
        return (ChessPiece) capturedPiece;
    }

    public ChessPiece replacePromotedPiece(String type) {
        if(promoted == null){
            throw new IllegalStateException("Não há peça para ser promovida");
        }
        if(!type.equals("B") && !type.equals("R") && !type.equals("N") && !type.equals("Q")){
            throw new InvalidParameterException("Invalid type for promotion");
        }
        Position pos = promoted.getChessPosition().toPosition();
        Piece  p = board.removePiece(pos);
        piecesOnTheBoard.remove(p);

        ChessPiece newPiece = newPiece(type, promoted.getColor());
        board.placePiece(newPiece, pos);
        piecesOnTheBoard.add(newPiece);

        return newPiece;
    }

    public ChessPiece newPiece (String type, Color color){
        if(type.equals("B")) return new Bishop(board, color);
        if(type.equals("R")) return new Rook(board, color);
        if(type.equals("N")) return new Knight(board, color);
        return new Queen(board, color);
    }

    /**
     * @validateTargePosition: Este método verifica se da posiçao de partida até a posiçao
     * que a peça chegará, será válida.
     **/
    private void validateTargePosition(Position source, Position target) {
        /**
         Verificaçao pegando o objeto @source e acessando este objeto o método
         @possibleMoves passando como parametro a variavel @target
         **/
        if (!board.getPiece(source).possibleMoves(target)) {
            throw new ChessException("There is no possible moves for this chosen Piece.");
        }
    }


    private Piece makeMovie(Position source, Position target) {

        ChessPiece p = (ChessPiece)board.removePiece(source);

        p.increaseMoveCount();
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        // #specialmove castling kingside rook
        if (p instanceof King && target.getColum() == source.getColum() + 2) {
            Position sourceT = new Position(source.getRow(), source.getColum() + 3);
            Position targetT = new Position(source.getRow(), source.getColum() + 1);
            ChessPiece rook = (ChessPiece)board.removePiece(sourceT);
            board.placePiece(rook, targetT);
            rook.increaseMoveCount();
        }
        // #specialmove castling queenside rook
        if (p instanceof King && target.getColum() == source.getColum() - 2) {
            Position sourceT = new Position(source.getRow(), source.getColum() - 4);
            Position targetT = new Position(source.getRow(), source.getColum() - 1);
            ChessPiece rook = (ChessPiece)board.removePiece(sourceT);
            board.placePiece(rook, targetT);
            rook.increaseMoveCount();
        }

        // #specialmove en passant
        if (p instanceof Pawn) {
            if (source.getColum() != target.getColum() && capturedPiece == null) {
                Position pawnPosition;
                if (p.getColor() == Color.WHITE) {
                    pawnPosition = new Position(target.getRow() + 1, target.getColum());
                }
                else {
                    pawnPosition = new Position(target.getRow() - 1, target.getColum());
                }
                capturedPiece = board.removePiece(pawnPosition);
                capturedPieces.add(capturedPiece);
                piecesOnTheBoard.remove(capturedPiece);
            }
        }
        return capturedPiece;
    }

    public void undoMove(Position source, Position target, Piece capturedPiece) {
        ChessPiece p = (ChessPiece) board.removePiece(target);
        p.decreaseMoveCount();

        board.placePiece(p, source);

        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }

        //special move castling king side rook
        if (capturedPiece instanceof King && target.getColum() == source.getColum() + 2) {
            Position sourceT = new Position(source.getRow(), source.getColum() + 3);
            Position targetT = new Position(source.getRow(), source.getColum() + 1);
            ChessPiece rook = (ChessPiece) board.removePiece(targetT);
            board.placePiece(rook, sourceT);
            rook.decreaseMoveCount();
        }
        //special move castling queen side rook
        if (capturedPiece instanceof King && target.getColum() == source.getColum() - 2) {
            Position sourceT = new Position(source.getRow(), source.getColum() - 4);
            Position targetT = new Position(source.getRow(), source.getColum() - 1);
            ChessPiece rook = (ChessPiece) board.removePiece(targetT);
            board.placePiece(rook, sourceT);
            rook.decreaseMoveCount();
        }

        //special move en passant

        if (p instanceof Pawn) {
            if (source.getColum() != target.getColum() && capturedPiece == null) {
                ChessPiece pawn = (ChessPiece) capturedPiece;
                Position pawnsPosition;
                if (pawn.getColor() == Color.WHITE) {
                    pawnsPosition = new Position(3, target.getColum());

                } else {
                    pawnsPosition = new Position(4, target.getColum());
                }
                board.placePiece(pawn, pawnsPosition);
            }
        }
    }

    private Color getOpponent(Color color) {
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public boolean testCheckmate(Color color) {
        //verificando se a peça do adversário está em check, se não estiver, o método é interrompido por um return false.
        if (!testeCheck(color)) {
            return false;
        }
        //pega a peça do adversário e armazena em uma lista.
        List<Piece> pieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());

        //for para pegar cada peça "P" do adversário.
        for (Piece p : pieces) {
            //criado uma lista auxiliar para armazenar os possíveis movimentos de peça do adversario.
            boolean[][] mat = p.possibleMoves();

            for (int i = 0; i < getBoard().getRows(); i++) {
                for (int j = 0; j < getBoard().getColumns(); j++) {
                    //se em uma determinada posiçao I, J tiver um possível movimento é feita a verificaçao.
                    if (mat[i][j]) {
                        //a instancia target é representada para a Position distino, na qual a peça ira se deslocar.
                        Position target = new Position(i, j);
                        //a position da peça do adversário é instanciada como source.
                        Position source = ((ChessPiece) p).getChessPosition().toPosition();
                        //após fazer o movimento, o método retorna a peça capturada.
                        Piece capturedPiece = makeMovie(source, target);
                        //após o movimento, é verificado se a peça do adversario ainda está em check
                        boolean check = testeCheck(color);
                        //depois de armazenar o resultado em uma variavel, o movimento é desfeito.
                        undoMove(source, target, capturedPiece);
                        //se existir uma movimentaçao possivel que tire do check, então é retornando false.
                        //pois o jogador nao esta em CHECKMATE
                        if (!check) {
                            return false;
                        }
                    }
                }
            }
        }
        //se nenhuma peça escapar do CHECK, então retorna true, pois o jogador entrou em CHECKMATE.
        return true;
    }

    private ChessPiece king(Color color) {
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list) {
            if (p instanceof King) {
                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("There is no " + color + " king on the board");
    }

    private boolean testeCheck(Color color) {
        List<Piece> opponents = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == getOpponent(color)).collect(Collectors.toList());
        Position kingPosition = king(color).getChessPosition().toPosition();


        for (Piece p : opponents) {
            boolean[][] mat = p.possibleMoves();
            if (mat[kingPosition.getRow()][kingPosition.getColum()]) {
                return true;
            }
        }

        return false;
    }

    private void validateSourcePosition(Position sourcePosition) {

        if (!board.thereIsAPiece(sourcePosition)) {
            throw new ChessException("There is no a Piece on this Position");
        }
        ChessPiece p = (ChessPiece) board.getPiece(sourcePosition);

        if (currentPlayer != p.getColor()) {
            throw new ChessException("The chosne Piece is not yours");
        }

        if (!board.getPiece(sourcePosition).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible moves for the chosen piece");
        }
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        Piece p = board.getPiece(position);
        boolean mat[][] = p.possibleMoves();

        return mat;
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE);
    }

}