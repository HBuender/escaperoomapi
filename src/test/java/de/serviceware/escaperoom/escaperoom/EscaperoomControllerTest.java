package de.serviceware.escaperoom.escaperoom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Controller-level tests using MockMvc to verify HTTP endpoints.
 * Uses KV profile as it is the best-tested riddle implementation.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "KV")
public class EscaperoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenGetHealth_thenReturnsOkWithMessage() throws Exception {
        mockMvc.perform(get("/health"))
            .andExpect(status().isOk())
            .andExpect(content().string("Up and running"));
    }

    @Test
    void whenGetInitEscapeRoom_thenReturnsEscapeRoomJson() throws Exception {
        mockMvc.perform(get("/initEscapeRoom"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.initialRiddle").exists())
            .andExpect(jsonPath("$.hints").isArray())
            .andExpect(jsonPath("$.staticTextContent").exists())
            .andExpect(jsonPath("$.picture").exists());
    }

    @Test
    void whenPostCorrectSolution_thenReturnsCorrectTrue() throws Exception {
        mockMvc.perform(post("/solutionProposal")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"proposal\":\"2017\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.correct").value(true))
            .andExpect(jsonPath("$.riddle").exists());
    }

    @Test
    void whenPostIncorrectSolution_thenReturnsCorrectFalse() throws Exception {
        mockMvc.perform(post("/solutionProposal")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"proposal\":\"wrong\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.correct").value(false));
    }

    @Test
    void whenPostSolutionProposal_thenContentTypeIsJson() throws Exception {
        mockMvc.perform(post("/solutionProposal")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"proposal\":\"2017\"}"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void whenPostNullBody_thenReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/solutionProposal")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }
}
