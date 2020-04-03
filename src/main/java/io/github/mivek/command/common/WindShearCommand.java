package io.github.mivek.command.common;

import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.WindShear;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class WindShearCommand extends BaseWindCommand {
    /** Pattern regex for windshear. */
    private static final Pattern WIND_SHEAR_REGEX = Pattern.compile("WS(\\d{3})\\/(\\w{3})(\\d{2})G?(\\d{2})?(KT|MPS|KM\\/H)");

    @Override public boolean execute(final AbstractWeatherContainer pContainer, final String pPart) {
        WindShear windShear = parseWindShear(pPart);
        pContainer.setWindShear(windShear);
        return getReturnValue();
    }

    @Override
    public boolean getReturnValue() {
        return true;
    }

    /**
     * Parses the wind shear part.
     *
     * @param pStringWindShear the string to parse
     * @return a wind shear object.
     */
    protected WindShear parseWindShear(final String pStringWindShear) {
        WindShear wind = new WindShear();
        String[] windPart = Regex.pregMatch(WIND_SHEAR_REGEX, pStringWindShear);
        wind.setHeight(100 * Integer.parseInt(windPart[1]));
        setWindElements(wind, windPart[2], windPart[3], windPart[4], windPart[5]);
        return wind;
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(WIND_SHEAR_REGEX, pInput);
    }
}
