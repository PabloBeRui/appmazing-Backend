package com.campusdual.appmazing.service;


import com.campusdual.appmazing.model.Product;

import com.campusdual.appmazing.model.dao.ProductDao;

import com.campusdual.appmazing.model.dto.ProductDTO;

import com.campusdual.appmazing.model.dto.dtomapper.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductDao productDao; /* Este Dao es el único atributo a nivel de clase (objeto) que tiene
                        la clase ProductService. Cada atributo será lo que pongamos a mockear
                        con @Mock. La clase será donde se inyectan los mocks.*/


    @InjectMocks
    ProductService productService; /* Con @InjectMocks crearemos una instancia de la clase en la que
                                queremos testear los métodos. En esa instancia se van a inyectar
                                los @Mock anteriores. */

    Product product1 = new Product();

    @BeforeEach
    void init() {

        this.product1.setId(1);
        this.product1.setName("cascarilla");
        this.product1.setStock(4);
        this.product1.setPrice(new BigDecimal("20.30"));
        this.product1.setActive(true);
        this.product1.setDate_added(new Date());


    }

    @Test
    void queryProductTest() {

        Mockito.when(this.productDao.getReferenceById(Mockito.anyInt())).thenReturn(this.product1);

        ProductDTO convertedProductFromEntityToDTO = ProductMapper.INSTANCE.toDTO(this.product1);

        ProductDTO productDTOFromQueryProduct = productService.queryProduct(convertedProductFromEntityToDTO);

        org.assertj.core.api.Assertions.assertThat(convertedProductFromEntityToDTO)
                .usingRecursiveComparison().isEqualTo(productDTOFromQueryProduct);


    }


    @Test
    void queryAllProductsTest() {
        List<Product> productList = new ArrayList<>();
        productList.add(this.product1);
//.when  .thenReturn
        Mockito.when(this.productDao.findAll()).thenReturn(productList);

        List<ProductDTO> convertedProductFromEntityToDTO = ProductMapper.INSTANCE.toDTOList(productList);

        org.assertj.core.api.Assertions.assertThat(convertedProductFromEntityToDTO)
                .usingRecursiveComparison().isEqualTo(this.productService.queryAllProducts());

        Mockito.verify(productDao, Mockito.times(1)).findAll();

    }

    @Test
    void insertProductTest() {

        Mockito.when(this.productDao.saveAndFlush(Mockito.any(Product.class))).thenReturn(this.product1);

        ProductDTO convertedProductFromEntityToDTO = ProductMapper.INSTANCE.toDTO(this.product1);

        int productIdFromInsertedProduct = productService.insertProduct(convertedProductFromEntityToDTO);

        Assertions.assertNotNull(productIdFromInsertedProduct);
        Assertions.assertEquals(1, productIdFromInsertedProduct);

        Mockito.verify(productDao, Mockito.times(1)).saveAndFlush(Mockito.any(Product.class));
        /* Verifica que se llama las veces que se indica en times() al método concreto del objeto mockeado.*/

        Mockito.verify(productDao, Mockito.times(0)).findAll();
        /* Verifica que en los procesos realizados en este test nunca se llama al método findAll()*/


    }

    @Test
    void updateProductTest() {

        Mockito.when(this.productDao.saveAndFlush(Mockito.any(Product.class))).thenReturn(this.product1);

        ProductDTO convertedProductFromEntityToDTO = ProductMapper.INSTANCE.toDTO(this.product1);

        int productIdFromUpdatedProduct = productService.updateProduct(convertedProductFromEntityToDTO);

        Assertions.assertEquals(convertedProductFromEntityToDTO.getId(), productIdFromUpdatedProduct);


    }

    @Test
    void deleteProductTest() {

        Mockito.doNothing().when(this.productDao).delete(Mockito.any(Product.class)); //Gepeto

        ProductDTO convertedProductFromEntityToDTO = ProductMapper.INSTANCE.toDTO(this.product1);

        int productIDFromDeletedProduct = productService.deleteProduct(convertedProductFromEntityToDTO);

        Assertions.assertEquals(convertedProductFromEntityToDTO.getId(), productIDFromDeletedProduct);


    }

    // DELETE CHUNGO DE ALBERTO
    @Test
    void deleteProductTestUsandoMockingChungo() {
        ArgumentCaptor<Product> objetoParaCapturar = ArgumentCaptor.forClass(Product.class);

        this.productService.deleteProduct(ProductMapper.INSTANCE.toDTO(this.product1));

        Mockito.verify(this.productDao).delete(objetoParaCapturar.capture());

        org.assertj.core.api.Assertions.assertThat(this.product1).usingRecursiveComparison()
                .isEqualTo(objetoParaCapturar.getValue());
    }


}