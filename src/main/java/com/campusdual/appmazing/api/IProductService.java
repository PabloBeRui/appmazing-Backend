package com.campusdual.appmazing.api;

import com.campusdual.appmazing.model.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    //CRUD Operations

    ProductDTO queryProduct(ProductDTO productDTO);

    List<ProductDTO> queryAllProducts();

    int insertProduct(ProductDTO productDTO);

    int updateProduct(ProductDTO productDTO);

    int deleteProduct(ProductDTO productDTO);






}
