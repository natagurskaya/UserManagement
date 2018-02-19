function updateUser(id) {
    var firstName = $('#firstName').val();
    var lastName = $('#lastName').val();
    var payment = $('#payment').val();
    $.ajax({
        url: '/user/' + id,
        type: 'PUT',
        contentType: 'application/json',
        dataType: 'json',
        processData: false,
        data: JSON.stringify({
            "id": id,
            "firstName": firstName,
            "lastName": lastName,
            "payment": payment
        }),

        success: function () {
        },
        error: function () {
            window.location.replace("/user")
        }
    });

}
