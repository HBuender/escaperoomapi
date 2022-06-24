package de.serviceware.escaperoom.escaperoom;


import de.serviceware.escaperoom.escaperoom.model.Riddle;
import de.serviceware.escaperoom.escaperoom.model.SolutionProposal;
import de.serviceware.escaperoom.escaperoom.model.SolutionProposalResult;
import de.serviceware.escaperoom.escaperoom.service.KVRiddle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles(profiles = "KV")
public class KVRiddleTest {
    @Autowired EscaperoomController escaperoomController;
    @Autowired KVRiddle service;

    private String solutionRiddleOne="2017";
    private String solutionRiddleTwo="13301725";
    private String solutionRiddleThree="1224";
    private String solutionRiddleFour="210,5";
    private String solutionRiddleFive="131221";
    private String solutionRiddleSix="1986";
    private String wrong="wrong";

    @Test
    void testInit(){
        assertNotNull(escaperoomController.initEscapeRoom());
    }
    @Test
    void testInitHints(){
        assertNotNull(escaperoomController.initEscapeRoom().getHints());
    }

    @Test
    void testInitStaticTextContent(){
        assertNotNull(escaperoomController.initEscapeRoom().getStaticTextContent());
    }

    @Test
    void testInitPicture(){
        assertNotNull(escaperoomController.initEscapeRoom().getPicture());
    }


    @Test
    void testInitInitialRiddle(){
        assertNotNull(escaperoomController.initEscapeRoom().getInitialRiddle());
    }
    @Test
    void testInitInitialRiddle_Title(){
        assertEquals("Rätsel 1",escaperoomController.initEscapeRoom().getInitialRiddle().getTitleRiddle());
    }

    @Test
    void testInitInitialRiddle_Image(){
        assertNull(escaperoomController.initEscapeRoom().getInitialRiddle().getImageContent());
    }



    @Test
    void testInitInitialRiddle_Text(){
        assertEquals(KVRiddle.riddleOne,escaperoomController.initEscapeRoom().getInitialRiddle().getRiddle());
    }

    @Test
    void testKVRiddle_Riddle2_Title(){
        assertEquals("Rätsel 2", getRiddleTwo().getTitleRiddle());
    }

    @Test
    void testKVRiddle_Riddle2_Text(){
        assertEquals(KVRiddle.riddleTwo, getRiddleTwo().getRiddle());
    }

    @Test
    void testKVRiddle_Riddle2_Image(){
        assertNull(getRiddleTwo().getImageContent());
    }

    @Test
    void testKVRiddle_Riddle3_Title(){
        assertEquals("Rätsel 3", getRiddleThree().getTitleRiddle());
    }

    @Test
    void testKVRiddle_Riddle3_Text(){
        assertEquals(KVRiddle.riddleThree, getRiddleThree().getRiddle());
    }

    @Test
    void testKVRiddle_Riddle3_Image(){
        assertNull(getRiddleThree().getImageContent());
    }

    @Test
    void testKVRiddle_Riddle4_Title(){
        assertEquals("Rätsel 4", getRiddleFour().getTitleRiddle());
    }

    @Test
    void testKVRiddle_Riddle4_Text(){
        assertEquals(KVRiddle.riddleFour, getRiddleFour().getRiddle());
    }

    @Test
    void testKVRiddle_Riddle4_Image(){
        assertNull(getRiddleFour().getImageContent());
    }

    @Test
    void testKVRiddle_Riddle5_Title(){
        assertEquals("Rätsel 5", getRiddleFive().getTitleRiddle());
    }

    @Test
    void testKVRiddle_Riddle5_Text(){
        assertEquals(KVRiddle.riddleFive, getRiddleFive().getRiddle());
    }

    @Test
    void testKVRiddle_Riddle5_Image(){
        assertNull(getRiddleFive().getImageContent());
    }

    @Test
    void testKVRiddle_Riddle6_Title(){
        assertEquals("Rätsel 6", getRiddleSix().getTitleRiddle());
    }

    @Test
    void testKVRiddle_Riddle6_Text(){
        assertEquals(KVRiddle.riddleSix, getRiddleSix().getRiddle());
    }

    @Test
    void testKVRiddle_Riddle6_Image(){
        assertNull(getRiddleSix().getImageContent());
    }

    @Test
    void testKVRiddle_Final_Title(){
        assertEquals("Geschafft!!", getFinalRiddle().getTitleRiddle());
    }

    @Test
    void testKVRiddle_Final_Text(){
        assertEquals(KVRiddle.finalText, getFinalRiddle().getRiddle());
    }

    @Test
    void testKVRiddle_Final_Image(){
        assertNull(getFinalRiddle().getImageContent());
    }

    @Test
    void testKVRiddle_Wrong_Title(){
        assertNull(getIncorrectResult());
    }


    Riddle getRiddleTwo(){
        return getRiddleForSolution(solutionRiddleOne).getRiddle();
    }

    Riddle getRiddleThree(){
        return getRiddleForSolution(solutionRiddleTwo).getRiddle();
    }

    Riddle getRiddleFour(){
        return getRiddleForSolution(solutionRiddleThree).getRiddle();
    }

    Riddle getRiddleFive(){
        return getRiddleForSolution(solutionRiddleFour).getRiddle();
    }

    Riddle getRiddleSix(){
        return getRiddleForSolution(solutionRiddleFive).getRiddle();
    }

    Riddle getFinalRiddle(){
        return getRiddleForSolution(solutionRiddleSix).getRiddle();
    }
    Riddle getIncorrectResult(){
        return getRiddleForSolution(wrong).getRiddle();
    }

    SolutionProposalResult getRiddleForSolution(String solution){
        return service.validateSolutionProposal(new SolutionProposal(solution));
    }
}
