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

    @BeforeMethod
    public void beforeTest() {
        Point max = new Point(50, 50);
        location = new Location(new Point(21, 13), Direction.NORTH);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(44, 44));
        obstacles.add(new Point(45, 46));
        planet = new Planet(max, obstacles);
//        ship = new Ship(location);
        ship = new Ship(location, planet);
    }

    public void whenInstantiatedThenLocationIsSet() {
        Location location = new Location(new Point(21, 13), Direction.NORTH);
        Ship ship = new Ship(location, planet);
        assertNotEquals ( null, ship.getLocation () );
    }

//    public void givenNorthWhenMoveForwardThenYDecreases() {
//        ship.moveForward();
//        assertEquals(ship.getLocation().getPoint().getY(), 12);
//    }
//
//    public void givenEastWhenMoveForwardThenXIncreases() {
//        ship.getLocation().setDirection(Direction.EAST);
//        ship.moveForward();
//        assertEquals(ship.getLocation().getPoint().getX(), 22);
//    }

    public void whenMoveForwardThenForward() {
        assertTrue ( location.forward () && ship.moveForward () );
    }

    public void whenMoveBackwardThenBackward() {
        assertTrue ( location.backward () && ship.moveBackward () );
    }

    public void whenTurnLeftThenLeft() {
        ship.turnLeft ();
        assertEquals ( Direction.WEST, location.getDirection () );
    }

    public void whenTurnRightThenRight() {
        ship.turnRight ();
        assertEquals ( Direction.EAST, location.getDirection () );
    }

    public void whenReceiveCommandsFThenForward() {
        int y1 = location.getY ();
        ship.receiveCommands ( "f" );
        int y2=location.getY ();
        assertTrue ( y1>y2 );
    }

    public void whenReceiveCommandsBThenBackward() {
        int y1 = location.getY ();
        ship.receiveCommands ( "b" );
        int y2=location.getY ();
        assertTrue ( y1<y2 );
    }

    public void whenReceiveCommandsLThenTurnLeft() {
        ship.receiveCommands ( "l" );
        assertEquals ( location.getDirection (), Direction.WEST );
    }

    public void whenReceiveCommandsRThenTurnRight() {
        ship.receiveCommands ( "r" );
        assertEquals ( location.getDirection (), Direction.EAST );
    }

    public void whenReceiveCommandsThenAllAreExecuted() {

    }

    public void whenInstantiatedThenPlanetIsStored() {
          Point max = new Point(50, 50);
          Planet planet = new Planet(max);
          ship = new Ship(location, planet);
          assertEquals ( ship.getPlanet (), planet );
    }

    public void givenDirectionEAndXEqualsMaxXWhenReceiveCommandsFThenWrap() {

    }

    public void givenDirectionEAndXEquals1WhenReceiveCommandsBThenWrap() {

    }

    public void whenReceiveCommandsThenStopOnObstacle() {
        //obstacle at (44, 44)
        Ship s = new Ship ( new Location ( new Point(44, 45),Direction.NORTH), planet);
        assertEquals (s.receiveCommands ( "f" ), "X" );
    }

    public void whenReceiveCommandsThenOForOkAndXForObstacle() {
        //obstacle at (44, 44)
        Ship s = new Ship ( new Location ( new Point(44, 45),Direction.NORTH), planet);
        assertEquals (s.receiveCommands ( "f" ), "X" );
        assertEquals ( s.receiveCommands ( "b" ), "O");
    }

}
