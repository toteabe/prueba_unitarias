package org.iesvdm.tddjava.ship;
import org.testng.annotations.*;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.*;


@Test
public class LocationSpec {

//ATRIBUTOS DEL LOCATIONSPEC
    private final int x = 12;
    private final int y = 32;
    private Direction direction = Direction.NORTH;
    private Point max;
    private Location location;
    private List<Point> obstacles;


    public LocationSpec() {
        this.direction = Direction.NORTH;
    }


    @BeforeMethod
    public void beforeTest() {
        this.max = new Point(50, 50);
        this.location = new Location(new Point(12, 32), this.direction);
        this.obstacles = new ArrayList<>();
    }


    public void whenInstantiatedThenXIsStored() {
        assertEquals(x, location.getX());
    }


    public void whenInstantiatedThenYIsStored() {
        assertEquals(y, location.getY());
    }


    public void whenInstantiatedThenDirectionIsStored() {
        assertEquals(direction, location.getDirection());
    }


    public void givenDirectionNWhenForwardThenYDecreases() {
        location.forward();
        assertEquals(y - 1, location.getY());
    }


    public void givenDirectionSWhenForwardThenYIncreases() {
        location.setDirection(Direction.SOUTH);
        location.forward();
        assertEquals(y + 1, location.getY());
    }


    public void givenDirectionEWhenForwardThenXIncreases() {
        location.setDirection(Direction.EAST);
        location.forward();
        assertEquals(x + 1, location.getX());
    }


    public void givenDirectionWWhenForwardThenXDecreases() {
        location.setDirection(Direction.WEST);
        location.forward();
        assertEquals(x - 1, location.getX());
    }


    public void givenDirectionNWhenBackwardThenYIncreases() {
        location.backward();
        assertEquals(y + 1, location.getY());
    }


    public void givenDirectionSWhenBackwardThenYDecreases() {
        location.setDirection(Direction.SOUTH);
        location.backward();
        assertEquals(y - 1, location.getY());
    }


    public void givenDirectionEWhenBackwardThenXDecreases() {
        location.setDirection(Direction.EAST);
        location.backward();
        assertEquals(x - 1, location.getX());
    }


    public void givenDirectionWWhenBackwardThenXIncreases() {
        location.setDirection(Direction.WEST);
        location.backward();
        assertEquals(x + 1, location.getX());
    }


    public void whenTurnLeftThenDirectionIsSet() {
        location.turnLeft();
        assertEquals(Direction.WEST, location.getDirection());
    }


    public void whenTurnRightThenDirectionIsSet() {
        location.turnRight();
        assertEquals(Direction.EAST, location.getDirection());
    }


    public void givenSameObjectsWhenEqualsThenTrue() {
        assertEquals(location, location);
    }


    public void givenDifferentObjectWhenEqualsThenFalse() {
        assertNotEquals(new Object(), location);
    }


    public void givenDifferentXWhenEqualsThenFalse() {
        Location other = new Location(new Point(15, y), direction);
        assertNotEquals(other, location);
    }


    public void givenDifferentYWhenEqualsThenFalse() {
        Location other = new Location(new Point(x, 35), direction);
        assertNotEquals(other, location);
    }


    public void givenDifferentDirectionWhenEqualsThenFalse() {
        Location other = new Location(new Point(x, y), Direction.WEST);
        assertNotEquals(other, location);
    }


    public void givenSameXYDirectionWhenEqualsThenTrue() {
        Location other = new Location(new Point(x, y), direction);
        assertTrue(location.equals(other));
    }


    public void whenCopyThenDifferentObject() {
        Location copy = location.copy();
        assertNotSame(location, copy);
    }


    public void whenCopyThenEquals() {
        Location copy = location.copy();
        assertEquals(location, copy);
    }


    public void givenDirectionEAndXEqualsMaxXWhenForwardThen1() {
        location.setDirection(Direction.EAST);
        location.getPoint().setX(max.getX());
        location.forward(max);
        assertEquals(1, location.getX());
    }


    public void givenDirectionWAndXEquals1WhenForwardThenMaxX() {
        location.setDirection(Direction.WEST);
        location.getPoint().setX(1);
        location.forward(max);
        assertEquals(max.getX(), location.getX());
    }


    public void givenDirectionNAndYEquals1WhenForwardThenMaxY() {
        location.setDirection(Direction.NORTH);
        location.getPoint().setY(1);
        location.forward(max);
        assertEquals(max.getY(), location.getY());
    }


    public void givenDirectionSAndYEqualsMaxYWhenForwardThen1() {
        location.setDirection(Direction.SOUTH);
        location.getPoint().setY(max.getY());
        location.forward(max);
        assertEquals(1, location.getY());
    }


    public void givenObstacleWhenForwardThenReturnFalse() {
        Point obstacle = new Point(location.getX(), location.getY() - 1);
        obstacles.add(obstacle);
        assertFalse(location.forward(max, obstacles));
    }


    public void givenObstacleWhenBackwardThenReturnFalse() {
        Point obstacle = new Point(location.getX(), location.getY() + 1);
        obstacles.add(obstacle);
        assertFalse(location.backward(max, obstacles));
    }


}

