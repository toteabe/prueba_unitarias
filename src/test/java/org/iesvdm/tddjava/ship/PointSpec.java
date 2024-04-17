package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;
import static org.testng.Assert.*;

@Test
public class PointSpec {

    private Point point;
    private final int x = 12;
    private final int y = 21;

    @BeforeMethod
    public void beforeTest() {
        point = new Point(x, y);
    }

    public void whenInstantiatedThenXIsSet() {
        assertNotEquals ( point.getX (), 0 );
        assertEquals ( point.getX (), 12 );
    }

    public void whenInstantiatedThenYIsSet() {
        assertNotEquals ( point.getY (), 0 );
        assertEquals ( point.getY (), 21 );

    }

}
