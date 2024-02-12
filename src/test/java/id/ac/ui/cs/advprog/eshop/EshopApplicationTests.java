package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EshopApplicationTestsNew {

    @Autowired
    private EshopApplication application;

    @Test
    void checkApplicationContext() {
        assertNotNull(application);
    }

    @Test
    void checkMainMethod() {
        assertDoesNotThrow(() -> EshopApplication.main(new String[] {}));
    }
}
