package pn.user_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import pn.user_system.models.entities.User;
import pn.user_system.services.api.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AppController implements CommandLineRunner {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d/M/yyyy");

    private final UserService userService;

    @Autowired
    public AppController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.addUsers(50);
        this.printAllWithinAge(20, 30);
//        this.printAllUsersWithProvider("gmail.com");
//        this.deactivateInactiveUsersSince(DATE_FORMAT.parse("21/01/2020"));
        System.out.println("yoyo");
    }

    private void printAllWithinAge(int after, int before) {
        this.userService.getAllWithinAge(after, before)
                .forEach(u -> System.out.println(u.getUsername()));
    }

    private void printAllUsersWithProvider(String provider) {
        this.userService.getAllWithProvider("gmail.com")
                .forEach(u -> System.out.println(u.getUsername()));
    }

    private void deactivateInactiveUsersSince(Date date) {
        this.userService.putAllInactiveToDeletedSince(date);
    }

    private void addUsers(final int count) {
        for (int i = 1; i <= count; i++) {
            User user = new User();
            user.setUsername("username" + i);
            user.setPassword("pasSword%" + i);
            user.setEmail("mail" + i + "x@gmail.com");
            user.setAge(i % 120 + 1);
            user.setFirstName("First" + i);
            user.setLastName("Last" + i);
            user.setRegisteredOn(new Date());
            user.setLastTimeLoggedIn(new Date());
            user.setDeleted(false);
            this.userService.save(user);
        }
    }
}
