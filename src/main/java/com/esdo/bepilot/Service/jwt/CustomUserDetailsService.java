package com.esdo.bepilot.Service.jwt;

import com.esdo.bepilot.Model.Entity.Account;
import com.esdo.bepilot.Model.Request.AccountInputDTO;
import com.esdo.bepilot.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles;

        Account account = accountRepository.findByEmail(email);
        if (account != null) {
            roles = List.of(new SimpleGrantedAuthority(account.getRole()));
            return new User(account.getEmail(), account.getPassword(), roles);
        }
        throw new UsernameNotFoundException("Account not found with the email " + email);
    }

    public Account save(AccountInputDTO user) {
        Account account = new Account();
        account.setEmail(user.getEmail());
        account.setPassword(bcryptEncoder.encode(user.getPassword()));
        account.setRole(user.getRole());
        return accountRepository.save(account);
    }
}
