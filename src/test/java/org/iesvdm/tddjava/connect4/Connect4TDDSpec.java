package org.iesvdm.tddjava.connect4;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.iesvdm.tddjava.connect4.Connect4.COLUMNS;
import static org.iesvdm.tddjava.connect4.Connect4.ROWS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@Nested
class Connect4TDDSpec {

    private Connect4TDD tested;

    private OutputStream output;

    @BeforeEach
    public void beforeEachTest() {
        output = new ByteArrayOutputStream();

        //Se instancia el juego modificado para acceder a la salida de consola
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
        assertThatThrownBy(() -> tested.putDiscInColumn(9))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(String.format("Invalid column %d", 9));

    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {

        tested.putDiscInColumn(0);
        assertThat(tested.putDiscInColumn(0)).isEqualTo(1);

    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {

        assertThat(tested.putDiscInColumn(0)).isEqualTo(0);
    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscsIncreases() {
        tested.putDiscInColumn(0);

        assertThat(tested.getNumberOfDiscs()).isEqualTo(1);


    }

    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException() {
        for (int i = 0; i< 6; i++){
            tested.putDiscInColumn(0);
        }

        assertThatThrownBy(() -> tested.putDiscInColumn(0)).isInstanceOf(RuntimeException.class);
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
    tested.putDiscInColumn(0);
        assertThat(tested.getCurrentPlayer()).isEqualTo("G");
    }

    /*
     * We want feedback when either, event or error occur within the game.
     * The output shows the status of the board on every move
     */

    @Test
    public void whenAskedForCurrentPlayerTheOutputNotice() {
        tested.getCurrentPlayer();
        assertThat(output.toString()).isEqualToIgnoringWhitespace("Player R turn\n");

    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {
        tested.putDiscInColumn(0);
    }

    /*
     * When no more discs can be inserted, the game finishes and it is considered a draw
     */

    @Test
    public void whenTheGameStartsItIsNotFinished() {
        assertFalse(tested.isFinished());
    }

    @Test
    public void whenNoDiscCanBeIntroducedTheGamesIsFinished() {

        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLUMNS; j++){
                tested.putDiscInColumn(j);
            }
        }

        // Verify that the game is considered finished
        assertTrue(tested.isFinished());
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight vertical line then that player wins
     */

    @Test
    public void when4VerticalDiscsAreConnectedThenThatPlayerWins() {
        tested.putDiscInColumn(0);
        tested.putDiscInColumn(1);
        tested.putDiscInColumn(0);
        tested.putDiscInColumn(2);
        tested.putDiscInColumn(0);
        tested.putDiscInColumn(1);
        tested.putDiscInColumn(0);

        assertThat(tested.getWinner()).isEqualTo("R");

    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight horizontal line then that player wins
     */

    @Test
    public void when4HorizontalDiscsAreConnectedThenThatPlayerWins() {


        tested.putDiscInColumn(0);
        tested.putDiscInColumn(0);
        tested.putDiscInColumn(1);
        tested.putDiscInColumn(5);
        tested.putDiscInColumn(2);
        tested.putDiscInColumn(2);
        tested.putDiscInColumn(3);


        assertThat(tested.getWinner()).isEqualTo("R");
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight diagonal line then that player wins
     */

    @Test
    public void when4Diagonal1DiscsAreConnectedThenThatPlayerWins() {
        tested.putDiscInColumn(0);
        tested.putDiscInColumn(1);
        tested.putDiscInColumn(1);
        tested.putDiscInColumn(2);
        tested.putDiscInColumn(2);
        tested.putDiscInColumn(3);
        tested.putDiscInColumn(2);
        tested.putDiscInColumn(4);
        tested.putDiscInColumn(3);
        tested.putDiscInColumn(3);
        tested.putDiscInColumn(3);
        tested.putDiscInColumn(3);

        System.out.println(tested.getCurrentPlayer());
        //el ganador deberia ser R, pero sale G
        // Then
        assertThat(tested.getWinner()).isEqualTo("R");
    }

    @Test
    public void when4Diagonal2DiscsAreConnectedThenThatPlayerWins() {
        tested.putDiscInColumn(0);
        tested.putDiscInColumn(1);
        tested.putDiscInColumn(1);
        tested.putDiscInColumn(2);
        tested.putDiscInColumn(2);
        tested.putDiscInColumn(3);
        tested.putDiscInColumn(2);
        tested.putDiscInColumn(4);
        tested.putDiscInColumn(3);
        tested.putDiscInColumn(3);
        tested.putDiscInColumn(3);
        tested.putDiscInColumn(3);

    assertThat(tested.getWinner()).isEqualTo("R");

    /*el ganador deberia ser r tambien, no entiendo la diferencia con el otro metodo*/
    }

}
