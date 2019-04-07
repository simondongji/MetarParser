package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class WindTest {

    @Test
    public void testToString(){
        Wind sut = new Wind();

        sut.setDirection(Messages.getInstance().getString("Converter.NE"));
        sut.setDirectionDegrees(45);
        sut.setSpeed(25);
        sut.setUnit("KM/H");


        String desc = sut.toString();

        assertThat(desc, containsString("speed=25"));
        assertThat(desc, containsString("direction="+Messages.getInstance().getString("Converter.NE")));
        assertThat(desc, containsString("direction (degrees)=45"));
        assertThat(desc, containsString("gusts=0"));
        assertThat(desc, containsString("minimal wind variation=0"));
        assertThat(desc, containsString("maximal wind variation=0"));
        assertThat(desc, containsString("unit=KM/H"));
    }

}