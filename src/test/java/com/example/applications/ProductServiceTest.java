package com.example.applications;

import com.example.entities.Product;
import com.example.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    @InjectMocks
    private ProductService produtoService;

    private Product produto;

    @BeforeEach
    public void setUp() {
        produtoService = new ProductService();

        produto = new Product(1, "Produto", 10, "C:\\Users\\Admin\\Documents\\foto.jpg");
    }

    @Test
    public void deveSalvarImagemCorretamente(){
        boolean resultado = produtoService.save(produto);

        assertTrue(resultado);
        assertEquals(produto.getImage(), "C:\\Users\\Admin\\Documents\\foto.jpg");
    }

    @Test
    public void deveRemoverImagemCorretamente(){
        produtoService.save(produto);
        produtoService.remove(produto.getId());

        assertThrows(NoSuchElementException.class, () -> {produtoService.getImagePathById(produto.getId());
        } );
    }

    @Test
    public void deveAtualizarImagemCorretamente(){
        produtoService.save(produto);
        assertEquals(produto.getImage(), "C:\\Users\\Admin\\Documents\\foto.jpg");

        produto.setImage("C:\\Users\\Admin\\Documents\\foto2.jpg");
        assertEquals(produto.getImage(), "C:\\Users\\Admin\\Documents\\foto2.jpg");
    }
}
