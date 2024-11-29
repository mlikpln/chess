public class Queen extends ChessPiece { // класс фигуры ферзь

    public Queen(String color) { // конструктор, который принимает цвет
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

        // проверка, чтобы ферзь не передвинулся в свою же текущую позицию
        if (line == toLine && column == toColumn) {
            return false;
        }

        // проверка, что ферзь двигается по прямой линии или по диагонали
        int lineDiff = Math.abs(toLine - line);
        int columnDiff = Math.abs(toColumn - column);

        // проверка, что движение по прямой или по диагонали
        if (line == toLine || column == toColumn || lineDiff == columnDiff) {
            int lineStep = Integer.compare(toLine - line, 0);
            int colStep = Integer.compare(toColumn - column, 0);

            // Проверка на отсутствие других фигур на пути
            int currentLine = line + lineStep;
            int currentColumn = column + colStep;

            while (currentLine != toLine || currentColumn != toColumn) {
                if (chessBoard.board[currentLine][currentColumn] != null) {
                    return false;
                }

                currentLine += lineStep;
                currentColumn += colStep;
            }

            // проверка конечной клетки
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            return targetPiece == null || !targetPiece.getColor().equals(this.color);
        }

        return false;


    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}