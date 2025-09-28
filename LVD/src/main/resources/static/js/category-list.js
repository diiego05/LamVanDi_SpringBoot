$(document).ready(function () {
    loadCategories();
});

function loadCategories(keyword = "") {
    $.get("/api/category", function (res) {
        let data = res.data;
        if (keyword) {
            data = data.filter(c => c.categoryName.toLowerCase().includes(keyword.toLowerCase()));
        }
        let rows = "";
        data.forEach(cat => {
            rows += `
                <tr>
                    <td>${cat.categoryId}</td>
                    <td>${cat.categoryName}</td>
                    <td><img src="/uploads/${cat.icon ?? ''}" width="120"></td>
                    <td>
                        <a href="javascript:void(0)" onclick="editCategory(${cat.categoryId})">Sửa</a> |
                        <a href="javascript:void(0)" onclick="deleteCategory(${cat.categoryId})">Xóa</a>
                    </td>
                </tr>`;
        });
        $("#categoryTable").html(rows);
    });
}

function searchCategory() {
    let keyword = $("#keyword").val();
    loadCategories(keyword);
}

function deleteCategory(id) {
    if (confirm("Bạn có chắc muốn xóa?")) {
        $.ajax({
            url: "/api/category/deleteCategory?categoryId=" + id,
            type: "DELETE",
            success: function (res) {
                alert(res.message);
                loadCategories();
            },
            error: function () {
                alert("Lỗi khi xóa category!");
            }
        });
    }
}
