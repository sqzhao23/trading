package dao;

import bean.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public int getTotal() {
        int total = 0;
        try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select count(*) from User";
            s.execute(sql);

            ResultSet rs = s.getGeneratedKeys();//获取自增主键
            while (rs.next()) {
                total = rs.getInt(1);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    public static void add(User bean) {
        String sql = "insert into user value(null,?,?,?)";
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());
            ps.setString(3, bean.getContact());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from user where id = " + id;
            s.execute(sql );

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(User bean) {
        String sql = "update user set name = ?, password = ?, contact = ? where id = ? ";
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());
            ps.setString(3,bean.getContact());
            ps.setInt(4, bean.getId());

            ps.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User get(int id) {
        User bean = null;
        try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from user where id = " + id;
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                bean = new User();
                String name = rs.getString("name");
                bean.setName(name);
                String password = rs.getString("password");
                bean.setPassword(password);
                String contact = rs.getString("contact");
                bean.setContact(contact);
                bean.setId(id);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public List<User> list() {
        List<User> beans = new ArrayList<User>();

        String sql = "select * from User order by id ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User bean = new User();
                int id = rs.getInt(1);

                String name = rs.getString("name");
                bean.setName(name);
                String password = rs.getString("password");
                bean.setPassword(password);
                String contact = rs.getString("contact");
                bean.setContact(contact);

                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

    public boolean isExist(String name) {
        User user = get(name);
        return user!=null;

    }

    public User get(String name) {
        User bean = null;

        String sql = "select * from User where name = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs =ps.executeQuery();

            if (rs.next()) {
                bean = new User();
                int id = rs.getInt("id");
                bean.setName(name);
                String password = rs.getString("password");
                bean.setPassword(password);
                String contact = rs.getString("contact");
                bean.setContact(contact);
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public User get(String name, String password) {
        User bean = null;

        String sql = "select * from User where name = ? and password=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs =ps.executeQuery();

            if (rs.next()) {
                bean = new User();
                int id = rs.getInt("id");
                bean.setName(name);
                bean.setPassword(password);
                String contact = rs.getString("contact");
                bean.setContact(contact);
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
