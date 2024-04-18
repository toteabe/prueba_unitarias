package org.iesvdm.tddjava.ship;
import org.testng.annotations.*;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.*;
@Test
public class PointSpec {
    private Ship ship;
    private Location location;
    private Planet planet;
    public PointSpec() {
    }
    @Test
    public void beforeTest() {
        Point max = new Point(50, 50);
        this.location = new Location(new Point(21, 13), Direction.NORTH);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(44, 44));
        obstacles.add(new Point(45, 46));
        this.planet = new Planet(max, obstacles);
        this.ship = new Ship(this.location, this.planet);
    }
    @Test
    public void whenInstantiatedThenLocationIsSet() {
        assertEquals(location, ship.getLocation());
    }
    @Test
    public void whenMoveForwardThenForward() {
        ship.moveForward();
        assertEquals(14, location.getPoint().getY());
    }
    @Test
    public void whenMoveBackwardThenBackward() {
        ship.moveBackward();
        assertEquals(12, location.getPoint().getY());
    }
    @Test
    public void whenTurnLeftThenLeft() {
        ship.turnLeft();
        assertEquals(Direction.WEST, location.getDirection());
    }
    @Test
    public void whenTurnRightThenRight() {
        ship.turnRight();
        assertEquals(Direction.EAST, location.getDirection());
    }
    @Test
    public void whenReceiveCommandsFThenForward() {
        assertEquals("O", ship.receiveCommands("f"));
    }
    @Test
    public void whenReceiveCommandsBThenBackward(){
        assertEquals("O", ship.receiveCommands("b"));
    }
    @Test
    public void whenReceiveCommandsLThenTurnLeft() {
        assertEquals("", ship.receiveCommands("l"));
    }
    @Test
    public void whenReceiveCommandsRThenTurnRight() {
        assertEquals("", ship.receiveCommands("r"));
    }
    @Test
    public void whenReceiveCommandsThenAllAreExecuted() {
        assertEquals("OOO", ship.receiveCommands("ff"));
    }
    @Test
    public void whenInstantiatedThenPlanetIsStored() {
        assertEquals(planet, ship.getPlanet());
    }
    @Test
    public void givenDirectionEAndXEqualsMaxXWhenReceiveCommandsFThenWrap() {
        assertEquals("O", ship.receiveCommands("f"));
    }
    @Test
    public void givenDirectionEAndXEquals1WhenReceiveCommandsBThenWrap() {
        assertEquals("O", ship.receiveCommands("b"));
    }
    @Test
    public void whenReceiveCommandsThenStopOnObstacle() {
        assertEquals("OX", ship.receiveCommands("ff"));
    }
    @Test
    public void whenReceiveCommandsThenOForOkAndXForObstacle() {
        assertEquals("OOX", ship.receiveCommands("ffb"));
    }
}
