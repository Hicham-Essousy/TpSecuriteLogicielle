package com.example.tpSecurity;

import com.example.tpSecurity.beans.AppUser;
import com.example.tpSecurity.beans.AppUserRole;
import com.example.tpSecurity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class TpSecurityApplication implements CommandLineRunner {
	private final UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(TpSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(userService.getAllUsers().isEmpty())
		{
			AppUser user1 = new AppUser("user","user", AppUserRole.USER);
			AppUser user2 = new AppUser("admin","admin",AppUserRole.ADMIN);
			userService.addUser(user1);
			userService.addUser(user2);
		}
	}
}
