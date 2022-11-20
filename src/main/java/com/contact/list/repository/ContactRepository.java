package com.contact.list.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contact.list.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

	@Query(value = "select * from contact_list c where upper(c.name) like %:keyword%", nativeQuery = true)
	 List<Contact> findByKeyword(@Param("keyword") String keyword);
}
