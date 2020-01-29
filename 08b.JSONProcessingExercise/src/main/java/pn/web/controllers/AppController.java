package pn.web.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("yoyo");
    }
}
