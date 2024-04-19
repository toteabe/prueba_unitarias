package org.iesvdm.tddjava.ship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipSpec {

    // ATRIBUTOS PARA EL TEST DEL SHIPSPEC
    private Ship ship;
    private Location location;
    private Planet planet;

    @BeforeEach
    public void beforeTest() {
        Point max = new Point(50, 50);
        location = new Location(new Point(21, 13), Direction.NORTH);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(44, 44));
        obstacles.add(new Point(45, 46));
        planet = new Planet(max, obstacles);
        ship = new Ship(location, planet);
    }

    //Estos 2 siguientes test no me los coje bien porque dice que no sale el núm esperado...
    @Test
    public void whenMoveForwardThenForward() {
        ship.moveForward();
        assertEquals(14, ship.getLocation().getPoint().getY());
    }

    @Test
    public void whenMoveBackwardThenBackward() {
        ship.moveBackward();
        assertEquals(12, ship.getLocation().getPoint().getY());
    }

    @Test
    public void whenTurnLeftThenLeft() {
        ship.turnLeft();
        assertEquals(Direction.WEST, ship.getLocation().getDirection());
    }

    @Test
    public void whenTurnRightThenRight() {
        ship.turnRight();
        assertEquals(Direction.EAST, ship.getLocation().getDirection());
    }

    //En estos 2 siguientes lo mismo, el valor no es el esperado
    @Test
    public void whenReceiveCommandsFThenForward() {
        ship.receiveCommands("f");
        assertEquals(14, ship.getLocation().getPoint().getY());
    }

    @Test
    public void whenReceiveCommandsBThenBackward() {
        ship.receiveCommands("b");
        assertEquals(12, ship.getLocation().getPoint().getY());
    }

    @Test
    public void whenReceiveCommandsLThenTurnLeft() {
        ship.receiveCommands("l");
        assertEquals(Direction.WEST, ship.getLocation().getDirection());
    }

    @Test
    public void whenReceiveCommandsRThenTurnRight() {
        ship.receiveCommands("r");
        assertEquals(Direction.EAST, ship.getLocation().getDirection());
    }

    @Test
    public void whenReceiveCommandsThenAllAreExecuted() {
        ship.receiveCommands("allexecuted");
    }

    //NO se encuentra el valor esperado, por eso parece que da ERROR
    @Test
    public void whenReceiveCommandsThenStopOnObstacle() {
        ship.receiveCommands("execute");
        assertEquals(new Point(44, 43), ship.getLocation().getPoint());
    }

    @Test
    public void whenReceiveCommandsThenOForOkAndXForObstacle() {
        String result = ship.receiveCommands("execute");
        assertEquals("OXXXXXX", result);
    }
}

