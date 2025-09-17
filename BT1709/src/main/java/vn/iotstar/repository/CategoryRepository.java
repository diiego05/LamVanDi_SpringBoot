package vn.iotstar.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	List<Category> findAllByOrderByCreatedDateDesc();

    @Query("SELECT c FROM Category c WHERE c.name LIKE %:keyword% OR c.description LIKE %:keyword%")
    Page<Category> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT c FROM Category c WHERE c.status = :status")
    Page<Category> findByStatus(@Param("status") Boolean status, Pageable pageable);

    @Query("SELECT c FROM Category c WHERE (c.name LIKE %:keyword% OR c.description LIKE %:keyword%) AND c.status = :status")
    Page<Category> findByKeywordAndStatus(@Param("keyword") String keyword, @Param("status") Boolean status, Pageable pageable);

    boolean existsByName(String name);

    @Query("SELECT c FROM Category c WHERE c.name = :name AND c.id != :id")
    Category findByNameAndNotId(@Param("name") String name, @Param("id") Long id);

}
