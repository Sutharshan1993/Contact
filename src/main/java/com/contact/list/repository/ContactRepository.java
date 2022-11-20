package com.contact.list.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.contact.list.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

	@Query(value = "select * from contact_list  where upper(name) like %:keyword%", nativeQuery = true)
	Page<Contact> findByKeyword(Pageable pageable,String keyword);
}
 