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

    private String riddleOne="Let’s start the Connection Room! To keep an overview over the market and the potential of excellent service, you need to follow the Serviceware Blog and have all the numbers together.";
    private String riddleTwo="Great! Now do some down to earth puzzling to see what AI can do for you! Putting the number of requestors, service agents and managers in the correct order gives you the next code.";
    private String finalText="You have completed the Connection Room Successfully. Find you next session behind this link.";
    @PostMapping("/solutionProposal")
    public @ResponseBody SolutionResult getNextRiddlePost(@RequestBody SolutionProposal proposal, @RequestHeader HttpHeaders headers) {
        System.out.println(headers);
        System.out.println(headers.getHost()+": "+proposal.getProposal());
        String result= "";
        if("304".equals(proposal.getProposal())){
            return new SolutionResult(true,riddleTwo,"Riddle 2",null);
        }
        if("332".equals(proposal.getProposal())){
            return new SolutionResult(true,finalText,"You did it!   ",null);
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
        return new EscapeRoom(this.riddleOne,"Riddle One");
    }


}

