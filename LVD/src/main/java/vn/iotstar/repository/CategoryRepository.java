package vn.iotstar.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.iotstar.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // Tìm kiếm theo tên có chứa chuỗi (ignore case)
    Page<Category> findByCategoryNameContainingIgnoreCase(String name, Pageable pageable);
}