package io.github.mivek.model.trend.validity;

import io.github.mivek.enums.TimeIndicator;
import io.github.mivek.utils.LocalTime;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class FMTimeTest {

    @Test
    public void testToString() {
        FMTime sut = new FMTime();
        sut.setTime(LocalTime.of(12, 15));
        String des = sut.toString();
        assertEquals(TimeIndicator.FM.toString() + " 12:15", des);
    }
}
