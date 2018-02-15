function deleteUser(id){
$.ajax({
    url: '/user/' + id,
    type: 'DELETE',


    success: function() {
        window.location.href = 'http://localhost:8080/user';
    }
});
}