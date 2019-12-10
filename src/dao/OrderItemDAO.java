package dao;

import bean.Item;
import bean.Order;
import bean.OrderItem;
import bean.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from orderItem";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(OrderItem bean) {

        String sql = "insert into orderItem values(null,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, bean.getItem().getId());

            //订单项在创建的时候，是没有订单信息的
            if(null==bean.getOrder()) {
                ps.setInt(2, -1);
            } else {
                ps.setInt(2, bean.getOrder().getId());
            }

            ps.setInt(3, bean.getUser().getId());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(OrderItem bean) {

        String sql = "update orderItem set iid= ?, oid=?, uid=?  where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, bean.getItem().getId());
            if(null==bean.getOrder()) {
                ps.setInt(2, -1);
            } else {
                ps.setInt(2, bean.getOrder().getId());
            }
            ps.setInt(3, bean.getUser().getId());
            ps.setInt(4, bean.getId());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from orderItem where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public OrderItem get(int id) {
        OrderItem bean = new OrderItem();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from orderItem where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                int iid = rs.getInt("iid");
                int oid = rs.getInt("oid");
                int uid = rs.getInt("uid");
                Item item = new ItemDAO().get(iid);
                User user = new UserDAO().get(uid);
                bean.setItem(item);
                bean.setUser(user);

                if(-1!=oid){
                    Order order= new OrderDAO().get(oid);
                    bean.setOrder(order);
                }
                bean.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }


    public List<OrderItem> listByUser(int uid) {
        List<OrderItem> beans = new ArrayList<OrderItem>();

        String sql = "select * from orderItem where uid = ? and oid=-1 order by id";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, uid);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem bean = new OrderItem();
                int id = rs.getInt(1);

                int iid = rs.getInt("iid");
                int oid = rs.getInt("oid");

                Item item = new ItemDAO().get(iid);
                bean.setItem(item);
                if(-1!=oid){
                    Order order= new OrderDAO().get(oid);
                    bean.setOrder(order);
                }

                User user = new UserDAO().get(uid);

                bean.setUser(user);
                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }


    public List<OrderItem> listByOrder(int oid) {
        List<OrderItem> beans = new ArrayList<OrderItem>();

        String sql = "select * from orderItem where oid = ? order by id desc ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, oid);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem bean = new OrderItem();
                int id = rs.getInt(1);

                int iid = rs.getInt("iid");
                int uid = rs.getInt("uid");

                Item item = new ItemDAO().get(iid);
                if(-1!=oid){
                    Order order= new OrderDAO().get(oid);
                    bean.setOrder(order);
                }

                User user = new UserDAO().get(uid);
                bean.setItem(item);

                bean.setUser(user);
                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

    public void fill(List<Order> os) {
        for (Order o : os) {
            List<OrderItem> ois=listByOrder(o.getId());
            float totalPrice = 0;
            int totalNumber = 0;
            for (OrderItem oi : ois) {
                totalPrice+=oi.getItem().getSalePrice();
                totalNumber++;
            }
            o.setTotalPrice(totalPrice);
            o.setOrderItems(ois);
            o.setTotalNumber(totalNumber);
        }

    }

    public void fill(Order o) {
        List<OrderItem> ois=listByOrder(o.getId());
        float totalPrice = 0;
        for (OrderItem oi : ois) {
            totalPrice+=oi.getItem().getSalePrice();
        }
        o.setTotalPrice(totalPrice);
        o.setOrderItems(ois);
    }


    public List<OrderItem> listByItem(int iid) {
        List<OrderItem> beans = new ArrayList<OrderItem>();

        String sql = "select * from orderItem where iid = ? order by id ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, iid);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem bean = new OrderItem();
                int id = rs.getInt(1);
                int oid = rs.getInt("oid");
                int uid = rs.getInt("uid");

                Item item = new ItemDAO().get(iid);
                if(-1!=oid){
                    Order order= new OrderDAO().get(oid);
                    bean.setOrder(order);
                }

                User user = new UserDAO().get(uid);
                bean.setItem(item);

                bean.setUser(user);
                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

}
