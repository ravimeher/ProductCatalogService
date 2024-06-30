package org.example.productcatalogservice.service;

import org.example.productcatalogservice.client.FakeStoreAPIClient;
import org.example.productcatalogservice.dto.FakeStoreProductDto;
import org.example.productcatalogservice.model.Category;
import org.example.productcatalogservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements IProductService {

    @Autowired
    private FakeStoreAPIClient fakeStoreAPIClient;

    @Override
    public Product getProductById(int id) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreAPIClient.getProductById(id);
        return from(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreAPIClient.getAllProducts();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            Product product = from(fakeStoreProductDto);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(int id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreAPIClient.replaceProduct(id,from(product));
        return from(fakeStoreProductDto);
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        Category category = new Category();
        category.setId(product.getId());
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(fakeStoreProductDto.getImageUrl());
        return product;
    }
    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImageUrl(product.getImageUrl());
        fakeStoreProductDto.setTitle(product.getName());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        fakeStoreProductDto.setDescription(product.getDescription());

        return fakeStoreProductDto;
    }
}
