package com.example.aaly.bosspuzzle;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class NPuzzleTest {
    @Test
    public void testZeroZeroIsZero(){
        assertEquals(0, NPuzzle.coordinatesToPosition(0, 0, 3));
    }

}
