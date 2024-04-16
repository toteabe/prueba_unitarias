package org.iesvdm.tddjava.connect4;


import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.iesvdm.tddjava.ship.Direction;
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
        MatcherAssert.assertThat(this.tested.getNumberOfDiscs(), Matchers.is(0));
        output=new ByteArrayOutputStream();

        assertThat(tested.getNumberOfDiscs()).isZero();
    }

    /*
     * Players introduce discs on the top of the columns.
     * Introduced disc drops down the board if the column is empty.
     * Future discs introduced in the same column will stack over previous ones
     */

    /**
     * Clase de test del juego connect4
     */

    @Test
    public void whenGetFromShortNameNThenReturnDirectionN() {
        assertEquals(Direction.NORTH, Direction.getFromShortName('N'));
    }

    @Test
    public void whenGetFromShortNameWThenReturnDirectionW() {
        assertEquals(Direction.WEST, Direction.getFromShortName('W'));
    }

    @Test
    public void whenGetFromShortNameBThenReturnNone() {
        assertEquals(Direction.NONE, Direction.getFromShortName('B'));
    }

    @Test
    public void givenSWhenLeftThenE() {
        assertEquals(Direction.EAST, Direction.SOUTH.turnLeft());
    }

    @Test
    public void givenNWhenLeftThenW() {
        assertEquals(Direction.WEST, Direction.NORTH.turnLeft());
    }

    @Test
    public void givenSWhenRightThenW() {
        assertEquals(Direction.WEST, Direction.SOUTH.turnRight());
    }
    @Test
    public void givenWWhenRightThenN() {
        assertEquals(Direction.NORTH, Direction.WEST.turnRight());
    }
}


