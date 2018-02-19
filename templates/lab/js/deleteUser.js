function deleteUser(id) {
    $.ajax({
        url: '/user/' + id,
        type: 'DELETE',
        contentType: 'application/json',
        dataType: 'json',
        processData: false,
        data: JSON.stringify({
             "id": id
        }),
        success: function () {
        },
        error: function () {
            window.location.replace("/user")
        }
    });
}