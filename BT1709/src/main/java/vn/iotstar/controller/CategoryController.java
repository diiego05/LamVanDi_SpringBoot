package vn.iotstar.controller;

import jakarta.validation.Valid;
import vn.iotstar.entity.Category;
import vn.iotstar.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Root redirect
    @GetMapping("/")
    public String home() {
        return "redirect:/categories";
    }

    // Main categories listing - FIXED 2025-09-17 08:26:37
    @GetMapping("/categories")
    public String listCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Boolean status,
            Model model) {

        try {
            Page<Category> categoryPage;

            if (status != null && keyword != null && !keyword.trim().isEmpty()) {
                categoryPage = categoryService.searchByKeywordAndStatus(keyword, status, page, size);
            } else if (status != null) {
                categoryPage = categoryService.findByStatus(status, page, size);
            } else if (keyword != null && !keyword.trim().isEmpty()) {
                categoryPage = categoryService.searchCategories(keyword, page, size);
            } else {
                categoryPage = categoryService.findAllWithPagination(page, size);
            }

            model.addAttribute("categories", categoryPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", categoryPage.getTotalPages());
            model.addAttribute("totalElements", categoryPage.getTotalElements());
            model.addAttribute("size", size);
            model.addAttribute("keyword", keyword);
            model.addAttribute("status", status);
            model.addAttribute("pageTitle", "Danh sách Categories");

            // UPDATED TIME: 2025-09-17 08:26:37
            model.addAttribute("currentDateTime", "2025-09-17 08:26:37 UTC");

            System.out.println("✅ Categories loaded successfully at: 2025-09-17 08:26:37 UTC");
            System.out.println("📊 Total categories: " + categoryPage.getTotalElements());
            System.out.println("🎯 Template: category/list");
            System.out.println("🎨 Layout: layout/layout");

            return "category/list";
        } catch (Exception e) {
            System.err.println("❌ Error loading categories: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Có lỗi xảy ra khi tải danh sách categories: " + e.getMessage());
            model.addAttribute("categories", java.util.Collections.emptyList());
            model.addAttribute("totalElements", 0);
            model.addAttribute("totalPages", 0);
            model.addAttribute("currentPage", 0);
            model.addAttribute("size", size);
            return "category/list";
        }
    }

    @GetMapping("/categories/new")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Thêm Danh mục mới");
        return "category/form";
    }

    @PostMapping("/categories/new")
    public String createCategory(@Valid @ModelAttribute Category category,
                               BindingResult result,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        if (categoryService.existsByName(category.getName())) {
            result.rejectValue("name", "error.category", "Tên danh mục đã tồn tại");
        }

        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Thêm Danh mục mới");
            return "category/form";
        }

        try {
            categoryService.save(category);
            redirectAttributes.addFlashAttribute("success", "Thêm danh mục thành công!");
            System.out.println("✅ Category created: " + category.getName() + " at 2025-09-17 08:26:37");
        } catch (Exception e) {
            System.err.println("❌ Error creating category: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi thêm danh mục!");
        }

        return "redirect:/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Category category = categoryService.findById(id).orElse(null);

            if (category == null) {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy danh mục với ID: " + id);
                return "redirect:/categories";
            }

            model.addAttribute("category", category);
            model.addAttribute("pageTitle", "Chỉnh sửa Danh mục");
            return "category/form";
        } catch (Exception e) {
            System.err.println("❌ Error loading category for edit: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi tải danh mục!");
            return "redirect:/categories";
        }
    }

    @PostMapping("/categories/edit/{id}")
    public String updateCategory(@PathVariable Long id,
                               @Valid @ModelAttribute Category category,
                               BindingResult result,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        if (categoryService.existsByNameAndNotId(category.getName(), id)) {
            result.rejectValue("name", "error.category", "Tên danh mục đã tồn tại");
        }

        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Chỉnh sửa Danh mục");
            return "category/form";
        }

        try {
            Category existingCategory = categoryService.findById(id).orElse(null);
            if (existingCategory == null) {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy danh mục!");
                return "redirect:/categories";
            }

            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
            existingCategory.setStatus(category.getStatus());

            categoryService.save(existingCategory);
            redirectAttributes.addFlashAttribute("success", "Cập nhật danh mục thành công!");
            System.out.println("✅ Category updated: " + category.getName() + " at 2025-09-17 08:26:37");
        } catch (Exception e) {
            System.err.println("❌ Error updating category: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi cập nhật danh mục!");
        }

        return "redirect:/categories";
    }

    @GetMapping("/categories/detail/{id}")
    public String viewCategory(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Category category = categoryService.findById(id).orElse(null);

            if (category == null) {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy danh mục với ID: " + id);
                return "redirect:/categories";
            }

            model.addAttribute("category", category);
            model.addAttribute("pageTitle", "Chi tiết Danh mục");
            return "category/detail";
        } catch (Exception e) {
            System.err.println("❌ Error loading category detail: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi tải chi tiết danh mục!");
            return "redirect:/categories";
        }
    }

    @PostMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Category category = categoryService.findById(id).orElse(null);
            if (category == null) {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy danh mục với ID: " + id);
                return "redirect:/categories";
            }

            String categoryName = category.getName();
            categoryService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Xóa danh mục '" + categoryName + "' thành công!");
            System.out.println("✅ Category deleted: " + categoryName + " at 2025-09-17 08:26:37");
        } catch (Exception e) {
            System.err.println("❌ Error deleting category: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi xóa danh mục: " + e.getMessage());
        }

        return "redirect:/categories";
    }
}