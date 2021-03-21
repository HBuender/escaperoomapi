package de.serviceware.escaperoom.escaperoom.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Riddle {
    private String riddle;
    private String titleRiddle;
    private ImageContent imageContent;
}
