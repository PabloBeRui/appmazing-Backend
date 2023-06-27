package com.campusdual.appmazing.controller;


import com.campusdual.appmazing.api.IContactService;
import com.campusdual.appmazing.model.Contact;
import com.campusdual.appmazing.model.dto.ContactDTO;
import com.campusdual.appmazing.model.dto.ProductDTO;
import com.campusdual.appmazing.model.dto.dtomapper.ContactMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)

public class ContactControllerTest {

    @Mock
    IContactService contactService;

    @InjectMocks

    ContactController contactController;

    Contact contact1 = new Contact();

    Contact contact2 = new Contact();



    @BeforeEach
    void init() {

        this.contact1.setId(1);
        this.contact1.setName("One");
        this.contact1.setSurname("Surname1One");
        this.contact1.setLastName("LastName2One");
        this.contact1.setTelephone("666555444");
        this.contact1.setEmail("contact-one@gmail.com");


        this.contact2.setId(2);
        this.contact2.setName("Two");
        this.contact2.setSurname("SurnameTwo");
        this.contact2.setLastName("LastNameTwo");
        this.contact2.setTelephone("666999888");
        this.contact2.setEmail("contact-two@gmail.com");


    }

    @Test

    void testControllerHelloTest(){

        Assertions.assertEquals("Hello from ContactController", this.contactController.testControllerHello());
    }

    @Test

    void testControllerTest(){
        String name= "Mairena";

        Assertions.assertEquals("ContactController received POST request with name: " + name, this.contactController.testController("Mairena"));
    }

    @Test

    void testControllerMethodTest(){

        String phrase = "ContactController test method is working";

        Assertions.assertEquals(phrase, this.contactController.testControllerMethod());

    }

    @Test

    void addContactTest() {

        Mockito.when(this.contactService.insertContact(Mockito.any(ContactDTO.class))).thenReturn(5);

        Assertions.assertEquals(5, this.contactController.addContact(new ContactDTO()));


    }

    @Test
    void updateContactTest(){

        Mockito.when(this.contactService.updateContact(Mockito.any(ContactDTO.class))).thenReturn(8);

        Assertions.assertEquals(8, this.contactController.updateContact(new ContactDTO()));


    }

    @Test

    void deleteContactTest(){

        Mockito.when(this.contactService.deleteContact(Mockito.any(ContactDTO.class))).thenReturn(25);

        Assertions.assertEquals(25, this.contactController.deleteContact(new ContactDTO()));

    }

    @Test
    void QueryContactTest(){

        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(18);  // Creo un Obj DTO y le asigno un valor(aqui el DTO no puede ser vacio)

        Mockito.when(this.contactService.queryContact(Mockito.any(ContactDTO.class))).thenReturn(contactDTO);
            //Evidentemente le retorno el ContactDTO, no un new DTO, FIJATE!!

        Assertions.assertEquals(contactDTO, this.contactController.queryContact(new ContactDTO()));


    }

    @Test

    void queryAllContactsTest(){

        List<ContactDTO> AllContactsList = new ArrayList<>();
        AllContactsList.add(ContactMapper.INSTANCE.toDTO(this.contact1));
        AllContactsList.add(ContactMapper.INSTANCE.toDTO(this.contact2));

        Mockito.when(this.contactService.queryAllContacts()).thenReturn(AllContactsList);

        Assertions.assertEquals(AllContactsList, this.contactController.queryAllContacts());


    }




/*    @Test

    void GetReturnHelloTest() {

        String respuesta = "Hello from ContactController";

        ContactController FuncionaDeUnaPVez = new ContactController();

        Mockito.when(FuncionaDeUnaPVez.testControllerHello()).thenReturn(respuesta);

        String respuestaCorrecta = FuncionaDeUnaPVez.testControllerHello();

        Assertions.assertEquals(respuesta,respuestaCorrecta);

    }

        //Mockito.when(this.contactService.queryContact(Mockito.any())).thenReturn(this.message);



      //  Assertions.assertEquals(message1,  );

             /*   Mockito.when(this.contactService.queryContact(Mockito.any())).thenReturn(this.contact1);
     Mockito.when(this.contactService.queryContact(Mockito.any())).thenReturn("Hello from ContactController");

        ContactDTO convertedContactDTOFromEntity= ContactMapper.INSTANCE.toDTO(this.contact1);

        ContactDTO contactDTOFromQueryContact = contactService.queryContact(convertedContactDTOFromEntity);


            Assertions.assertEquals(message1, contactDTOFromQueryContact.getName());
        }

    //String message1 = "funciona";
        //String message2


// ContactControllerTest contactControllerTest = new ContactControllerTest();
          */

        //String message= "ola k ase";

      /*  Mockito.when(this.contactService.queryContact(Mockito.any())).thenReturn(contact1);

        ContactDTO convertedContactDTOFromEntity= ContactMapper.INSTANCE.toDTO(this.contact1);

        ContactDTO contactDTOFromQueryContact = con */


    }



