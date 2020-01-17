package entities.ingredients;

import interfaces.ChemicalIngredient;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public  class BasicChemicalIngredient extends BasicIngredient implements ChemicalIngredient {

    private String chemicalFormula;

    protected BasicChemicalIngredient() {

    }

    public BasicChemicalIngredient(String name, BigDecimal price, String chemicalFormula) {
        super(name, price);
        this.setChemicalFormula(chemicalFormula);
    }

    @Column(name = "chemical_formula")
    @Override
    public String getChemicalFormula() {
        return this.chemicalFormula;
    }

    @Override
    public void setChemicalFormula(String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
    }
}
