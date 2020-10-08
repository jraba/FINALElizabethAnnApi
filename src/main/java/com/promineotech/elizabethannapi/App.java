package com.promineotech.elizabethannapi;

import java.security.Key;
import java.util.Base64;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@ComponentScan("com.promineotech.elizabethannapi")
@SpringBootApplication
public class App implements CommandLineRunner {
	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		String jwt = Jwts.builder().claim("role", "ADMIN").setSubject("PROMINEO ELIZABETHANNAPI JWT").signWith(key)
				.compact();

		System.out.println(jwt);

		String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());

		System.out.println(encodedKey);

		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);

		System.out.println(Jwts.parser().setSigningKey(decodedKey).parseClaimsJws(jwt).getBody().getSubject()
				.contentEquals("PROMINEO ELIZABETHANNAPI JWT"));

	}
}
