package servlets;

import MyException.MyException;
import dao.SellerDAO;
import dto.Seller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws MyException, IOException {

        PrintWriter out = response.getWriter();
        SellerDAO sellerDAO = new SellerDAO();
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {

            List<String> lastNameList = new ArrayList<String>();
            List<String> firstNameList = new ArrayList<String>();
            for (Seller seller : sellerDAO.getSeller()) {
                lastNameList.add(seller.getLastName());
                firstNameList.add(seller.getFirstName());
            }

            if (lastNameList.contains(request.getParameter("login"))
                    && firstNameList.contains(request.getParameter("password"))) {
                request.getRequestDispatcher("ProductController").forward(request, response);
                request.getSession().setAttribute("selLog", request.getParameter("login"));
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            out.close();
            sellerDAO.conCloseSeller();
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
