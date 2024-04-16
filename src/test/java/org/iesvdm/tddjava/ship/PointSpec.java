package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
@Test
public class ShipSpec {
    private Ship ship;
    private Location location;
    private Planet planet;

    public ShipSpec() {
    }

    @BeforeMethod
    public void beforeTest() {
        Point max = new Point(50, 50);
        this.location = new Location(new Point(21, 13), Direction.NORTH);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(44, 44));
        obstacles.add(new Point(45, 46));
        this.planet = new Planet(max, obstacles);
        this.ship = new Ship(this.location, this.planet);
    }

    public void whenInstantiatedThenLocationIsSet() {
        assertEquals(location, ship.getLocation());
    }

    public void whenMoveForwardThenForward() {
        ship.moveForward();
        assertEquals(14, location.getPoint().getY());
    }

    public void whenMoveBackwardThenBackward() {
        ship.moveBackward();
        assertEquals(12, location.getPoint().getY());
    }

    public void whenTurnLeftThenLeft() {
        ship.turnLeft();
        assertEquals(Direction.WEST, location.getDirection());
    }

    public void whenTurnRightThenRight() {
        ship.turnRight();
        assertEquals(Direction.EAST, location.getDirection());
    }

    public void whenReceiveCommandsFThenForward() {
        assertEquals("O", ship.receiveCommands("f"));
    }

    public void whenReceiveCommandsBThenBackward() {
        assertEquals("O", ship.receiveCommands("b"));
    }

    public void whenReceiveCommandsLThenTurnLeft() {
        assertEquals("", ship.receiveCommands("l"));
    }

    public void whenReceiveCommandsRThenTurnRight() {
        assertEquals("", ship.receiveCommands("r"));
    }

    public void whenReceiveCommandsThenAllAreExecuted() {
        assertEquals("OOO", ship.receiveCommands("ff"));
    }

    public void whenInstantiatedThenPlanetIsStored() {
        assertEquals(planet, ship.getPlanet());
    }

    public void givenDirectionEAndXEqualsMaxXWhenReceiveCommandsFThenWrap() {
        // Ship facing East at the max X coordinate of the planet, moving forward should wrap around to 1
        assertEquals("O", ship.receiveCommands("f"));
    }

    public void givenDirectionEAndXEquals1WhenReceiveCommandsBThenWrap() {
        // Ship facing East at X coordinate 1, moving backward should wrap around to the max X coordinate of the planet
        assertEquals("O", ship.receiveCommands("b"));
    }

    public void whenReceiveCommandsThenStopOnObstacle() {
        // Ship facing North, encountering obstacle, should stop moving forward
        assertEquals("OX", ship.receiveCommands("ff"));
    }

    public void whenReceiveCommandsThenOForOkAndXForObstacle() {
        // Ship moves forward, backward, and encounters obstacle
        assertEquals("OOX", ship.receiveCommands("ffb"));
    }
}

