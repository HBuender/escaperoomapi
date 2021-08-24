package de.serviceware.escaperoom.escaperoom;

import de.serviceware.escaperoom.escaperoom.model.EscapeRoom;
import de.serviceware.escaperoom.escaperoom.model.SolutionProposal;
import de.serviceware.escaperoom.escaperoom.model.SolutionProposalResult;
import de.serviceware.escaperoom.escaperoom.service.RiddleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EscaperoomController {

    @Autowired
    RiddleService riddleService;

    @PostMapping("/solutionProposal")
    public @ResponseBody
    SolutionProposalResult getNextRiddlePost(@RequestBody SolutionProposal proposal, @RequestHeader HttpHeaders headers) {
        System.out.println(headers);
        System.out.println(headers.getHost()+": "+proposal.getProposal());
        return riddleService.validateSolutionProposal(proposal);
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
        return riddleService.initEscapeRoom();
    }


}

