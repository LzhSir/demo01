const rentedTable = $('#rentedTable');

function addButton(value, row, index) {
    return [
        '<div class="btn-group">',
        '<button id="back" type="button" class="btn btn-default">归还</button>',
        '</div>'
    ].join('');
}
window.operateEvents = {
    'click #back': function (e, value, row, index) {
        $.ajax({
            type: "get",
            url: "lease/back",
            dataType: "json",
            data: {
                id: row.id,
                productId: row.product.id
            },
            success: function(res) {
                if (res.code === 20000) {
                    rentedTable.bootstrapTable('refresh');
                } else {
                    alert(res.message);
                }
            }
        });
    }
};

rentedTable.bootstrapTable({
    method: 'get',
    url: 'lease/query',
    datatype: 'json',
    columns: [{
        field: 'brand',
        title: '单车类型',
        formatter: function (value, row, index) {
            if (row.product) {
                return row.product.brand;
            }
        }
    }, {
        field: 'model',
        title: '描述',
        formatter: function (value, row, index) {
            if (row.product) {
                return row.product.model;
            }
        }
    }, {
        field: 'cost',
        title: '金额',
        formatter: function (value, row, index) {
            return value + "元";
        }
    }, {
        field: 'expireTime',
        title: '到期时间',
        formatter: function (value, row, index) {
            return transferDate(value);
        }
    }, {
        field: 'expired',
        title: '是否到期',
        formatter: function (value, row, index) {
            return value ? '是' : '否';
        }
    }, {
        field: 'backed',
        title: '是否归还',
        formatter: function (value, row, index) {
            return value ? '是' : '否';
        }
    }, {
        field: 'operate',
        title: '操作',
        events: operateEvents,
        formatter: addButton
    }],
    responseHandler: function (res) {
        if (res.code !== 20000) {
            alert(res.message);
            return;
        }
        return res.data;
    }
})

/**
 * 时间戳解析
 * @param date 传入需要解析的json字段
 */
function transferDate(date) {
    if (date == null) return '';
    let jsonDate = new Date(parseInt(date));
    Date.prototype.format = function(format) {
        const o = {
            "y+": this.getFullYear(),
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds()
        };
        //将年转换为完整的年形式
        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1,
                (this.getFullYear() + "")
                    .substr(4 - RegExp.$1.length));
        }
        //连接得到的年月日 时分秒信息
        for ( var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1,
                    RegExp.$1.length === 1 ? o[k] : ("00" + o[k])
                        .substr(("" + o[k]).length));
            }
        }
        return format;
    };
    return jsonDate.format("yyyy-MM-dd hh:mm:ss");
}