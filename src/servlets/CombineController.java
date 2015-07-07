package servlets;

import javax.servlet.annotation.WebServlet;
import MyException.MyException;
import dao.CombineQuerryDAO;
import dao.SellerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CombineController", urlPatterns = {"/CombineController"})
public class CombineController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws MyException, IOException {

        PrintWriter out = response.getWriter();
        CombineQuerryDAO combineQuerryDAO = new CombineQuerryDAO();
        SellerDAO sellerDAO = new SellerDAO();
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            if (request.getParameter("comboboxLastName") != null) {
                request.setAttribute("showSellerForCombine", sellerDAO.getSellerById(Integer.parseInt(request.getParameter("comboboxLastName"))));
            }
            request.setAttribute("combolastNameList", sellerDAO.getSeller());
            request.setAttribute("combineQuerryList", combineQuerryDAO.getCombineQuerryWhoFromTill(
                        request.getParameter("txtLastName"),
                        request.getParameter("txtFrom"),
                        request.getParameter("txtTill")));
            request.getRequestDispatcher("salary.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            out.close();
            combineQuerryDAO.conCloseCombineQuerry();
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
