package gmeran.banking.service.impl;

import gmeran.banking.dto.AccountDto;
import gmeran.banking.entity.Account;
import gmeran.banking.mapper.AccountMapper;
import gmeran.banking.repository.AccountRespository;
import gmeran.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRespository accountRespository;

    public AccountServiceImpl(AccountRespository accountRespository) {
        this.accountRespository = accountRespository;
    }

    @Override
    public AccountDto createAccouint(AccountDto accountDto) {
        Account account = AccountMapper.maptoAccount(accountDto);
        Account saveAccount = accountRespository.save(account);
        return AccountMapper.maptoAccountDto(saveAccount);
    }

    @Override
    public AccountDto getAccountByID(long id) {
        Account account = accountRespository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does exist"));
        return AccountMapper.maptoAccountDto(account);
    }

    @Override
    public AccountDto deposit(long id, double amount) {
        Account account = accountRespository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does exist"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRespository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(long id, double amount) {
        Account account = accountRespository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does exist"));

        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRespository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRespository.findAll();
        return accounts.stream().map((account -> AccountMapper.maptoAccountDto(account))).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(long id) {
        Account account = accountRespository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does exist"));
        accountRespository.deleteById(id);
    }
}
