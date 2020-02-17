$(loadInfo());

function loadInfo() {
    /*  dddd   */

       $.ajax({
            type: "GET",
            url: "./order",
            success: function (data) {
                console.log(data);
                $('#chosenPlaceInfo').append('<h3>' + data + '</h3>');
            }
        });

}

function makeOrder() {
    var fullName = $('#fullName');
    var phone = $('#phone');
    var params = {
        "fullName": fullName.val(),
        "phone": phone.val()
    };

    $.ajax('./order', {
        method : 'post',
        data: JSON.stringify(params),
        dataType: "json",
        cache: false,
        complete: function(data) {
            console.log(data.responseText);
        }
    });
}

function goToMain() {
   window.location.href = 'hall';
}