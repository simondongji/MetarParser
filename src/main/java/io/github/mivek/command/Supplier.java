package io.github.mivek.command;

/**
 * @param <T> type of command to return.
 * @author mivek
 */
public interface Supplier<T> {

    /**
     * @param pString the string to parse.
     * @return the command able to parse the string.
     */
    T get(String pString);
}

