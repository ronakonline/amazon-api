package com.jay.amazonapi.Models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    public List<Product> findByCategory(String category);
    public List<Product> findByBestseller(String bestseller);

}
