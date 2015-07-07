<%--
  Created by IntelliJ IDEA.
  User: B50-30
  Date: 01.07.2015
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="dto.Seller"%>
<%@ page import="dao.CombineQuerryDAO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dto.CombineQuerry"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Salary calculator</title>
  <link rel="stylesheet" href="css/tcal.css" type="text/css">
  <script type="text/javascript" src="javascript/tcal_en.js"></script>
</head>
<body background=img/bricks-12.gif>
<h3 align="center"><a href="ProductController">To food database</a>                <a href="SellerController">To sellers manager</a></h3>



<table border="1" align="center">
  <thead>
  <tr>
    <th></th>
    <th>select seller</th>
    <th>last name</th>
    <th>first name</th>
    <th>second name</th>
    <th></th>
    <th>from</th>
    <th>till</th>
  </tr>
  </thead>
  <tbody>
  <tr>
    <td>Seller:</td>
    <td>
      <form action="CombineController">
        <h3><select name="comboboxLastName">
          <%for (Seller next : (List<Seller>) request.getAttribute("combolastNameList")) {%>
          <option value="<%=next.getIdSeller()%>"><%=next.getLastName()%></option>
          <%}%>
        </select>
        <input type="submit" value="OK"></h3>
      </form>
    </td>
    <%List<Seller> sellerList = (List<Seller>) request.getAttribute("showSellerForCombine");%>
    <form action="CombineController">
      <td>
        <input type="text" name="txtLastName" value="<%if (request.getParameter("comboboxLastName") != null) {%><%=sellerList.get(0).getLastName()%><%}%>">
      </td>
      <td>
        <input type="text" name="txtFirstName" value="<%if (request.getParameter("comboboxLastName") != null) {%><%=sellerList.get(0).getFirstName()%><%}%>">
      </td>
      <td>
        <input type="text" name="txtSecondName" value="<%if (request.getParameter("comboboxLastName") != null) {%><%=sellerList.get(0).getSecondName()%><%}%>">
      </td>
      <td>
        <input type="submit" name="btnCalc" value="Calculate">
      </td>
      <td>
        <input type="text" name="txtFrom" class="tcal" value="">
      </td>
      <td>
        <input type="text" name="txtTill" class="tcal" value="">
      </td>
    </form>
  </tr>
  </tbody>
</table>

<%if (request.getParameter("btnCalc") != null){%>
<h3 align="center">The result of your work</h3>
<table border="1" align="center">
  <thead>
  <tr>
    <th>Last name</th>
    <th>First name</th>
    <th>Second name</th>
    <th>Product</th>
    <th>Rate</th>
    <th>Quantity</th>
    <th>Date</th>
  </tr>
  </thead>
  <tbody>
  <%float fl = 0;%>
  <%for (CombineQuerry combineQuerry : (List<CombineQuerry>) request.getAttribute("combineQuerryList")) {%>
  <tr>
    <td>
      <input type="text" name="txtLastName" value="<%=combineQuerry.getLastName()%>" />
    </td>
    <td>
      <input type="text" name="txtFirstName" value="<%=combineQuerry.getFirstName()%>" />
    </td>
    <td>
      <input type="text" name="txtSecondName" value="<%=combineQuerry.getSecondName()%>" />
    </td>
    <td>
      <input type="text" name="txtProduct" value="<%=combineQuerry.getDescription()%>"/>
    </td>
    <td>
      <input type="text" name="txtRate" value="<%=String.valueOf(combineQuerry.getRateResult())%>" />
    </td>
    <td>
      <input type="text" name="txtQuantity" value="<%=String.valueOf(combineQuerry.getQuantityResult())%>" />
    </td>
    <td>
      <input type="text" name="txtDate" value="<%=combineQuerry.getDateResult()%>" />
    </td>
  </tr>
  <%fl = (combineQuerry.getRateResult()*combineQuerry.getQuantityResult()) + fl;%>
  <%}%>
  </tbody>
</table>
<h3 align="center">During this period, your salary was $
  <input type="text" name="txtSalaryResult" value="<%=fl/7.6923%>"> <!--13% salary-->
</h3>
<%}%>
</body>
</html>