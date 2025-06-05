package com.jenkins_app.Dao;

import com.jenkins_app.Exemple;
import com.jenkins_app.Repo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class Dao {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    Repo repo;


    @Test
    public void findAllProductsTest() {
        Exemple p1  = new Exemple();
        p1.id=5;
        entityManager.persist(p1);
        Exemple p2 = new Exemple();
        p2.id=6;
        entityManager.persist(p2);
        Exemple p3  = new Exemple();
        p3.id=7;

        entityManager.flush();

        List<Exemple> products = repo.findAll();
        // assertThat(products).hasSize(3).contains(p1, p2, p3); // if no data is in the H2 database
        assertThat(products).hasSize(2).contains(p1, p2);
    }

    @Test
    public void findProductByIdTest() {
        Exemple p1  = new Exemple();
        p1.id=8;
        entityManager.persist(p1);
        Exemple foundProduct = repo.findById(p1.id).get();
        assertThat(foundProduct).isEqualTo(p1);
    }

    /*@Test
    public void createProductTest() {
        Exemple p  = new Product("product 1", "product 1 description", 400.0);
        productDao.save(p);
        assertThat(p).hasFieldOrPropertyWithValue("name", "product 1");
        assertThat(p).hasFieldOrPropertyWithValue("description", "product 1 description");
        assertThat(p).hasFieldOrPropertyWithValue("price", 400.0);
    }

    @Test
    public void deleteProductTest() {
        Product p1  = new Product("product 1", "product 1 description", 400.0);
        entityManager.persist(p1);
        Product p2  = new Product("product 2", "product 2 description", 600.0);
        entityManager.persist(p2);
        Product p3  = new Product("product 3", "product 3 description", 700.0);
        entityManager.persist(p3);
        productDao.delete(p1);
        Iterable<Product> products = productDao.findAll();
        assertThat(products).doesNotContain(p1).contains(p2, p3);
    }*/


}
