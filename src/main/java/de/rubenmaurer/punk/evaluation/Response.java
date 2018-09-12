package de.rubenmaurer.punk.evaluation;

/**
 * Enum with all needed response codes.
 *
 * @author Ruben Maurer
 * @version 1.0
 * @since 1.0
 */
public enum Response {
    NONE (0),
    WELCOME (001),
    YOURHOST (002),
    CREATED (003),
    MY_INFO (004),
    LUSER_CLIENT (251),
    LUSER_OP (252),
    LUSER_UNKNOWN (253),
    LUSER_CHANNEL (254),
    LUSER_ME (255),
    WHO_IS_USER (311),
    WHO_IS_SERVER (312),
    END_OF_WHO (315),
    END_OF_WHO_IS (318),
    LIST (322),
    LIST_END (323),
    NO_TOPIC (331),
    TOPIC (332),
    WHO_RPLY (352),
    NAME_RPLY (353),
    END_OF_NAMES (366),
    MOTD (372),
    MOTD_START (375),
    END_OF_MOTD (376),
    NO_SUCH_NICK (401),
    CANNOT_SEND_TO_CHANNEL (404),
    UNKNOWN_COMMAND (421),
    NO_MOTD (422),
    NICKNAME_IN_USE (433),
    NOT_ON_CHANNEL (442);

    /**
     * The response code.
     */
    public final int value;

    /**
     * Create a new response.
     *
     * @param code the response code
     */
    Response(int code) {
        value = code;
    }
}
