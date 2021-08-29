package de.rubenmaurer.punk.util;

import de.rubenmaurer.punk.Pricefield;
import de.rubenmaurer.punk.Settings;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * Class for terminal/ jansi utilty methods.
 *
 * @author Ruben Maurer
 * @version 1.0
 * @since 1.0
 */
public class Terminal {

    /**
     * Terminal line length.
     */
    private static final int LINE_LENGTH = 80;

    /**
     * Size of a cage.
     */
    private static final int CAGE_SIZE = 28;

    /**
     * Print the application header.
     */
    public static void printHeader() {
        System.out.println(ansi()
                .render(Terminal.getDivider())
                .render(Terminal.center(String.format("Project Pricefield | [Version]: %s | [Build]: %s", Settings.version(), Settings.build())))
                .render(Terminal.getDivider())
                .render(Terminal.center(String.format("runtimeID: %s", Pricefield.runtimeID)))
                .render(Terminal.getDivider()));
    }

    /**
     * Get a line divider.
     *
     * @return the divider
     */
    static String getDivider() {
        return getDivider("=");
    }

    /**
     * Get a line divider based on given character.
     *
     * @param lineChar the line divider character
     * @return the divider
     */
    public static String getDivider(String lineChar) {
        return String.format("%s\r\n", StringUtils.repeat(lineChar, LINE_LENGTH));
    }

    /**
     * Center a text.
     *
     * @param input the text to center
     * @return the centered text
     */
    public static String center(String input) {
        return center(input, LINE_LENGTH);
    }

    /**
     * Center a text on given length.
     *
     * @param input the text to center
     * @param length the length to center on
     * @return the centered text
     */
    private static String center(String input, int length) {
        return center(input, length, true);
    }

    /**
     * Center a text on given length.
     *
     * @param input the text to center
     * @param length the length to center on
     * @param newLine new line after center?
     * @return the centered text
     */
    private static String center(String input, int length, boolean newLine) {
        String format = "%s";
        if (newLine) format = "%s\r\n";

        return String.format(format, StringUtils.center(input, length));
    }

    /**
     * Create a table with two columns.
     *
     * @param col1 content for column one
     * @param col2 content for column two
     * @return the table
     */
    public static String twoSidedColumn(String col1, String col2) {
        return twoSidedColumn(col1, col2, 10, false);
    }

    /**
     * Create a table with two columns.
     *
     * @param col1 content for column one
     * @param col2 content for column two
     * @param comp compensation for jansi
     * @param newLine new line after column?
     * @return the table
     */
    private static String twoSidedColumn(String col1, String col2, int comp, boolean newLine) {
        int space = LINE_LENGTH - (col1.length() + col2.length()) + comp;

        if (newLine) {
            return String.format("%s%s%s\r\n", col1, StringUtils.repeat(" ", space), col2);
        }

        return String.format("%s%s%s", col1, StringUtils.repeat(" ", space), col2);
    }

    /**
     * Cage a string with [].
     *
     * @param status the string to cage
     * @return the caged string
     */
    public static String cageStatus(String status) {
        return String.format("[%s]", center(status, CAGE_SIZE, false));
    }

    /**
     * Print an error message.
     *
     * @param message the error message
     */
    public static void printError(String message) {
        printError(message, "pricefield");
    }

    /**
     * Print an error message and it's source.
     *
     * @param message the message
     * @param source the source
     */
    public static void printError(String message, String source) {
        if (message != null && !message.isEmpty()) {
            System.err.print(String.format("%s\r\n\r\n",
                    Template.get("LOG_ERROR")
                            .single("timestamp", new SimpleDateFormat("hh:mm:ss").format(new Date()))
                            .single("message", message)
                            .single("place", source).render()));
        }
    }

    public static void printLastResponse(String... responses) {
        if (responses == null) return;

        System.err.println(Template.get("LAST_RESPONSE")
                .single("response", responses)
                .single("date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()))
                .single("count", String.valueOf(responses.length)).render());
    }

    /**
     * Print a Log message.
     *
     * @param message the message
     */
    public static void printLog(String message) {
        if (message != null && !message.isEmpty()) {
            System.err.println(Template.get("LOG")
                .single("type", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()))
                .single("message", message).render());
        }
    }

    /* === LOGGING === */

    /**
     * Print a 'RECV' message.
     *
     * @param message the message
     */
    public static void debugRecv(String message) {
        System.err.println(Template.recv(message));
    }

    /**
     * Print a 'SEND' message.
     *
     * @param message the message
     */
    public static void debugSend(String message) {
        System.err.println(Template.send(message));
    }

    /**
     * Print a 'ERROR' message.
     *
     * @param message the message
     */
    public static void debugErro(String message) {
        System.err.println(Template.erro(message));
    }

    public static void devLog(String message) {
        System.err.println(Template.log("DEV", message));
    }

    public static void parserLog(String message) {
        System.err.println(Template.log("PARSER", message));
    }
}
