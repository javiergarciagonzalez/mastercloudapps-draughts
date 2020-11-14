package usantatecla.draughts.models;

import java.util.Arrays;


public class GameBuilder {

    public Game build(String... rows) {
        assert rows.length == 8;

        Board board = new Board();
        Game game = new Game(board);

        Error error;

        int index = 0;
        for (String row : rows) {
            error = this.setRow(board, row, index);
            if (error != null) {
                return null;
            }
            index++;
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
