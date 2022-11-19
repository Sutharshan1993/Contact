package com.contact.list.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.contact.list.entity.Contact;
import com.contact.list.repository.ContactRepository;
import com.contact.list.service.ContactService;
@Service
public class ContactServiceImpl implements ContactService {

	private ContactRepository contactRepo;
	
	
	
	public ContactServiceImpl(ContactRepository contactRepo) {
		super();
		this.contactRepo = contactRepo;
	}



	@Override
	public List<Contact> getAllContacts() {
		return contactRepo.findAll();
	}



	@Override
	public Page<Contact> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		 return this.contactRepo.findAll(pageable);
	}



	@Override
	public void loadData(List<Contact> cont) {
		contactRepo.saveAll(cont);
	}
	
	

}
