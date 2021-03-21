package de.serviceware.escaperoom.escaperoom.service;

import de.serviceware.escaperoom.escaperoom.model.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Profile("PD")
public class PDRiddle extends RiddleServiceBase{
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

    public EscapeRoom initEscapeRoom() {
        Riddle riddle = new Riddle(this.riddleOne , "Riddle 1", null);
        ImageContent image = new ImageContent("pd/AlwaysTogetherAlwaysLockedUp.png",null,null);
        return new EscapeRoom(riddle,image, getHints(),getStaticTextContent());
    }

    private List<Hint> getHints(){
        return Arrays.asList(new Hint("Hint Player 1","pd/hints/A.pdf"),
                new Hint("Hint Player 2","pd/hints/B.pdf"),
                new Hint("Hint Player 3","pd/hints/C.pdf"),
                new Hint("Hint Player 4","pd/hints/D.pdf"),
                new Hint("Hint Player 5","pd/hints/E.pdf"));
    }

    private StaticTextContent getStaticTextContent(){
        return new StaticTextContent(escapeRoomTitle,escapeRoomDescription,hintTitle,hintDescription,areYouReadyTitle,areYouReadyDescription);
    }

    @Override
    public SolutionProposalResult validateSolutionProposal(SolutionProposal proposal) {
        if("9886".equals(proposal.getProposal())){

            return getCorrectResult(riddleTwo,"Riddle 2",new ImageContent("pd/Wimmelbild.png","1200","300"));
        }
        if("2:123".equals(proposal.getProposal())){
            return getCorrectResult(solUnderstood+riddleTwo,"Riddle 2", new ImageContent("pd/Wimmelbild.png","1200","300"));
        }
        if("2:332".equals(proposal.getProposal())){
            return getCorrectResult(riddleThree,"Riddle 3",new ImageContent("pd/ticker.gif.txt",null,null));
        }
        if("SJJ".equals(proposal.getProposal())){
            return getCorrectResult(riddleFour,"Riddle 4",null);
        }
        if("4{871}".equals(proposal.getProposal())){
            return getCorrectResult(riddleFive,"Riddle 5",null);
        }
        if("4{123}".equals(proposal.getProposal())){
            return getCorrectResult(solUnderstood+riddleFive,"Riddle 5",null);
        }
        return getIncorrectResult();
    }
}
