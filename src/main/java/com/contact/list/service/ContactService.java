package com.contact.list.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.contact.list.entity.Contact;

public interface ContactService {

	List<Contact> getAllContacts();

	Page<Contact> findPaginated(int pageNo, int pageSize);

	void loadData(List<Contact> cont);
}
