package de.serviceware.escaperoom.escaperoom.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SolutionResult {
    private String hint;
    private boolean showImage;
    private boolean correct;
    private boolean showTicker;

}
