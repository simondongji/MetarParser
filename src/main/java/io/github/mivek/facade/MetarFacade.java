package io.github.mivek.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import io.github.mivek.model.Metar;
import io.github.mivek.parser.MetarParser;

/**
 * Class representing the facade for metars.
 * @author mivek
 */
public final class MetarFacade extends AbstractWeatherCodeFacade<Metar> {
    /** URL to retrieve the metar from. */
    private static final String NOAA_METAR_URL = "https://tgftp.nws.noaa.gov/data/observations/metar/stations/";
    /**
     * Instance.
     */
    private static final MetarFacade INSTANCE = new MetarFacade();

    /**
     * Private constructor.
     */
    private MetarFacade() {
        super(MetarParser.getInstance());
    }

    @Override
    public Metar decode(final String pCode) throws ParseException {
        return getParser().parse(pCode);
    }

    @Override
    public Metar retrieveFromAirport(final String pIcao) throws ParseException, IOException {
        if (pIcao.length() != AbstractWeatherCodeFacade.ICAO) {
            throw new ParseException(ErrorCodes.ERROR_CODE_INVALID_ICAO); // $NON-NLS-1$
        }
        String website = NOAA_METAR_URL + pIcao.toUpperCase() // $NON-NLS-1$
        + ".TXT"; //$NON-NLS-1$
        URL url = new URL(website);
        URLConnection urlCo = url.openConnection();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(urlCo.getInputStream(), StandardCharsets.UTF_8))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null){
                lines.add(line);
            }
            line = lines.get(1);
            //TODO 兼容JDK1.7
//            String line = br.lines().toArray(String[]::new)[1];
            return getParser().parse(line);
        }
    }

    /**
     * Returns a instance of the class.
     * @return the instance of the class.
     */
    public static MetarFacade getInstance() {
        return INSTANCE;
    }
}
