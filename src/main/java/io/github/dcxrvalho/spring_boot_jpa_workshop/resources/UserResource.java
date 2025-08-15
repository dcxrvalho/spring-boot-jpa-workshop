package io.github.dcxrvalho.spring_boot_jpa_workshop.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dcxrvalho.spring_boot_jpa_workshop.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "Izabella", "izabella@gmail.com", "31951213409", "123456");
		return ResponseEntity.ok().body(u);
	}
}
