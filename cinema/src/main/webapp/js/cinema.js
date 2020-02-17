

function loadBuzyPlaces() {
    $.ajax('./hall', {
        method : 'get',
        data: {
            first: 'n',
        },
        complete: function(data) {
            var places = JSON.parse(data.responseText);
            console.log(places);
            $("#buzyT tbody").empty();
            $.each(places, function (key, value) {
                var akk = "";
                $.each(value, function (key, value) {
                    akk += '<td>' + value + '</td>';
                });
                $('#buzyT tbody').append('<tr>' + akk + '</tr>');
            });
        }
    });
}


function orderPlace() {
    var place = $('input[name=place]:checked');
    if (place.val() === undefined) {
        alert("Select the seat, please!");
    } else {
        window.location.href = 'order?place=' + place.val();
    }
}