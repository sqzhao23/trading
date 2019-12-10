package servlet;

import bean.Category;
import bean.Item;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "ItemServlet")
public class ItemServlet extends BaseBackServlet {
    @Override
    public String add(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Item i = itemDAO.get(id);
        itemDAO.delete(id);
        return "@admin_item_list?cid="+i.getCategory().getId();
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
        int cid = Integer.parseInt(request.getParameter("cid"));
        Category c = categoryDAO.get(cid);

        List<Item> is = itemDAO.listByCategory(cid);

        request.setAttribute("is", is);
        request.setAttribute("c", c);

        return "admin/listItem.jsp";
    }

}
