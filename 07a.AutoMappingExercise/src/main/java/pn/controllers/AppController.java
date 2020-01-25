package pn.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import pn.models.entities.User;

@Controller
public class AppController implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("yoyo");
    }

    private void seedUsers() {
        
    }
}
