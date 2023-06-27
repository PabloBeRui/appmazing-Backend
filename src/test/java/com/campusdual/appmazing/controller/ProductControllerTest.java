package com.campusdual.appmazing.controller;

import com.campusdual.appmazing.api.IProductService;
import com.campusdual.appmazing.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;

@ExtendWith(MockitoExtension.class)

public class ProductControllerTest {

    @Mock
    IProductService iProductService;

    @InjectMocks

    ProductController productController;

    Product product1 = new Product();

    Product product2 = new Product();

    @BeforeEach
    void init() {

        this.product1.setId(1);
        this.product1.setName("cascarilla");
        this.product1.setStock(4);
        this.product1.setPrice(new BigDecimal("20.30"));
        this.product1.setActive(true);
        this.product1.setDate_added(new Date());

        this.product2.setId(1);
        this.product2.setName("melange");
        this.product2.setStock(500);
        this.product2.setPrice(new BigDecimal("90000000000000000000000.99"));
        this.product2.setActive(true);
        this.product2.setDate_added(new Date());


}

@Test

    void testControllerGETTest(){

    Assertions.assertEquals("Va que flipas Neno!", this.productController.testControllerGET());

}

@Test

    void testControllerPOSTTest(){

        
}

}
