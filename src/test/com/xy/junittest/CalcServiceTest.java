package com.xy.junittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalcServiceTest {

    private CalcService calcService;

    @BeforeEach
    public void initCalcService() {
        calcService = new CalcService();
    }

    @AfterEach
    public void closeCalcService() {
        calcService = null;
    }

    @Test
    public void testAdd() {
        assertEquals(6, calcService.add(3, 3));
    }

    @Test
    public void testSub() {
        assertThrows(IllegalArgumentException.class, () -> calcService.sub(5, 6));
    }
}
