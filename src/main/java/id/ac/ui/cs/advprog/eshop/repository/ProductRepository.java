package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
  private List<Product> productData = new ArrayList<>();
  public int id = 1;

  public Product create(Product product) {
    productData.add(product);
    return product;
  }

  public Iterator<Product> findAll() {
    return productData.iterator();
  }

  public Product deleteById(String productId) {
    Product deletedProduct = this.findById(productId);
    productData.remove(deletedProduct);
    return deletedProduct;
  }

  public Product findById(String findProductId) {
    Iterator<Product> productIterator = this.findAll();
    while (productIterator.hasNext()) {
      Product dataProduct = productIterator.next();
      if (dataProduct.getProductId().equals(findProductId)) {
        return dataProduct;
      }
    }
    return null;
  }
}
