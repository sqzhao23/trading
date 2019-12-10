package servlet;

import bean.Category;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "CategoryServlet")
public class CategoryServlet extends BaseBackServlet {

    @Override
    public String add(HttpServletRequest request, HttpServletResponse response) {


        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Category c = new Category();
        c.setName(name);
        c.setDescription(description);
        categoryDAO.add(c);

        return "@admin_category_list";
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryDAO.delete(id);
        return "@admin_category_list";
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Category c = categoryDAO.get(id);
        request.setAttribute("c", c);
        return "admin/editCategory.jsp";
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response) {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Category c = new Category();
        c.setId(id);
        c.setName(name);
        c.setDescription(description);
        categoryDAO.update(c);

        return "@admin_category_list";
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response) {
        List<Category> cs = categoryDAO.list();

        request.setAttribute("cs", cs);
        return "admin/listCategory.jsp";
    }
}
