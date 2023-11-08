package repositories;

import models.Item;
import models.Order;
import utils.ConnectionUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepo implements CRUDRepo<Order> {

        public OrderRepo(){ }

        ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

        @Override
        public Order add(Order o) {
            try (Connection conn = cu.getConnection()) {
                String sql = "insert into \"electronicsP0\".order_history values(default, ?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, o.getUserID());
                ps.setInt(2, o.getItemID());
                ps.setInt(3, o.getQuantity());
                ps.setString(4, o.getTimestamp());
                ps.setString(5, o.getStatus());
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public Order getById(Integer id) {
            try (Connection conn = cu.getConnection()) {
                String sql = "select * from \"electronicsP0\".order_history where transact_id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Order o = new Order(
                            rs.getInt("transact_id"),
                            rs.getInt("user_id"),
                            rs.getInt("item_id"),
                            rs.getInt("order_quantity"),
                            rs.getString("order_time"),
                            rs.getString("order_status")
                    );
                    return o;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public List<Order> getByUserId(Integer id){//select * from "electronicsP0".order_history where user_id = 1;
            try (Connection conn = cu.getConnection()) {
                String sql = "select * from \"electronicsP0\".order_history where user_id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                List<Order> x = new ArrayList<Order>();
                while (rs.next()) {
                    Order o = new Order(
                            rs.getInt("transact_id"),
                            rs.getInt("user_id"),
                            rs.getInt("item_id"),
                            rs.getInt("order_quantity"),
                            rs.getString("order_time"),
                            rs.getString("order_status")
                    );
                    x.add(o);

                }
                return x;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public List<Order> getAll() {
            try (Connection conn = cu.getConnection()) {
                String sql = "select * from \"electronicsP0\".order_history";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                List<Order> x = new ArrayList<Order>();
                while (rs.next()) {
                    Order o = new Order(
                            rs.getInt("transact_id"),
                            rs.getInt("user_id"),
                            rs.getInt("item_id"),
                            rs.getInt("order_quantity"),
                            rs.getString("order_time"),
                            rs.getString("order_status")
                    );
                    x.add(o);
                }
                return x;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void update(Order o) {
            try (Connection conn = cu.getConnection()) {
                String sql = "update \"electronicsP0\".order_history set order_quantity = ?, order_status = ? where transact_id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, o.getQuantity());
                ps.setString(2, o.getStatus());
                ps.setInt(3, o.getOrderID());
                ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void delete(Order o) {
            try (Connection conn = cu.getConnection()) {
                String sql = "delete from \"electronicsP0\".order_history where transact_id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, o.getOrderID());
                ps.executeQuery();
            } catch (SQLException e) {//use custom exception here
                e.printStackTrace();
            }
        }


}
