public class King extends ChessPiece { // класс фигуры король

    public King(String color) { // конструктор, который принимает цвет
        super(color);
    }

    @Override
    public String getColor() { // получение цвета фигуры
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверка выхода за пределы доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // проверка, чтобы король не передвинулся в свою же текущую позицию
        if (line == toLine && column == toColumn) {
            return false;
        }

        // проверка, что король двигается на одну клетку в любом направлении
        int lineDiff = Math.abs(toLine - line);
        int columnDiff = Math.abs(toColumn - column);

        if (lineDiff <= 1 && columnDiff <= 1) {
            // проверка, что клетка пуста или на ней фигура противника
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            return targetPiece == null || !targetPiece.getColor().equals(this.color);
        }

        return false;
    }

    @Override
    public String getSymbol() { // символ для короля
        return "K";
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        for (int i = 0; i < chessBoard.board.length; i++) {
            for (int j = 0; j < chessBoard.board[i].length; j++) {
                ChessPiece piece = chessBoard.board[i][j];

                if (piece != null && !piece.getColor().equals(this.color)) {
                    if (piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }

        return false; // если король не под атакой
    }
}