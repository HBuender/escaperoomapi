package de.serviceware.escaperoom.escaperoom.model;

import de.serviceware.escaperoom.escaperoom.EscaperoomController;
import de.serviceware.escaperoom.escaperoom.model.EscapeRoom;
import de.serviceware.escaperoom.escaperoom.model.ImageContent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles(profiles = "KV")
public class EscaperoomModelTest {
    @Autowired
    EscaperoomController escaperoomController;

    @Test
    void testSetterPicture(){
        EscapeRoom escapeRoom = escaperoomController.initEscapeRoom();
        escapeRoom.setPicture(new ImageContent("test","10","10"));
        assertEquals("test",escapeRoom.getPicture().getImageURL());
    }
}
