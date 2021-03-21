package de.serviceware.escaperoom.escaperoom.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageContent {
    private String imageURL;
    private String width;
    String heigth;
}
