package de.serviceware.escaperoom.escaperoom;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class EscaperoomApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testInit(){
        EscaperoomController escaperoomController = new EscaperoomController();
        assertNotNull(escaperoomController.initEscapeRoom());
    }
    @Test
    void testInitInitialRiddle(){
        EscaperoomController escaperoomController = new EscaperoomController();
        assertNotNull(escaperoomController.initEscapeRoom().getInitialRiddle());
    }
    @Test
    void testInitInitialRiddleTitle(){
        EscaperoomController escaperoomController = new EscaperoomController();
        assertNotNull(escaperoomController.initEscapeRoom().getTitleRiddle());
    }

}
