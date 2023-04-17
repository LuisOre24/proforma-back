package com.soldev;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.soldev.entity.Usuario;
import com.soldev.repo.IUsuarioRepo;

@SpringBootTest
class ProformaAppApplicationTests {

	@Autowired
	private IUsuarioRepo userRepo;
	
	@Autowired BCryptPasswordEncoder encoder;
	
	@Test
	void contextLoads() {
		Usuario user = new Usuario();
		user.setIdUsuario(1);
		user.setNombres("administrator");
		user.setUsername("admin");
		user.setPassword(encoder.encode("admin"));
		
		Usuario account = userRepo.save(user);
		
		assertThat(account.getPassword().equalsIgnoreCase(user.getPassword()));
		
		
	}

}
