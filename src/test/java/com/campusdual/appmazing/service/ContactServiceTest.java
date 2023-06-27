package com.campusdual.appmazing.service;

import com.campusdual.appmazing.model.Contact;
import com.campusdual.appmazing.model.dao.ContactDao;
import com.campusdual.appmazing.model.dto.ContactDTO;
import com.campusdual.appmazing.model.dto.dtomapper.ContactMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {
    @Mock
    ContactDao contactDao; /* Este Dao es el único atributo a nivel de clase (objeto) que tiene
                        la clase ContactService. Cada atributo será lo que pongamos a mockear
                        con @Mock. La clase será donde se inyectan los mocks.*/

    @InjectMocks
    ContactService contactService; /* Con @InjectMocks crearemos una instancia de la clase en la que
                                queremos testear los métodos. En esa instancia se van a inyectar
                                los @Mock anteriores. */

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
    void insertContactTest() {

        Mockito.when(this.contactDao.saveAndFlush(Mockito.any(Contact.class))).thenReturn(this.contact1);
        /* Cuando (when) se produzca una llamada al método saveAndFlush, recibiendo cualquier objeto
         * Contact (representado con el "comodín" any(Contact.class), entonces (then), tienes que
         * devolver (Return) lo que yo te diga (el contact creado anteriormente).
         *
         * Así conseguimos simular (mockear) el acceso a la BBDD para insertar un nuevo contacto.
         * Pero realmente no se accede, se mockea su comportamiento. */


        ContactDTO convertedContactDTOFromEntity = ContactMapper.INSTANCE.toDTO(this.contact1);
        /* Convertimos con el mapper la entidad creada al principio del test a DTO. Y almacenamos lo
         * que devuelve el un objeto DTO nuevo. */

        int contactIdFromInsertedContact = contactService.insertContact(convertedContactDTOFromEntity);
        /* Llamamos al servicio que inserta un nuevo contacto, y le pasamos como argumento el DTO.*/

        Assertions.assertNotNull(contactIdFromInsertedContact);
        Assertions.assertEquals(1, contactIdFromInsertedContact);

        Mockito.verify(contactDao, Mockito.times(1)).saveAndFlush(Mockito.any(Contact.class));
        /* Verifica que se llama las veces que se indica en times() al método concreto del objeto mockeado.*/

        Mockito.verify(contactDao, Mockito.times(0)).findAll();
        /* Verifica que en los procesos realizados en este test nunca se llama al método findAll()*/
    }


    @Test
    void queryAllContactsTest() {
        List<Contact> contactList = new ArrayList<>();
        contactList.add(this.contact1);
        contactList.add(this.contact2);

        Mockito.when(this.contactDao.findAll()).thenReturn(contactList);

        List<ContactDTO> contactDTOListExpected = ContactMapper.INSTANCE.toDTOList(contactList);

        org.assertj.core.api.Assertions.assertThat(contactDTOListExpected).usingRecursiveComparison().
                isEqualTo(this.contactService.queryAllContacts());
//        Assertions.assertEquals(contactDTOListExpected, this.contactService.queryAllContacts()); //si lo pongo así peta
        Assertions.assertEquals(2, this.contactService.queryAllContacts().size());

        Mockito.verify(contactDao, Mockito.times(2)).findAll();
        Mockito.verify(contactDao, Mockito.times(0)).getReferenceById(Mockito.anyInt());
    }

    @Test
    void queryContactTest() {


        Mockito.when(this.contactDao.getReferenceById(Mockito.anyInt())).thenReturn(this.contact1);

        ContactDTO convertedContactDTOFromEntity = ContactMapper.INSTANCE.toDTO(this.contact1);

        ContactDTO contactDTOFromQueryContact = contactService.queryContact(convertedContactDTOFromEntity);

        //Assertions.assertEquals(convertedContactDTOFromEntity, contactDTOFromQueryContact);
        org.assertj.core.api.Assertions.assertThat(convertedContactDTOFromEntity).usingRecursiveComparison()
                .isEqualTo(contactDTOFromQueryContact);

    }

    @Test
    void updateContactTest() {

        Mockito.when(this.contactDao.saveAndFlush(Mockito.any(Contact.class))).thenReturn(this.contact1);

        ContactDTO convertedContactDTOFromEntity = ContactMapper.INSTANCE.toDTO(this.contact1);

        int contactIdFromInsertedContact = contactService.updateContact(convertedContactDTOFromEntity);

        Assertions.assertEquals(convertedContactDTOFromEntity.getId(), contactIdFromInsertedContact);

    }

    @Test
    void deleteContactTest() {

        // Mockito.when(this.contactDao.delete(Mockito.any(Contact.class))).thenReturn(this.contact1);

        Mockito.doNothing().when(this.contactDao).delete(Mockito.any(Contact.class)); //Gepeto

        ContactDTO convertedContactDTOFromEntity = ContactMapper.INSTANCE.toDTO(this.contact1);

        int contactIDFromDeletedContact = contactService.deleteContact(convertedContactDTOFromEntity);

        Assertions.assertEquals(convertedContactDTOFromEntity.getId(), contactIDFromDeletedContact);

    }

    // DELETE CHUNGO DE ALBERTO
    @Test
    void deleteContactTestUsandoMockingChungo() {
        ArgumentCaptor<Contact> objetoParaCapturar = ArgumentCaptor.forClass(Contact.class);

        this.contactService.deleteContact(ContactMapper.INSTANCE.toDTO(this.contact2));

        Mockito.verify(this.contactDao).delete(objetoParaCapturar.capture());

        org.assertj.core.api.Assertions.assertThat(this.contact2).usingRecursiveComparison()
                .isEqualTo(objetoParaCapturar.getValue());
    }


}

/*

DELETE CHUNGO DE ALBERTO


@Test
void deleteContactTestUsandoMockingChungo() {
    ArgumentCaptor<Contact> objetoParaCapturar = ArgumentCaptor.forClass(Contact.class);

    this.contactService.deleteContact(ContactMapper.INSTANCE.toDTO(this.contact2));

    Mockito.verify(this.contactDao).delete(objetoParaCapturar.capture());

    org.assertj.core.api.Assertions.assertThat(this.contact2).usingRecursiveComparison()
    	.isEqualTo(objetoParaCapturar.getValue());
}

*/


// Contact.class es un Argument Matcher
//SaveAndFlush()devuleve una entidad