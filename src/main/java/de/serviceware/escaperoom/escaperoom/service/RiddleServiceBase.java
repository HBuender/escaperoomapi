package de.serviceware.escaperoom.escaperoom.service;

import de.serviceware.escaperoom.escaperoom.model.ImageContent;
import de.serviceware.escaperoom.escaperoom.model.Riddle;
import de.serviceware.escaperoom.escaperoom.model.SolutionProposalResult;

public abstract class RiddleServiceBase implements RiddleService{
    protected SolutionProposalResult getCorrectResult(String riddle, String id, ImageContent imageContent){
        return new SolutionProposalResult(true,new Riddle(riddle,id,imageContent));
    }
    protected SolutionProposalResult getIncorrectResult(){
        return new SolutionProposalResult(false,null);
    }
}
