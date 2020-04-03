package io.github.mivek.command.common;

import io.github.mivek.model.AbstractWeatherContainer;

/**
 * @author mivek
 */
public interface Command {

    /**
     * Handles the pPart and updates the pContainer.
     *
     * @param pContainer the container to update.
     * @param pPart      the string to parse.
     * @return true if the part has been properly handled false otherwise
     */
    boolean execute(AbstractWeatherContainer pContainer, String pPart);

    /**
     * @return the default return value of a command.
     */
    boolean getReturnValue();

    /**
     * @param pInput the input string to test.
     * @return true if the input can be handled by the command.
     */
    boolean canParse(String pInput);
}
