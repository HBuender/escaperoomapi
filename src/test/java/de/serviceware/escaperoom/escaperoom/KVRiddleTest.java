package de.serviceware.escaperoom.escaperoom;

import de.serviceware.escaperoom.escaperoom.model.*;
import de.serviceware.escaperoom.escaperoom.service.KVRiddle;
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
 * Test class for the KV-themed escape room riddles.
 * Tests the initialization and validation of riddles and their solutions.
 */
@SpringBootTest
@ActiveProfiles(profiles = "KV")
public class KVRiddleTest {
    @Autowired 
    private EscaperoomController escaperoomController;
    
    @Autowired 
    private KVRiddle service;

    private static final String SOLUTION_1 = KVRiddle.SOLUTION_1;
    private static final String SOLUTION_2 = KVRiddle.SOLUTION_2;
    private static final String SOLUTION_3 = KVRiddle.SOLUTION_3;
    private static final String SOLUTION_4 = KVRiddle.SOLUTION_4;
    private static final String SOLUTION_5 = KVRiddle.SOLUTION_5;
    private static final String SOLUTION_6 = KVRiddle.SOLUTION_6;
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
        
        Riddle initialRiddle = escapeRoom.getInitialRiddle();
        assertNotNull(initialRiddle, "Initial riddle should be initialized");
        assertEquals("Rätsel 1", initialRiddle.getTitleRiddle(), "Initial riddle title should be 'Rätsel 1'");
        assertNull(initialRiddle.getImageContent(), "Initial riddle should not have an image");
        assertEquals(KVRiddle.getRiddleOne(), initialRiddle.getRiddle(), "Initial riddle text should match");
    }

    /**
     * Tests the initialization of the escape room.
     * Verifies that the static text content is correct.
     */
    @Test
    void whenInitializingEscapeRoom_thenStaticTextContentIsCorrect() {
        StaticTextContent staticTextContent = escapeRoom.getStaticTextContent();
        assertNotNull(staticTextContent, "Static text content should be initialized");

        assertEquals(KVRiddle.getEscapeRoomTitle(), staticTextContent.getEscapeRoomTitle(), 
            "Escape room title should match");
        assertEquals(KVRiddle.getEscapeRoomDescription(), staticTextContent.getEscapeRoomDescription(), 
            "Escape room description should match");
        assertEquals(KVRiddle.getHintTitle(), staticTextContent.getHintTitle(), 
            "Hint title should match");
        assertEquals(KVRiddle.getHintDescription(), staticTextContent.getHintDescription(), 
            "Hint description should match");
        assertEquals(KVRiddle.getReadyTitle(), staticTextContent.getAreYouReadyTitle(), 
            "Ready title should match");
        assertEquals(KVRiddle.getReadyDescription(), staticTextContent.getAreYourReadyDescription(), 
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
        assertEquals(4, hints.size(), "There should be 4 hints");

        for (int i = 0; i < hints.size(); i++) {
            Hint hint = hints.get(i);
            assertNotNull(hint, "Hint " + (i + 1) + " should not be null");
            assertEquals("Hinweis Spieler " + (i + 1), hint.getTitle(), 
                "Hint " + (i + 1) + " title should match");
            assertEquals("kv/hints/KV_Escaperoom_Player" + (i + 1) + ".pdf", hint.getUrl(), 
                "Hint " + (i + 1) + " URL should match");
        }
    }

    /**
     * Tests the progression through riddles 2-6.
     * Verifies that each riddle's title, text and image content are correct.
     */
    @Test
    void whenSolvingRiddles_thenNextRiddleIsCorrect() {
        Riddle riddleTwo = getRiddleTwo();
        assertNotNull(riddleTwo, "Riddle 2 should be initialized");
        assertEquals("Rätsel 2", riddleTwo.getTitleRiddle(), "Riddle 2 title should be 'Rätsel 2'");
        assertEquals(KVRiddle.getRiddleTwo(), riddleTwo.getRiddle(), "Riddle 2 text should match");
        assertNull(riddleTwo.getImageContent(), "Riddle 2 should not have an image");

        Riddle riddleThree = getRiddleThree();
        assertNotNull(riddleThree, "Riddle 3 should be initialized");
        assertEquals("Rätsel 3", riddleThree.getTitleRiddle(), "Riddle 3 title should be 'Rätsel 3'");
        assertEquals(KVRiddle.getRiddleThree(), riddleThree.getRiddle(), "Riddle 3 text should match");
        assertNull(riddleThree.getImageContent(), "Riddle 3 should not have an image");

        Riddle riddleFour = getRiddleFour();
        assertNotNull(riddleFour, "Riddle 4 should be initialized");
        assertEquals("Rätsel 4", riddleFour.getTitleRiddle(), "Riddle 4 title should be 'Rätsel 4'");
        assertEquals(KVRiddle.getRiddleFour(), riddleFour.getRiddle(), "Riddle 4 text should match");
        assertNull(riddleFour.getImageContent(), "Riddle 4 should not have an image");

        Riddle riddleFive = getRiddleFive();
        assertNotNull(riddleFive, "Riddle 5 should be initialized");
        assertEquals("Rätsel 5", riddleFive.getTitleRiddle(), "Riddle 5 title should be 'Rätsel 5'");
        assertEquals(KVRiddle.getRiddleFive(), riddleFive.getRiddle(), "Riddle 5 text should match");
        assertNull(riddleFive.getImageContent(), "Riddle 5 should not have an image");

        Riddle riddleSix = getRiddleSix();
        assertNotNull(riddleSix, "Riddle 6 should be initialized");
        assertEquals("Rätsel 6", riddleSix.getTitleRiddle(), "Riddle 6 title should be 'Rätsel 6'");
        assertEquals(KVRiddle.getRiddleSix(), riddleSix.getRiddle(), "Riddle 6 text should match");
        assertNull(riddleSix.getImageContent(), "Riddle 6 should not have an image");
    }

    /**
     * Tests the final riddle and incorrect solution handling.
     * Verifies that the final riddle is correct and incorrect solutions are handled properly.
     */
    @Test
    void whenReachingFinalRiddle_thenFinalTextIsCorrect() {
        Riddle finalRiddle = getFinalRiddle();
        assertNotNull(finalRiddle, "Final riddle should be initialized");
        assertEquals("Geschafft!!", finalRiddle.getTitleRiddle(), "Final riddle title should be 'Geschafft!!'");
        assertEquals(KVRiddle.getFinalText(), finalRiddle.getRiddle(), "Final riddle text should match");
        assertNull(finalRiddle.getImageContent(), "Final riddle should not have an image");

        assertNull(getIncorrectResult(), "Incorrect solution should return null");
    }

    @ParameterizedTest
    @CsvSource(value = {
        "2017, true",
        "13301725, true",
        "1224, true",
        "'210,5', true",
        "131221, true",
        "1986, true",
        "wrong, false",
        ", false",
        "'null', false"
    })
    void whenValidatingSolution_thenResultIsCorrect(String solution, boolean expectedResult) {
        SolutionProposalResult result = getRiddleForSolution(solution);
        assertEquals(expectedResult, result.isCorrect(), 
            "Solution validation result should match expected result");
    }

    @Test
    void whenValidatingNullSolution_thenResultIsIncorrect() {
        SolutionProposalResult result = service.validateSolutionProposal(null);
        assertFalse(result.isCorrect(), "Null solution should be incorrect");
        assertNull(result.getRiddle(), "Null solution should return null riddle");
    }

    /**
     * Helper method to get riddle two.
     * @return The riddle object for riddle two
     */
    private Riddle getRiddleTwo() {
        return getRiddleForSolution(SOLUTION_1).getRiddle();
    }

    /**
     * Helper method to get riddle three.
     * @return The riddle object for riddle three
     */
    private Riddle getRiddleThree() {
        return getRiddleForSolution(SOLUTION_2).getRiddle();
    }

    /**
     * Helper method to get riddle four.
     * @return The riddle object for riddle four
     */
    private Riddle getRiddleFour() {
        return getRiddleForSolution(SOLUTION_3).getRiddle();
    }

    /**
     * Helper method to get riddle five.
     * @return The riddle object for riddle five
     */
    private Riddle getRiddleFive() {
        return getRiddleForSolution(SOLUTION_4).getRiddle();
    }

    /**
     * Helper method to get riddle six.
     * @return The riddle object for riddle six
     */
    private Riddle getRiddleSix() {
        return getRiddleForSolution(SOLUTION_5).getRiddle();
    }

    /**
     * Helper method to get the final riddle.
     * @return The riddle object for the final riddle
     */
    private Riddle getFinalRiddle() {
        return getRiddleForSolution(SOLUTION_6).getRiddle();
    }

    /**
     * Helper method to get the result for an incorrect solution.
     * @return The riddle object for an incorrect solution
     */
    private Riddle getIncorrectResult() {
        return getRiddleForSolution(WRONG).getRiddle();
    }

    /**
     * Helper method to get a riddle for a given solution.
     * @param solution The solution to validate
     * @return The solution proposal result
     */
    private SolutionProposalResult getRiddleForSolution(String solution) {
        if (solution == null || "null".equals(solution)) {
            return service.validateSolutionProposal(null);
        }
        return service.validateSolutionProposal(new SolutionProposal(solution));
    }
}
