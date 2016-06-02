<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="head.jsp"></jsp:include>
     <style type="text/css">
        body{
            margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
        }
        .header
        {
            background:url(../header.gif) repeat-x 0 -1px;
        }
        .Note
        {
            background:url(Notes_Large.png) no-repeat;width:32px;height:32px;
        }
        .Reports
        {
            background:url(Reports_Large.png) no-repeat;width:32px;height:32px;
        }


        </style>
    <title>账户测试</title>
</head>
<body>

<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
    <div class="header" region="north" height="70" showSplit="false" showHeader="false">
        <h1 style="margin:0;padding:15px;cursor:default;font-family:'Trebuchet MS',Arial,sans-serif;">帐务管理平台</h1>
    </div>
    <div title="south" region="south" showSplit="false" showHeader="false" height="30" >
        <div style="line-height:28px;text-align:center;cursor:default">Copyright © 东方金融 </div>
    </div>

   <div region="west" title="在线演示" showHeader="true" bodyStyle="padding-left:1px;" showSplitIcon="true" width="230" minWidth="100" maxWidth="350">

         <ul id="tree1" class="mini-tree" url="tree/init" style="width:300px;height:200px;padding:5px;"
             showTreeIcon="true" textField="text" onbeforeload="onBeforeTreeLoad" onNodeClick="onNodeSelect";
             idField="id" parentField="pid" value="text" resultAsTree="false"
                 >
         </ul>

    </div>


    <div title="center" region="center" bodyStyle="overflow:hidden;">
        <iframe id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" border="0">
        </iframe>
    </div>
</div>


<script type="text/javascript">
mini.parse();
function onBeforeTreeLoad(e) {
        var tree = e.sender;    //树控件
        var node = e.node;      //当前节点
        var params = e.params;  //参数对象

        //可以传递自定义的属性
        //params.myField = "123"; //后台：request对象获取"myField"
    }


    function onNodeSelect(e){
        var node=e.node;
        alert(node.url);
    }
    </script>
</body>


</html>