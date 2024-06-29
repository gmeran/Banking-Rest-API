package gmeran.banking.repository;

import gmeran.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AccountRespository extends JpaRepository<Account,Long> {
}
