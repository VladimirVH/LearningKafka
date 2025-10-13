package com.appsdeveloperblog.ws.products.ProductsMicroservice.service;

import com.appsdeveloperblog.ws.products.ProductsMicroservice.CreateProductRestModel;
import com.appsdeveloperblog.ws.products.ProductsMicroservice.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    @Override
    public String createProduct(CreateProductRestModel productRestModel) {

        String productId = UUID.randomUUID().toString();

        // TODO: Stored PRoduct details into DB before publishing Event.

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, productRestModel.getTitle(), productRestModel.getPrice(), productRestModel.getQuantity());

        CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent);

        return "";
    }
}
