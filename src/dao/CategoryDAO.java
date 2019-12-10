package dao;

import bean.Category;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {


    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from category";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(Category bean) {

        String sql = "insert into category values(null,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, bean.getName());
            ps.setString(2,bean.getDescription());

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

    public void update(Category bean) {

        String sql = "update category set name= ?,description=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, bean.getName());
            ps.setString(2,bean.getDescription());
            ps.setInt(3, bean.getId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from category where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category get(int id) {
        Category bean = null;

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from category where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                bean = new Category();
                String name = rs.getString(2);
                bean.setName(name);
                String description = rs.getString(3);
                bean.setDescription(description);
                bean.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public Category get(String name) {
        Category bean = null;

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from category where name = " + name;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                bean = new Category();
                int id = rs.getInt(1);
                bean.setId(id);
                String description = rs.getString(3);
                bean.setDescription(description);
                bean.setName(name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }


    public List<Category> list() {
        List<Category> beans = new ArrayList<Category>();
        String sql = "select * from category order by id ";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category bean = new Category();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);

                bean.setId(id);
                bean.setName(name);
                bean.setDescription(description);
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

}
