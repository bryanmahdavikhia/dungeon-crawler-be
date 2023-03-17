package id.ac.ui.cs.advprog.dungeonbe.repository;

import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
    Account findAccountById(String id);
}
