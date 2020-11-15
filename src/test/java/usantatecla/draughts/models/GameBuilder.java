package usantatecla.draughts.models;

import java.util.Arrays;

public class GameBuilder {

    String[] defaultRows = {
    " n n n n",
    "n n n n ",
    " n n n n",
    "        ",
    "        ",
    "b b b b ",
    " b b b b",
    "b b b b "};

    public Game build(String... rows) {
        if (rows.length == 0) {
            rows = defaultRows;
        }

        assert rows.length == 8;

        Board board = new Board();
        Game game = new Game(board);
        Error error;

        for (int i = 0; i < rows.length; i++) {
            error = this.setRow(board, rows[i], i);
            if (error != null) {
                return null;
            }
        }

        return game;
    }

    private Error setRow(Board board, String row, int rowPosition) {
        for (int j = 0; j < row.length(); j++) {
            if (!this.isCorrectCharacter(row.charAt(j))) {
                return Error.BAD_FORMAT;
            }

            Color color = getColor(row.charAt(j));
            if (color != null) {
                Piece piece = Character.isUpperCase(row.charAt(j)) ? new Draught(color) : new Pawn(color);
                board.put(new Coordinate(rowPosition, j), piece);
            }
        }

        return null;
    }

    private boolean isCorrectCharacter(char character) {
        Character[] values = {' ','B','b','n', 'N'};
        return Arrays.asList(values).contains(character);
    }

    private Color getColor(char color) {
        switch (color) {
        case 'N':
        case 'n':
            return Color.BLACK;
        case 'B':
        case 'b':
            return Color.WHITE;
        default:
            return null;
        }
    }
}
