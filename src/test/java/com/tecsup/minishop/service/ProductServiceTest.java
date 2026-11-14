package com.tecsup.minishop.service;

import com.tecsup.minishop.model.Product;
import com.tecsup.minishop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    private ProductRepository repository;
    private ProductService service;

    @BeforeEach
    void setUp() {
        repository = mock(ProductRepository.class);
        service = new ProductService(repository);
    }

    @Test
    void save_precioCero_lanzaExcepcion() {
        Product product = Product.builder().name("Mouse").price(0.0).stock(5).build();

        assertThrows(IllegalArgumentException.class, () -> service.save(product));
    }

    @Test
    void save_stockNegativo_lanzaExcepcion() {
        Product product = Product.builder().name("Mouse").price(50.0).stock(-1).build();

        assertThrows(IllegalArgumentException.class, () -> service.save(product));
    }

    @Test
    void save_productoValido_devuelveProductoGuardado() {
        Product product = Product.builder().name("Mouse").price(50.0).stock(5).build();
        when(repository.save(product)).thenReturn(product);

        assertSame(product, service.save(product));
    }
}