package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class LocationSpec {

    private final int x = 12;
    private final int y = 32;
    private final Direction direction = Direction.NORTH;
    private Point max;
    private Location location;
    private List<Point> obstacles;

    @BeforeMethod
    public void beforeTest() {
        max = new Point(50, 50);
        location = new Location(new Point(x, y), direction);
        obstacles = new ArrayList<Point>();
    }

    public void whenInstantiatedThenXIsStored() {
        assertEquals ( location.getPoint ().getX (),12 );
    }

    public void whenInstantiatedThenYIsStored() {
        assertEquals ( location.getY (),32 );
    }

    public void whenInstantiatedThenDirectionIsStored() {
        assertEquals ( location.getDirection (),Direction.NORTH );
    }

    public void givenDirectionNWhenForwardThenYDecreases() {
        int y1 = location.getY ();
        location.forward ();
        int y2=location.getY ();
        assertTrue ( y1>y2 );
    }

    public void givenDirectionSWhenForwardThenYIncreases() {
        location.setDirection ( Direction.SOUTH );
        int y1 = location.getY ();
        location.forward ();
        int y2=location.getY ();
        assertTrue ( y1<y2 );
    }

    public void givenDirectionEWhenForwardThenXIncreases() {
        location.setDirection ( Direction.EAST );
        int x1 = location.getX ();
        location.forward ();
        int x2=location.getX ();
        assertTrue ( x1<x2 );
    }

    public void givenDirectionWWhenForwardThenXDecreases() {
        location.setDirection ( Direction.WEST );
        int x1 = location.getX ();
        location.forward ();
        int x2=location.getX ();
        assertTrue ( x1>x2 );
    }

    public void givenDirectionNWhenBackwardThenYIncreases() {
        location.setDirection ( Direction.NORTH );
        int y1 = location.getY ();
        location.backward ();
        int y2=location.getY ();
        assertTrue ( y1<y2 );
    }

    public void givenDirectionSWhenBackwardThenYDecreases() {
        location.setDirection ( Direction.SOUTH );
        int y1 = location.getY ();
        location.backward ();
        int y2=location.getY ();
        assertTrue ( y1>y2 );
    }

    public void givenDirectionEWhenBackwardThenXDecreases() {
        location.setDirection ( Direction.EAST );
        int x1 = location.getX ();
        location.backward ();
        int x2=location.getX ();
        assertTrue ( x1>x2 );
    }

    public void givenDirectionWWhenBackwardThenXIncreases() {
        location.setDirection ( Direction.WEST );
        int x1 = location.getX ();
        location.backward ();
        int x2=location.getX ();
        assertTrue ( x1<x2 );
    }

    public void whenTurnLeftThenDirectionIsSet() {
        location.turnLeft ();
        assertNotEquals ( location.getDirection (), null );
        assertEquals ( location.getDirection (), Direction.WEST );
    }

    public void whenTurnRightThenDirectionIsSet() {
        location.turnRight ();
        assertNotEquals ( location.getDirection (), null );
        assertEquals ( location.getDirection (), Direction.EAST );
    }

    public void givenSameObjectsWhenEqualsThenTrue() {
        assertTrue ( location.equals(location) );
    }

    public void givenDifferentObjectWhenEqualsThenFalse() {
        Location location2 = new Location(max, direction);
        location2.setDirection ( Direction.SOUTH );
        assertFalse ( location.equals ( location2 ) );
    }

    public void givenDifferentXWhenEqualsThenFalse() {
        Location location2 = new Location ( new Point( 0, location.getY ()), location.getDirection () );
        assertFalse ( location.equals ( location2 ) );
    }

    public void givenDifferentYWhenEqualsThenFalse() {
        Location location2 = new Location ( new Point( location.getX (), 0 ), location.getDirection () );
        assertFalse ( location.equals ( location2 ) );
    }

    public void givenDifferentDirectionWhenEqualsThenFalse() {
        Location location2 = new Location ( new Point( location.getX (), location.getY () ), Direction.SOUTH);
        assertFalse ( location.equals ( location2 ) );
    }

    public void givenSameXYDirectionWhenEqualsThenTrue() {
        Location location2 = new Location ( new Point( location.getX (), location.getY () ), location.getDirection ());
        assertTrue ( location.equals ( location2 ) );
    }

    public void whenCopyThenDifferentObject() {
        Location location2 = location.copy ();
        assertFalse ( location == location2 );
    }

    public void whenCopyThenEquals() {
        Location location2 = location;
        assertTrue ( location.equals(location2) );
    }

    public void givenDirectionEAndXEqualsMaxXWhenForwardThen1() {
        location.setDirection ( Direction.EAST );
        location.getPoint ().setX ( max.getX () );
        location.forward (max);
        assertEquals ( location.getX (), 1 );
    }

    public void givenDirectionWAndXEquals1WhenForwardThenMaxX() {
        location.setDirection ( Direction.WEST );
        location.getPoint ().setX ( 1 );
        location.forward (max);
        assertEquals ( location.getX (), max.getX () );
    }

    public void givenDirectionNAndYEquals1WhenForwardThenMaxY() {
        location.setDirection ( Direction.NORTH );
        location.getPoint ().setY ( 1 );
        location.forward (max);
        assertEquals ( location.getY (), max.getY () );
    }

    public void givenDirectionSAndYEqualsMaxYWhenForwardThen1() {
        location.setDirection ( Direction.SOUTH );
        location.getPoint ().setY ( max.getY () );
        location.forward (max);
        assertEquals ( location.getY (), 1 );
    }

    public void givenObstacleWhenForwardThenReturnFalse() {
        //givenDirectionNWhenForwardThenYDecreases
        Point p = new Point( location.getX (), location.getY () -1);
        obstacles.add (p);
        assertFalse ( location.forward (max, obstacles) );
    }

    public void givenObstacleWhenBackwardThenReturnFalse() {
        Point p = new Point( location.getX (), location.getY () +1);
        obstacles.add (p);
        assertFalse ( location.backward (max, obstacles) );
        
    }

}
