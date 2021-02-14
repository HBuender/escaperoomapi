package de.serviceware.escaperoom.escaperoom;

import de.serviceware.escaperoom.escaperoom.model.EscapeRoom;
import de.serviceware.escaperoom.escaperoom.model.SolutionProposal;
import de.serviceware.escaperoom.escaperoom.model.SolutionResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@CrossOrigin
public class EscaperoomController {

    private String riddleOne="Let’s start the game with the history geek challenge. Be fast in finding out the correct answers and sum them up to a four-digit code.";
    private String riddleTwo="Great! Now Find the numbers!\n\nFind five numbers on the 'Where’s Waldo' style picture below.\n\n Enter the solution in format: '2:___' replacing the '_' with the correct number, e.g. 2:123";
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
    @PostMapping("/solutionProposal")
    public @ResponseBody SolutionResult getNextRiddlePost(@RequestBody SolutionProposal proposal, @RequestHeader HttpHeaders headers) {
        System.out.println(headers);
        System.out.println(headers.getHost()+": "+proposal.getProposal());
        String result= "";
        if("9886".equals(proposal.getProposal())){
            return new SolutionResult(true,riddleTwo,"2","pd/Wimmelbild.png");
        }
        if("2:123".equals(proposal.getProposal())){
            return new SolutionResult(false,solUnderstood+this.riddleTwo,"2","pd/Wimmelbild.png");
        }
        if("2:332".equals(proposal.getProposal())){
            return new SolutionResult(true,riddleThree,"3","pd/ticker.gif.txt");
        }
        if("SJJ".equals(proposal.getProposal())){
            return new SolutionResult(true,riddleFour,"4",null);
        }
        if("4{871}".equals(proposal.getProposal())){

            return new SolutionResult(true,riddleFive,"5",null);
        }
        if("4{123}".equals(proposal.getProposal())){
            return new SolutionResult(false,solUnderstood+this.riddleFour,"5",null);
        }
        return new SolutionResult(false,"egal","egal",null);
    }





    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public String isHealthy(@RequestHeader HttpHeaders headers){
        System.out.println(headers.getHost()+": Health Check");
        return "Up and running";
    }

    @GetMapping("/initEscapeRoom")
    @ResponseStatus(HttpStatus.OK)
    public EscapeRoom initEscapeRoom(){
        return new EscapeRoom(this.riddleOne);
    }


}

