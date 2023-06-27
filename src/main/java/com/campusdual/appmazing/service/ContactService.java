package com.campusdual.appmazing.service;


import com.campusdual.appmazing.api.IContactService;
import com.campusdual.appmazing.model.Contact;
import com.campusdual.appmazing.model.dao.ContactDao;
import com.campusdual.appmazing.model.dto.ContactDTO;
import com.campusdual.appmazing.model.dto.dtomapper.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ContactService")
@Lazy

public class ContactService implements IContactService {

    @Autowired
    private ContactDao contactDao;

    @Override
    public ContactDTO queryContact(ContactDTO contactDTO) {
        Contact contact = ContactMapper.INSTANCE.toEntity(contactDTO); /* Usamos el DTO que recibimos por
                                                                    parámetro y con el método del mapper
                                                                    lo convertimos a entidad. */

        contact = contactDao.getReferenceById(contact.getId()); /* El método del DAO accede a la BBDD y
                                                               devuelve el registro que coincide con la
                                                               información que le envía. Al final devuelve
                                                               el mismo registro que el dato que se le pasa. */

        contactDTO = ContactMapper.INSTANCE.toDTO(contact); /* Volvemos a usar el mapper para convertir la entidad
                                                            en un DTO y devolvemos el DTO. */
        return contactDTO;
    }

    @Override
    public List<ContactDTO> queryAllContacts() {
        List<Contact> listaEntidadesContact = this.contactDao.findAll(); /* Primero invocamos el método findAll del repositorio
                                                                        y devuelve una lista de objeto de tipo Product que
                                                                        guardamos en una varialbe List. */

        List<ContactDTO> listaDeEntidadesConvertidasADTO = ContactMapper.INSTANCE.toDTOList(listaEntidadesContact);
        /* Usando la instancia del mappper (donde están las cabeceras implementadas (desarrolladas)) convertimos
         * la lista de entidades a una lista de POJOs*/

        return listaDeEntidadesConvertidasADTO;  /* Devolvemos la lista de DTOs (los POJOs) */
    }

    @Override
    public int insertContact(ContactDTO contactDTO) {
        Contact contact = ContactMapper.INSTANCE.toEntity(contactDTO); /* Recibimos un DTO por parámetro y con el mapper
                                                                          lo convertimos a entidad. */

        this.contactDao.saveAndFlush(contact); /* Usamos el método "saveAndFlush" del DAO y así crea un nuevo registro en
                                                  la BBDD con la información que contiene la entidad. Flush lo que hace es
                                                  "renovar" la BBDD (F5). */

        /* Por último se devuelve el número del id (la clave primaria de la tabla) del nuevo registro insertado en BBDD. */
        return contact.getId();
    }

    @Override
    public int updateContact(ContactDTO contactDTO) {
        return insertContact(contactDTO);
    }

    @Override
    public int deleteContact(ContactDTO contactDTO) {
        int id = contactDTO.getId();
        Contact contact = ContactMapper.INSTANCE.toEntity(contactDTO); // lo convertimos en entidad
        this.contactDao.delete(contact); // lo borramos
        return id;
    }
}
