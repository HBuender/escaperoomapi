package de.serviceware.escaperoom.escaperoom;

import de.serviceware.escaperoom.escaperoom.model.SolutionProposal;
import de.serviceware.escaperoom.escaperoom.model.SolutionProposalResult;
import de.serviceware.escaperoom.escaperoom.service.KVRiddle;
import de.serviceware.escaperoom.escaperoom.service.RiddleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles(profiles = "KV")
class EscaperoomApplicationTests {
    @Autowired
    EscaperoomController escaperoomController;
    @Test
    void contextLoads() {
    }


}
