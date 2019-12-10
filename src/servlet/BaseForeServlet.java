package servlet;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class BaseForeServlet extends HttpServlet{

    protected CategoryDAO categoryDAO = new CategoryDAO();
    protected OrderDAO orderDAO = new OrderDAO();
    protected OrderItemDAO orderItemDAO = new OrderItemDAO();
    protected ItemDAO itemDAO = new ItemDAO();
    protected ItemImageDAO itemImageDAO = new ItemImageDAO();
    protected UserDAO userDAO = new UserDAO();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {

            String method = (String) request.getAttribute("method");
            Method m = this.getClass().getMethod(method, javax.servlet.http.HttpServletRequest.class,
                    javax.servlet.http.HttpServletResponse.class);
            String redirect = m.invoke(this,request, response).toString();

            if(redirect.startsWith("@"))
                // 客户端跳转
            {
                response.sendRedirect(redirect.substring(1));
            } else if(redirect.startsWith("%"))
                // 输出字符串
            {
                response.getWriter().print(redirect.substring(1));
            } else
                // 服务器端跳转
            {
                request.getRequestDispatcher(redirect).forward(request, response);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    // 使用FileUpload接受和保存上传文件
    public InputStream parseUpload(HttpServletRequest request, Map<String, String> params) {
        // 初始化输入流
        InputStream is = null;
        try {
            // 创建磁盘工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 创建处理工具
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 设置上传文件的大小限制为10M
            upload.setFileSizeMax(1024 * 10240);
            // 接受全部内容
            List<FileItem> items = upload.parseRequest(request);
            // 全部内容转化为Iterator实例
            Iterator<FileItem> iter = items.iterator();

            while (iter.hasNext()) {
                // 依次取出
                FileItem item = iter.next();
                // 如果不是普通文本数据，是上传文件
                if (!item.isFormField()) {
                    // 取得输入流
                    is = item.getInputStream();
                } else {
                    // 取得表单内容
                    // 取得控件名称
                    String paramName = item.getFieldName();
                    // 取得表单内容
                    String paramValue = item.getString();
                    // 二进制转String
                    paramValue = new String(paramValue.getBytes("ISO-8859-1"), "UTF-8");
                    // 输出Map
                    params.put(paramName, paramValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }
}
