public class Pawn extends ChessPiece { // класс фигуры пешка

    public Pawn(String color) { // конструктор, который принимает цвет
        super(color);
    }

    @Override
    public String getColor() { // получение цвета фигуры
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверка, чтобы пешка не выходила за пределы доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) || !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // проверка, чтобы пешка не передвинулась в свою же текущую позицию
        if (line == toLine && column == toColumn) {
            return false;
        }

        int direction = (this.color.equals("White")) ? 1 : -1; // направление движения для белой (1) и черной (-1) пешки

        // проверка для обычного хода на одну клетку вперед
        if (column == toColumn && toLine == line + direction) {
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            return targetPiece == null;
        }

        // проверка для первого хода (пешка может пойти на 2 клетки вперед)
        if (column == toColumn && line == (this.color.equals("White") ? 1 : 6) && toLine == line + (2 * direction)) {
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            ChessPiece intermediatePiece = chessBoard.board[line + direction][column];
            return targetPiece == null && intermediatePiece == null;
        }

        // проверка для взятия по диагонали
        if (Math.abs(column - toColumn) == 1 && toLine == line + direction) {
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            return targetPiece != null && !targetPiece.getColor().equals(this.color);
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "P"; // символ для пешки
    }
}