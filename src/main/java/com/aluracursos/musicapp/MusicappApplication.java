package com.aluracursos.musicapp;

import com.aluracursos.musicapp.principal.Principal;
import com.aluracursos.musicapp.repository.CantanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicappApplication implements CommandLineRunner {

	@Autowired
	private CantanteRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MusicappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.correApp();
	}
}
