package com.campusdual.appmazing.controller;


import com.campusdual.appmazing.api.IProductService;
import com.campusdual.appmazing.model.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS }, allowedHeaders = "*")
public class ProductController {

    @Autowired

    private IProductService productService;

    @GetMapping // Para la request de tipo GET

    public String testControllerGET(){
        return "Va que flipas Neno!";
    }

    @PostMapping //Para la request de tipo POST

    public String testControllerPOST(@RequestBody String name) {

        return "El controlador ha recibido la petición POST correctamente y muestra el nombre "+ name;


    }

    @GetMapping(value="/testMethod")
    public String testControllerMethod() {
        return "El metodo funciona correctamente";
    }

    @PostMapping(value = "getPost")

    public ProductDTO queryProduct(@RequestBody ProductDTO productDTO) {

        return productService.queryProduct(productDTO);
    }

    @GetMapping(value = "/getAll")

    public List<ProductDTO> queryAllProducts() {
        return productService.queryAllProducts();
    }

    @PostMapping( value= "/add")
    public int addProduct(@RequestBody ProductDTO productDTO) {

        return productService.insertProduct(productDTO);
    }

    @PutMapping(value = "/update")
    public int updateProduct(@RequestBody ProductDTO productDTO)
    {
       return productService.updateProduct(productDTO);

    }

    @PostMapping(value = "/delete")

    public int deleteProduct(@RequestBody ProductDTO productDTO)
    {
        return productService.deleteProduct(productDTO);
    }

    @DeleteMapping(value = "deleteList")

    public List<Integer> deleteProductList(@RequestBody List<ProductDTO> productDTOList)
    {
        List<Integer> registerIdList= new ArrayList<>();
        for (ProductDTO p: productDTOList)

        { registerIdList.add(p.getId());
            productService.deleteProduct(p);

        }
        return registerIdList;
    }
}
