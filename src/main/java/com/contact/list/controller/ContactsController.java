package com.contact.list.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contact.list.entity.Contact;
import com.contact.list.service.ContactService;
import com.opencsv.bean.CsvToBeanBuilder;

@Controller
public class ContactsController {
	@Autowired
	private ContactService contService;

	public ContactsController(ContactService contService) {
		super();
		this.contService = contService;
	}

	int pageSize = 20;

	@GetMapping("/")
	public String viewHomePage(Model model) throws Exception, FileNotFoundException {
		List<Contact> beans = new ArrayList<Contact>();
		String fileName = "./src/main/resources/people.csv";
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			int i = 1;
			while ((line = br.readLine()) != null) {

				Contact conts = new Contact();
				if (i != 1) {
					String[] split = line.split(",");
					conts.setName(split[0].trim());
					conts.setUrl(split[1].trim());
					beans.add(conts);
					System.out.println("\nLength : " + split.length);
					System.out.println("split[0] : " + split[0]);
					System.out.println("split[1] : " + split[1]);
					// System.out.println("split[2] : " + split[2]);
				}
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		contService.loadData(beans);
		return findPaginated(1, model);

	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {

		Page<Contact> page = contService.findPaginated(pageNo, pageSize);
		List<Contact> listContact = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("contacts", listContact);
		return "contacts";
	}

	@RequestMapping(path = { "/search" })
	public String searchByKeyword(Model model, String keyword) {
		if (keyword != null) {
			List<Contact> list = contService.getByKeyword(keyword.toUpperCase());
			model.addAttribute("contacts", list);
			return "contacts";
		} else {
			return findPaginated(1, model);
		}
		
	}
}
