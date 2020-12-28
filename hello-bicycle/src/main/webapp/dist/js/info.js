const infoTable = $('#infoTable');
const rentModal = $('#rentModal');
const formModal = $('#formModal');
var curItem;
var isCreate = true;

function addButton(value, row, index) {
    if (roles.includes('admin')) {
        return [
            '<div class="btn-group">',
            '<button id="hire" type="button" class="btn btn-default" data-toggle="modal">骑行</button>',
            '<button id="update" type="button" class="btn btn-primary" data-toggle="modal" data-target="#formModal">修改</button>',
            '<button id="delete" type="button" class="btn btn-danger">删除</button>',
            '</div>'
        ].join('');
    } else {
        return [
            '<div class="btn-group">',
            '<button id="hire" type="button" class="btn btn-default" data-toggle="modal">骑行</button>',
            '</div>'
        ].join('');
    }
}
window.operateEvents = {
    'click #update': function (e, value, row, index) {
        updateInit(row);
    }, 'click #delete': function (e, value, row, index) {
        deleteItem(row.id);
    }, 'click #hire': function (e, value, row, index) {
        if (row.status) {
            alert('该车辆已被使用');
            return;
        }
        rentModal.modal('show');
        hireInit(row);
    }
};

infoTable.bootstrapTable({
    method: 'get',
    url: 'products/query',
    datatype: 'json',
    columns: [{
        field: 'brand',
        title: '单车类型'
    }, {
        field: 'model',
        title: '描述'
    }, {
        field: 'rent',
        title: '租金'
    }, {
        field: 'licence',
        title: '车辆编号'
    }, {
        field: 'status',
        title: '状态',
        formatter: function (value, row, index) {
            return value ? '使用中' : '闲置';
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

function createInit() {
    isCreate = true;
    $('#brandIn').val('');
    $('#modalIn').val('');
    $('#rentIn').val('');
    $('#licenceIn').val('');
}

function createItem() {
    let brand = $('#brandIn').val();
    let model = $('#modalIn').val();
    let rent = $('#rentIn').val();
    let licence = $('#licenceIn').val();
    formModal.modal('hide');
    $.ajax({
        type: "post",
        url: "products/create",
        dataType: "json",
        data: {
            brand: brand,
            model: model,
            rent: rent,
            licence: licence
        },
        success: function(res) {
            if (res.code === 20000) {
                infoTable.bootstrapTable('refresh');
            } else {
                alert(res.message);
            }
        }
    })
}

function updateInit(row) {
    isCreate = false;
    curItem = row;
    $('#brandIn').val(row.brand);
    $('#modalIn').val(row.model);
    $('#rentIn').val(row.rent);
    $('#licenceIn').val(row.licence);
}

function updateItem() {
    let brand = $('#brandIn').val();
    let model = $('#modalIn').val();
    let rent = $('#rentIn').val();
    let licence = $('#licenceIn').val();
    formModal.modal('hide');
    $.ajax({
        type: "post",
        url: "products/update",
        dataType: "json",
        data: {
            id: curItem.id,
            brand: brand,
            model: model,
            rent: rent,
            licence: licence
        },
        success: function(res) {
            if (res.code === 20000) {
                infoTable.bootstrapTable('refresh');
            } else {
                alert(res.message);
            }
        }
    });
}

function deleteItem(id) {
    $.ajax({
        type: "get",
        url: "products/delete?id=" + id,
        dataType: "json",
        success: function(res) {
            if (res.code === 20000) {
                infoTable.bootstrapTable('refresh');
            } else {
                alert(res.message);
            }
        }
    })
}

function hireInit(row) {
    curItem = row;
    $('#hireBrand').val(row.brand);
    $('#hireModel').val(row.model);
    $('#hireRent').val(row.rent);
    $('#hireLicence').val(row.licence);
}

$('#createBtn').on('click', function () {
    createInit();
})

$('#hireSubmit').on('click', function () {
    let value = $('#hireRentTime').val();
    if (value === '') {
        alert('请填写租用时长');
        return;
    }
    let days = parseInt(value);
    rentModal.modal('hide');
    $.ajax({
        type: "get",
        url: "lease/hire",
        dataType: "json",
        data: {
            productId: curItem.id,
            duration: days
        },
        success: function(res) {
            if (res.code === 20000) {
                infoTable.bootstrapTable('refresh');
            } else {
                alert(res.message);
            }
        }
    });
});

$('#formSubmit').on('click', function () {
    if (isCreate) {
        createItem();
    } else {
        updateItem();
    }
});