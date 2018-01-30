function deleteUser(id){
$.ajax({
    url: '/user',
    type: 'DELETE',
    data:{"id": id},

    success: function() {
        // Do something with the result
    }
});
}