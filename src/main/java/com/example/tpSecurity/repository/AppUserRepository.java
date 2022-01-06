package com.example.tpSecurity.repository;

import com.example.tpSecurity.beans.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    public AppUser findByEmail(String email);
}
