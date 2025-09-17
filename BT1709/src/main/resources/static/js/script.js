// ========== GLOBAL VARIABLES ==========
let currentPage = 0;
let pageSize = 5;

// ========== DOCUMENT READY ==========
document.addEventListener('DOMContentLoaded', function() {
    initializeComponents();
    setupEventListeners();
    setupFormValidation();
});

// ========== INITIALIZATION ==========
function initializeComponents() {
    // Initialize tooltips
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });

    // Initialize popovers
    const popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
    popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl);
    });

    // Setup auto-hide alerts
    setupAutoHideAlerts();

    // Setup search functionality
    setupSearchFunctionality();

    // Setup table enhancements
    setupTableEnhancements();

    // Setup loading states
    setupLoadingStates();
}

// ========== EVENT LISTENERS ==========
function setupEventListeners() {
    // Search form auto-submit on Enter
    const searchInput = document.querySelector('input[name="keyword"]');
    if (searchInput) {
        searchInput.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                e.preventDefault();
                this.closest('form').submit();
            }
        });
    }

    // Status filter change
    const statusSelect = document.querySelector('select[name="status"]');
    if (statusSelect) {
        statusSelect.addEventListener('change', function() {
            showLoadingSpinner();
            this.closest('form').submit();
        });
    }

    // Page size change
    const sizeSelect = document.querySelector('select[name="size"]');
    if (sizeSelect) {
        sizeSelect.addEventListener('change', function() {
            showLoadingSpinner();
            this.closest('form').submit();
        });
    }

    // Delete confirmations
    setupDeleteConfirmations();

    // Form submissions
    setupFormSubmissions();
}

// ========== ALERT MANAGEMENT ==========
function setupAutoHideAlerts() {
    const alerts = document.querySelectorAll('.alert:not(.alert-permanent)');
    alerts.forEach(alert => {
        if (alert.classList.contains('alert-success')) {
            setTimeout(() => {
                fadeOutAlert(alert);
            }, 3000);
        } else if (alert.classList.contains('alert-info')) {
            setTimeout(() => {
                fadeOutAlert(alert);
            }, 5000);
        }
    });
}

function fadeOutAlert(alert) {
    alert.style.transition = 'opacity 0.5s ease';
    alert.style.opacity = '0';
    setTimeout(() => {
        if (alert.parentNode) {
            alert.parentNode.removeChild(alert);
        }
    }, 500);
}

function showAlert(message, type = 'info', duration = 3000) {
    const alertContainer = document.querySelector('.container .mt-3') || document.querySelector('main .container');
    if (!alertContainer) return;

    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type} alert-dismissible fade show`;
    alertDiv.innerHTML = `
        <i class="fas fa-${getAlertIcon(type)} me-2"></i>
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;

    alertContainer.insertBefore(alertDiv, alertContainer.firstChild);

    if (duration > 0) {
        setTimeout(() => fadeOutAlert(alertDiv), duration);
    }
}

function getAlertIcon(type) {
    const icons = {
        'success': 'check-circle',
        'danger': 'exclamation-circle',
        'warning': 'exclamation-triangle',
        'info': 'info-circle'
    };
    return icons[type] || 'info-circle';
}

// ========== LOADING STATES ==========
function setupLoadingStates() {
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', function() {
            const submitBtn = form.querySelector('button[type="submit"]');
            if (submitBtn) {
                showButtonLoading(submitBtn);
            }
        });
    });
}

function showLoadingSpinner() {
    const spinner = document.createElement('div');
    spinner.id = 'globalSpinner';
    spinner.innerHTML = `
        <div class="d-flex justify-content-center align-items-center position-fixed w-100 h-100"
             style="top: 0; left: 0; background: rgba(0,0,0,0.5); z-index: 9999;">
            <div class="spinner-border text-primary" role="status" style="width: 3rem; height: 3rem;">
                <span class="visually-hidden">Đang tải...</span>
            </div>
        </div>
    `;
    document.body.appendChild(spinner);
}

function hideLoadingSpinner() {
    const spinner = document.getElementById('globalSpinner');
    if (spinner) {
        spinner.remove();
    }
}

function showButtonLoading(button) {
    const originalText = button.innerHTML;
    button.dataset.originalText = originalText;
    button.innerHTML = `
        <span class="spinner-border spinner-border-sm me-2" role="status"></span>
        Đang xử lý...
    `;
    button.disabled = true;
}

function hideButtonLoading(button) {
    if (button.dataset.originalText) {
        button.innerHTML = button.dataset.originalText;
        delete button.dataset.originalText;
    }
    button.disabled = false;
}

// ========== DELETE CONFIRMATIONS ==========
function setupDeleteConfirmations() {
    const deleteButtons = document.querySelectorAll('form[action*="/delete/"] button[type="submit"]');
    deleteButtons.forEach(button => {
        const form = button.closest('form');
        form.addEventListener('submit', function(e) {
            e.preventDefault();

            const categoryName = this.dataset.categoryName || 'danh mục này';
            showDeleteConfirmModal(categoryName, () => {
                showButtonLoading(button);
                this.submit();
            });
        });
    });
}

function showDeleteConfirmModal(itemName, onConfirm) {
    const modalHtml = `
        <div class="modal fade" id="deleteConfirmModal" tabindex="-1">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header bg-danger text-white">
                        <h5 class="modal-title">
                            <i class="fas fa-exclamation-triangle me-2"></i>
                            Xác nhận xóa
                        </h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <div class="text-center">
                            <i class="fas fa-trash-alt fa-3x text-danger mb-3"></i>
                            <h5>Bạn có chắc chắn muốn xóa?</h5>
                            <p class="text-muted">Danh mục "<strong>${itemName}</strong>" sẽ bị xóa vĩnh viễn và không thể khôi phục.</p>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                            <i class="fas fa-times me-1"></i>Hủy
                        </button>
                        <button type="button" class="btn btn-danger" id="confirmDelete">
                            <i class="fas fa-trash me-1"></i>Xóa
                        </button>
                    </div>
                </div>
            </div>
        </div>
    `;

    // Remove existing modal if any
    const existingModal = document.getElementById('deleteConfirmModal');
    if (existingModal) {
        existingModal.remove();
    }

    // Add new modal to body
    document.body.insertAdjacentHTML('beforeend', modalHtml);

    const modal = new bootstrap.Modal(document.getElementById('deleteConfirmModal'));

    // Setup confirm button
    document.getElementById('confirmDelete').addEventListener('click', function() {
        modal.hide();
        onConfirm();
    });

    modal.show();
}

// ========== SEARCH FUNCTIONALITY ==========
function setupSearchFunctionality() {
    const searchInput = document.querySelector('input[name="keyword"]');
    if (!searchInput) return;

    let searchTimeout;

    // Real-time search suggestions
    searchInput.addEventListener('input', function() {
        clearTimeout(searchTimeout);
        const query = this.value.trim();

        if (query.length >= 2) {
            searchTimeout = setTimeout(() => {
                showSearchSuggestions(query);
            }, 300);
        } else {
            hideSearchSuggestions();
        }
    });

    // Hide suggestions when clicking outside
    document.addEventListener('click', function(e) {
        if (!e.target.closest('.search-suggestions')) {
            hideSearchSuggestions();
        }
    });
}

function showSearchSuggestions(query) {
    // This would typically make an AJAX call to get suggestions
    // For now, we'll show a simple loading indicator
    const searchInput = document.querySelector('input[name="keyword"]');
    let suggestionsContainer = document.getElementById('searchSuggestions');

    if (!suggestionsContainer) {
        suggestionsContainer = document.createElement('div');
        suggestionsContainer.id = 'searchSuggestions';
        suggestionsContainer.className = 'search-suggestions position-absolute bg-white border rounded shadow-sm w-100 mt-1';
        suggestionsContainer.style.zIndex = '1000';
        searchInput.parentNode.style.position = 'relative';
        searchInput.parentNode.appendChild(suggestionsContainer);
    }

    suggestionsContainer.innerHTML = `
        <div class="p-2 text-muted">
            <i class="fas fa-search me-2"></i>
            Tìm kiếm: "${query}"
        </div>
    `;
    suggestionsContainer.style.display = 'block';
}

function hideSearchSuggestions() {
    const suggestionsContainer = document.getElementById('searchSuggestions');
    if (suggestionsContainer) {
        suggestionsContainer.style.display = 'none';
    }
}

// ========== TABLE ENHANCEMENTS ==========
function setupTableEnhancements() {
    const tables = document.querySelectorAll('.table');
    tables.forEach(table => {
        // Add row hover effects
        const rows = table.querySelectorAll('tbody tr');
        rows.forEach(row => {
            row.addEventListener('mouseenter', function() {
                this.style.transform = 'scale(1.02)';
            });

            row.addEventListener('mouseleave', function() {
                this.style.transform = 'scale(1)';
            });
        });

        // Add sortable columns (if needed)
        setupTableSorting(table);
    });
}

function setupTableSorting(table) {
    const headers = table.querySelectorAll('thead th');
    headers.forEach((header, index) => {
        if (index === 0 || index === headers.length - 1) return; // Skip STT and Actions columns

        header.style.cursor = 'pointer';
        header.addEventListener('click', function() {
            sortTable(table, index);
        });
    });
}

function sortTable(table, columnIndex) {
    const tbody = table.querySelector('tbody');
    const rows = Array.from(tbody.querySelectorAll('tr'));
    const isAscending = table.dataset.sortOrder !== 'asc';

    rows.sort((a, b) => {
        const aText = a.children[columnIndex].textContent.trim();
        const bText = b.children[columnIndex].textContent.trim();

        if (isAscending) {
            return aText.localeCompare(bText, 'vi');
        } else {
            return bText.localeCompare(aText, 'vi');
        }
    });

    // Clear tbody and append sorted rows
    tbody.innerHTML = '';
    rows.forEach(row => tbody.appendChild(row));

    // Update sort order
    table.dataset.sortOrder = isAscending ? 'asc' : 'desc';

    // Update STT column
    updateSTTColumn(table);
}

function updateSTTColumn(table) {
    const rows = table.querySelectorAll('tbody tr');
    rows.forEach((row, index) => {
        const sttCell = row.querySelector('td:first-child');
        if (sttCell) {
            sttCell.textContent = index + 1;
        }
    });
}

// ========== FORM VALIDATION ==========
function setupFormValidation() {
    const forms = document.querySelectorAll('form[novalidate]');
    forms.forEach(form => {
        form.addEventListener('submit', function(e) {
            if (!validateForm(this)) {
                e.preventDefault();
                e.stopPropagation();
            }
            this.classList.add('was-validated');
        });

        // Real-time validation
        const inputs = form.querySelectorAll('input, textarea, select');
        inputs.forEach(input => {
            input.addEventListener('blur', function() {
                validateField(this);
            });
        });
    });
}

function validateForm(form) {
    let isValid = true;
    const inputs = form.querySelectorAll('input[required], textarea[required], select[required]');

    inputs.forEach(input => {
        if (!validateField(input)) {
            isValid = false;
        }
    });

    return isValid;
}

function validateField(field) {
    const value = field.value.trim();
    let isValid = true;
    let errorMessage = '';

    // Required validation
    if (field.hasAttribute('required') && !value) {
        isValid = false;
        errorMessage = 'Trường này là bắt buộc';
    }

    // Length validation
    if (isValid && field.hasAttribute('maxlength')) {
        const maxLength = parseInt(field.getAttribute('maxlength'));
        if (value.length > maxLength) {
            isValid = false;
            errorMessage = `Không được vượt quá ${maxLength} ký tự`;
        }
    }

    if (isValid && field.hasAttribute('minlength')) {
        const minLength = parseInt(field.getAttribute('minlength'));
        if (value.length < minLength) {
            isValid = false;
            errorMessage = `Phải có ít nhất ${minLength} ký tự`;
        }
    }

    // Update field appearance
    field.classList.toggle('is-valid', isValid);
    field.classList.toggle('is-invalid', !isValid);

    // Show/hide error message
    let errorDiv = field.parentNode.querySelector('.invalid-feedback');
    if (errorDiv && errorMessage) {
        errorDiv.textContent = errorMessage;
    }

    return isValid;
}

// ========== FORM SUBMISSIONS ==========
function setupFormSubmissions() {
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', function(e) {
            const submitButton = this.querySelector('button[type="submit"]');
            if (submitButton && !submitButton.disabled) {
                showButtonLoading(submitButton);

                // Auto-enable button after 10 seconds (fallback)
                setTimeout(() => {
                    hideButtonLoading(submitButton);
                }, 10000);
            }
        });
    });
}

// ========== UTILITY FUNCTIONS ==========
function formatDate(date, format = 'dd/MM/yyyy HH:mm') {
    const d = new Date(date);
    const day = d.getDate().toString().padStart(2, '0');
    const month = (d.getMonth() + 1).toString().padStart(2, '0');
    const year = d.getFullYear();
    const hours = d.getHours().toString().padStart(2, '0');
    const minutes = d.getMinutes().toString().padStart(2, '0');

    return format
        .replace('dd', day)
        .replace('MM', month)
        .replace('yyyy', year)
        .replace('HH', hours)
        .replace('mm', minutes);
}

function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

function throttle(func, limit) {
    let lastFunc;
    let lastRan;
    return function() {
        const context = this;
        const args = arguments;
        if (!lastRan) {
            func.apply(context, args);
            lastRan = Date.now();
        } else {
            clearTimeout(lastFunc);
            lastFunc = setTimeout(function() {
                if ((Date.now() - lastRan) >= limit) {
                    func.apply(context, args);
                    lastRan = Date.now();
                }
            }, limit - (Date.now() - lastRan));
        }
    };
}

// ========== EXPORT FOR TESTING ==========
if (typeof module !== 'undefined' && module.exports) {
    module.exports = {
        formatDate,
        debounce,
        throttle,
        validateField,
        showAlert
    };
}