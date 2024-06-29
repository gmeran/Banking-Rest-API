package gmeran.banking.service;

import gmeran.banking.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccouint(AccountDto account);

    AccountDto getAccountByID (long id);

    AccountDto deposit(long id, double amount);

    AccountDto withdraw(long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(long id);
}
