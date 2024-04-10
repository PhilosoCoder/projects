package hu.geralt.repositories.beer;

import hu.geralt.TestEnvironment;
import hu.geralt.entities.beer.Beer;
import hu.geralt.entities.beer.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryRepositoryTest extends TestEnvironment {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BeerRepository beerRepository;

    Beer testBeer;

    @BeforeEach
    void setUp() {
        testBeer = beerRepository.findAll().getFirst();
    }

    @Test
    void testAddCategory() {
        Category savedCat = categoryRepository.save(Category.builder()
                .description("Ales")
                .build());

        testBeer.addCategory(savedCat);

        Beer saveBeer = beerRepository.save(testBeer);

        System.out.println(saveBeer.getCategories());
    }

}