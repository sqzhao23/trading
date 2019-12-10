package dao;

import bean.Category;
import bean.Item;
import bean.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    public static final String SALE = "售卖中";
    public static final String SOLD = "已售出";

    public int getTotal(int uid) {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from item where uid = " + uid;

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    public void add(Item bean) {
        String sql = "insert into item values(null,?,?,?,?,?,?,?)";
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, bean.getName());
            ps.setFloat(2, bean.getOriginalPrice());
            ps.setFloat(3, bean.getSalePrice());
            ps.setString(4, bean.getDescription());
            ps.setString(5, bean.getStatus());
            ps.setInt(6, bean.getUser().getId());
            ps.setInt(7,bean.getCategory().getId());

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

    public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "delete from item where id = " + id;
            s.execute(sql );

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(Item bean) {
        String sql = "update item set name = ?, originalPrice = ?, salePrice = ?, " +
                "description = ?, status = ?, uid = ? , cid = ? where id=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, bean.getName()) ;
            ps.setFloat(2, bean.getOriginalPrice()) ;
            ps.setFloat(3, bean.getSalePrice()) ;
            ps.setString(4, bean.getDescription()) ;
            ps.setString(5, bean.getStatus()) ;
            ps.setInt(6, bean.getUser().getId());
            ps.setInt(7,bean.getCategory().getId());
            ps.setInt(8,bean.getId());

            ps.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Item get(int id) {
        Item bean = new Item();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from item where id = " + id;
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                String name = rs.getString("name");
                Float originalPrice = rs.getFloat("originalPrice");
                Float salePrice = rs.getFloat("salePrice");
                String description = rs.getString("description");
                String status = rs.getString("status");
                int uid = rs.getInt("uid");
                int cid = rs.getInt("cid");

                bean.setName(name);
                bean.setOriginalPrice(originalPrice);
                bean.setSalePrice(salePrice);
                bean.setDescription(description);
                bean.setStatus(status);
                User user = new UserDAO().get(uid);
                bean.setUser(user);
                Category category = new CategoryDAO().get(cid);
                bean.setCategory(category);

                bean.setId(id);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }


    public List<Item> listByCategory(int cid) {
        List<Item> beans = new ArrayList<Item>();
        Category category = new CategoryDAO().get(cid);
        String sql = "select * from item where cid = ? order by id";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, cid);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item bean = new Item();
                int id = rs.getInt(1);
                String name = rs.getString("name");
                float originalPrice = rs.getFloat("originalPrice");
                float salePrice = rs.getFloat("salePrice");
                String description = rs.getString("description");
                String status = rs.getString("status");
                int uid = rs.getInt("uid");
                User user = new UserDAO().get(uid);

                bean.setId(id);
                bean.setName(name);
                bean.setOriginalPrice(originalPrice);
                bean.setSalePrice(salePrice);
                bean.setDescription(description);
                bean.setStatus(status);
                bean.setUser(user);
                bean.setCategory(category);
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }


    public List<Item> listByUser(int uid) {
        List<Item> beans = new ArrayList<Item>();
        User user = new UserDAO().get(uid);
        String sql = "select * from item where uid = ? order by id";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, uid);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item bean = new Item();

                int id = rs.getInt(1);
                String name = rs.getString("name");
                float originalPrice = rs.getFloat("originalPrice");
                float salePrice = rs.getFloat("salePrice");
                String description = rs.getString("description");
                String status = rs.getString("status");
                int cid = rs.getInt("cid");
                Category category = new CategoryDAO().get(cid);

                bean.setId(id);
                bean.setName(name);
                bean.setOriginalPrice(originalPrice);
                bean.setSalePrice(salePrice);
                bean.setDescription(description);
                bean.setStatus(status);
                bean.setUser(user);
                bean.setCategory(category);
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }


    public List<Item> list() {
        List<Item> beans = new ArrayList<Item>();

        String sql = "select * from item order by rand() limit 50";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item bean = new Item();
                int id = rs.getInt(1);
                String name = rs.getString("name");
                float originalPrice = rs.getFloat("originalPrice");
                float salePrice = rs.getFloat("salePrice");
                String description = rs.getString("description");
                String status = rs.getString("status");
                int uid = rs.getInt("uid");
                User user = new UserDAO().get(uid);
                int cid = rs.getInt("cid");
                Category category = new CategoryDAO().get(cid);

                bean.setId(id);
                bean.setName(name);
                bean.setOriginalPrice(originalPrice);
                bean.setSalePrice(salePrice);
                bean.setDescription(description);
                bean.setStatus(status);
                bean.setUser(user);
                bean.setCategory(category);
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }



    public List<Item> search(String keyword) {
        List<Item> beans = new ArrayList<Item>();

        if(null==keyword||0==keyword.trim().length()) {
            return beans;
        }
        String sql = "select * from item where name like ? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, "%"+keyword.trim()+"%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item bean = new Item();
                int id = rs.getInt(1);
                int cid = rs.getInt("cid");
                int uid = rs.getInt("uid");
                String name = rs.getString("name");
                float originalPrice = rs.getFloat("originalPrice");
                float salePrice = rs.getFloat("salePrice");
                String description = rs.getString("description");
                String status = rs.getString("status");

                bean.setName(name);
                bean.setOriginalPrice(originalPrice);
                bean.setSalePrice(salePrice);
                bean.setDescription(description);
                bean.setStatus(status);
                User user = new UserDAO().get(uid);
                bean.setUser(user);
                Category category = new CategoryDAO().get(cid);
                bean.setCategory(category);
                bean.setId(id);

                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }
}
