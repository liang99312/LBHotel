
$(document).ready(function () {
    getAllA01s(setTrager_a01);
    getAllKeHus(setTrager_keHu);
    getAllXianLus(setTrager_xianLu);
    getAllDiJieShes(setTrager_diJieShe);
    $('#selSjq').datetimepicker({language: 'zh-CN', format: 'yyyy-mm-dd', weekStart: 7, todayBtn: 1, autoclose: 1, todayHighlight: 1, minView: 2, startView: 2, forceParse: 0, showMeridian: 1});
    $('#selSjz').datetimepicker({language: 'zh-CN', format: 'yyyy-mm-dd', weekStart: 7, todayBtn: 1, autoclose: 1, todayHighlight: 1, minView: 2, startView: 2, forceParse: 0, showMeridian: 1});
});

function setTrager_a01() {
    $('#selA01').AutoComplete({'data': lb_allA01s});
}

function setTrager_keHu() {
    $('#selName').AutoComplete({'data': lb_keHus});
}

function setTrager_xianLu() {
    $('#selXianLu').AutoComplete({'data': lb_xianLus});
}

function setTrager_diJieShe() {
    $('#selDiJieShe').AutoComplete({'data': lb_diJieShes});
}


function jxTongJi(list) {
    $("#data_table_body tr").remove();
    var js = list.length;
    var zfy = 0;
    var zlr = 0;
    var zyj = 0;
    var zbx = 0;
    for (var i = 0; i < list.length; i++) {
        var item = list[i];
        var trStr = '<tr><td>' + item.khmc + '</td><td>' + item.sfz + '</td><td>' + item.dh
                + '</td><td>' + item.ctsj + '</td><td>' + item.ctxl + '</td><td>' + item.ctxz + '</td><td>' + item.ctdjs + '</td><td>' + item.ctfy
                + '</td><td>' + item.ctlr + '</td><td>' + item.a01mc + '</td><td>' + item.ctfj + '</td><td>' + item.ctbx + '</td><td>' + item.bz
                + '</td><td></tr>';
        $("#data_table_body").append(trStr);
        zfy = zfy + item.ctfy;
        zlr = zlr + item.ctlr;
        zyj = zyj + item.ctfj;
        zbx = zbx + item.ctbx;
    }
    var ztrStr = '<tr style="background:#c1fffc"><td>合计：</td><td>' + js + '条</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>' + zfy
            + '</td><td>' + zlr + '</td><td>' + item.a01mc + '</td><td>' + zyj + '</td><td>' + zbx + '</td><td>&nbsp;</td><td></tr>';
    $("#data_table_body").append(ztrStr);
}

function selectTongJi() {
    var lvXing = {};
    if ($("#selName").val() !== "") {
        lvXing.khmc = $("#selName").val();
    }
    if ($("#selXianLu").val() !== "") {
        lvXing.ctxl = $("#selXianLu").val();
    }
    if ($("#selDiJieShe").val() !== "") {
        lvXing.ctdjs = $("#selDiJieShe").val();
    }
    if ($("#selA01").val() !== "") {
        lvXing.a01mc = $("#selA01").val();
    }
    if ($("#selSjq").val() !== "") {
        lvXing.sjq = $("#selSjq").val();
    } else {
        return alert("请输入查询起始日期！")
    }
    if ($("#selSjz").val() !== "") {
        lvXing.sjz = $("#selSjz").val();
    }
    $.ajax({
        url: "/LBTravel/lvXing/tongJiLvXing.do",
        data: JSON.stringify(lvXing),
        contentType: "application/json",
        type: "post",
        cache: false,
        error: function (msg, textStatus) {
            alert("保存失败");
        },
        success: function (json) {
            if (json.result === 0) {
                jxTongJi(json.data);
            } else {
                alert("保存失败:" + json.msg ? json.msg : "");
            }
        }
    });
}

function daochu() {
    var temptitle = $.trim($("#selSjq").val()) + "至" + $.trim($("#selSjz").val()) + '参团信息汇总.xls';
    tableToExcel('data_table', 'Sheet1', temptitle);
}
var tableToExcel = (function () {
    var uri = 'data:application/vnd.ms-excel;base64,';
    var template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines></x:DisplayGridlines></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>';
    var base64 = function (s) {
        return window.btoa(unescape(encodeURIComponent(s)));
    };
    var format = function (s, c) {
        return s.replace(/{(\w+)}/g, function (m, p) {
            return c[p];
        });
    };
    return function (table, name, filename) {
        if (!table.nodeType)
            table = document.getElementById(table);
        var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}

        var a = document.createElement("a");
        a.href = uri + base64(format(template, ctx));
        a.download = filename;
        a.click();
        a.remove();
    };
})();

