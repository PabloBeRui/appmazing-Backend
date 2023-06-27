package com.campusdual.appmazing.controller;

import com.campusdual.appmazing.api.IContactService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ContactControllerTestTRUCHOconmockMvc {

    @Mock
    IContactService contactService;

    @InjectMocks
    ContactController contactController;


    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
    }

    /*  método setUp():

Se llama al método MockMvcBuilders.standaloneSetup(),
que crea una instancia de MockMvc configurada para simular
peticiones HTTP a un controlador de Spring.

El argumento que se pasa a standaloneSetup() es una instancia de ContactController,
el controlador que se está probando.

El método build() finaliza la configuración y crea la instancia de MockMvc.

La instancia de MockMvc se asigna a la variable de instancia mockMvc.

En resumen, la función del método setUp() es crear una instancia de MockMvc
configurada para simular peticiones HTTP a ContactController,
de modo que pueda ser utilizada en las pruebas unitarias de este controlador.

Es importante tener en cuenta que este método se ejecutará antes
de cada método de prueba que haya en la clase, asegurando que la instancia de MockMvc
esté disponible en cada prueba. */

    @Test
    void GetContactsHello() throws Exception {


        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello from ContactController"));

    }

    /* testController():

1.	mockMvc.perform(get("/contacts")): Se llama al método perform()
 de la instancia de MockMvc para simular una petición HTTP GET a la URL "/contacts".

2.	.andExpect(status().isOk()): Se encadena un método andExpect() para verificar
 que el código de estado de la respuesta HTTP sea 200 OK.

3.	.andExpect(content().string("Hello from ContactController")):
 Se encadena otro método andExpect() para verificar que el contenido
  de la respuesta HTTP sea igual a la cadena "Hello from ContactController".

En resumen, este método de prueba simula una petición HTTP GET a la URL "/contacts",
 y luego verifica que la respuesta tenga un código de estado 200
  y contenga la cadena "Hello from ContactController".
   Si alguna de las verificaciones falla, la prueba fallará.

 */

    @Test
    void PostContactsReturnName() throws Exception {
        String name = "Pepito";
        mockMvc.perform(post("/contacts").content(name))
                .andExpect(status().isOk())
                .andExpect(content().string("ContactController received POST request with name: " + name));

    }
/* para simular una petición POST con un cuerpo de solicitud (@RequestBody),
 necesitas pasar el cuerpo de la solicitud como un parámetro en el método perform()

  FIJATE: añadiste . content() al perform() y FIJATE que va DENTRO de este

  */

    @Test

    void TestMethodGetReturnIsWorking() throws Exception {

        mockMvc.perform(get("/contacts/testMethod"))
                .andExpect(status().isOk())
                .andExpect(content().string("ContactController test method is working"));
    }

/*
    @Test

    void addPostAddContact() throws Exception {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName("Krispin");
        contactDTO.setSurname("Klander");
        contactDTO.setTelephone("675432234");
        // Inicializo los valores del objeto ContactDTO

        mockMvc.perform(post("/add").content(String.valueOf(contactDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(contactDTO));

    } */




}
