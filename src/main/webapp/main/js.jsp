<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/30 0030
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    pageContext.setAttribute("basepath",request.getContextPath());
%>

<!-- JQUERY -->
<script src="${basepath }/assets/js/jquery-2.0.3.min.js"></script>
<!-- tree -->
<script src="${basepath }/assets/zTree_v3-master/js/jquery.ztree.core.js"></script>
<!-- CUSTOM SCRIPT -->
<script type="text/javascript" src="${basepath }/assets/js/fuelux-tree/fuelux.tree-sampledata.js"></script>
<script type="text/javascript" src="${basepath }/assets/js/fuelux-tree/fuelux.tree.min.js"></script>

<script type="text/javascript"
        src="${basepath }/style/custom/js/jquery.twbsPagination.min.js"></script>

<script type="text/javascript" src="${basepath }/style/My97DatePicker/WdatePicker.js"></script>
