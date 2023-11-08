package repositories;

import models.Item;
import models.User;
import utils.ConnectionUtil;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepo implements CRUDRepo<Item> {

    public ItemRepo(){ }

    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    @Override
    public Item add(Item item) {
        try (Connection conn = cu.getConnection()) {
            String sql = "insert into \"electronicsP0\".items values(default, ?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, item.getItemName());
            ps.setString(2, item.getItemSpecs());
            ps.setInt(3, item.getNumInStock());
            ps.setString(4, item.getItemPrice());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Item getById(Integer id) {
        try (Connection conn = cu.getConnection()) {
            String sql = "select * from \"electronicsP0\".items where item_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Item it = new Item(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("item_specs"),
                        rs.getInt("item_quantity"),
                        rs.getString("item_price")
                );
                return it;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> getAll() {
        try (Connection conn = cu.getConnection()) {
            String sql = "select * from \"electronicsP0\".items";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Item> x = new ArrayList<Item>();
            while (rs.next()) {
                Item it = new Item(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("item_specs"),
                        rs.getInt("item_quantity"),
                        rs.getString("item_price")
                );
                x.add(it);
            }
            return x;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Item item) {
        try (Connection conn = cu.getConnection()) {
            String sql = "update \"electronicsP0\".items set item_name = ?, item_specs = ?, item_quantity = ?, item_price = ? where item_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, item.getItemName());
            ps.setString(2, item.getItemSpecs());
            ps.setInt(3, item.getNumInStock());
            ps.setString(4, item.getItemPrice());
            ps.setInt(5, item.getItemID());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Item item) {
        try (Connection conn = cu.getConnection()) {
            String sql = "delete from \"electronicsP0\".items where item_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, item.getItemID());
            ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {//use custom exception here
            e.printStackTrace();
        }
    }
}
