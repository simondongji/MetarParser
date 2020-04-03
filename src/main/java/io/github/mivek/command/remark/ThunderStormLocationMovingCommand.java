package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class ThunderStormLocationMovingCommand extends Command {
    /** Thunderstorm location moving. */
    private static final Pattern THUNDERSTORM_LOCATION_MOVING = Pattern.compile("^TS ([A-Z]{2}) MOV ([A-Z]{2})");

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    ThunderStormLocationMovingCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] thunderStormParts = Regex.pregMatch(THUNDERSTORM_LOCATION_MOVING, pRemark);
        pStringBuilder
                .append(fMessages.getString("Remark.Thunderstorm.Location.Moving", fMessages.getString("Converter." + thunderStormParts[1]), fMessages.getString("Converter." + thunderStormParts[2])))
                .append(" ");
        return pRemark.replaceFirst(THUNDERSTORM_LOCATION_MOVING.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(THUNDERSTORM_LOCATION_MOVING, pInput);
    }
}
