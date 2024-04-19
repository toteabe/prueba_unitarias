package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        assertThat(location.getX() == x).isTrue();
    }

    public void whenInstantiatedThenYIsStored() {
        assertThat(location.getY() == y).isTrue();
    }

    public void whenInstantiatedThenDirectionIsStored() {
        assertThat(location.getDirection() == direction).isTrue();
    }

    public void givenDirectionNWhenForwardThenYDecreases() {
        location.forward();

        assertThat(location.getY() < y).isTrue();

    }

    public void givenDirectionSWhenForwardThenYIncreases() {
        location.setDirection(Direction.SOUTH);
        int currentY = location.getY();
        location.forward();
        assertThat(location.getY() > currentY).isTrue();
    }

    public void givenDirectionEWhenForwardThenXIncreases() {
        location.setDirection(Direction.EAST);
        int currentX = location.getX();
        location.forward();
        assertThat(location.getX() > currentX).isTrue();

    }

    public void givenDirectionWWhenForwardThenXDecreases() {
        location.setDirection(Direction.WEST);
        int currentX = location.getX();
        location.forward();
        assertThat(location.getX() < currentX).isTrue();
    }

    public void givenDirectionNWhenBackwardThenYIncreases() {
        location.setDirection(Direction.NORTH);
        int currentY = location.getY();
        location.backward();
        assertThat(location.getY() > currentY).isTrue();

    }

    public void givenDirectionSWhenBackwardThenYDecreases() {
        location.setDirection(Direction.SOUTH);
        int currentY = location.getY();
        location.backward();
        assertThat(location.getY() < currentY).isTrue();
    }

    public void givenDirectionEWhenBackwardThenXDecreases() {
        location.setDirection(Direction.EAST);
        int currentX = location.getX();
        location.backward();
        assertThat(location.getX() < currentX).isTrue();
    }

    public void givenDirectionWWhenBackwardThenXIncreases() {
        location.setDirection(Direction.WEST);
        int currentX = location.getX();
        location.backward();
        assertThat(location.getX() > currentX).isTrue();
    }

    public void whenTurnLeftThenDirectionIsSet() {
        location.turnLeft();
        assertThat(location.getDirection() == Direction.WEST).isTrue();
    }

    public void whenTurnRightThenDirectionIsSet() {
        location.turnRight();
        assertThat(location.getDirection() == Direction.EAST).isTrue();
    }

    public void givenSameObjectsWhenEqualsThenTrue() {
        assertThat(location.equals(location)).isTrue();
    }

    public void givenDifferentObjectWhenEqualsThenFalse() {
        String test = "test";
        assertThat(location.equals(test)).isFalse();
    }

    public void givenDifferentXWhenEqualsThenFalse() {
        Location location2 = new Location(new Point(x+1, y), direction);
        assertThat(location.equals(location2)).isFalse();
    }

    public void givenDifferentYWhenEqualsThenFalse() {
        Location location2 = new Location(new Point(x, y+1), direction);
        assertThat(location.equals(location2)).isFalse();
    }

    public void givenDifferentDirectionWhenEqualsThenFalse() {
        Location location2 = new Location(new Point(x, y), Direction.WEST);
        assertThat(location.equals(location2)).isFalse();
    }

    public void givenSameXYDirectionWhenEqualsThenTrue() {
        Location location2 = new Location(new Point(x, y), Direction.NORTH);
        assertThat(location.equals(location2)).isTrue();
    }

    public void whenCopyThenDifferentObject() {
        Location location2 = location.copy();
        assertThat(location2 != location).isTrue();
    }

    public void whenCopyThenEquals() {
        Location location2 = location.copy();
        assertThat(
          location.getX()==location2.getX() &&
                location.getY()==location2.getY() &&
                location.getDirection().compareTo(location2.getDirection()) == 0
            ).isTrue();
    }

    public void givenDirectionEAndXEqualsMaxXWhenForwardThen1() {
        Location locationTest = new Location(max, Direction.EAST);
        locationTest.forward(new Point(x, y));    int alvaro = locationTest.getX();
        assertThat(locationTest.getX() == 1).isTrue();
    }

    public void givenDirectionWAndXEquals1WhenForwardThenMaxX() {
        Location locationTest = new Location(new Point(1, y), Direction.WEST);
        locationTest.forward(max);
        assertThat(locationTest.getX() == max.getX()).isTrue();
    }

    public void givenDirectionNAndYEquals1WhenForwardThenMaxY() {
        Location locationTest = new Location(new Point(x, 1), Direction.NORTH);
        locationTest.forward(max);
        assertThat(locationTest.getY() == max.getY()).isTrue();

    }

    public void givenDirectionSAndYEqualsMaxYWhenForwardThen1() {
        Location locationTest = new Location(max, Direction.SOUTH);
        locationTest.forward(new Point(x, y));
        assertThat(locationTest.getY() == 1).isTrue();
    }

    public void givenObstacleWhenForwardThenReturnFalse() {
        obstacles.add(new Point(x,y-1));
        assertThat(location.forward(max, obstacles)).isFalse();

    }

    public void givenObstacleWhenBackwardThenReturnFalse() {
        obstacles.add(new Point(x,y+1));
        assertThat(location.backward(max, obstacles)).isFalse();
    }

}
