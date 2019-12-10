package dao;

import bean.Item;
import bean.ItemImage;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemImageDAO {

    public static final String TYPE_SINGLE = "type_single";
    public static final String TYPE_DETAIL = "type_detail";

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from itemImage";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(ItemImage bean) {

        String sql = "insert into itemImage values(null,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, bean.getItem().getId());
            ps.setString(2, bean.getType());
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

    public void update(ItemImage bean) {

    }

    public void deleteById(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from itemImage where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByIid(int iid) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from itemImage where iid = " + iid;

            s.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ItemImage getById(int id) {
        ItemImage bean = new ItemImage();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from itemImage where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                int iid = rs.getInt("iid");
                String type = rs.getString("type");
                Item item = new ItemDAO().get(iid);
                bean.setItem(item);
                bean.setType(type);
                bean.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public ItemImage getByIid(int iid) {
        ItemImage bean = new ItemImage();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from itemImage where iid = " + iid;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                Item item = new ItemDAO().get(iid);
                bean.setItem(item);
                bean.setType(type);
                bean.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public List<ItemImage> list() {
        List<ItemImage> beans = new ArrayList<ItemImage>();
        String sql = "select * from itemImage";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ItemImage bean = new ItemImage();
                int id = rs.getInt(1);
                int iid = rs.getInt("iid");
                Item item = new ItemDAO().get(iid);
                String type = rs.getString("type");


                bean.setItem(item);
                bean.setType(type);
                bean.setId(id);

                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

    public List<ItemImage> list(Item i, String type) {
        List<ItemImage> beans = new ArrayList<ItemImage>();

        String sql = "select * from itemImage where pid =? and type =? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, i.getId());
            ps.setString(2, type);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ItemImage bean = new ItemImage();
                int id = rs.getInt(1);


                bean.setItem(i);
                bean.setType(type);
                bean.setId(id);

                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }
}
