package com.campusdual.appmazing.controller;

import com.campusdual.appmazing.api.IContactService;
import com.campusdual.appmazing.model.Contact;
import com.campusdual.appmazing.model.dto.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contacts")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS }, allowedHeaders = "*")

public class ContactController {

    @Autowired
    private IContactService contactService;

    @GetMapping
    public String testControllerHello() {
        return "Hello from ContactController";
    }

    @PostMapping
    public String testController(@RequestBody String name) {
        return "ContactController received POST request with name: " + name;
    }

    @GetMapping(value = "/testMethod")
    public String testControllerMethod() {
        return "ContactController test method is working";
    }

    @PostMapping(value = "/add")
    public int addContact(@RequestBody ContactDTO contactDTO) {
        return contactService.insertContact(contactDTO);
    }

    @PutMapping(value = "/update")
    public int updateContact(@RequestBody ContactDTO contactDTO) {
        return contactService.updateContact(contactDTO);
    }

    @PostMapping(value = "/delete")
    public int deleteContact(@RequestBody ContactDTO contactDTO) {
        return contactService.deleteContact(contactDTO);
    }

    @GetMapping(value = "/getAll")
    public List<ContactDTO> queryAllContacts() {
        return contactService.queryAllContacts();
    }

    @DeleteMapping(value = "/deleteList")
    public List<Integer> deleteContactList(@RequestBody List<ContactDTO> contactDTOList) {
        List<Integer> contactIds = new ArrayList<>();
        for (ContactDTO contactDTO : contactDTOList) {
            contactIds.add(contactDTO.getId());
            contactService.deleteContact(contactDTO);
        }
        return contactIds;
    }

    @PostMapping(value = "getPost")

    public ContactDTO queryContact(@RequestBody ContactDTO contactDTO) {

        return contactService.queryContact(contactDTO);
    }
}
