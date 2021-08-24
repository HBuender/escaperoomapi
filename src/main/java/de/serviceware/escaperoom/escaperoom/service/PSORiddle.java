package de.serviceware.escaperoom.escaperoom.service;


import de.serviceware.escaperoom.escaperoom.model.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Profile("PSO")
public class PSORiddle  extends RiddleServiceBase{

    private String riddleOne = "You are called to the big conference room in Bad Camberg. Alex is supposedly waiting there with a surprise for you. What exactly it is, you will find out on the spot.\n" +
            "You come to the 3rd floor and find a completely chaotic conference room. Paper is scattered everywhere. There's a flipchart and the TV is turned on.\n" +
            "After a closer look, you notice that there are questions on the flipchart. You see the following on the <br>1st page (Hint Player 1)\n" +
            "<br>2nd page (Hint Player 2)\n" +
            "<br>3rd page (Hint Player 3)\n" +
            "<br>4th page (Hint Player 4)\n" +
            "<br>5th page (Hint Player 5)\n" +
            "<br><br>Use the following formula a+b+c-d-e for your results and you will get to the next room.\n";
    private String riddleTwo="The reason for your appearance in the conference room probably has something to do with the number 500. Maybe it was to organize the celebration of the 500th employee? Or to say “That's How” 500 times in chorus? The only way you'll find out is to find Alex and ask him.\n" +
            "You continue to look around and see an ad scrolling on the large display.\n" +
            "<br>\nNow find out what these codes stand for, find their common competitor and enter his three-digit code as the solution of this riddle.\n";
    private String riddleThree="Well done folks! SJJ is the short name on the Frankfurt stock exchange of the Serviceware share.  Frankfurt what?! Stock exchange?! Is that a clue to Alex's whereabouts?\n" +
            "You run out of the building and get into the car and drive to Frankfurt to the stock exchange. When you arrive in Frankfurt, you witness a chaos." +
            "<br> Bring order to the chaos and write your results in the following notation:\n" +
            "<br> a_bc_d_e\n";

    private String riddleFour="Super! You have gained an overview and have made it to the Frankfurt Stock Exchange. You are now standing in front of the building and see the following notice:\n" +
            "<br>Directions:\n" +
            "<br>Börsenpl. 4, 60313 Frankfurt am Main\n" +
            "<br>-\tYou are standing in front of Frankfurt Stock Exchange and you see the bear and the bull\n" +
            "<br>-\tYou are facing the Euro City bank\n" +
            "<br>-\tYou turn right\n" +
            "<br>-\tAt the end of the road you turn left and then right at the next junction \n" +
            "<br>-\tYou go ahead until you reach a fountain and grab a coffee to go\n" +
            "<br>-\tYou turn right and walk on until you cross a park on the left\n" +
            "<br>-\tYou hear the sound of water like in a fairytale\n" +
            "<br>-\tYou reach the end of the green and turn right\n" +
            "<br>-\tYou follow the street until you see a building with three rectangular courtyards\n" +
            "<br>A.B. Serviceware\n";

    private String finalText="You are at the door of our client Deutsche Vermögensberatung and Alex comes running out of the building to greet you at your destination.\n" +
            "<br><b>When you land here, write the names of your team members in an email to pso.education-management@serviceware.de</b> \n" +
            "<br>You finished the first PSO Escape Room!!!! Whoop Whoop! \uD83D\uDE0A\n" +
            "<br>Come back to the main teams room and celebrate with Alex a great deal at DVAG and that you found him after this scavenger hunt.\n" +
            "<br>Thanks for your participation! We hope you had a little bit of fun during the Escape Room!\n" +
            "<br>Cheers and happy weekend \uD83D\uDE0A\n" +
            "<br>\n" +
            "<br>Your Platform Consulting Meeting Team\n";
    private String escapeRoomTitle = "Welcome to the PSO Escape Room!";
    private String escapeRoomDescription = "Welcome to our virtual Escape Room Game, the one and only 'Escape the MS Teams Room\" challenge! \n" +
            "<br>Your task as a team for the next 60 minutes is to solve four riddles. \n" +
            "<br>The solution of each riddle is a code. After having solved one riddle, you must enter the code within this website. \n" +
            "<br>You will then receive instructions to the next riddle. \n";
    private String hintTitle = "Hints";
    private String hintDescription = "For the first and third riddle there are some hints in the five different pieces. " +
            "<br>Put them together as a team and you can solve the riddle." +
            "<br>It is recommendable that each player takes care of one piece, in order for you to be fast.\n" +
            "<br>The team which solves all the riddles the fastest, wins!”";

    private String areYouReadyTitle="Click, and start!";
    private String areYouReadyDescription="All clear? If so, click below and start";

    @Override
    public EscapeRoom initEscapeRoom() {
        Riddle riddle = new Riddle(this.riddleOne , "Riddle 1", null);
        ImageContent image = new ImageContent("pso/titlePSO.png",null,null);
        return new EscapeRoom(riddle,image, getHints(),getStaticTextContent());
    }

    private StaticTextContent getStaticTextContent() {
        return new StaticTextContent(escapeRoomTitle,escapeRoomDescription,hintTitle,hintDescription,areYouReadyTitle,areYouReadyDescription);
    }

    private List<Hint> getHints(){
        return Arrays.asList(new Hint("Hint Player 1","pso/hints/Hint Player 1.pdf"),
                new Hint("Hint Player 2","pso/hints/Hint Player 2.pdf"),
                new Hint("Hint Player 3","pso/hints/Hint Player 3.pdf"),
                new Hint("Hint Player 4","pso/hints/Hint Player 4.pdf"),
                new Hint("Hint Player 5","pso/hints/Hint Player 5.pdf"));
    }

    @Override
    public SolutionProposalResult validateSolutionProposal(SolutionProposal proposal) {
        if("500".equals(proposal.getProposal())){
            return getCorrectResult(riddleTwo,"Riddle 2",new ImageContent("pd/ticker.gif.txt",null,null));
        }
        if("SJJ".equals(proposal.getProposal())){
            return getCorrectResult(riddleThree,"Riddle 3",new ImageContent("pso/theansweris42.png",null,null));
        }
        if("9_989_1056_42".equals(proposal.getProposal())){
            return getCorrectResult(riddleFour,"Riddle 4",null);
        }
        if("24".equals(proposal.getProposal())){
            return getCorrectResult(finalText,"Congratulations!",new ImageContent("pso/done.jpg",null,null));
        }
        return getIncorrectResult();

    }
}
