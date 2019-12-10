package dao;

import bean.Order;
import bean.User;
import util.DBUtil;
import util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO {

    public static final String WAITPAY = "waitpay";
    public static final String WAITCONFIRM = "waitConfirm";
    public static final String FINASH = "finish";


    public int getTotal() {
        int total = 0;
        try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) form order_";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }


    public void add(Order bean) {
        String sql = "insert into order_ values(null,?,?,?,?,?,?,?)";

        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, bean.getOrderCode());
            ps.setString(2, bean.getAddress());
            ps.setString(3, bean.getReceiver());
            ps.setString(4, bean.getMobile());
            ps.setTimestamp(5, DateUtil.d2t(bean.getCreateDate()));
            ps.setString(6, bean.getStatus());
            ps.setInt(7, bean.getUser().getId());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Order bean) {

        String sql = "update order_ set orderCode= ?, address=?, receiver=?, mobile=?,createDate = ?," +
                " status=? , uid=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, bean.getOrderCode());
            ps.setString(2, bean.getAddress());
            ps.setString(3, bean.getReceiver());
            ps.setString(4, bean.getMobile());
            ps.setTimestamp(5, DateUtil.d2t(bean.getCreateDate()));
            ps.setString(6, bean.getStatus());
            ps.setInt(7, bean.getUser().getId());
            ps.setInt(8, bean.getId());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from order_ where id = " + id;

            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Order get(int id) {
        Order bean = new Order();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from order_ where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                String orderCode =rs.getString("orderCode");
                String address = rs.getString("address");
                String receiver = rs.getString("receiver");
                String mobile = rs.getString("mobile");
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                String status = rs.getString("status");
                int uid =rs.getInt("uid");

                bean.setOrderCode(orderCode);
                bean.setAddress(address);
                bean.setReceiver(receiver);
                bean.setMobile(mobile);
                bean.setCreateDate(createDate);
                bean.setStatus(status);
                User user = new UserDAO().get(uid);
                bean.setUser(user);

                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }


    public List<Order> list() {
        List<Order> beans = new ArrayList<Order>();

        String sql = "select * from order_ order by id";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order bean = new Order();
                String orderCode =rs.getString("orderCode");
                String address = rs.getString("address");
                String receiver = rs.getString("receiver");
                String mobile = rs.getString("mobile");
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                String status = rs.getString("status");
                int uid = rs.getInt("uid");
                int id = rs.getInt("id");

                bean.setId(id);
                bean.setOrderCode(orderCode);
                bean.setAddress(address);
                bean.setReceiver(receiver);
                bean.setMobile(mobile);
                bean.setCreateDate(createDate);
                bean.setStatus(status);
                User user = new UserDAO().get(uid);
                bean.setUser(user);

                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

    public List<Order> listByUser(int uid) {
        List<Order> beans = new ArrayList<Order>();
        User user = new UserDAO().get(uid);
        String sql = "select * from order_ where uid = ? order by id";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1,uid);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order bean = new Order();
                int id = rs.getInt("id");
                String orderCode =rs.getString("orderCode");
                String address = rs.getString("address");
                String receiver = rs.getString("receiver");
                String mobile = rs.getString("mobile");
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                String status = rs.getString("status");

                bean.setId(id);
                bean.setOrderCode(orderCode);
                bean.setAddress(address);
                bean.setReceiver(receiver);
                bean.setMobile(mobile);
                bean.setCreateDate(createDate);
                bean.setStatus(status);
                bean.setUser(user);

                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

}
