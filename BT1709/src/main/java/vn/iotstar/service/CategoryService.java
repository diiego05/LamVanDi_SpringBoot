package vn.iotstar.service;

import vn.iotstar.entity.Category;
import vn.iotstar.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
	@Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        // Sử dụng method có sẵn thay vì findAll(Sort)
        return categoryRepository.findAllByOrderByCreatedDateDesc();
    }

    public Page<Category> findAllWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        return categoryRepository.findAll(pageable);
    }

    public Page<Category> searchCategories(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        if (keyword == null || keyword.trim().isEmpty()) {
            return categoryRepository.findAll(pageable);
        }
        return categoryRepository.findByKeyword(keyword.trim(), pageable);
    }

    public Page<Category> findByStatus(Boolean status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        return categoryRepository.findByStatus(status, pageable);
    }

    public Page<Category> searchByKeywordAndStatus(String keyword, Boolean status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        if (keyword == null || keyword.trim().isEmpty()) {
            return categoryRepository.findByStatus(status, pageable);
        }
        return categoryRepository.findByKeywordAndStatus(keyword.trim(), status, pageable);
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    public boolean existsByNameAndNotId(String name, Long id) {
        return categoryRepository.findByNameAndNotId(name, id) != null;
    }

    public long count() {
        return categoryRepository.count();
    }
}
