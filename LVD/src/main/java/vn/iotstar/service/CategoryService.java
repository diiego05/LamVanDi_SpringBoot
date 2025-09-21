package vn.iotstar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.iotstar.entity.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
    Category save(Category category);
    void deleteById(int id);
    
    // phân trang + tìm kiếm
    Page<Category> findAll(Pageable pageable);
    Page<Category> searchByName(String name, Pageable pageable);
}