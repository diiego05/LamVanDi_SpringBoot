// Submit form add/update
$("#categoryForm").submit(function (e) {
    e.preventDefault();

    let formData = new FormData(this);
    let action = $("#categoryId").val() ? "updateCategory" : "addCategory";

    $.ajax({
        url: "/api/category/" + action,
        type: "POST", // nếu update thì đổi sang PUT
        data: formData,
        processData: false,
        contentType: false,
        success: function (res) {
            alert(res.message);
            loadCategories();
            $("#categoryForm")[0].reset();
        },
        error: function () {
            alert("Lỗi khi lưu category!");
        }
    });
});

// Edit (lấy data theo id)
function editCategory(id) {
    $.post("/api/category/getCategory", {id: id}, function (res) {
        let cat = res.data;
        $("#categoryId").val(cat.categoryId);
        $("#categoryName").val(cat.categoryName);
    });
}
