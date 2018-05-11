var goYuanGong = "/LBTravel/a01/goA01.do";
var goKeHu = "/LBTravel/keHu/goKeHu.do";
var goLvXing = "/LBTravel/lvXing/goLvXing.do";
var goPassword = "/LBTravel/goPassword.do";

var allMenu = {data: [
        {id: '1', text: '组织机构', icon: 'icon-leaf', url: '', menus: [
                {id: '101', text: '人员管理', icon: 'icon-glass', url: goYuanGong}]},
        {id: '2', text: '客户管理', icon: 'icon-leaf', url: '', menus: [
                {id: '201', text: '客户管理', icon: 'icon-glass', url: goKeHu}]},
        {id: '3', text: '业务管理', icon: 'icon-leaf', url: '', menus: [
                {id: '301', text: '旅行信息', icon: 'icon-glass', url: goLvXing},
                {id: '302', text: '统计分析', icon: 'icon-glass', url: goTongJi}]},
        {id: '9', text: '系统管理', icon: 'icon-cog', url: '', menus: [
                {id: '901', text: '修改密码', icon: 'icon-glass', url: goPassword}]}]};
var loadUser = null;

$(document).ready(function () {
    $.ajax({
        url: "/LBTravel/getLoginA01.do",
        contentType: "application/json",
        type: "post",
        cache: false,
        error: function (msg, textStatus) {
        },
        success: function (json) {
            if (json.result === 0) {
                loadUser = json.a01;
                $("#span_user_name").html(loadUser.mc);
                setMenu(loadUser.a01qx);
            } else {
                window.top.location.href = "/LBTravel/logout.do";
            }
        }
    });
});

function setMenu(quanxian) {
    var qxArray = quanxian.split(";");
    var menu = jQuery.extend(true, {}, allMenu);
    if (quanxian !== '-1') {
        for (var i = allMenu.data.length - 1; i > -1; i--) {
            var m = allMenu.data[i];
            for (var j = m.menus.length - 1; j > -1; j--) {
                var e = m.menus[j];
                if (qxArray.indexOf(e.id) < 0) {
                    menu.data[i].menus.splice(j, 1);
                }
            }
            if (menu.data[i].menus.length === 0) {
                menu.data.splice(i, 1);
            }
        }
    }
    $('#menu').sidebarMenu(menu);
}

function SetWinHeight(obj) {
    var win = obj;
    if (document.getElementById) {
        if (win && !window.opera) {
            if (win.contentDocument && win.contentDocument.body.offsetHeight) {
                win.height = win.contentDocument.body.offsetHeight + 25;
            } else if (win.Document && win.Document.body.scrollHeight) {
                win.height = win.Document.body.scrollHeight;
            }
        }
    }
}

function SetFrameHeight(id, height, force) {
    if (force) {
        $("#" + id).css("height", height);
    } else if (parseInt($("#" + id).css("height")) < height) {
        $("#" + id).css("height", height);
    }
}