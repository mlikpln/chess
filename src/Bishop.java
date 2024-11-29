public class Bishop extends ChessPiece { // класс фигуры слон

    public Bishop(String color) { // конструктор, который принимает цвет
        super(color);
    }

    @Override
    public String getColor() { // получение цвета фигуры
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверка, чтобы слон не выходил за пределы доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // проверка, чтобы слон не передвинулся в свою же текущую позицию
        if (line == toLine && column == toColumn) {
            return false;
        }

        // проверка, что слон двигается по диагонали
        int lineDiff = Math.abs(toLine - line);
        int columnDiff = Math.abs(toColumn - column);

        // если разница по строкам и столбцам не одинаковая, значит движение не по диагонали
        if (lineDiff != columnDiff) {
            return false;
        }

        // проверка, что слон не пересекает другие фигуры на пути
        int lineDirection = (toLine > line) ? 1 : -1;  // определяем направление по строкам
        int columnDirection = (toColumn > column) ? 1 : -1;  // определяем направление по столбцам

        // проверка каждой клетки на пути слона
        while (line != toLine && column != toColumn) {
            line += lineDirection;
            column += columnDirection;

            // если на пути встречается фигура, возвращаем false
            if (chessBoard.board[line][column] != null) {
                return false;
            }
        }

        // проверка целевой клетки, должна быть пустой или с фигурой противника
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        return targetPiece == null || !targetPiece.getColor().equals(this.color);
    }

    @Override
    public String getSymbol() { // символ для слона
        return "B";
    }
}