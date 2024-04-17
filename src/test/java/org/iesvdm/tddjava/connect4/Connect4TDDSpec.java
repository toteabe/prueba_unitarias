package org.iesvdm.tddjava.connect4;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


public class Connect4TDDSpec {

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
        assertThat ( tested.getNumberOfDiscs ()==0 );
    }

    /*
     * Players introduce discs on the top of the columns.
     * Introduced disc drops down the board if the column is empty.
     * Future discs introduced in the same column will stack over previous ones
     */

    @Test
    public void whenDiscOutsideBoardThenRuntimeException() {

    assertThrows ( RuntimeException.class, () -> {tested.putDiscInColumn ( 8 );} );
    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {
        assertThat ( tested.getNumberOfDiscs ()==0 );
        assertThat(tested.putDiscInColumn(0)).isEqualTo(0);
    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {
        assertThat(tested.putDiscInColumn(0)).isEqualTo(0);
        assertThat(tested.putDiscInColumn(0)).isEqualTo(1);

    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscsIncreases() {
        int discsbefore=tested.getNumberOfDiscs ();
        tested.putDiscInColumn ( 0 );
        assertThat ( tested.getNumberOfDiscs()==discsbefore+1 );
    }

    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException() {
        for(int i=0;i<6;i++)
        {
            tested.putDiscInColumn(0);
        }
        assertThrows ( RuntimeException.class, () -> {tested.putDiscInColumn ( 0 );} );
    }

    /*
     * It is a two-person game so there is one colour for each player.
     * One player uses red ('R'), the other one uses green ('G').
     * Players alternate turns, inserting one disc every time
     */

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed() {
        assertThat ( tested.getNumberOfDiscs ()==0 );
        assertThat(tested.getCurrentPlayer ().equals ( Connect4.Color.RED ));
    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen() {
        assertEquals ( tested.getNumberOfDiscs (), 0);
        tested.putDiscInColumn ( 0 );
        assertThat(tested.getCurrentPlayer ().equals ( Connect4.Color.GREEN ));
    }

    /*
     * We want feedback when either, event or error occur within the game.
     * The output shows the status of the board on every move
     */

    @Test
    public void whenAskedForCurrentPlayerTheOutputNotice() {
        String current = tested.getCurrentPlayer ();
        assertEquals ( current, tested.getCurrentPlayer () );
        assertThat ( output.toString () ).isNotEmpty ();
    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {
        tested.putDiscInColumn ( 1 );
        assertThat ( output.toString () ).isNotEmpty ();
    }
    /*
     * When no more discs can be inserted, the game finishes and it is considered a draw
     */

    @Test
    public void whenTheGameStartsItIsNotFinished() {
        assertFalse ( tested.isFinished () );
    }

    @Test
    public void whenNoDiscCanBeIntroducedTheGamesIsFinished() {
        for(int column = 0;column<Connect4.COLUMNS;column++)
        {
            for(int row = 0; row<Connect4.ROWS;row++)
            {
                tested.putDiscInColumn ( column );
            }
        }
        assertTrue ( tested.isFinished ());
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight vertical line then that player wins
     */

    @Test
    public void when4VerticalDiscsAreConnectedThenThatPlayerWins() {
        for(int j=0;j<4;j++)
        {
            for(int i=0;i<2;i++){
                tested.putDiscInColumn ( i );
            }
        }
        assertTrue(tested.getWinner ().equals ( "R" ));
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight horizontal line then that player wins
     */

    @Test
    public void when4HorizontalDiscsAreConnectedThenThatPlayerWins() {
        for(int j=0;j<4;j++)
        {
            for(int i=0;i<2;i++){
                tested.putDiscInColumn ( 0+j );
            }
        }
        assertTrue(tested.getWinner ().equals ( "R" ));
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight diagonal line then that player wins
     */

    @Test
    public void when4Diagonal1DiscsAreConnectedThenThatPlayerWins() {
        for(int i=0 ;i<2;i++)
        {
            for(int j=1;j<5;j++)
            {
                tested.putDiscInColumn ( j+i );
            }
        }
        tested.putDiscInColumn ( 3 );
        tested.putDiscInColumn ( 4 );
        tested.putDiscInColumn ( 4 );
        assertTrue(tested.getWinner ().equals ( "R" ));
    }

    @Test
    public void when4Diagonal2DiscsAreConnectedThenThatPlayerWins() {
        for(int i=0 ;i<2;i++)
        {
            for(int j=5;j>1;j--)
            {
                tested.putDiscInColumn ( j-i );
            }
        }
        tested.putDiscInColumn ( 3 );
        tested.putDiscInColumn ( 2 );
        tested.putDiscInColumn ( 2 );
        assertTrue(tested.getWinner ().equals ( "R" ));
    }
}
