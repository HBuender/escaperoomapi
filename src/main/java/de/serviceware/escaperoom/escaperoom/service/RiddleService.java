package de.serviceware.escaperoom.escaperoom.service;

import de.serviceware.escaperoom.escaperoom.model.EscapeRoom;
import de.serviceware.escaperoom.escaperoom.model.Riddle;
import de.serviceware.escaperoom.escaperoom.model.SolutionProposal;
import de.serviceware.escaperoom.escaperoom.model.SolutionProposalResult;

public interface RiddleService {
    public EscapeRoom initEscapeRoom();
    public SolutionProposalResult validateSolutionProposal(SolutionProposal proposal);

}
