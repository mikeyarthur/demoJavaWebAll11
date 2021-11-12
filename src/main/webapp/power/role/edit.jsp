<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>
	学生信息管理平台
</title>
	<link href="../../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
	<link href="../../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
	<link href="../../Style/ks.css" rel="stylesheet" type="text/css" />
	<link href="../../css/mine.css" type="text/css" rel="stylesheet">
    <script src="../../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
    <script src="../../Script/Common.js" type="text/javascript"></script>
    <script src="../../Script/Data.js" type="text/javascript"></script>
    
    
</head>
<body>

		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：教务中心-》考试-》更改</span>
                <span style="float:right;margin-right: 8px;font-weight: bold">
                    <a style="text-decoration: none" href="javascript:history.back();">【返回】</a>
                </span>
            </span>
        </div>
</div>
<div class="cztable">
	<form action="roles?operation=editRole&roleid=${role.roleId}" method="post">
<table border="1" width="100%" class="table_a">
                <tr  width="120px;">
                    <td width="120px">角色名：<span style="color:red">*</span>：</td>
                    <td>
<%--						<input type="text"  name="f_goods_image" value="管理员" />--%>
						<input type="text"  name="rolename" value="${role.roleName}" />
					</td>
                </tr>

                <tr  width="120px;">
                    <td>菜单资源<span style="color:red">*</span>：</td>
                    <td>
<%--						<ul>--%>
<%--                        	<li><input type="checkbox" name="menu"  />权限管理--%>
<%--                            	<ul>--%>
<%--                                	<li>&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="menu"  />人员管理</li>--%>
<%--                                    <li>&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="menu"  />角色管理</li>--%>
<%--                                    <li>&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="menu"  />菜单管理</li>--%>
<%--                                </ul>--%>
<%--                            </li>--%>
<%--                            <li><input type="checkbox" name="menu"  />个人中心</li>--%>
<%--                            <li><input type="checkbox" name="menu"  />教务中心</li>--%>
<%--                        </ul>--%>
                        <c:forEach items="${allmenulist}" var="m1" varStatus="sta1">
                        <ul>
                            <li><input id="idMenuM1${m1.menuId}" type="checkbox" name="namemenu" value="${m1.menuId}" ${m1.checkedType == 1 ? 'checked' : ''} />
                                    ${m1.upmenuId == 0 ? m1.menuName : ''}
                                <c:forEach items="${m1.secondMenuList}" var="m2" varStatus="sta2">
                                <ul>
                                    <li>&nbsp;&nbsp;&nbsp;&nbsp;<input id="idMenuM2${m2.menuId}" type="checkbox" name="namemenu" value="${m2.menuId}" ${m2.checkedType == 1 ? 'checked' : ''} />${m2.menuName}</li>
                                    <script type="text/javascript">
                                        $(function () {
                                            $("input[id='idMenuM2${m2.menuId}']").click(function () {
                                                // 二级菜单选中一个，其父一级菜单也要选中
                                                if ($("input[id='idMenuM2${m2.menuId}']").attr('checked') == true) {
                                                    $("input[id='idMenuM1${m1.menuId}']").attr("checked", true);
                                                }
                                            });
                                            // 一级菜单去勾选，其子二级菜单都去勾选
                                            $("input[id='idMenuM1${m1.menuId}']").click(function () {
                                                if ($("input[id='idMenuM1${m1.menuId}']").attr('checked') == false) {
                                                    $("input[id='idMenuM2${m2.menuId}']").attr("checked", false);
                                                }
                                            });
                                        });
                                    </script>
                                </ul>
                                </c:forEach>
                            </li>
                        </ul>
                        </c:forEach>
					</td>
                </tr>
                
                <tr>
                    <td>启用状态<span style="color:red">*</span>：</td>
                    <td>
<%--                        <input type="radio" name="state" checked value="1" />启用 <input type="radio" name="state" value="0"/>禁用--%>
                        <input type="radio" name="state" value="1" ${role.roleState == 1 ? 'checked' : ''} />启用
                        <input type="radio" name="state" value="0" ${role.roleState == 0 ? 'checked' : ''} />禁用
                    </td>
                </tr>
				
				
				<tr width="120px">
					<td colspan="2" align="center">
						<input type="submit" value="修改" /> 
					</td> 
				</tr>
			</table>
	</form>
</div>

            </div>
        </div>
        
    </div>
</body>
</html>
