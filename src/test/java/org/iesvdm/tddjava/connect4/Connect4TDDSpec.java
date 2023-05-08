package org.iesvdm.tddjava.connect4;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.stream.IntStream;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class Connect4TDDSpec {

    private Connect4TDD tested;

    private OutputStream output;

    @BeforeEach
    public void beforeEachTest() {
        output = new ByteArrayOutputStream();
        tested = new Connect4TDD(new PrintStream(output));
    }

    /*
     * The board is composed by 7 horizontal and 6 vertical empty positions
     */

    @Test
    public void whenTheGameStartsTheBoardIsEmpty() {

        assertThat(tested.getNumberOfDiscs()).isEqualTo(0);
    }

    /*
     * Players introduce discs on the top of the columns.
     * Introduced disc drops down the board if the column is empty.
     * Future discs introduced in the same column will stack over previous ones
     */

    @Test
    public void whenDiscOutsideBoardThenRuntimeException() {

        assertAll(
                () -> {
                    int columnUp = 8;
                    RuntimeException thrown = assertThrows(RuntimeException.class, () -> tested.putDiscInColumn(columnUp));
                    assertThat(thrown.getMessage()).isEqualTo(String.format("Invalid column %d", columnUp));
                            },
                () -> {
                    int columnDown = -1;
                    RuntimeException thrown = assertThrows(RuntimeException.class, () -> tested.putDiscInColumn(columnDown));
                    assertThat(thrown.getMessage()).isEqualTo(String.format("Invalid column %d", columnDown));
                }
                );

    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {

        assertThat(tested.putDiscInColumn(0)).isEqualTo(0);

    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {

        tested.putDiscInColumn(0);
        assertThat(tested.putDiscInColumn(0)).isEqualTo(1);

    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscsIncreases() {

        int beforeInsert = tested.getNumberOfDiscs();
        tested.putDiscInColumn(6);

        assertThat(tested.getNumberOfDiscs()).isEqualTo(beforeInsert + 1);

    }

    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException() {

        final int column = 4;

        RuntimeException thrown = assertThrows(RuntimeException.class, () ->  IntStream.range(0, 6).forEach((i) -> tested.putDiscInColumn(column)));
        assertThat(thrown.getMessage()).isEqualTo(String.format("No more room in column %d", column));


    }

    /*
     * It is a two-person game so there is one colour for each player.
     * One player uses red ('R'), the other one uses green ('G').
     * Players alternate turns, inserting one disc every time
     */

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed() {
        assertThat(tested.getCurrentPlayer()).isEqualTo("R");

    }
    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen() {

        tested.putDiscInColumn(3);
        assertThat(tested.getCurrentPlayer()).isEqualTo("G");

    }

    /*
     * We want feedback when either, event or error occur within the game.
     * The output shows the status of the board on every move
     */

    @Test
    public void whenAskedForCurrentPlayerTheOutputNotice() {

        tested.getCurrentPlayer();

        assertThat(output.toString()).contains(String.format("Player %s turn%n", "R"));

        tested.putDiscInColumn(5);

        assertThat(output.toString()).contains(String.format("Player %s turn%n", "G"));

    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {

        tested.putDiscInColumn(0);

        IntStream.range(0, 5).forEach( (i) -> assertThat(output.toString()).contains("| | | | | | | |") );

        assertThat(output.toString()).contains("|R| | | | | | |");

    }

    /*
     * When no more discs can be inserted, the game finishes and it is considered a draw
     */

    @Test
    public void whenTheGameStartsItIsNotFinished() {

        assertThat(tested.isFinished()).isFalse();

    }

    @Test
    public void whenNoDiscCanBeIntroducedTheGamesIsFinished() {

        IntStream.range(0, 8).forEach( (c) -> {

                    IntStream.range(0, 7).forEach( (f) -> {
                        tested.putDiscInColumn(c);
                    });

                }
        );

        assertThat(tested.isFinished()).isTrue();

    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight vertical line then that player wins
     */

    @Test
    public void when4VerticalDiscsAreConnectedThenThatPlayerWins() {
        final int column = 2;
        IntStream.range(0,4).forEach( (f) -> {
            tested.putDiscInColumn(column);
            if (f < 3)  tested.putDiscInColumn(column+1);
        });

        assertThat(tested.getWinner()).isEqualTo("R");

    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight horizontal line then that player wins
     */

    @Test
    public void when4HorizontalDiscsAreConnectedThenThatPlayerWins() {

        final int columnOffset = 1;
        IntStream.range(0,4).forEach( (c) -> {
            tested.putDiscInColumn(columnOffset+c);
            if (c < 3)  tested.putDiscInColumn(columnOffset+c);
        });

        assertThat(tested.getWinner()).isEqualTo("R");

    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight diagonal line then that player wins
     */

    @Test
    public void when4Diagonal1DiscsAreConnectedThenThatPlayerWins() {
        int[] gameplay = new int[] {1, 2, 2, 3, 4, 3, 3, 4, 4, 5, 4};
        IntStream.of(gameplay).forEach( c -> tested.putDiscInColumn(c));

        //Comentar
        System.out.println(output.toString());
        //
        assertThat(tested.getWinner()).isEqualTo("R");


    }

    @Test
    public void when4Diagonal2DiscsAreConnectedThenThatPlayerWins() {
        int[] gameplay = new int[] {3, 4, 2, 3, 2, 2, 1, 1, 1, 1};

        IntStream.of(gameplay).forEach( c -> tested.putDiscInColumn(c));

        //Comentar
        System.out.println(output.toString());
        //
        assertThat(tested.getWinner()).isEqualTo("G");


    }
}
