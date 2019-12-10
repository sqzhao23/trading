package servlet;

import bean.Order;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "OrderServlet")
public class OrderServlet extends BaseBackServlet {

    @Override
    public String add(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response) {
        List<Order> os = orderDAO.list();
        orderItemDAO.fill(os);

        request.setAttribute("os", os);

        return "admin/listOrder.jsp";
    }
}
