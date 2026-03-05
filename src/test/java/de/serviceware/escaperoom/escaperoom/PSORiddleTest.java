package de.serviceware.escaperoom.escaperoom;

import de.serviceware.escaperoom.escaperoom.model.*;
import de.serviceware.escaperoom.escaperoom.service.PSORiddle;
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
 * Test class for the PSO-themed escape room riddles.
 * Tests the initialization and validation of riddles and their solutions.
 */
@SpringBootTest
@ActiveProfiles(profiles = "PSO")
public class PSORiddleTest {
    @Autowired
    private EscaperoomController escaperoomController;

    @Autowired
    private PSORiddle service;

    private static final String SOLUTION_1 = "500";
    private static final String SOLUTION_2 = "SJJ";
    private static final String SOLUTION_3 = "9_989_1056_42";
    private static final String SOLUTION_4 = "24";
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
        assertEquals("pso/titlePSO.png", escapeRoom.getPicture().getImageURL(),
            "Picture URL should match");

        Riddle initialRiddle = escapeRoom.getInitialRiddle();
        assertNotNull(initialRiddle, "Initial riddle should be initialized");
        assertEquals("Riddle 1", initialRiddle.getTitleRiddle(), "Initial riddle title should be 'Riddle 1'");
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

        assertEquals("Welcome to the PSO Escape Room!", staticTextContent.getEscapeRoomTitle(),
            "Escape room title should match");
        assertNotNull(staticTextContent.getEscapeRoomDescription(),
            "Escape room description should not be null");
        assertEquals("Hints", staticTextContent.getHintTitle(),
            "Hint title should match");
        assertNotNull(staticTextContent.getHintDescription(),
            "Hint description should not be null");
        assertEquals("Click, and start!", staticTextContent.getAreYouReadyTitle(),
            "Ready title should match");
        assertEquals("All clear? If so, click below and start", staticTextContent.getAreYourReadyDescription(),
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
            assertEquals("Hint Player " + (i + 1), hint.getTitle(),
                "Hint " + (i + 1) + " title should match");
            assertEquals("pso/hints/Hint Player " + (i + 1) + ".pdf", hint.getUrl(),
                "Hint " + (i + 1) + " URL should match");
        }
    }

    /**
     * Tests the progression through all riddles.
     * Verifies titles and image content for each riddle.
     */
    @Test
    void whenSolvingRiddles_thenNextRiddleIsCorrect() {
        SolutionProposalResult result1 = getRiddleForSolution(SOLUTION_1);
        assertTrue(result1.isCorrect(), "Solution 1 should be correct");
        Riddle riddleTwo = result1.getRiddle();
        assertNotNull(riddleTwo, "Riddle 2 should be initialized");
        assertEquals("Riddle 2", riddleTwo.getTitleRiddle(), "Riddle 2 title should be 'Riddle 2'");
        assertNotNull(riddleTwo.getImageContent(), "Riddle 2 should have an image");
        assertEquals("pd/ticker.gif.txt", riddleTwo.getImageContent().getImageURL(),
            "Riddle 2 image URL should match");

        SolutionProposalResult result2 = getRiddleForSolution(SOLUTION_2);
        assertTrue(result2.isCorrect(), "Solution 2 should be correct");
        Riddle riddleThree = result2.getRiddle();
        assertNotNull(riddleThree, "Riddle 3 should be initialized");
        assertEquals("Riddle 3", riddleThree.getTitleRiddle(), "Riddle 3 title should be 'Riddle 3'");
        assertNotNull(riddleThree.getImageContent(), "Riddle 3 should have an image");
        assertEquals("pso/theansweris42.png", riddleThree.getImageContent().getImageURL(),
            "Riddle 3 image URL should match");

        SolutionProposalResult result3 = getRiddleForSolution(SOLUTION_3);
        assertTrue(result3.isCorrect(), "Solution 3 should be correct");
        Riddle riddleFour = result3.getRiddle();
        assertNotNull(riddleFour, "Riddle 4 should be initialized");
        assertEquals("Riddle 4", riddleFour.getTitleRiddle(), "Riddle 4 title should be 'Riddle 4'");
        assertNull(riddleFour.getImageContent(), "Riddle 4 should not have an image");

        SolutionProposalResult result4 = getRiddleForSolution(SOLUTION_4);
        assertTrue(result4.isCorrect(), "Solution 4 should be correct");
        Riddle finalRiddle = result4.getRiddle();
        assertNotNull(finalRiddle, "Final riddle should be initialized");
        assertEquals("Congratulations!", finalRiddle.getTitleRiddle(),
            "Final riddle title should be 'Congratulations!'");
        assertNotNull(finalRiddle.getImageContent(), "Final riddle should have an image");
        assertEquals("pso/done.jpg", finalRiddle.getImageContent().getImageURL(),
            "Final image URL should match");
    }

    @ParameterizedTest
    @CsvSource(value = {
        "500, true",
        "SJJ, true",
        "9_989_1056_42, true",
        "24, true",
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
