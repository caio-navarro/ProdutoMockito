package com.example.applications;

import com.example.entities.Product;
import com.example.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductApplicationTest {

    @InjectMocks
    private ProductApplication produtoApplication;

    @Mock
    private ProductService produtoService;

    private Product produto;

    @BeforeEach
    public void setUp() {
        produtoApplication = new ProductApplication(produtoService);

        produto = new Product(1, "Produto", 10, "C:\\Users\\Admin\\Documents\\foto.jpg");
    }

    @Test
    public void deveSalvarImagemCorretamente(){
        produtoApplication.append(produto);

        assertEquals(produto.getImage(), "C:\\Users\\Admin\\Documents\\foto.jpg");
    }

    @Test
    public void deveRemoverImagemCorretamente(){
        produtoApplication.append(produto);
        produtoApplication.remove(produto.getId());

        verify(produtoService, times(1)).remove(produto.getId());
    }

    @Test
    public void deveAtualizarImagemCorretamente(){
        produtoApplication.append(produto);
        assertEquals(produto.getImage(), "C:\\Users\\Admin\\Documents\\foto.jpg");

        produto.setImage("C:\\Users\\Admin\\Documents\\foto2.jpg");
        assertEquals(produto.getImage(), "C:\\Users\\Admin\\Documents\\foto2.jpg");
    }
}
