package de.serviceware.escaperoom.escaperoom.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StaticTextContent {
    private String escapeRoomTitle;
    private String escapeRoomDescription;
    private String hintTitle;
    private String hintDescription;
    private String areYouReadyTitle;
    private String areYourReadyDescription;
}
