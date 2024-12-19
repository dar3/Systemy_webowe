package org.example.spring_2;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Query("SELECT c.code FROM Category c ORDER BY c.code DESC LIMIT 1")
    Optional<String> findMaxCode();
    Category findByName(String name);
    Category findByCode(String code);

}
