package de.serviceware.escaperoom.escaperoom;

import de.serviceware.escaperoom.escaperoom.model.*;
import de.serviceware.escaperoom.escaperoom.service.KV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the KV2 (Scotland Yard) themed escape room riddles.
 * Tests the initialization and validation of riddles and their solutions.
 */
@SpringBootTest
@ActiveProfiles(profiles = "KV2")
public class KV2RiddleTest {
    @Autowired
    private EscaperoomController escaperoomController;

    @Autowired
    private KV2 service;

    private static final String SOLUTION_1 = "85356";
    private static final String SOLUTION_2 = "EW6891";
    private static final String SOLUTION_3 = "Hafen";
    private static final String SOLUTION_4 = "BARCA";
    private static final String SOLUTION_5 = "train";
    private static final String SOLUTION_6 = "50.0897, 14.4221";
    private static final String WRONG = "wrong";

    private EscapeRoom escapeRoom;

    @BeforeEach
    void setUp() {
        escapeRoom = escaperoomController.initEscapeRoom();
    }

    /**
     * Tests the initialization of the escape room.
     * Verifies that all required components are properly initialized.
     */
    @Test
    void whenInitializingEscapeRoom_thenAllComponentsArePresent() {
        assertNotNull(escapeRoom, "Escape room should be initialized");
        assertNotNull(escapeRoom.getHints(), "Hints should be initialized");
        assertNotNull(escapeRoom.getStaticTextContent(), "Static text content should be initialized");
        assertNotNull(escapeRoom.getPicture(), "Picture should be initialized");
        assertEquals("kv2/KV-Scotland-Yard.jpg", escapeRoom.getPicture().getImageURL(),
            "Picture URL should match");

        Riddle initialRiddle = escapeRoom.getInitialRiddle();
        assertNotNull(initialRiddle, "Initial riddle should be initialized");
        assertEquals("Rätsel 1", initialRiddle.getTitleRiddle(), "Initial riddle title should be 'Rätsel 1'");
        assertNull(initialRiddle.getImageContent(), "Initial riddle should not have an image");
    }

    /**
     * Tests the initialization of the escape room.
     * Verifies that the static text content is correct.
     */
    @Test
    void whenInitializingEscapeRoom_thenStaticTextContentIsCorrect() {
        StaticTextContent staticTextContent = escapeRoom.getStaticTextContent();
        assertNotNull(staticTextContent, "Static text content should be initialized");

        assertEquals("Herzlich Willkommen bei KV Scotland Yard", staticTextContent.getEscapeRoomTitle(),
            "Escape room title should match");
        assertNotNull(staticTextContent.getEscapeRoomDescription(),
            "Escape room description should not be null");
        assertEquals("Hinweise", staticTextContent.getHintTitle(),
            "Hint title should match");
        assertNotNull(staticTextContent.getHintDescription(),
            "Hint description should not be null");
        assertEquals("Klickt hier und es geht los!", staticTextContent.getAreYouReadyTitle(),
            "Ready title should match");
        assertEquals("Habt ihr die Hinweise verteilt und die Aufgaben verstanden?",
            staticTextContent.getAreYourReadyDescription(),
            "Ready description should match");
    }

    /**
     * Tests the initialization of the escape room.
     * Verifies that the hints are correct.
     */
    @Test
    void whenInitializingEscapeRoom_thenHintsAreCorrect() {
        List<Hint> hints = escapeRoom.getHints();
        assertNotNull(hints, "Hints should be initialized");
        assertEquals(5, hints.size(), "There should be 5 hints");

        for (int i = 0; i < hints.size(); i++) {
            Hint hint = hints.get(i);
            assertNotNull(hint, "Hint " + (i + 1) + " should not be null");
            assertEquals("Hinweis Spieler " + (i + 1), hint.getTitle(),
                "Hint " + (i + 1) + " title should match");
            assertEquals("kv2/hints/TN" + (i + 1) + ".pdf", hint.getUrl(),
                "Hint " + (i + 1) + " URL should match");
        }
    }

    /**
     * Tests the progression through riddles 2-6 and the final text.
     * Verifies that each riddle's title and image content are correct.
     */
    @Test
    void whenSolvingRiddles_thenNextRiddleIsCorrect() {
        Riddle riddleTwo = getRiddleForSolution(SOLUTION_1).getRiddle();
        assertNotNull(riddleTwo, "Riddle 2 should be initialized");
        assertEquals("Rätsel 2", riddleTwo.getTitleRiddle(), "Riddle 2 title should be 'Rätsel 2'");
        assertNull(riddleTwo.getImageContent(), "Riddle 2 should not have an image");

        Riddle riddleThree = getRiddleForSolution(SOLUTION_2).getRiddle();
        assertNotNull(riddleThree, "Riddle 3 should be initialized");
        assertEquals("Rätsel 3", riddleThree.getTitleRiddle(), "Riddle 3 title should be 'Rätsel 3'");
        assertNull(riddleThree.getImageContent(), "Riddle 3 should not have an image");

        Riddle riddleFour = getRiddleForSolution(SOLUTION_3).getRiddle();
        assertNotNull(riddleFour, "Riddle 4 should be initialized");
        assertEquals("Rätsel 4", riddleFour.getTitleRiddle(), "Riddle 4 title should be 'Rätsel 4'");
        assertNull(riddleFour.getImageContent(), "Riddle 4 should not have an image");

        Riddle riddleFive = getRiddleForSolution(SOLUTION_4).getRiddle();
        assertNotNull(riddleFive, "Riddle 5 should be initialized");
        assertEquals("Rätsel 5", riddleFive.getTitleRiddle(), "Riddle 5 title should be 'Rätsel 5'");
        assertNotNull(riddleFive.getImageContent(), "BARCA solution should return an image");
        assertEquals("kv2/Full.png", riddleFive.getImageContent().getImageURL(),
            "BARCA image URL should match");

        Riddle riddleSix = getRiddleForSolution(SOLUTION_5).getRiddle();
        assertNotNull(riddleSix, "Riddle 6 should be initialized");
        assertEquals("Rätsel 6", riddleSix.getTitleRiddle(), "Riddle 6 title should be 'Rätsel 6'");
        assertNull(riddleSix.getImageContent(), "Riddle 6 should not have an image");

        Riddle finalRiddle = getRiddleForSolution(SOLUTION_6).getRiddle();
        assertNotNull(finalRiddle, "Final riddle should be initialized");
        assertEquals("Herzlichen Glückwunsch!", finalRiddle.getTitleRiddle(),
            "Final riddle title should be 'Herzlichen Glückwunsch!'");
        assertNull(finalRiddle.getImageContent(), "Final riddle should not have an image");
    }

    @ParameterizedTest
    @CsvSource(value = {
        "85356, true",
        "EW6891, true",
        "Hafen, true",
        "BARCA, true",
        "train, true",
        "'50.0897, 14.4221', true",
        "wrong, false",
        ", false"
    })
    void whenValidatingSolution_thenResultIsCorrect(String solution, boolean expectedResult) {
        SolutionProposalResult result = getRiddleForSolution(solution);
        assertEquals(expectedResult, result.isCorrect(),
            "Solution validation result should match expected result");
    }

    @Test
    void whenSubmittingWrongSolution_thenResultIsIncorrect() {
        SolutionProposalResult result = getRiddleForSolution(WRONG);
        assertFalse(result.isCorrect(), "Wrong solution should be incorrect");
        assertNull(result.getRiddle(), "Wrong solution should return null riddle");
    }

    @Test
    void whenSubmittingNullProposal_thenNullPointerExceptionIsThrown() {
        assertThrows(NullPointerException.class,
            () -> service.validateSolutionProposal(null),
            "Null proposal should throw NullPointerException");
    }

    private SolutionProposalResult getRiddleForSolution(String solution) {
        if (solution == null) {
            return service.validateSolutionProposal(new SolutionProposal(null));
        }
        return service.validateSolutionProposal(new SolutionProposal(solution));
    }
}
