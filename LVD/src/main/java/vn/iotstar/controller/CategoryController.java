package vn.iotstar.controller;

import vn.iotstar.entity.Category;
import vn.iotstar.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Danh sách Categories + phân trang + tìm kiếm
     */
    @GetMapping("")
    public String list(Model model,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "size", defaultValue = "5") int size,
                       @RequestParam(name = "keyword", required = false) String keyword) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage;

        if (keyword != null && !keyword.isEmpty()) {
            categoryPage = categoryService.searchByName(keyword, pageable);
            model.addAttribute("keyword", keyword);
        } else {
            categoryPage = categoryService.findAll(pageable);
        }

        model.addAttribute("categoryPage", categoryPage);
        return "admin/categories/list"; // Trỏ đến templates/admin/categories/list.html
    }

    /**
     * Form thêm mới
     */
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/categories/add"; // Trỏ đến templates/admin/categories/add.html
    }

    /**
     * Lưu category (thêm hoặc sửa)
     */
    @PostMapping("/save")
    public String save(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    /**
     * Form sửa
     */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") int id, Model model) {
        Category category = categoryService.findById(id);
        if (category != null) {
            model.addAttribute("category", category);
            return "admin/categories/edit"; // Trỏ đến templates/admin/categories/edit.html
        }
        return "redirect:/admin/categories";
    }

    /**
     * Xóa category
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        categoryService.deleteById(id);
        return "redirect:/admin/categories";
    }
}
