package de.serviceware.escaperoom.escaperoom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ActiveProfiles(profiles = "KV")
class EscaperoomApplicationTests {
    @Autowired
    EscaperoomController escaperoomController;
    @Test
    void contextLoads() {
    }

    @Test
    void testInit(){
        assertNotNull(escaperoomController.initEscapeRoom());
    }
    @Test
    void testInitInitialRiddle(){
        assertNotNull(escaperoomController.initEscapeRoom().getInitialRiddle());
    }
    @Test
    void testInitInitialRiddleTitle(){
        assertNotNull(escaperoomController.initEscapeRoom().getInitialRiddle());
    }

}
