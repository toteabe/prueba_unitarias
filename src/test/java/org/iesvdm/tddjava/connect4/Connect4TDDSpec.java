package org.iesvdm.tddjava.connect4;


import org.iesvdm.tddjava.ship.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class Connect4TDDSpec {

    //ATRIBUTOS DEL CONNECT4TDDSPEC
    private Connect4TDD tested;
    private OutputStream output;


    @BeforeEach
    public void beforeEachTest() {
        output = new ByteArrayOutputStream();
        tested = new Connect4TDD(new PrintStream(output));
    }


    @Test
    public void whenTheGameStartsTheBoardIsEmpty() {
        assertEquals(0, tested.getNumberOfDiscs());
    }


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



