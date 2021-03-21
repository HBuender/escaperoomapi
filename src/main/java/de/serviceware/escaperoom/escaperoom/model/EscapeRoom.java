package de.serviceware.escaperoom.escaperoom.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EscapeRoom {
    private Riddle initialRiddle;
    private ImageContent picture;
    private List<Hint> hints;
    private StaticTextContent staticTextContent;
}
