<%--
  Created by IntelliJ IDEA.
  User: B50-30
  Date: 01.07.2015
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="java.util.List"%>
<%@ page import="dto.Product"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>food</title>
</head>
<body background=img/bricks-12.gif>
<h2 align="center"><font color="red">PRODUCTS</font><br><font color="green"><u>DATABASE</u></font></h2>
<%if (request.getAttribute("notDelete") != null
        || request.getAttribute("notUpdate") != null
        || request.getAttribute("notAdd") != null
        || request.getAttribute("notSaled") != null
        || request.getAttribute("notSaled1") != null) {%>
<table border="1" align="center" width="450">
  <tbody>
  <tr>
    <td>
      <form action="ProductController">
        <%if (request.getAttribute("notDelete") != null) {%>
        <h2 align="center">
          Attention!<br>Error delete product <%=request.getParameter("txtDescription")%><br>
        </h2>
        <h3 align="left">
          Please, follow these guidelines when removing from the base:<br>
          select product, press button "OK" and press button "Delete".
        </h3>
        <%}%>
        <%if (request.getAttribute("notUpdate") != null) {%>
        <h2 align="center">
          Attention!<br>Error updates data of product <%=request.getParameter("txtDescription")%><br>
        </h2>
        <h3 align="left">
          Please, follow these guidelines when update product's data:<br>
        </h3>
        <ul type="circle">
          <li><h3>field "Product number" must not be empty and should not exceed 9 characters;</h3></li>
          <li><h3>the product number and its name is not updated. To update the number of the product, remove the product and create a new one with a different number;</h3></li>
          <li><h3>field "Rate" and "Quantity" must not be empty, and in the "Quantity" should not be more than 9 characters;</h3></li>
        </ul>
        <%}%>
        <%if (request.getAttribute("notAdd") != null) {%>
        <h2 align="center">
          Attention!<br>Error adding product <%=request.getParameter("txtDescription")%><br>
        </h2>
        <h3 align="left">
          Please, follow these guidelines when adding product:<br>
        </h3>
        <ul type="circle">
          <li><h3>all fields is not must be empty;</h3></li>
          <li><h3>value in the "Product number" and "Quantity" should not exceed 9 characters;</h3></li>
          <li><h3>product name entered in Latin letters and begins with a capital letter;</h3></li>
          <li><h3>"Rate" field has the following format (example: 54.5);</h3></li>
        </ul>
        <%}%>
        <%if (request.getAttribute("notSaled") != null) {%>
        <h2 align="center">
          Attention!<br>Error salling product <%=request.getParameter("txtDescription")%><br>
        </h2>
        <h3 align="left">
          Please, follow these guidelines when adding product:<br>
        </h3>
        <ul type="circle">
          <li><h3>value in the "Quantity" to change the data on the amount of goods sold;</h3></li>
          <li><h3>press the "Saled" button;</h3></li>
        </ul>
        <%}%>
        <%if (request.getAttribute("notSaled1") != null) {%>
        <h2 align="center">
          !Check the availability product <%=request.getParameter("txtDescription")%> in stock!
        </h2>
        <%}%>
      </form>
    </td>
  </tr>
  </tbody>
</table>
<%}%>
<h3 align="center"><a href="SellerController">To seller's database</a><a>             </a><a href="CombineController">To calculation of salaries</a></h3>
<table border="1" align="center" width="450">
  <tbody>
  <tr>
    <td>
      <h2 align="left"><em>Select product</em></h2>
    </td>
    <td>
      <form action="ProductController">
        <h2 align="left">
          <select name="combobox">
            <%for (Product next:(List<Product>) request.getAttribute("productList")){%>
            <option value="<%=next.getId()%>"><%=next.getDescription()%></option>
            <%}%>
          </select>
          <input type="submit" value="OK">
        </h2>
      </form>
    </td>
  </tr>
  <%List<Product> productList = (List<Product>) request.getAttribute("showProduct");%>
  <form action="ProductController">
  <tr>
    <td>
      <h2 align="left">Product number</h2>
    </td>
    <td>
      <h2 align="left">
        <input type="hidden" name="txtId" value="<%if (request.getParameter("combobox") != null) {%><%=String.valueOf(
                                    productList.get(0).getId())%><%}%>">
      </h2>
      <h2 align="left">
        <input type="text" name="txtNumberProduct" value="<%if (request.getParameter("combobox") != null) {%><%=String.valueOf(
                                       productList.get(0).getNumberProduct())%><%}%>" required pattern="[0-9]+">
      </h2>
    </td>
  </tr>
  <tr>
    <td>
      <h2 align="left">Product name</h2>
    </td>
    <td>
      <h2 align="left">
        <input type="text" name="txtDescription" value="<%if (request.getParameter("combobox") != null) {%><%=String.valueOf(
                                       productList.get(0).getDescription())%><%}%>" required pattern="[A-Z][a-z]+">
      </h2>
    </td>
  </tr>
  <tr>
    <td>
      <h2 align="left">Rate</h2>
    </td>
    <td>
      <h2 align="left">
        <input type="text" name="txtRate" value="<%if (request.getParameter("combobox") != null) {%><%=String.valueOf(
                                       productList.get(0).getRate())%><%}%>" required pattern="[0-9]+.[0-9]+">
      </h2>
    </td>
  </tr>
  <tr>
    <td>
      <h2 align="left">Quantity</h2>
    </td>
    <td>
      <h2 align="left">
        <input type="text" name="txtQuantity" value="<%if (request.getParameter("combobox") != null) {%><%=String.valueOf(
                                       productList.get(0).getQuantity())%><%}%>" required pattern="[0-9]+">
      </h2>
    </td>
  </tr>
  <tr>
    <td>
      <h2 align="left">
        <input type="submit" name="btnAdd" value="Add">
      </h2>
    </td>
    <td>
      <h2 align="left">
        <input type="submit" name="btnUpdate" value="Update">
      </h2>
    </td>
  </tr>
  <tr>
    <td>
      <h2 align="left">
        <input type="submit" name="btnDelete" value="Delete">
      </h2>
    </td>
    <td>
      <h2 align="left">
        <input type="submit" name="btnSaled" value="Saled">
      </h2>
      </form>
    </td>
  </tr>
  <tr>
    <td colspan="2">
      <form action="ProductController">
        <h2 align="center">
          <input type="submit" name="btnClean" value="Clean">
        </h2>
      </form>
    </td>
  </tr>
  </tbody>
</table>
</body>
</html>
