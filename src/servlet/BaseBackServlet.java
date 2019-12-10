package servlet;

import dao.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BaseServlet")
public abstract class BaseBackServlet extends HttpServlet {


    public abstract String add(HttpServletRequest request, HttpServletResponse response);
    public abstract String delete(HttpServletRequest request, HttpServletResponse response);
    public abstract String edit(HttpServletRequest request, HttpServletResponse response);
    public abstract String update(HttpServletRequest request, HttpServletResponse response);

    public abstract String list(HttpServletRequest request, HttpServletResponse response);

    protected CategoryDAO categoryDAO = new CategoryDAO();
    protected OrderDAO orderDAO = new OrderDAO();
    protected OrderItemDAO orderItemDAO = new OrderItemDAO();
    protected ItemDAO itemDAO = new ItemDAO();
    protected UserDAO userDAO = new UserDAO();



    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException{


        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        try {

            /*借助反射，调用对应的方法*/
            String method = (String) request.getAttribute("method");
            Method m = this.getClass().getMethod(method, javax.servlet.http.HttpServletRequest.class,
                    javax.servlet.http.HttpServletResponse.class);
            String redirect = m.invoke(this,request, response).toString();

            /*根据方法的返回值，进行相应的客户端跳转，服务端跳转，或者仅仅是输出字符串*/

            if(redirect.startsWith("@")) {
                response.sendRedirect(redirect.substring(1));
            } else if(redirect.startsWith("%")) {
                response.getWriter().print(redirect.substring(1));
            } else {
                request.getRequestDispatcher(redirect).forward(request, response);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
