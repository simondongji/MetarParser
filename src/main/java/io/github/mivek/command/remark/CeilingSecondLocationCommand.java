package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class CeilingSecondLocationCommand extends Command {
    /** Ceiling height second location. */
    private static final Pattern CEILING_SECOND_LOCATION = Pattern.compile("^CIG (\\d{3}) (\\w+)");

    /** The messages instance. */
    private Messages fMessages;

    /**
     * Default constructor.
     */
    CeilingSecondLocationCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] ceilingParts = Regex.pregMatch(CEILING_SECOND_LOCATION, pRemark);
        int height = 100 * Integer.parseInt(ceilingParts[1]);
        String location = ceilingParts[2];
        pStringBuilder.append(fMessages.getString("Remark.Ceiling.Second.Location", height, location)).append(" ");
        return pRemark.replaceFirst(CEILING_SECOND_LOCATION.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(CEILING_SECOND_LOCATION, pInput);
    }
}
