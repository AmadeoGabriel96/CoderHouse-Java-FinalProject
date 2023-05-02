package com.MoyanoManchon.FinalProject.service;

import com.MoyanoManchon.FinalProject.exception.AlreadyExistException;
import com.MoyanoManchon.FinalProject.exception.NotFoundException;
import com.MoyanoManchon.FinalProject.model.Product;
import com.MoyanoManchon.FinalProject.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(Product newProduct) throws AlreadyExistException {

        Optional<Product> productOp = this.productRepository.findByCode(newProduct.getCode());

        if (productOp.isPresent()){
            log.info("El producto que desea agregar ya existe" + newProduct);
            throw new AlreadyExistException("El producto que desea agregar ya existe");
        }else{
            return this.productRepository.save(newProduct);
        }
    }

    public Product update(Product newProduct, Long id) throws Exception {

        if (id <= 0){
            throw new Exception("El id no es valido");
        }

        Optional<Product> productOp = this.productRepository.findById(newProduct.getId());

        if(productOp.isEmpty()){
            log.info("El producto ya existe enla base de datos" +  newProduct);
            throw new NotFoundException("El producto ya existe enla base de datos");
        } else{
            Product productBd = productOp.get();
            productBd.setId(newProduct.getId());
            productBd.setDescription(newProduct.getDescription());
            productBd.setCode(newProduct.getCode());
            productBd.setStock(newProduct.getStock());
            productBd.setPrice(newProduct.getPrice());
            return this.productRepository.save(newProduct);
        }
    }

    public Product findById(Long id) throws Exception {

        if (id <= 0){
            throw new Exception("El id no es valido");
        }

        Optional<Product> productOp = this.productRepository.findById(id);

        if (productOp.isPresent()){
            log.info("El producto que intenta agregar ya existe en la base de datos" + id);
            throw new NotFoundException("El producto que intenta agregar ya existe en la base de datos");
        } else{
            return productOp.get();
        }

    }

    public List<Product> findByAll(){

        return this.productRepository.findAll();

    }

}


