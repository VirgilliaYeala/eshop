package id.ac.ui.cs.advprog.eshop.controller;
import org.mockito.InjectMocks;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import id.ac.ui.cs.advprog.eshop.service.ServiceManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private Model model;
    @Mock
    private ServiceManager<Product> service;
    @Mock
    private ProductService serviceProduct;
    @Mock
    private Product product;
    @InjectMocks
    private ProductController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String createProduct = controller.createProductPage(model);
        assertEquals("CreateProduct",createProduct);

    }

    @Test
    void testCreateProductPost(){
        String createPost = controller.createProductPost(product, model);
        assertEquals("redirect:list",createPost);

    }
    @Test
    void testProductListPage(){
        String pages = controller.productListPage(model);
        assertEquals("ProductList",pages);
    }

    @Test
    void testEditProductPage(){
        when(service.findById(String.valueOf(0))).thenReturn(new Product());

        String editProduct = controller.editProductPage(String.valueOf(0),model);
        assertEquals( "EditProduct", editProduct);
    }



    @Test
    void testEditProductPost(){
        Product product = new Product();
        String expectedViewName = "redirect:list";
        String actualViewName = controller.editProductPost(product);
        assertEquals(expectedViewName, actualViewName);
        verify(serviceProduct, times(1)).edit(product);

    }

    @Test
    void testDeleteProduct(){
        String deleteProductPage = controller.deleteProduct(String.valueOf(0));
        assertEquals("redirect:../list", deleteProductPage);

    }
}