package com.contact.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.contact.list.repository.ContactRepository;

@SpringBootApplication
public class ContactListApplication implements CommandLineRunner{
	@Autowired
	private ContactRepository contRepo;

	public static void main(String[] args) {
		SpringApplication.run(ContactListApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}
