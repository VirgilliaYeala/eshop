package id.ac.ui.cs.advprog.eshop.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
// import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;


public class ProductControllerTest {

    @InjectMocks
    private ProductController controller;
    @Mock
    private Model model;
    @Mock
    private ProductService service;

    @Test
    public void testCreateProductPage() throws Exception {
        String actualViewName = controller.createProductPage(model);
        assertEquals("createProduct", actualViewName);
    }

    @Test
    public void testCreateProductPost() throws Exception {
        Product product = new Product();
        String createPost = controller.createProductPost(product, model);
        assertEquals("redirect:list",createPost);
    }

    @Test
    public void testProductListPage() throws Exception {
        String productPages = controller.productListPage(model);
        assertEquals("productList",productPages);
    }

    @Test
    public void testEditProductPage() throws Exception {
        String productId = "1";
        Product product = new Product();
        when(service.findById(productId)).thenReturn(product); // Assuming Product has an appropriate constructor

        String editProduct = controller.editProductPage(productId,model);
        assertEquals(editProduct, "editProduct");
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        when(service.edit(product)).thenReturn(product);
        String editProductPostPage = controller.editProductPost(product);
        assertEquals("redirect:list", editProductPostPage);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        String productId = "1";
        Product product = new Product();
        when(service.findById(productId)).thenReturn(product); // Assuming Product has an appropriate constructor

        String deleteProduct = controller.deleteProduct(productId);
        assertEquals("redirect:../list", deleteProduct);
    }
}