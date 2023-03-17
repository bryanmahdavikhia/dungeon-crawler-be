package id.ac.ui.cs.advprog.dungeonbe.controller;

import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.AccessAccountDTO;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.CreateAccountDTO;
import id.ac.ui.cs.advprog.dungeonbe.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/")
    public ResponseEntity<Account> getAccountById(@RequestBody AccessAccountDTO accessAccountDTO){
        String id = accessAccountDTO.getId();
        if (accountService.accountExistsById(id)){
            return ResponseEntity.ok().body(accountService.getAccountById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountDTO createAccountDTO){
        String id = createAccountDTO.getId();
        var heroClassType = createAccountDTO.getHeroClassType();
        if (accountService.accountExistsById(id)){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok().body(accountService.createAccount(id, heroClassType));
    }
}
