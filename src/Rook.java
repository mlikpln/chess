public class Rook extends ChessPiece { // класс фигуры ладья

    public Rook(String color) { // конструктор, который принимает цвет
        super(color);
    }

    @Override
    public String getColor() { // получение цвета фигуры
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // проверка, чтобы ладья не выходила за пределы доски
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // проверка, чтобы ладья не передвинулась в свою же текущую позицию
        if (line == toLine && column == toColumn) {
            return false;
        }

        // проверка, что ладья двигается по прямой линии (горизонтально или вертикально)
        if (line != toLine && column != toColumn) {
            return false; // движение не по прямой
        }

        // проверка, что ладья не пересекает другие фигуры на пути
        if (line == toLine) { // движение по горизонтали
            int columnDirection = (toColumn > column) ? 1 : -1;

            // проверка каждой клетки на пути ладьи по горизонтали
            for (int c = column + columnDirection; c != toColumn; c += columnDirection) {
                if (chessBoard.board[line][c] != null) {
                    return false;
                }
            }
        } else { // движение по вертикали
            int lineDirection = (toLine > line) ? 1 : -1;

            // проверка каждой клетки на пути ладьи по вертикали
            for (int r = line + lineDirection; r != toLine; r += lineDirection) {
                if (chessBoard.board[r][column] != null) {
                    return false;
                }
            }
        }

        // проверка целевой клетки: должна быть пустой или на ней должна стоять фигура противника
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        return targetPiece == null || !targetPiece.getColor().equals(this.color);
    }

    @Override
    public String getSymbol() { // символ для ладьи
        return "R";
    }
}