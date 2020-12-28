$.ajax({
    method: 'get',
    url: 'statistics',
    datatype: 'json',
    success: function(res) {
        if (res.code === 20000) {
            const data = res.data;
            $('#totalProduct').text(data.totalProduct);
            $('#availableProduct').text(data.availableProduct);
            $('#totalUser').text(data.totalUser);
            $('#totalBusiness').text(data.totalBusiness);
        } else {
            alert(res.message);
        }
    }
});