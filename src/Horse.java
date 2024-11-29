public class Horse extends ChessPiece { // класс фигуры конь

    public Horse(String color) { // конструктор, который принимает цвет
        super(color);
    }

    @Override
    public String getColor() { // получение цвета фигуры
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверка, чтобы конь не выходил за пределы доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) || !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // проверка, чтобы конь не передвинулся в свою же текущую позицию
        if (line == toLine && column == toColumn) {
            return false;
        }

        // разница в позициях
        int lineDiff = Math.abs(toLine - line);
        int columnDiff = Math.abs(toColumn - column);

        //проверка, что конь ходит буквой г
        if ((lineDiff == 2 && columnDiff == 1) || (lineDiff == 1 && columnDiff == 2)) {
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];

            // проверка, что клетка или пуста или там фигура противника
            return targetPiece == null || !targetPiece.getColor().equals(this.color);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "H"; // символ для коня
    }