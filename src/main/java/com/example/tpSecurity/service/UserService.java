package com.example.tpSecurity.service;

import com.example.tpSecurity.beans.AppUser;
import com.example.tpSecurity.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final AppUserRepository appUserRepository ;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email);
        if(appUser == null)
            throw new UsernameNotFoundException("this user is not found");
        return appUser ;

    }

    public AppUser addUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    public List<AppUser> getAllUsers()
    {
        return appUserRepository.findAll();
    }
}
