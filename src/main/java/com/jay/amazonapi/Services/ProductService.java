package com.jay.amazonapi.Services;

import com.jay.amazonapi.Models.Product;
import com.jay.amazonapi.Models.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public void addproduct(Product product){
        repository.insert(product);
    }

    public List<Product> allproducts(){
        return  repository.findAll();
    }

    public List<Product> productsByCategory(String category){
        return repository.findByCategory(category);
    }

    public List allcategories(){
        return repository.findAll();
    }

    public List<Product> bestsellerproducts(){
        return repository.findByBestseller("true");
    }

    public Optional<Product> getproduct(String id){
        return repository.findById(id);
    }

    public String updateproduct(String id,Product newproduct){
        Optional<Product> product;
        product = repository.findById(id);
        if(product.isPresent()){
            Product oldproduct = product.get();
            oldproduct.setName(newproduct.getName());
            oldproduct.setBestseller(newproduct.getBestseller());
            oldproduct.setCategory(newproduct.getCategory());
            oldproduct.setDescription(newproduct.getDescription());
            oldproduct.setImg(newproduct.getImg());
            oldproduct.setPrice(newproduct.getPrice());
            oldproduct.setQuantity(newproduct.getQuantity());
            repository.save(oldproduct);
            return "Product Updated!";
        }else{
            return "Invalid Product Id!";
        }

    }

    public String deleteproduct(String id){
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            return "Product Deleted!";
        }else{
            return "Invalid Product Id!";
        }
    }

}
