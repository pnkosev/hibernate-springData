package pn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import pn.models.dtos.GameDto;
import pn.models.dtos.UserLoginDto;
import pn.models.dtos.UserRegisterDto;
import pn.services.GameService;
import pn.services.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


@Controller
public class AppController implements CommandLineRunner {
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public AppController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String[] input = scanner.nextLine().split("\\|");
            String command = input[0];
            String email;
            String password;

            switch (command) {
                case "RegisterUser":
                    email = input[1];
                    password = input[2];
                    String confirmPassword = input[3];
                    String fullName = input[4];
                    UserRegisterDto userRegisterDto = new UserRegisterDto(email, password, confirmPassword, fullName);
                    System.out.println(this.userService.registerUser(userRegisterDto));
                    break;
                case "LoginUser":
                    email = input[1];
                    password = input[2];
                    UserLoginDto userLoginDto = new UserLoginDto(email, password);
                    System.out.println(this.userService.login(userLoginDto));
                    break;
                case "Logout":
                    System.out.println(this.userService.logout());
                    break;
                case "AddGame":
                    GameDto gameDto = new GameDto(input[1], new BigDecimal(input[2]), Double.parseDouble(input[3]),
                            input[4], input[5], input[6], LocalDate.parse(input[7], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    this.gameService.addGame(this.userService.getLoggedInUser(), gameDto);
                    break;
            }
        }

        // System.out.println("yoyo");
    }
}
