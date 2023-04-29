package de.serviceware.escaperoom.escaperoom;
import de.serviceware.escaperoom.escaperoom.model.Riddle;
import de.serviceware.escaperoom.escaperoom.model.SolutionProposal;
import de.serviceware.escaperoom.escaperoom.model.SolutionProposalResult;
import de.serviceware.escaperoom.escaperoom.service.KVRiddle;
import de.serviceware.escaperoom.escaperoom.service.PDRiddle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles(profiles = "PD")
public class PDRiddleTest {
    // test EscapeRoomController
    @Autowired EscaperoomController escaperoomController;
    @Autowired PDRiddle service;
    // test Riddle
    private String solutionRiddleOne="9886";
    private String solutionRiddleTwo="13301725";
    private String solutionRiddleThree="1224";
    private String solutionRiddleFour="210,5";
    private String solutionRiddleFive="131221";
    private String solutionRiddleSix="1986";
    private String wrong="wrong";

    private String riddleOne = "Let’s start the game with the history geek challenge. Be fast in finding out the correct answers and sum them up to a four-digit code.";
    private String riddleTwo="Great! Now Find the numbers!\n\nFind five numbers on the 'Where’s Waldo' style picture above.\n\n Enter the solution in format: '2:___' replacing the '_' with the correct number, e.g. 2:123";
    private String riddleThree="Well done!\nNow find out what these codes stand for, find their common competitor and enter his three-digit code as the solution of this riddle.\n";
    private String riddleFour="Fantastic!\n\nNow let’s do some real visual down-to-earth-puzzling.\n\n Enter the solution in format: '4{___}' replacing the '_' with the correct number, e.g. 4{123})";
    private String riddleFive="“You rock! You’ve almost done it. Now tackle the very simple last riddle:\n\n" +
            "\t- Come up with a creative team name\n" +
            "\t- Take a screenshot of you guys in your MS Teams Room\n" +
            "\t- Via Email, send the screenshot to Cordula and Hendrik (Subject of the email shall be your team name).\n" +
            "\t- The time of the email being received counts as your finishing time\n" +
            "\t- Now: Be patient! Later today, we will announce the scoring of all teams in the PD team channel.\n" +
            "\n\n" +
            "You are now finished with the riddles, we hope you had some fun! If there is time left until the official end of this years kick-off, you can just keep on chatting with your team mates or join one of the discussion sessions on either product or technology strategy.\n" +
            "\n";
    private String solUnderstood="Great you understood how to exchange the '_' for digits, now go and solve the riddle ;)\n\n\n";
    private String escapeRoomTitle = "Welcome to the PD Escape Room";
    private String escapeRoomDescription = "“Welcome to our virtual Escape Room Game, the one and only “Escape the MS Teams Room” challenge!\n" +
            "<br>Your task as a team for the next <b>60 minutes</b> is to solve four riddles.\n" +
            "<br>The solution to each riddle is a code. After having solved one riddle, you must enter the code within this website.\n" +
            "<br>You will then receive instructions to the next riddle.";

    private String hintTitle = "Hints";
    private String hintDescription = "For each of the first three riddles, there are five different pieces that you as a team must put together." +
            "<br>It is recommendable that each player takes care of one piece, in order for you to be fast.\n" +
            "<br>The team which solves all the riddles the fastest, wins!”";

    private String areYouReadyTitle="Click, and start!";
    private String areYouReadyDescription="All clear? If so, click below and start";


    // test initEscapeRoom()
    @Test
    void testInit(){
        assertNotNull(escaperoomController.initEscapeRoom());
    }
    //test initEscapeRoom().getHints()
    @Test
    void testInitHints(){
        assertNotNull(escaperoomController.initEscapeRoom().getHints());
    }
    //test initEscapeRoom().getStaticTextContent()
    @Test
    void testInitStaticTextContent(){
        assertNotNull(escaperoomController.initEscapeRoom().getStaticTextContent());
    }
    //test initEscapeRoom().getPicture()
    @Test
    void testInitPicture(){
        assertNotNull(escaperoomController.initEscapeRoom().getPicture());
    }
    //test initEscapeRoom().getInitialRiddle()
    @Test
    void testInitInitialRiddle(){
        assertNotNull(escaperoomController.initEscapeRoom().getInitialRiddle());
    }
    //test initEscapeRoom().getInitialRiddle().getTitleRiddle()
    @Test
    void testInitInitialRiddle_Title(){
        assertEquals("Riddle 1",escaperoomController.initEscapeRoom().getInitialRiddle().getTitleRiddle());
    }
    //test initEscapeRoom().getInitialRiddle().getImage()
    @Test
    void testInitInitialRiddle_Image(){
        assertNull(escaperoomController.initEscapeRoom().getInitialRiddle().getImageContent());
    }
    //test initEscapeRoom().getInitialRiddle().getQuestion()
    @Test
    void testInitInitialRiddle_Question(){
        assertEquals(riddleOne,escaperoomController.initEscapeRoom().getInitialRiddle().getRiddle());
    }
    //test initEscapeRoom().getInitialRiddle().getSolutionProposal()
    @Test
    void testInitInitialRiddle_SolutionProposal(){
        assertNotNull(service.validateSolutionProposal(new SolutionProposal(solutionRiddleOne)));
        SolutionProposalResult validateSolutionProposal = service.validateSolutionProposal(new SolutionProposal(solutionRiddleOne));
        assertTrue(validateSolutionProposal.isCorrect());
    }

}
