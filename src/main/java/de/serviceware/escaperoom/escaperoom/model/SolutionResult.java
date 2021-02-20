package de.serviceware.escaperoom.escaperoom.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SolutionResult {
    private boolean correct;
    private String riddle;
    private String titleRiddle;
    private String imageURL;
}
