package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        assertThat(ship.getLocation() == location).isTrue();

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
        int previousY = ship.getLocation().getY();
        ship.moveForward();
        assertThat(ship.getLocation().getY() < previousY ).isTrue();
    }

    public void whenMoveBackwardThenBackward() {
        int previousY = ship.getLocation().getY();
        ship.moveBackward();
        assertThat(ship.getLocation().getY() > previousY).isTrue();
    }

    public void whenTurnLeftThenLeft() {
        ship.turnLeft();
        assertThat(ship.getLocation().getDirection() == Direction.WEST ).isTrue();
    }

    public void whenTurnRightThenRight() {
        ship.turnRight();
        assertThat(ship.getLocation().getDirection() == Direction.EAST ).isTrue();
    }

    public void whenReceiveCommandsFThenForward() {
        int previousY = ship.getLocation().getY();
        ship.receiveCommands("f");
        assertThat(ship.getLocation().getY() < previousY ).isTrue();
    }

    public void whenReceiveCommandsBThenBackward() {
        int previousY = ship.getLocation().getY();
        ship.receiveCommands("b");
        assertThat(ship.getLocation().getY() > previousY ).isTrue();
    }

    public void whenReceiveCommandsLThenTurnLeft() {
        ship.receiveCommands("l");
        assertThat(ship.getLocation().getDirection() == Direction.WEST ).isTrue();
    }

    public void whenReceiveCommandsRThenTurnRight() {
        ship.receiveCommands("r");
        assertThat(ship.getLocation().getDirection() == Direction.EAST ).isTrue();
    }

    public void whenReceiveCommandsThenAllAreExecuted() {
        ship.receiveCommands("f");
        ship.receiveCommands("b");
        ship.receiveCommands("l");;
        ship.receiveCommands("r");
        assertThat(ship.getLocation().getDirection() == Direction.NORTH ).isTrue();
        assertThat(ship.getLocation().getY() == location.getY() ).isTrue();

    }

    public void whenInstantiatedThenPlanetIsStored() {
        assertThat(ship.getPlanet() == planet).isTrue();

    }

    public void givenDirectionEAndXEqualsMaxXWhenReceiveCommandsFThenWrap() {
        Location locationTest = new Location(new Point(50, 50), Direction.EAST);
        Ship shipTest = new Ship(locationTest, planet);
        shipTest.receiveCommands("f");

        assertThat(shipTest.getLocation().getX() == 1 ).isTrue();
    }

    public void givenDirectionEAndXEquals1WhenReceiveCommandsBThenWrap() {
        Location locationTest = new Location(new Point(1, 50), Direction.EAST);
        Ship shipTest = new Ship(locationTest, planet);
        shipTest.receiveCommands("b");

        assertThat(shipTest.getLocation().getX() == 50 ).isTrue();
    }

    public void whenReceiveCommandsThenStopOnObstacle() {
        String colision = "O";
        int security = 0;

        Point max = new Point(50, 50);
        Location location = new Location(new Point(21, 13), Direction.NORTH);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(21, 14));
        Planet planet = new Planet(max, obstacles);
        Ship shipTest = new Ship(location, planet);

        while (colision.compareTo("X") != 0 && security < 100){
            colision = shipTest.receiveCommands("f");
            security++;
        }
        assertThat(colision.compareTo("X") == 0 ).isTrue();
    }

    public void whenReceiveCommandsThenOForOkAndXForObstacle() {

        Point max = new Point(50, 50);
        Location location = new Location(new Point(21, 12), Direction.NORTH);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(21, 10));
        Planet planet = new Planet(max, obstacles);
        Ship shipTest = new Ship(location, planet);
        assertThat(
                shipTest.receiveCommands("f").compareTo("O") == 0 &&
                        shipTest.receiveCommands("f").compareTo("X") == 0 ).isTrue();
    }

}
