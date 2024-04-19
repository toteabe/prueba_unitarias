package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;



@Test
public class PointSpec {
    private Point point;
    private final int x = 12;
    private final int y = 21;

    public PointSpec() {
    }

    @BeforeMethod
    public void beforeTest() {
        this.point = new Point(x, y);
    }

    public void whenInstantiatedThenXIsSet() {
        assertEquals(x, point.getX());
    }

    public void whenInstantiatedThenYIsSet() {
        assertEquals(y, point.getY());
    }
}
