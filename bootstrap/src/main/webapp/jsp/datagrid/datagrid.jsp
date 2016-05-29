<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>DataGrid 数据表格</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <jsp:include page="../head.jsp"></jsp:include>
</head>
<body>
    <h1>DataGrid 数据表格</h1>      

        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                        <!--<a class="mini-button" iconCls="icon-add" onclick="edit()">编辑</a>-->
                        <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>       
                    </td>
                </tr>
            </table>           
        </div>

    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:1000;" allowResize="false"
        url="init"  idField="id" multiSelect="true">
        <div property="columns">
            <!--<div type="indexcolumn"></div>        -->
            <div type="checkcolumn" ></div>        
            <div field="loginId" width="120" headerAlign="center" allowSort="true">员工帐号</div>
            <div field="name" width="120" headerAlign="center" allowSort="true">姓名</div>    
            <div field="time" width="100" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">创建日期</div>
            <div field="channel" width="120" headerAlign="center" allowSort="true">渠道</div>
        </div>
    </div>
    

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("datagrid1");
        grid.load();

        
        function add() {
            mini.open({
                url: bootPATH + "../CommonLibs/EmployeeWindow.html",
                title: "新增员工", width: 600, height: 400,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "new"};
                    iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {

                    grid.reload();
                }
            });
        }

        function edit() {
            var row = grid.getSelected();
            if (row) {
                mini.open({
                    url: bootPATH + "../demo/CommonLibs/EmployeeWindow.html",
                    title: "编辑员工", width: 600, height: 400,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = { action: "edit", id: row.id };
                        iframe.contentWindow.SetData(data);
                        
                    },
                    ondestroy: function (action) {
                        grid.reload();
                        
                    }
                });
            } else {
                alert("请选中一条记录");
            }
            
        }

        function remove() {
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                if (confirm("确定删除选中记录？")) {
                    var ids = [];
                    for (var i = 0, l = rows.length; i < l; i++) {
                        var r = rows[i];
                        ids.push(r.id);
                    }
                    var id = ids.join(',');
                    grid.loading("操作中，请稍后......");
                    $.ajax({
                        url: "../data/AjaxService.jsp?method=RemoveEmployees&id=" +id,
                        success: function (text) {
                            grid.reload();
                        },
                        error: function () {
                        }
                    });
                }
            } else {
                alert("请选中一条记录");
            }
        }

        /////////////////////////////////////////////////
        function onBirthdayRenderer(e) {
            var value = e.value;
            if (value) return mini.formatDate(value, 'yyyy-MM-dd');
            return "";
        }
        function onMarriedRenderer(e) {
            if (e.value == 1) return "是";
            else return "否";
        }
        var Genders = [{ id: 1, text: '男' }, { id: 2, text: '女'}];        
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
    </script>
</body>
</html>