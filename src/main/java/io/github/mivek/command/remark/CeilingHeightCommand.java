package io.github.mivek.command.remark;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.utils.Regex;

import java.util.regex.Pattern;

/**
 * @author mivek
 */
public final class CeilingHeightCommand extends Command {
    /** Ceiling height. */
    private static final Pattern CEILING_HEIGHT = Pattern.compile("^CIG (\\d{3})V(\\d{3})");

    /** The message instance. */
    private final Messages fMessages;

    /**
     * Default constructor.
     */
    CeilingHeightCommand() {
        fMessages = Messages.getInstance();
    }

    @Override public String execute(final String pRemark, final StringBuilder pStringBuilder) {
        String[] ceilingParts = Regex.pregMatch(CEILING_HEIGHT, pRemark);
        int min = Integer.parseInt(ceilingParts[1]) * 100;
        int max = Integer.parseInt(ceilingParts[2]) * 100;
        pStringBuilder.append(fMessages.getString("Remark.Ceiling.Height", min, max)).append(" ");
        return pRemark.replaceFirst(CEILING_HEIGHT.pattern(), "").trim();
    }

    @Override public boolean canParse(final String pInput) {
        return Regex.find(CEILING_HEIGHT, pInput);
    }
}
