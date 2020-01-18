package pn.springdataintro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import pn.springdataintro.models.Account;
import pn.springdataintro.models.User;
import pn.springdataintro.services.account.AccountServiceImpl;
import pn.springdataintro.services.user.UserServiceImpl;

import java.math.BigDecimal;
import java.util.HashSet;

@SpringBootApplication
@Component
public class App implements CommandLineRunner {
    private final UserServiceImpl userService;
    private final AccountServiceImpl accountService;

    public App(final UserServiceImpl userService,final AccountServiceImpl accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
//        User user = new User();
//        user.setUsername("Johny Boy");
//        user.setAge(31);
//        user.setAccounts(new HashSet<>());
//
//        Account account = new Account();
//        account.setBalance(new BigDecimal(25000));
//        account.setUser(user);
//
//        user.getAccounts().add(account);
//        this.userService.registerUser(user);

//        this.accountService.withdrawMoney(new BigDecimal(20000), 0L);

        this.accountService.depositMoney(new BigDecimal(50000), 0L);
    }
}
