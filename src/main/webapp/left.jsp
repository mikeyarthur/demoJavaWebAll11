<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta http-equiv=content-type content="text/html; charset=utf-8" />
        <link href="css/admin.css" type="text/css" rel="stylesheet" />
        <script language=javascript>
            function expand(el)
            {
                childobj = document.getElementById("child" + el);

                if (childobj.style.display == 'none')
                {
                    childobj.style.display = 'block';
                }
                else
                {
                    childobj.style.display = 'none';
                }
                return;
            }
        </script>
    </head>
    <body background=img/menu_bg.jpg >
        <table height="100%" cellspacing=0 cellpadding=0 width=170   background=./img/menu_bg.jpg border=0>
            <tr>
                <td valign=top align=middle>
                    <table cellspacing=0 cellpadding=0 width="100%" border=0>
						<tr>
                            <td height=10></td>
						</tr>
					</table>
                   
             
<%--					<table cellspacing=0 cellpadding=0 width=150 border=0>--%>
<%--                        <tr height=22>--%>
<%--                            <td style="padding-left: 30px" background=./img/menu_bt.jpg>--%>
<%--							   <a     class=menuparent onclick=expand(2)    href="javascript:void(0);">教务中心</a>--%>
<%--							 </td>--%>
<%--						</tr>--%>
<%--                        <tr height=4>--%>
<%--                            <td></td>--%>
<%--						</tr>--%>
<%--					</table>		--%>
<%--							--%>
<%--					<table id=child2 style="display: none" cellspacing=0 cellpadding=0  width=150 border=0>--%>
<%--                       --%>
<%--						<tr height=20>--%>
<%--                            <td align=middle width=30>--%>
<%--								<img height=9  src="./img/menu_icon.gif" width=9>--%>
<%--							</td>--%>
<%--                            <td>--%>
<%--&lt;%&ndash;								<a class=menuchild  href="Educational/student/getStudentList"   target="right">学生管理</a>&ndash;%&gt;--%>
<%--								<a class=menuchild  href="Educational/student/studentServlet"   target="right">学生管理</a>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						--%>
<%--						--%>
<%--                      --%>
<%--                        <tr height=4>--%>
<%--                            <td colspan=2></td>--%>
<%--						</tr>--%>
<%--					</table>--%>

<%--                    <table cellspacing=0 cellpadding=0 width=150 border=0>--%>
<%--                        <tr height=22>--%>
<%--                            <td style="padding-left: 30px" background=./img/menu_bt.jpg>--%>
<%--                                <a     class=menuparent onclick=expand(7)    href="javascript:void(0);">权限管理</a>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                        <tr height=4>--%>
<%--                            <td></td>--%>
<%--                        </tr>--%>
<%--                    </table>--%>

<%--                    <table id=child7 style="display: none" cellspacing=0 cellpadding=0  width=150 border=0>--%>
<%--                        <tr height=20>--%>
<%--                            <td align=middle width=30>--%>
<%--                                <img height=9 src="./img/menu_icon.gif" width=9>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                <a class=menuchild  href="power/user/users?operation=select"  target="right">用户管理</a>--%>
<%--                            </td>--%>
<%--                        </tr>--%>

<%--                        <tr height=20>--%>
<%--                            <td align=middle width=30>--%>
<%--                                <img height=9 src="./img/menu_icon.gif" width=9>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--&lt;%&ndash;                                <a class=menuchild  href="power/role/list.jsp"  target="right">角色管理</a>&ndash;%&gt;--%>
<%--                                <a class=menuchild  href="power/role/roles?operation=select"  target="right">角色管理</a>--%>
<%--                            </td>--%>
<%--                        </tr>--%>

<%--                        <tr height=20>--%>
<%--                            <td align=middle width=30>--%>
<%--                                <img height=9  src="./img/menu_icon.gif" width=9>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                <a class=menuchild  href="power/menu/list.jsp"   target="right">菜单管理</a>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                        <tr height=4>--%>
<%--                            <td colspan=2></td>--%>
<%--                        </tr>--%>
<%--                    </table>--%>






                    <c:forEach items="${u1.role.menuList}" var="m1" varStatus="sta1">
                    <table cellspacing=0 cellpadding=0 width=150 border=0>
                        <tr height=22>
                            <td style="padding-left: 30px" background=./img/menu_bt.jpg>
                                <a     class=menuparent onclick=expand(${m1.menuId})    href="javascript:void(0);">${m1.menuName}</a>
                            </td>
                        </tr>
                        <tr height=4>
                            <td></td>
                        </tr>
                    </table>
                    <table id=child${m1.menuId} style="display: none" cellspacing=0 cellpadding=0  width=150 border=0>

                        <c:forEach items="${m1.secondMenuList}" var="m2" varStatus="sta2">
                        <tr height=20>
                            <td align=middle width=30>
                                <img height=9  src="./img/menu_icon.gif" width=9>
                            </td>
                            <td>
                                <%--								<a class=menuchild  href="Educational/student/getStudentList"   target="right">学生管理</a>--%>
                                <a class=menuchild  href="${m2.url}"   target="right">${m2.menuName}</a>
                            </td>
                        </tr>



                        <tr height=4>
                            <td colspan=2></td>
                        </tr>
                        </c:forEach>
                    </table>
                    </c:forEach>

                </td>
                <td width=1 bgcolor=#d1e6f7></td>
            </tr>
        </table>
    </body>
</html>