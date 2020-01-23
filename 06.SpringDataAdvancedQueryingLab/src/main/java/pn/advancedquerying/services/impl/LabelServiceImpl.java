package pn.advancedquerying.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pn.advancedquerying.domain.entities.Label;
import pn.advancedquerying.repositories.LabelRepository;
import pn.advancedquerying.services.LabelService;

@Service
public class LabelServiceImpl implements LabelService {
    private final LabelRepository labelRepository;

    @Autowired
    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public Label getOne(Long id) {
        return this.labelRepository.getOne(id);
    }
}
