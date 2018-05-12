var keHus;
var optFlag = 1;
var editIndex = -1;

$(document).ready(function () {
});

function jxKeHu(json) {
    $("#data_table_body tr").remove();
    keHus = [];
    keHus = json.list;
    $.each(json.list, function (index, item) { //遍历返回的json
        var classStr = '';
        if(item.state === -1){
            classStr = ' class="danger"';
        }
        var trStr = '<tr'+classStr+'><td>' + item.mc + '</td><td>' + item.dm + '</td><td>' + item.sfz + '</td><td>' + item.dh + '</td><td>'
                + '<button class="btn btn-info btn-xs icon-edit" onclick="editKeHu(' + index + ' );" style="padding-top: 4px;padding-bottom: 3px;"></button>&nbsp;'
                + '<button class="btn btn-danger btn-xs icon-remove" onclick="deleteKeHu(' + index + ' );" style="padding-top: 4px;padding-bottom: 3px;"></button></td></tr>';
        $("#data_table_body").append(trStr);
    });
}

function selectKeHu() {
    var keHu = {};
    var tj = {"pageSize": 20, "currentPage": 1};
    if ($("#selName").val() !== "") {
        keHu.mc = $("#selName").val();
    }
    if ($("#selState").val() !== '' && $("#selState").val() !== "-9") {
        keHu.state = $("#selState").val();
    }
    tj.paramters = keHu;
    var options = {};
    options.url = "/LBTravel/keHu/listKeHusByPage.do";
    options.tj = tj;
    options.func = jxKeHu;
    options.ul = "#example";
    queryPaginator(options);
}

function addKeHu() {
    optFlag = 1;
    $("#keHuModel_title").html("新增客户");
    $("#inpMc").val("");
    $("#inpDm").val("");
    $("#inpSfz").val("");
    $("#inpDh").val("");
    $("#inpXb").val("");
    $("#inpNl").val("0");
    $("#inpBz").val("");
    $("#keHuModal").modal("show");
}

function editKeHu(index) {
    optFlag = 2;
    if (keHus[index] === undefined) {
        optFlag = 1;
        return alert("请选择客户");
    }
    var keHu = keHus[index];
    editIndex = index;
    $("#keHuModel_title").html("修改客户");
    $("#inpMc").val(keHu.mc);
    $("#inpDm").val(keHu.dm);
    $("#inpSfz").val(keHu.sfz);
    $("#inpDh").val(keHu.dh);
    $("#inpXb").val(keHu.xb);
    $("#inpNl").val(keHu.nl);
    $("#inpBz").val(keHu.bz);
    $("#keHuModal").modal("show");
}

function saveKeHu() {
    var keHu = {};
    var url = "";
    if (optFlag === 2) {
        if (keHus[editIndex] === undefined) {
            return;
        }
        keHu = keHus[editIndex];
        url = "/LBTravel/keHu/updateKeHu.do";
    } else if (optFlag === 1) {
        url = "/LBTravel/keHu/saveKeHu.do";
    }
    keHu.mc = $("#inpMc").val();
    keHu.dm = $("#inpDm").val();
    keHu.sfz = $("#inpSfz").val();
    keHu.dh = $("#inpDh").val();
    keHu.xb = $("#inpXb").val();
    keHu.nl = parseInt($("#inpNl").val());
    keHu.bz = $("#inpBz").val();
    $.ajax({
        url: url,
        data: JSON.stringify(keHu),
        contentType: "application/json",
        type: "post",
        cache: false,
        error: function (msg, textStatus) {
            alert("保存失败");
        },
        success: function (json) {
            if (json.result === 0) {
                $("#keHuModal").modal("hide");
                selectKeHu();
            } else {
                alert("保存失败:" + json.msg ? json.msg : "");
            }
        }
    });
}

function deleteKeHu(index) {
    if (keHus[index] === undefined) {
        return alert("请选择客户");
    }
    var keHu = keHus[index];
    if (confirm("确定删除客户：" + keHu.mc + "?")) {
        $.ajax({
            url: "/LBTravel/keHu/deleteKeHu.do?id="+keHu.id,
            contentType: "application/json",
            type: "get",
            dataType: "json",
            cache: false,
            error: function (msg, textStatus) {
                alert("删除失败");
            },
            success: function (json) {
                if (json.result === 0)
                    selectKeHu();
                else
                    alert("删除失败:" + json.msg ? json.msg : "");
            }
        });
    }
}
