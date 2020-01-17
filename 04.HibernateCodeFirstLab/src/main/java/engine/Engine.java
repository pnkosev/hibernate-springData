package engine;

import entities.ingredients.BasicIngredient;
import entities.ingredients.chemical.AmmoniumChloride;
import entities.ingredients.natural.Lavender;
import entities.ingredients.natural.Mint;
import entities.ingredients.natural.Nettle;
import entities.ingredients.natural.Strawberry;
import entities.labels.BasicLabel;
import entities.shampoo.BasicShampoo;
import entities.shampoo.concrete_shampoos.FiftyShades;
import entities.shampoo.concrete_shampoos.FreshNuke;
import entities.shampoo.concrete_shampoos.PinkPanther;
import interfaces.Shampoo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Engine implements Runnable {

    private EntityManager entityManager;

    public Engine() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shampoo_company");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void run() {
//        this.inTransaction(this.entityManager,
//                () -> {
//                    Shampoo shampoo = this.entityManager.find(BasicShampoo.class, 1L);
//                    System.out.println(shampoo.getLabel().getTitle());
//                });
        this.inTransaction(this.entityManager,
                this::createFreshNukeShampoo);

        this.inTransaction(this.entityManager,
                this::createFiftyShadesShampoo);

        this.inTransaction(this.entityManager,
                this::createPinkPantherShampoo);

        this.entityManager.close();
    }


    void createFreshNukeShampoo() {

        BasicIngredient am = new AmmoniumChloride();
        BasicIngredient mint = new Mint();
        BasicIngredient nettle = new Nettle();

        BasicLabel label = new BasicLabel("Fresh Nuke Shampoo", "Contains mint and nettle");

        BasicShampoo shampoo = new FreshNuke(label);

        shampoo.getIngredients().add(mint);
        shampoo.getIngredients().add(nettle);
        shampoo.getIngredients().add(am);

        entityManager.persist(shampoo);
    }

    void createPinkPantherShampoo() {

        BasicIngredient lavender = new Lavender();
        BasicIngredient nettle = new Nettle();

        BasicLabel label = new BasicLabel("Pink Panther", "It’s made of Lavender and Nettle");

        BasicShampoo shampoo = new PinkPanther(label);
        shampoo.getIngredients().add(lavender);
        shampoo.getIngredients().add(nettle);

        entityManager.persist(shampoo);
    }

    void createFiftyShadesShampoo() {

        BasicIngredient strawberry = new Strawberry();
        BasicIngredient nettle = new Nettle();

        BasicLabel label = new BasicLabel("Fifty Shades", "It’s made of Strawberry and Nettle");

        BasicShampoo shampoo = new FiftyShades(label);

        shampoo.getIngredients().add(strawberry);
        shampoo.getIngredients().add(nettle);

        entityManager.persist(shampoo);
    }

    private void inTransaction(EntityManager entityManager, Runnable runnable) {
        entityManager.getTransaction().begin();
        runnable.run();
        entityManager.getTransaction().commit();
    }
}
