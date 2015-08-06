<%@ page import="java.util.List"%>
<%@ page import="dto.Seller"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>sellers</title>
</head>
<body background=../../img/bricks-12.gif>
<h2 align="center"><font color="red">SELLERS</font><br><font color="green"><u>DATABASE</u></font></h2>
<%if (request.getAttribute("notDelete1") != null
        || request.getAttribute("notUpdate1") != null
        || request.getAttribute("notAdd1") != null) {%>
<table border="1" align="center" width="450">
  <tbody>
  <tr>
    <td>
      <form action="sellercontroller.form">
        <%if (request.getAttribute("notDelete1") != null) {%>
        <h2 align="center">
          Attention!<br>Error delete seller from database <%=request.getParameter("txtLastNameSeller")%><br>
        </h2>
        <h3 align="left">
          Please, follow these guidelines when removing from the base:<br>
          in first select seller, press button "OK" and press buttun "Delete".
        </h3>
        <%}%>
        <%if (request.getAttribute("notUpdate1") != null) {%>
        <h2 align="center">
          Attention!<br>Error update data for seller <%=request.getParameter("txtLastNameSeller")%><br>
        </h2>
        <h3 align="left">
          Please, follow these guidlines when update data for seller:<br>
        </h3>
        <ul type="circle">
          <li><h3>fields is not must be is empty;</h3></li>
          <li><h3>and must begin with a capital letter;</h3></li>
        </ul>
        <%}%>
        <%if (request.getAttribute("notAdd1") != null) {%>
        <h2 align="center">
          Attention!<br>Error add seller <%=request.getParameter("txtLastNameSeller")%> в базу<br>
        </h2>
        <h3 align="left">
          Please, follow these guidlines when add seller:<br>
        </h3>
        <ul type="circle">
          <li><h3>fields is not must be is empty;</h3></li>
          <li><h3>and must begin whith a  capital letter;</h3></li>
        </ul>
        <%}%>
      </form>
    </td>
  </tr>
  </tbody>
</table>
<%}%>
<h3 align="center"><a href="../../index.jsp">To entries form</a></h3>
<table border="1" align="center" width="450">
  <tbody>
  <tr>
    <td>
      <h2 align="left"><em>Select seller</em></h2>
    </td>
    <td>
      <form action="sellercontroller.form">
        <h2 align="left">
          <select name="combobox1">
            <%for (Seller seller : (List<Seller>) request.getAttribute("sellerList")) {%>
            <option value="<%=seller.getIdSeller()%>"><%=seller.getLastName()%></option>
            <%}%>
          </select>
          <input type="submit" value="OK">
        </h2>
      </form>
    </td>
  </tr>
  <%List<Seller> sellerList = (List<Seller>) request.getAttribute("showSeller");%>
  <form action="sellercontroller.form">
  <tr>
    <td>
      <h2 align="left">Last name</h2>
    </td>
    <td>
      <h2 align="left">
        <input type="hidden" name="txtIdSellers" value="<%if (
                                    request.getParameter("combobox1") != null) {%><%=String.valueOf(
                                    sellerList.get(0).getIdSeller())%><%}%>">
      </h2>
      <h2 align="left">
        <input type="text" name="txtLastNameSeller" value="<%if (
                                    request.getParameter("combobox1") != null) {%><%=sellerList.get(0).getLastName()%><%}%>"
                                    required pattern="[A-Z][a-z]+">
      </h2>
    </td>
  </tr>
  <tr>
    <td>
      <h2 align="left">First name</h2>
    </td>
    <td>
      <h2 align="left">
        <input type="text" name="txtFirstNameSeller" value="<%if (
                                    request.getParameter("combobox1") != null) {%><%=sellerList.get(0).getFirstName()%><%}%>" required pattern="[A-Z][a-z]+">
      </h2>
    </td>
  </tr>
  <tr>
    <td>
      <h2 align="left">Second name</h2>
    </td>
    <td>
      <h2 align="left">
        <input type="text" name="txtSecondNameSeller" value="<%if (
                                    request.getParameter("combobox1") != null) {%><%=sellerList.get(0).getSecondName()%><%}%>" required pattern="[A-Z][a-z]+">
      </h2>
    </td>
  </tr>
  <tr>
    <td>
      <h2 align="left">
        <input type="submit" name="btnAdd1" value="Add">
      </h2>
    </td>
    <td>
      <h2 align="left">
        <input type="submit" name="btnUpdate1" value="Update">
      </h2>
    </td>
  </tr>
  <tr>
    <td>
      <h2 align="left">
        <input type="submit" name="btnDelete1" value="Delete">
      </h2>
      </form>
    </td>
    <td>
      <form action="sellercontroller.form">
        <h2 align="left">
          <input type="submit" name="btnClean1" value="Clean">
        </h2>
      </form>
    </td>
  </tr>
  </tbody>
</table>
</body>
</html>
