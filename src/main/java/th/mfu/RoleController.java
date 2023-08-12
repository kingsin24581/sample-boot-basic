package th.mfu;

import java.util.Optional;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    
    @Autowired
    private RoleRepository roleRespository;
    @Autowired
    private AccountRepository accountRepository;

    // create method for role
    @PostMapping("/roles")
    public ResponseEntity createRole(@RequestBody Role role){
        roleRespository.save(role);
        return ResponseEntity.ok(role);
    }


    //set user role for account
    @PutMapping("/accounts/{account_id}/roles/{role_id}")
    public ResponseEntity setRoleToAccount(@PathVariable Long account_id, @PathVariable Long role_id){
        // search account by id
        Optional<Account> accountopt = accountRepository.findById(account_id);
        // check if account exists
        if(!accountopt.isPresent()){
            // return error message 404
            return ResponseEntity.notFound().build();
        }
        Account account = accountopt.get();
        // add role to account
        Optional<Role> roleopt = roleRespository.findById(role_id);
        // check if role exists
        if(!roleopt.isPresent()){
            // return error message 404
            return ResponseEntity.notFound().build();
        }
        Role role = roleopt.get();
        account.getRoles().add(role);
        // save account
        accountRepository.save(account);
        return ResponseEntity.ok("set role to account");
      
    }
}
