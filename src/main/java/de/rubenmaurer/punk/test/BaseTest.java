package de.rubenmaurer.punk.test;

import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.junit.PricefieldExtension;
import de.rubenmaurer.punk.Settings;
import de.rubenmaurer.punk.evaluation.antlr.Parser;
import de.rubenmaurer.punk.util.Terminal;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtendWith(PricefieldExtension.class)
public abstract class BaseTest {

    @BeforeAll
    static void beforeAll() {
        Session.initiate(Settings.hostname(), Settings.port(), Settings.executable());

        assumeTrue(Session.isInitiated());
    }

    @AfterAll
    static void afterAll() {
        assumeTrue(Session.terminate());
    }

    @BeforeEach
    void before(TestInfo testInfo) {
        Terminal.printLog("Start test \"" + testInfo.getDisplayName() + "\"");
        Parser.reset();

        assumeTrue(Session.startServer(testInfo.getDisplayName()));
    }

    @AfterEach
    void after() {
        assumeTrue(Session.stopServer());
        Terminal.printLog(Terminal.getDivider("-"));
    }
}
