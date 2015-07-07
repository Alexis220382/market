package servlets;

import javax.servlet.annotation.WebServlet;
import MyException.MyException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ProductDAO;
import dao.ResultingDAO;
import dao.SellerDAO;
import dto.Product;
import dto.Resulting;
import dto.Seller;


@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws MyException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        ProductDAO productDAO = new ProductDAO();
        ResultingDAO resultDAO = new ResultingDAO();
        SellerDAO sellerDAO = new SellerDAO();
        Product product;
        Resulting result;
        try {

            List<Integer> idList = new ArrayList<Integer>();
            List<String> descriptionList = new ArrayList<String>();
            List<Float> rateList = new ArrayList<Float>();
            List<Integer> numberProductList = new ArrayList<Integer>();
            for (Product prod : productDAO.getProduct()) {
                idList.add(prod.getId());
                descriptionList.add(prod.getDescription());
                rateList.add(prod.getRate());
                numberProductList.add(prod.getNumberProduct());
            }

           List<String> login = new ArrayList<String>();
            for (Seller sel : sellerDAO.getSeller()) {
                login.add(sel.getLastName());
            }

            if (request.getParameter("combobox") != null) {
                request.setAttribute("showProduct", productDAO.getProductById(
                        Integer.parseInt(request.getParameter("combobox"))));
            }
            if (request.getParameter("btnAdd") != null) {
                if (request.getParameter("txtNumberProduct").length() == 0
                        || request.getParameter("txtNumberProduct").length() > 9
                        || numberProductList.contains(Integer.parseInt(request.getParameter("txtNumberProduct")))
                        || request.getParameter("txtNumberProduct") == null
                        || descriptionList.contains(request.getParameter("txtDescription"))
                        || request.getParameter("txtDescription") == null
                        || request.getParameter("txtRate") == null
                        || request.getParameter("txtQuantity").length() > 9
                        || request.getParameter("txtQuantity") == null) {
                    request.setAttribute("notAdd", "message");
                } else {
                    product = new Product(
                            Integer.parseInt(request.getParameter("txtNumberProduct")),
                            request.getParameter("txtDescription"),
                            Float.parseFloat(request.getParameter("txtRate")),
                            Integer.parseInt(request.getParameter("txtQuantity")));
                    productDAO.addProduct(product);
                }
            }
            if (request.getParameter("btnUpdate") != null) {
                if (request.getParameter("txtId") != null
                        && request.getParameter("txtId").length() != 0
                        && idList.contains(Integer.parseInt(request.getParameter("txtId")))
                        && request.getParameter("txtNumberProduct").length() <= 9
                        && numberProductList.contains(Integer.parseInt(request.getParameter("txtNumberProduct")))
                        && request.getParameter("txtDescription") != null
                        && descriptionList.contains(request.getParameter("txtDescription"))
                        && request.getParameter("txtRate") != null
                        && request.getParameter("txtQuantity").length() <= 9
                        && request.getParameter("txtQuantity") != null) {
                    product = new Product(
                            Integer.parseInt(request.getParameter("txtId")),
                            Integer.parseInt(request.getParameter("txtNumberProduct")),
                            request.getParameter("txtDescription"),
                            Float.parseFloat(request.getParameter("txtRate")),
                            Integer.parseInt(request.getParameter("txtQuantity")));
                    productDAO.setProduct(product);
                } else {
                    request.setAttribute("notUpdate", "message");
                }
            }
            if (request.getParameter("btnDelete") != null) {
                if (request.getParameter("txtId") != null
                        && request.getParameter("txtId").length() != 0
                        && idList.contains(Integer.parseInt(request.getParameter("txtId")))) {
                    productDAO.removeProduct(Integer.parseInt(request.getParameter("txtId")));
                } else {
                    request.setAttribute("notDelete", "message");
                }
            }
            if (request.getParameter("btnSaled") != null) {
                if (request.getParameter("txtId") != null
                        && request.getParameter("txtId").length() != 0
                        && idList.contains(Integer.parseInt(request.getParameter("txtId")))
                        && request.getParameter("txtNumberProduct").length() <= 9
                        && numberProductList.contains(Integer.parseInt(request.getParameter("txtNumberProduct")))
                        && request.getParameter("txtDescription") != null
                        && descriptionList.contains(request.getParameter("txtDescription"))
                        && request.getParameter("txtRate") != null
                        && rateList.contains(Float.parseFloat(request.getParameter("txtRate")))
                        && request.getParameter("txtQuantity").length() <= 9
                        && request.getParameter("txtQuantity") != null) {
                    List<Product> lprd = productDAO.getProductById(Integer.parseInt(request.getParameter("txtId")));
                    List<Seller> sellerListLogin = sellerDAO.getSellerByLogin((String) request.getSession().getAttribute("selLog"));
                    if (Integer.parseInt(request.getParameter("txtQuantity")) <= lprd.get(0).getQuantity()) {
                        result = new Resulting(
                                sellerListLogin.get(0).getIdSeller(),
                                Integer.parseInt(request.getParameter("txtId")),
                                Float.parseFloat(request.getParameter("txtRate")),
                                Integer.parseInt(request.getParameter("txtQuantity")));
                        resultDAO.addResulting(result);
                        product = new Product(
                                lprd.get(0).getId(),
                                lprd.get(0).getNumberProduct(),
                                lprd.get(0).getDescription(),
                                lprd.get(0).getRate(),
                                lprd.get(0).getQuantity() - Integer.parseInt(request.getParameter("txtQuantity")));
                        productDAO.setProduct(product);
                    }else{
                        request.setAttribute("notSaled1", "message");
                    }
                } else {
                    request.setAttribute("notSaled", "message");
                }
            }
            request.setAttribute("productList", productDAO.getProduct());
            request.getRequestDispatcher("products.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            out.close();
            productDAO.conCloseProduct();
            sellerDAO.conCloseSeller();
            resultDAO.conCloseResulting();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MyException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MyException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
