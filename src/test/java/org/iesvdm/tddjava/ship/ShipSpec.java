package org.iesvdm.tddjava.ship;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.*;


public class ShipSpec {
    //ATRIBUTOS PARA EL TEST DEL SHIPSPEC
    private Ship ship;
    private Location location;
    private Planet planet;


    @Test
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
    @Test
    public void whenInstantiatedThenLocationIsSet() {

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
    @Test
    public void whenMoveForwardThenForward() {
        ship.moveForward();
        assertEquals(ship.getLocation().getPoint().getY(), 14);
    }


    @Test
    public void whenMoveBackwardThenBackward() {
        ship.moveBackward();
        assertEquals(ship.getLocation().getPoint().getY(), 12);
    }


    @Test
    public void whenTurnLeftThenLeft() {
        ship.turnLeft();
        assertEquals(ship.getLocation().getDirection(), Direction.WEST);
    }


    @Test
    public void whenTurnRightThenRight() {
        ship.turnRight();
        assertEquals(ship.getLocation().getDirection(), Direction.EAST);
    }


    @Test
    public void whenReceiveCommandsFThenForward() {
        ship.receiveCommands("f");
        assertEquals(ship.getLocation().getPoint().getY(), 14);
    }
    @Test
    public void whenReceiveCommandsBThenBackward() {
        ship.receiveCommands("b");
        assertEquals(ship.getLocation().getPoint().getY(), 12);
    }
    @Test
    public void whenReceiveCommandsLThenTurnLeft() {
        ship.receiveCommands("l");
        assertEquals(ship.getLocation().getDirection(), Direction.WEST);
    }
    @Test
    public void whenReceiveCommandsRThenTurnRight() {
        ship.receiveCommands("r");
        assertEquals(ship.getLocation().getDirection(), Direction.EAST);
    }
    @Test
    public void whenReceiveCommandsThenAllAreExecuted() {
        ship.receiveCommands("allexecuted");
    }
    @Test
    public void whenReceiveCommandsThenStopOnObstacle() {
        ship.receiveCommands("execute");
        Assert.assertEquals(new Point(44, 43), ship.getLocation().getPoint());
    }
    @Test
    public void whenReceiveCommandsThenOForOkAndXForObstacle() {
        String result = ship.receiveCommands("execute");
        assertEquals(result, "OXXXXXX");
    }
}

