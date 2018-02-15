function updateUser(id, firstName, lastName, payment){
$.ajax({
    url: '/user/' + id,
    type: 'PUT',
    contentType: 'application/json',
    data: {
        //"command": "on"
        "firstName": firstName,
        "lastName": lastName,
        "payment": payment
    },

    success: function() {

    }
});
}
