var lvXings;
var optFlag = 1;
var editIndex = -1;
var selKeHu;
var editKeHu;
var editXianLu;
var editDiJieShe;

$(document).ready(function () {
    getAllKeHus(setTrager_keHu);
    getAllXianLus(setTrager_xianLu);
    getAllDiJieShes(setTrager_diJieShe);
});

function setTrager_keHu() {
    $('#selName').AutoComplete({'data': lb_keHus, 'paramName': 'selKeHu'});
    $('#inpMc').AutoComplete({'data': lb_keHus, 'afterSelectedHandler': selectKeHu});
}

function setTrager_xianLu(){
    $('#inpCtxl').AutoComplete({'data': lb_xianLus, 'paramName': 'editXianLu'});
}

function setTrager_diJieShe(){
    $('#inpCtdjs').AutoComplete({'data': lb_diJieShes, 'paramName': 'editDiJieShe'});
}

function selectKeHu(json) {
    editKeHu = json;
    $("#inpSfz").val(editKeHu.sfz);
    $("#inpDh").val(editKeHu.dh);
}

function jxLvXing(json) {
    $("#data_table_body tr").remove();
    lvXings = [];
    lvXings = json.list;
    $.each(json.list, function (index, item) { //遍历返回的json
        var classStr = '';
        if(item.state === -1){
            classStr = ' class="danger"';
        }
        var trStr = '<tr'+classStr+'><td>' + item.mc + '</td><td>' + item.dm + '</td><td>' + item.lxr + '</td><td>' + item.lxdh + '</td><td>'
                + '<button class="btn btn-info btn-xs icon-edit" onclick="editLvXing(' + index + ' );" style="padding-top: 4px;padding-bottom: 3px;"></button>&nbsp;'
                + '<button class="btn btn-danger btn-xs icon-remove" onclick="deleteLvXing(' + index + ' );" style="padding-top: 4px;padding-bottom: 3px;"></button></td></tr>';
        $("#data_table_body").append(trStr);
    });
}

function selectLvXing() {
    var lvXing = {};
    var tj = {"pageSize": 20, "currentPage": 1};
    if ($("#selName").val() !== "") {
        lvXing.mc = $("#selName").val();
    }
    if ($("#selState").val() !== '' && $("#selState").val() !== "-9") {
        lvXing.state = $("#selState").val();
    }
    tj.paramters = lvXing;
    var options = {};
    options.url = "/LBTravel/lvXing/listLvXingsByPage.do";
    options.tj = tj;
    options.func = jxLvXing;
    options.ul = "#example";
    queryPaginator(options);
}

function addLvXing() {
    optFlag = 1;
    $("#lvXingModel_title").html("新增参团信息");
    $("#inpMc").val("");
    $("#inpSfz").val("");
    $("#inpDh").val("");
    $("#inpCtsj").val("");
    $("#inpCtxl").val("");
    $("#inpCtlx").val("");
    $("#inpCtdjs").val("");
    $("#inpCtfy").val("0");
    $("#inpCtlr").val("0");
    $("#inpA01").val("");
    $("#inpCtfj").val("0");
    $("#inpCtbx").val("0");
    $("#inpBz").val("");
    $("#lvXingModal").modal("show");
}

function editLvXing(index) {
    optFlag = 2;
    if (lvXings[index] === undefined) {
        optFlag = 1;
        return alert("请选择参团信息");
    }
    var lvXing = lvXings[index];
    editIndex = index;
    $("#lvXingModel_title").html("修改参团信息");
    $("#inpMc").val(lvXing.khmc);
    $("#inpSfz").val(lvXing.sfz);
    $("#inpDh").val(lvXing.dh);
    $("#inpCtsj").val(lvXing.ctsj);
    $("#inpCtxl").val(lvXing.ctxl);
    $("#inpCtlx").val(lvXing.ctlx);
    $("#inpCtdjs").val(lvXing.ctdjs);
    $("#inpCtfy").val(lvXing.ctfy);
    $("#inpCtlr").val(lvXing.ctlr);
    $("#inpA01").val(lvXing.a01mc);
    $("#inpCtfj").val(lvXing.ctfj);
    $("#inpCtbx").val(lvXing.ctbx);
    $("#inpBz").val(lvXing.bz);
    $("#lvXingModal").modal("show");
}

function saveLvXing() {
    var lvXing = {};
    var url = "";
    if (optFlag === 2) {
        if (lvXings[editIndex] === undefined) {
            return;
        }
        lvXing = lvXings[editIndex];
        url = "/LBTravel/lvXing/updateLvXing.do";
    } else if (optFlag === 1) {
        url = "/LBTravel/lvXing/saveLvXing.do";
    }
    lvXing.khmc = $("#inpMc").val();
    lvXing.sfz = $("#inpSfz").val();
    lvXing.dh = $("#inpDh").val();
    lvXing.ctsj = $("#inpCtsj").val();
    lvXing.ctxl = $("#inpCtxl").val();
    lvXing.ctlx = $("#inpCtlx").val();
    lvXing.ctdjs = $("#inpCtdjs").val();
    lvXing.ctfy = $("#inpCtfy").val();
    lvXing.ctlr = $("#inpCtlr").val();
    lvXing.a01mc = $("#inpA01").val();
    lvXing.ctfj = $("#inpCtfj").val();
    lvXing.ctbx = $("#inpCtbx").val();
    lvXing.bz = $("#inpBz").val();
    $.ajax({
        url: url,
        data: JSON.stringify(lvXing),
        contentType: "application/json",
        type: "post",
        cache: false,
        error: function (msg, textStatus) {
            alert("保存失败");
        },
        success: function (json) {
            if (json.result === 0) {
                $("#lvXingModal").modal("hide");
                selectLvXing();
            } else {
                alert("保存失败:" + json.msg ? json.msg : "");
            }
        }
    });
}

function deleteLvXing(index) {
    if (lvXings[index] === undefined) {
        return alert("请选择参团信息");
    }
    var lvXing = lvXings[index];
    if (confirm("确定删除参团信息：" + lvXing.mc + "?")) {
        $.ajax({
            url: "/LBTravel/lvXing/deleteLvXing.do?id="+lvXing.id,
            contentType: "application/json",
            type: "get",
            dataType: "json",
            cache: false,
            error: function (msg, textStatus) {
                alert("删除失败");
            },
            success: function (json) {
                if (json.result === 0)
                    selectLvXing();
                else
                    alert("删除失败:" + json.msg ? json.msg : "");
            }
        });
    }
}
