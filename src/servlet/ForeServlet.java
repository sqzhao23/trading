package servlet;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.*;
import dao.*;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.util.HtmlUtils;
import util.ImageUtil;

public class ForeServlet extends BaseForeServlet {

    public String home(HttpServletRequest request, HttpServletResponse response) {
        List<Category> cs = new CategoryDAO().list();
        List<Item> is = new ItemDAO().list();
        request.getSession().setAttribute("cs", cs);
        request.setAttribute("is", is);

        return "home.jsp";
    }


    public String register(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        // 转义，防止恶意注册
        name = HtmlUtils.htmlEscape(name);
        boolean exist = userDAO.isExist(name);

        if (exist) {
            request.setAttribute("msg", "fail");
            return "register.jsp";
        } else {
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            UserDAO.add(user);

            return "@login.jsp";
        }
    }


    public String login(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        // 转义，防止恶意注册
        name = HtmlUtils.htmlEscape(name);
        User user = userDAO.get(name, password);

        if (user == null) {
            request.setAttribute("msg", "fail_1");
            return "login.jsp";
        } else {
            // 把user信息存在session中
            request.getSession().setAttribute("u", user);

            return "forehome";
        }
    }


    public String logout(HttpServletRequest request, HttpServletResponse response){
        // 删除session中的user信息
        request.getSession().removeAttribute("u");

        return "@forehome";
    }


    public String beforePublish(HttpServletRequest request, HttpServletResponse response) {
        List<Category> cs = new CategoryDAO().list();
        request.setAttribute("cs", cs);

        return "publish.jsp";
    }

    public String contact(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
        User u = new UserDAO().get(name);
        u.setContact(contact);
        UserDAO.update(u);
        request.getSession().removeAttribute("u");
        request.getSession().setAttribute("u", u);
        return "forehome";
    }


    public String publish(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String uName = request.getParameter("userName");
        User u = userDAO.get(uName);
        String categoryName = request.getParameter("categoryName");
        Category c = categoryDAO.get(categoryName);
        float originalPrice = Float.parseFloat(request.getParameter("originalPrice"));
        float salePrice = Float.parseFloat(request.getParameter("salePrice"));
        String description = request.getParameter("description");

        Item i = new Item();
        i.setName(name);
        i.setOriginalPrice(originalPrice);
        i.setSalePrice(salePrice);
        i.setDescription(description);
        i.setStatus(ItemDAO.SALE);

        i.setUser(u);
        i.setCategory(c);

        itemDAO.add(i);

        request.setAttribute("i", i);
        request.setAttribute("c", c);

        return "publishNext.jsp";
    }


    public String publishImage(HttpServletRequest request, HttpServletResponse response) {
        //上传文件的输入流
        InputStream is = null;
        //提交上传文件时的其他参数
        Map<String, String> params = new HashMap<>(100);
        //解析上传
        is = parseUpload(request, params);
        //根据上传的参数生成productImage对象
        String type = params.get("type");
        int iid = Integer.parseInt(params.get("iid"));
        int uid = Integer.parseInt(request.getParameter("uid"));

        Item i = itemDAO.get(iid);

        ItemImage ii = new ItemImage();
        ii.setType(type);
        ii.setItem(i);
        itemImageDAO.add(ii);
        //生成文件
        String fileName = i.getId() + ".jpg";
        String imageFolder;
        if (ItemImageDAO.TYPE_SINGLE.equals(ii.getType())) {
            imageFolder = request.getSession().getServletContext().getRealPath("img/item/single");
        } else {
            imageFolder = request.getSession().getServletContext().getRealPath("img/item/detail");
        }
        File f = new File(imageFolder, fileName);
        f.getParentFile().mkdirs();

        // 复制文件
        try {
            if (null != is && 0 != is.available()) {
                try (FileOutputStream fos = new FileOutputStream(f)) {
                    byte[] b = new byte[1024 * 1024];
                    int length = 0;
                    while (-1 != (length = is.read(b))) {
                        fos.write(b, 0, length);
                    }
                    fos.flush();
                    //通过如下代码，把文件保存为jpg格式
                    BufferedImage img = ImageUtil.change2jpg(f);
                    ImageIO.write(img, "jpg", f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "@forepersonal?uid=" + uid;
    }


    public String personal(HttpServletRequest request, HttpServletResponse response){
        int uid = Integer.parseInt(request.getParameter("uid"));
        List<Item> is = new ItemDAO().listByUser(uid);
        request.setAttribute("is", is);

        return "personal.jsp";
    }

    public String visitor(HttpServletRequest request, HttpServletResponse response){
        int user_mid = Integer.parseInt(request.getParameter("user_mid"));
        User user_m = new UserDAO().get(user_mid);
        List<Item> is = new ItemDAO().listByUser(user_mid);
        request.setAttribute("is", is);
        request.setAttribute("user_m", user_m);


        return "visitor.jsp";
    }


    public String itemDelete(HttpServletRequest request, HttpServletResponse response){
        User u =  (User)request.getSession().getAttribute("u");
        int uid = u.getId();
        int iid = Integer.parseInt(request.getParameter("iid"));
        itemImageDAO.deleteByIid(iid);
        // 删除图片
        String imageFolder_single= request.getSession().getServletContext().getRealPath("img/item/single");
        File f_single = new File(imageFolder_single,iid + ".jpg");
        boolean b = f_single.exists();
        if (b) {
            f_single.delete();
        }
        itemDAO.delete(iid);

        return "@forepersonal?uid=" + uid;
    }


    public String search(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");
        List<Item> is = new ItemDAO().search(keyword);

        request.setAttribute("keyword", keyword);
        request.setAttribute("is",is);

        return "searchResult.jsp";
    }

    public String searchByCategory(HttpServletRequest request, HttpServletResponse response) {
        int cid = Integer.parseInt(request.getParameter("cid"));
        String cName = request.getParameter("cName");
        List<Item> is = new ItemDAO().listByCategory(cid);

        request.setAttribute("keyword", cName);
        request.setAttribute("is",is);

        return "searchResult.jsp";
    }


    public String item(HttpServletRequest request, HttpServletResponse response) {
        int iid = Integer.parseInt(request.getParameter("iid"));
        Item i = new ItemDAO().get(iid);
        User user_m = i.getUser();

        request.setAttribute("i", i);
        request.setAttribute("user_m", user_m);
        return "item.jsp";
    }


    public String buyone(HttpServletRequest request, HttpServletResponse response) {
        int iid = Integer.parseInt(request.getParameter("iid"));
        Item i = itemDAO.get(iid);
        int oiid = 0;
        User user =(User) request.getSession().getAttribute("u");

        OrderItem oi = new OrderItem();
        oi.setUser(user);
        oi.setItem(i);
        orderItemDAO.add(oi);
        oiid = oi.getId();

        return "@forebuy?oiid="+oiid;
    }


    public String buy(HttpServletRequest request, HttpServletResponse response){
        String[] oiids=request.getParameterValues("oiid");
        List<OrderItem> ois = new ArrayList<>();
        float totalPrice = 0;

        for (String strid : oiids) {
            int oiid = Integer.parseInt(strid);
            OrderItem oi= orderItemDAO.get(oiid);
            totalPrice +=oi.getItem().getSalePrice();
            ois.add(oi);
        }

        request.getSession().setAttribute("ois", ois);
        request.setAttribute("totalPrice", totalPrice);
        return "buy.jsp";
    }


    public String cartAdd(HttpServletRequest request, HttpServletResponse response){
        int iid = Integer.parseInt(request.getParameter("iid"));
        Item i = new ItemDAO().get(iid);
        User u = (User) request.getSession().getAttribute("u");

        boolean found = false;
        String addMsg = "fail";

        List<OrderItem> ois = orderItemDAO.listByUser(u.getId());
        for (OrderItem oi : ois) {
            if(oi.getItem().getId()==i.getId()){
                found = true;
                break;
            }
        }

        if(!found){
            OrderItem oi = new OrderItem();
            oi.setUser(u);
            oi.setItem(i);
            orderItemDAO.add(oi);
            addMsg = "success";
        }
        request.setAttribute("msg",addMsg);
        return "addResult.jsp";
    }


    public String cart(HttpServletRequest request, HttpServletResponse response) {
        User u = (User) request.getSession().getAttribute("u");
        List<OrderItem> ois = new OrderItemDAO().listByUser(u.getId());

        request.setAttribute("ois", ois);
        request.setAttribute("u", u);
        return "cart.jsp";
    }

    public String cartDelete(HttpServletRequest request, HttpServletResponse response){
        int oiid = Integer.parseInt(request.getParameter("oiid"));
        orderItemDAO.delete(oiid);

        return "forecart";
    }

    public String createOrder(HttpServletRequest request, HttpServletResponse response){
        User user =(User) request.getSession().getAttribute("u");

        List<OrderItem> ois= (List<OrderItem>) request.getSession().getAttribute("ois");
        if(ois.isEmpty()) {
            return "@forehome";
        }
        String address = request.getParameter("address");
        String receiver = request.getParameter("receiver");
        String mobile = request.getParameter("mobile");

        Order order = new Order();
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) +RandomUtils.nextInt(10000);

        order.setOrderCode(orderCode);
        order.setAddress(address);
        order.setReceiver(receiver);
        order.setMobile(mobile);
        order.setCreateDate(new Date());
        order.setUser(user);
        order.setStatus(OrderDAO.WAITPAY);

        orderDAO.add(order);
        float totalPrice = 0;
        for (OrderItem oi: ois) {
            oi.setOrder(order);
            orderItemDAO.update(oi);
            totalPrice+=oi.getItem().getSalePrice();
        }
        return "@forepay?oid=" + order.getId() + "&totalPrice=" + totalPrice;
    }

    public String pay(HttpServletRequest request, HttpServletResponse response){
        return "pay.jsp";
    }

    public String payed(HttpServletRequest request, HttpServletResponse response){
        int oid = Integer.parseInt(request.getParameter("oid"));
        Order order = orderDAO.get(oid);
        order.setStatus(OrderDAO.WAITCONFIRM);
        new OrderDAO().update(order);

        List<OrderItem> ois = new OrderItemDAO().listByOrder(oid);
        for (OrderItem oi: ois) {
            Item item =oi.getItem();
            item.setStatus(ItemDAO.SOLD);
            new ItemDAO().update(item);
        }

        request.setAttribute("o", order);
        return "paid.jsp";
    }

    public String orderList(HttpServletRequest request, HttpServletResponse response) {
        int uid = Integer.parseInt(request.getParameter("uid"));
        List<Order> os =  new OrderDAO().listByUser(uid);
        request.setAttribute("os",os);

        return "orderList.jsp";
    }

    public String orderItem(HttpServletRequest request, HttpServletResponse response) {
        int oid = Integer.parseInt(request.getParameter("oid"));
        List<OrderItem> ois = new OrderItemDAO().listByOrder(oid);
        request.setAttribute("ois", ois);
        return "orderItem.jsp";
    }

}


