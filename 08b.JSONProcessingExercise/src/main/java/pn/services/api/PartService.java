package pn.services.api;

import pn.models.entities.Part;

import java.util.List;

public interface PartService {
    void seedMultipleParts(String path);

    Part getRandomPart();

    List<Part> getAllParts();
}
