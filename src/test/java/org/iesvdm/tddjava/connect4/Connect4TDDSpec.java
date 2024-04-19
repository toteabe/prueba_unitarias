package org.iesvdm.tddjava.connect4;


import org.iesvdm.tddjava.ship.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertThrows;


public class Connect4TDDSpec {
    @Test
    public void whenTheGameStartsTheBoardIsEmpty() {
        Connect4TDD tested = null;
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



