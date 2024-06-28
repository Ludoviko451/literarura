package com.laqf.Litelalura;

import com.laqf.Litelalura.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	private Principal principal;

	@Autowired
	public LiteraluraApplication(Principal principal) {
		this.principal = principal;
	}
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal.mostrarMenu();
	}
}