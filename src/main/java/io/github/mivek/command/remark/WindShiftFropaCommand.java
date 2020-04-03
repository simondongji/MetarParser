package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class WindShiftFropaCommand extends Command {
    /** Wind shift fopa pattern. */
    private static final Pattern WIND_SHIFT_FROPA = Pattern.compile("^WSHFT (\\d{2})?(\\d{2}) FROPA");

    /** The messages instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    WindShiftFropaCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] windShiftParts = Regex.pregMatch(WIND_SHIFT_FROPA, pRemark);
        pStringBuilder.append(fMessages.getString("Remark.WindShift.FROPA", verifyString(windShiftParts[1]), windShiftParts[2]));
        pStringBuilder.append(" ");
        return pRemark.replaceFirst(WIND_SHIFT_FROPA.pattern(), "");
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(WIND_SHIFT_FROPA, pInput);
    }
}
