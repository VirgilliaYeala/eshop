package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.Iterator;

public interface ProductRepository {
  public Product create(Product Product);

  public Iterator<Product> findAll();

  Product findById(String id);

  public Product edit(Product editProduct);

  public Product deleteById(String findProductId);
}

