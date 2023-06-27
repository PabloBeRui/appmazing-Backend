package com.campusdual.appmazing.model.dao;
import com.campusdual.appmazing.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao  extends JpaRepository<Product, Integer> {

    //Devuelve los productos que estén marcados como activos
    @Query(value = "SELECT * FROM PRODUCTS WHERE active = TRUE;", nativeQuery = true)
    List<Product> getActiveProducts();

    List<Product> findByActiveTrue(); //hace lo mismo que el getActiveProducts sin el @Query. Pero tiene que llamarse así


    //Devuelve los productos que cuesten más de 1.89€
    @Query(value = "SELECT * FROM PRODUCTS WHERE price > 1.89;", nativeQuery = true)
    List<Product> getProductsPriceBigger189();


    //Devuelve los productos que cuesten más del precio que se recibe por parámetro
    @Query(value = "SELECT * FROM PRODUCTS WHERE price > :price ORDER BY price ASC", nativeQuery = true)
    List<Product> getProductsPriceBiggerThan(BigDecimal price);

    List<Product> findByPriceGreaterThan(BigDecimal price);
//hace lo mismo que el getProductsPriceBiggerThan() sin el @Query. Pero tiene que llamarse así obligatoriamente.



}
