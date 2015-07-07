package servlets;

import javax.servlet.annotation.WebServlet;
import MyException.MyException;
import dao.ResultingDAO;
import dao.SellerDAO;
import dto.Seller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SellerController", urlPatterns = {"/SellerController"})
public class SellerController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws MyException, IOException {

        PrintWriter out = response.getWriter();
        SellerDAO sellerDAO = new SellerDAO();
        ResultingDAO resultingDAO = new ResultingDAO();
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {

            List<Integer> idListSel = new ArrayList<Integer>();
            List<String> lastNameList = new ArrayList<String>();
            List<String> firstNameList = new ArrayList<String>();
            List<String> secondNameList = new ArrayList<String>();
            for (Seller seller : sellerDAO.getSeller()) {
                idListSel.add(seller.getIdSeller());
                lastNameList.add(seller.getLastName());
                firstNameList.add(seller.getFirstName());
                secondNameList.add(seller.getSecondName());
            }

                if (request.getParameter("combobox1") != null) {
                    request.setAttribute("showSeller", sellerDAO.getSellerById(
                            Integer.parseInt(request.getParameter("combobox1"))));
                }
                if (request.getParameter("btnAdd1") != null) {
                    if (lastNameList.contains(request.getParameter("txtLastNameSeller"))
                            || request.getParameter("txtLastNameSeller") == null
                            || firstNameList.contains(request.getParameter("txtFirstNameSeller"))
                            || request.getParameter("txtFirstNameSeller") == null
                            || secondNameList.contains(request.getParameter("txtSecondNameSeller"))
                            || request.getParameter("txtSecondNameSeller") == null) {
                        request.setAttribute("notAdd1", "message");
                    } else {
                        Seller seller = new Seller(
                                request.getParameter("txtLastNameSeller"),
                                request.getParameter("txtFirstNameSeller"),
                                request.getParameter("txtSecondNameSeller"));
                        sellerDAO.addSeller(seller);
                    }
                }
                if (request.getParameter("btnUpdate1") != null) {
                    if (request.getParameter("txtIdSellers") != null
                            && request.getParameter("txtIdSellers").length() != 0
                            && idListSel.contains(Integer.parseInt(request.getParameter("txtIdSellers")))
                            && request.getParameter("txtLastNameSeller") != null
                            && request.getParameter("txtFirstNameSeller") != null
                            && firstNameList.contains(request.getParameter("txtFirstNameSeller"))
                            && request.getParameter("txtSecondNameSeller") != null
                            && secondNameList.contains(request.getParameter("txtSecondNameSeller"))) {
                        Seller seller = new Seller(
                                Integer.parseInt(request.getParameter("txtIdSellers")),
                                request.getParameter("txtLastNameSeller"),
                                request.getParameter("txtFirstNameSeller"),
                                request.getParameter("txtSecondNameSeller"));
                        sellerDAO.setSeller(seller);
                    } else {
                        request.setAttribute("notUpdate1", "message");
                    }
                }
                if (request.getParameter("btnDelete1") != null) {
                    if (request.getParameter("txtIdSellers") != null
                            && request.getParameter("txtIdSellers").length() != 0
                            && idListSel.contains(Integer.parseInt(request.getParameter("txtIdSellers")))) {
                        resultingDAO.removeResultingByIdSeller(Integer.parseInt(request.getParameter("txtIdSellers")));
                        sellerDAO.removeSeller(Integer.parseInt(request.getParameter("txtIdSellers")));
                    } else {
                        request.setAttribute("notDelete1", "message");
                    }
                }
                request.setAttribute("sellerList", sellerDAO.getSeller());
                request.getRequestDispatcher("sellers.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            out.close();
            sellerDAO.conCloseSeller();
            resultingDAO.conCloseResulting();
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
