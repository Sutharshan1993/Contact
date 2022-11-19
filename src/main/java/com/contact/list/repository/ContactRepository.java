package com.contact.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contact.list.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

}
