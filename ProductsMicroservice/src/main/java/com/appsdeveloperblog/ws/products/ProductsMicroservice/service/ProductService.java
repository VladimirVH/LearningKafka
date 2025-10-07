package com.appsdeveloperblog.ws.products.ProductsMicroservice.service;

import com.appsdeveloperblog.ws.products.ProductsMicroservice.CreateProductRestModel;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    String createProduct(CreateProductRestModel productRestModel);

}
