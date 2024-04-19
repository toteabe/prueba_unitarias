package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.awt.geom.NoninvertibleTransformException;

import static org.testng.Assert.*;

import static org.assertj.core.api.Assertions.*;

@Test
public class DirectionSpec {

    public void whenGetFromShortNameNThenReturnDirectionN() {
        final char shortName = 'N';
        assertThat(Direction.getFromShortName(shortName) == Direction.NORTH).isTrue();
    }

    public void whenGetFromShortNameWThenReturnDirectionW() {
        final char shortName = 'W';
        assertThat(Direction.getFromShortName(shortName) == Direction.WEST).isTrue();

    }

    public void whenGetFromShortNameBThenReturnNone() {
        final char shortName = 'B';
        assertThat(Direction.getFromShortName(shortName) == Direction.NONE).isTrue();

    }

    public void givenSWhenLeftThenE() {
        Direction direction = Direction.getFromShortName('S');
        assertThat(direction.turnLeft() == Direction.EAST).isTrue();
    }

    public void givenNWhenLeftThenW() {
        Direction direction = Direction.getFromShortName('N');
        assertThat(direction.turnLeft() == Direction.WEST).isTrue();

    }

    public void givenSWhenRightThenW() {
        Direction direction = Direction.getFromShortName('S');
        assertThat(direction.turnRight() == Direction.WEST).isTrue();

    }

    public void givenWWhenRightThenN() {
        Direction direction = Direction.getFromShortName('W');
        assertThat(direction.turnRight() == Direction.NORTH).isTrue();

    }

}
