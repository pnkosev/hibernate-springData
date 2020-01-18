package pn.springdataintro.services.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pn.springdataintro.models.Account;
import pn.springdataintro.repositories.AccountRepository;

import java.math.BigDecimal;

@Service
@Primary
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Account account = validateAccount(money, id);

        BigDecimal balance = account.getBalance();

        if (balance.compareTo(money) < 0) {
            throw new IllegalArgumentException("Not enough money");
        }

        account.setBalance(balance.subtract(money));

        System.out.println(account.getBalance());

        this.accountRepository.update(account.getBalance(), account.getId());
    }

    @Override
    public void depositMoney(BigDecimal money, Long id) {
        Account account = validateAccount(money, id);

        BigDecimal balance = account.getBalance();

        account.setBalance(balance.add(money));

        this.accountRepository.update(account.getBalance(), account.getId());
    }

    private Account validateAccount(BigDecimal money, Long id) {
        if (BigDecimal.ZERO.compareTo(money) > 0) {
            throw new IllegalArgumentException("Money value should be positive");
        }

        Account account = this.accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));

        if (account.getUser() == null) {
            throw new RuntimeException("Account doesn't belong to anyone");
        }

        return account;
    }
}
