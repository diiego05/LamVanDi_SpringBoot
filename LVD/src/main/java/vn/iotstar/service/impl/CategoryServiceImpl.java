package vn.iotstar.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import vn.iotstar.entity.Category;
import vn.iotstar.repository.CategoryRepository;
import vn.iotstar.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Constructor
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public <S extends Category> S save(S entity) {
        if (entity.getCategoryId() == null) {
            // Thêm mới
            return categoryRepository.save(entity);
        } else {
            // Cập nhật
            Optional<Category> opt = findById(entity.getCategoryId());
            if (opt.isPresent()) {
                Category old = opt.get();

                // Giữ icon cũ nếu không nhập mới
                if (!StringUtils.hasText(entity.getIcon())) {
                    entity.setIcon(old.getIcon());
                }

                // Giữ images cũ nếu không nhập mới
                if (!StringUtils.hasText(entity.getImages())) {
                    entity.setImages(old.getImages());
                }
            }
            return categoryRepository.save(entity);
        }
    }

    @Override
    public Optional<Category> findByCategoryName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public List<Category> findAll(Sort sort) {
        return categoryRepository.findAll(sort);
    }

    @Override
    public List<Category> findAllById(Iterable<Long> ids) {
        return categoryRepository.findAllById(ids);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public <S extends Category> Optional<S> findOne(Example<S> example) {
        return categoryRepository.findOne(example);
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void delete(Category entity) {
        categoryRepository.delete(entity);
    }

    @Override
    public Page<Category> findByCategoryNameContainingIgnoreCase(String name, Pageable pageable) {
        return categoryRepository.findByCategoryNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public List<Category> findByCategoryNameContainingIgnoreCase(String name) {
        return categoryRepository.findByCategoryNameContaining(name);
    }
}
