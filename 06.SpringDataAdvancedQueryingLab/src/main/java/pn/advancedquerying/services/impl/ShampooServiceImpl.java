package pn.advancedquerying.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pn.advancedquerying.domain.entities.Label;
import pn.advancedquerying.domain.entities.Shampoo;
import pn.advancedquerying.domain.entities.Size;
import pn.advancedquerying.repositories.ShampooRepository;
import pn.advancedquerying.services.ShampooService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<String> getAllBySize(Size size) {
        return this.shampooRepository
                .findAllBySizeOrderById(size)
                .stream()
                .map(s -> String.format("%s %s %.2flv.",
                        s.getBrand(),
                        s.getSize().name(),
                        s.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBySizeOrLabel(Size size, Label label) {
        return this.shampooRepository
                .findAllBySizeOrLabelOrderByPriceAsc(size, label)
                .stream()
                .map(s -> String.format("%s %s %.2flv.",
                        s.getBrand(),
                        s.getSize().name(),
                        s.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllWithGreaterPriceDescByPrice(String price) {
        return this.shampooRepository
                .findAllByPriceGreaterThanOrderByPriceDesc(new BigDecimal(price))
                .stream()
                .map(s -> String.format("%s %s %.2flv.",
                        s.getBrand(),
                        s.getSize().name(),
                        s.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public long countAllWithPriceLessThan(String price) {
        return this.shampooRepository
                .countAllByPriceLessThan(new BigDecimal(price));
    }

    @Override
    public List<String> getAllWithIngredients(List<String> ingredients) {
        return this.shampooRepository.findAllByIngredientsNameIn(ingredients)
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllWithIngredientsLessThan(int numberOfIngredients) {
        return this.shampooRepository
                .findAllByIngredientsLessThan(numberOfIngredients)
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getPriceOfIngredientsOfShampoo(String brand) {
        return this.shampooRepository.getTotalPriceOfIngredients(brand);
    }
}
