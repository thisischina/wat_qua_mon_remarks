<!-- AUI Framework -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    pageContext.setAttribute("basepath", request.getContextPath());
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="${basepath}/main/css.jsp"></jsp:include>

    <title>用户列表</title>

    <style type="text/css">
        #pagination {
            margin: 0px 0px;
        }

        .pagination>li>a {
            background: #57697e;
            border: #c1cad5 solid 1px;
            color: #fff
        }

        .pagination>.active>a {
            border: #c1cad5 solid 1px;
            background: #e4e9f0;
            color: #57697e
        }

        #tbody td{text-align: center}

    </style>

    <jsp:include page="${basepath}/main/js.jsp"></jsp:include>

    <script type="text/javascript">
        jQuery(document).ready(function() {
            role = "${sessionScope.user.role }";

            loadDataGird();
        });

        var createTable = function(index, item) {
            var roleName =item.roleId;

            var str = "";
            if(index%2==0){
                str =str+"<tr style='background:RGB(211,223,238)'>" + "<td>";
            }else{
                str =str+"<tr>" + "<td>";
            }
            str=str+ (index + 1)
                + "</td>"
                + "<td>"
                + (item.account == null ? "" : item.account)
                + "</td>"
                + "<td>"
                + (item.tel == null ? "" : item.tel)
                + "</td>"
                + "<td>"
                + (item.email == null ? "" : item.email)
                + "</td>"
                + "<td>"
                +roleName
                + "</td>";

            if(role==1){
                str=str+ "<td>"
                    + "<div class=''>"
                    + "<a class='btn btn-xs btn-info' href='../User/directUpdateUser.action?id="
                    + item.id
                    + "' style='height:20px;font-size:10px;margin-right:4px'>"
                    + "<i class='ace-icon fa fa-pencil bigger-120'></i>修改"
                    + "</a>"
                    + "<a class='btn btn-xs btn-danger' href='javascript:void(0)' onclick='deleteUser("
                    + item.id
                    + ")' style='height:20px;font-size:10px;'>"
                    + "<i class='ace-icon fa fa-trash-o bigger-120'></i> 删除"
                    + "</a>" + "</div>" + "</td>" + "</tr>	";
            }else{
                str=str+ "</tr>	";
            }

            $("#tbody").append(str);
        }

        var loadDataGird = function() {

            var account = $("#account").val();
            $.ajax({
                    url : "${basepath }/user/getlist",
                    type : "post",
                    data : {account:account},
                    dataType : "json",
                    success : function(data) {
                        $("#tbody").html("");
                        $('#pagination_div').html("");

                        if (data == null || data.total == 0) {
                            return;
                        }
                        $.each(data.list, function(index, item) { //遍历返回的json
                            createTable(index, item);
                        });

                        //分页插件
                        $('#pagination_div').html(
                            "<ul id='pagination' class='pagination-sm' style='float:right'></ul>");
                        $('#pagination').twbsPagination({
                            totalPages : data.pageCount,
                            visiblePages : 5,
                            first : '首页',
                            prev : '上一页',
                            next : '下一页',
                            last : '末页',
                            onPageClick : function(event, page) {
                                var account = $("#account").val();
                                var page=page;
                                $.ajax({
                                    url : "${basepath }/user/getlist",
                                    type : "post",
                                    data : {pageNow:page,account:account},
                                    dataType : "json",
                                    success : function(data) {
                                        $("#tbody").html("");

                                        if (data == null|| data.total == 0) {
                                            return;
                                        }
                                        $.each(data.list,function(index,item) {
                                            createTable(index,item);
                                        });
                                    }
                                });
                            }
                        });
                        //分页插件end
                    }
                });
        }
    </script>

</head>
<body>

<div class='row' style="margin: 0px">
    <div class='col-md-12'>
        <form class="form-inline" role="form"
              style='float: right;margin-bottom:10px;margin-top: 5px;margin-left: 5px '>
            <div class="form-group">
                <label class="sr-only">Email
                    address</label> <input type="text" class="form-control"
                                           id="account" placeholder="账号">
            </div>
            <button type="button" onclick="loadDataGird()" class="btn btn-info"  style='background: #4F81BD;border: 1px solid #4F81BD' >查询</button>
        </form>
    </div>
</div>

<div class='row' style="margin: 0px">
    <div class='col-md-12'>
        <table class="table table-bordered">
            <thead style='background:#A8BC7B;color:#fff'>
            <tr>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>序号</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>用户名</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>手机</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>邮箱</th>
                <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>角色</th>

                <c:if test="${ sessionScope.user.role==1}">
                    <th class="text-center"  style='background:RGB(79,129,189);color:#fff'>操作</th>
                </c:if>
            </tr>
            </thead>
            <tbody id="tbody">

            </tbody>
        </table>
        <div class="dataTables_info" id="dynamic-table_info"
             style="float: left;">
                <a class="btn btn-info" style='background: #4F81BD;border: 1px solid #4F81BD'
                   href="${basepath}/user/toadd">
                    <span class="button-content">添加</span> </a>
        </div>
        <div id="pagination_div" style='float: right;padding-right: 0px'>

        </div>
    </div>
</div>


</body>


</html>