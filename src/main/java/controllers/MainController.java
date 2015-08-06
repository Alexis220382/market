package controllers;

import dao.CombineQuerryDAO;
import dao.ProductDAO;
import dao.ResultingDAO;
import dao.SellerDAO;
import dto.Product;
import dto.Resulting;
import dto.Seller;
import myException.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @RequestMapping(value = "/sellercontroller", method = RequestMethod.GET)
    public ModelAndView sellerControl(HttpServletRequest request) throws MyException {

        ModelAndView modelAndView = new ModelAndView();
        SellerDAO sellerDAO = new SellerDAO();
        ResultingDAO resultingDAO = new ResultingDAO();
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
            modelAndView.setViewName("WEB-INF/view/sellers");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            sellerDAO.conCloseSeller();
            resultingDAO.conCloseResulting();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/productcontroller", method = RequestMethod.GET)
     public ModelAndView productControl(HttpServletRequest request) throws MyException {

        ModelAndView modelAndView = new ModelAndView();
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
            modelAndView.setViewName("WEB-INF/view/products");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            productDAO.conCloseProduct();
            sellerDAO.conCloseSeller();
            resultDAO.conCloseResulting();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/logincontroller", method = RequestMethod.GET)
    public ModelAndView loginControl(HttpServletRequest request, HttpServletResponse response) throws MyException {

        ModelAndView modelAndView = new ModelAndView();
        SellerDAO sellerDAO = new SellerDAO();
        try {

            List<String> lastNameList = new ArrayList<String>();
            List<String> firstNameList = new ArrayList<String>();
            for (Seller seller : sellerDAO.getSeller()) {
                lastNameList.add(seller.getLastName());
                firstNameList.add(seller.getFirstName());
            }

            if (lastNameList.contains(request.getParameter("login"))
                    && firstNameList.contains(request.getParameter("password"))) {
                request.getRequestDispatcher("/productcontroller.form").forward(request, response);
                request.getSession().setAttribute("selLog", request.getParameter("login"));
            } else {
//                request.getRequestDispatcher("index.jsp");
                modelAndView.setViewName("index");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            sellerDAO.conCloseSeller();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/combinecontroller", method = RequestMethod.GET)
    public ModelAndView combineControl(HttpServletRequest request) throws MyException {

        ModelAndView modelAndView = new ModelAndView();

        CombineQuerryDAO combineQuerryDAO = new CombineQuerryDAO();
        SellerDAO sellerDAO = new SellerDAO();
        try {
            if (request.getParameter("comboboxLastName") != null) {
                request.setAttribute("showSellerForCombine", sellerDAO.getSellerById(Integer.parseInt(request.getParameter("comboboxLastName"))));
            }
            request.setAttribute("combolastNameList", sellerDAO.getSeller());
            request.setAttribute("combineQuerryList", combineQuerryDAO.getCombineQuerryWhoFromTill(
                    request.getParameter("txtLastName"),
                    request.getParameter("txtFrom"),
                    request.getParameter("txtTill")));
            modelAndView.setViewName("WEB-INF/view/salary");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            combineQuerryDAO.conCloseCombineQuerry();
            sellerDAO.conCloseSeller();
        }
        return modelAndView;
    }

}
