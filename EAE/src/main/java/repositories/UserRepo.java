package repositories;

import models.User;
import utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo implements  CRUDRepo<User>{

    public UserRepo(){

    }

    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    public User getByUsername(String username){//given a username, returns User object
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"electronicsP0\".users where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("user_password"),
                        rs.getString("payment_info"),
                        rs.getString("user_email"),
                        rs.getString("user_type")
                );
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // try-with-resources - automatically closes resources after execution
//        finally {
//            conn.close();
//        }

        return null;
    }

    @Override
    public User add(User user) {
        try (Connection conn = cu.getConnection()) {

            String sql = "insert into \"electronicsP0\".users values(default, ?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getEmail());
            ps.setString(4,user.getType());
            ps.setString(5,user.getPaymentInfo());

            ps.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getById(Integer id) {
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"electronicsP0\".users where user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("user_password"),
                        rs.getString("payment_info"),
                        rs.getString("user_email"),
                        rs.getString("user_type")
                );
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<User> getAll() {
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"electronicsP0\".users";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            List<User> x = new ArrayList<User>();

            if (rs.next()) {
                User u = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("user_password"),
                        rs.getString("payment_info"),
                        rs.getString("user_email"),
                        rs.getString("user_type")
                );
                x.add(u);
            }
            return x;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(User user) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update \"electronicsP0\".users set user_password = ?, payment_info = ?, email = ?, user_type = ? where user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getPassword());
            ps.setString(2,user.getPaymentInfo());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getType());
            ps.setInt(5,user.getAccountID());

            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User u) {
        try (Connection conn = cu.getConnection()) {

            String sql = "delete from \"electronicsP0\".users where user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, u.getAccountID());

            ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {//use custom exception here
            e.printStackTrace();
        }

    }
}
