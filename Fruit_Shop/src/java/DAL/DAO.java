/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.List;
import model.SiteUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import model.Cart;
import model.CartItem;
import model.Category;
import model.Discount;
import model.OrderDetail;
import model.OrderItem;
import model.Product;

/**
 *
 * @author tuong
 */
public class DAO extends DBContext {

    public List<SiteUser> getAllUser() {
        List<SiteUser> list = new ArrayList<>();
        String sql = "select * from site_user";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String name = rs.getString("first_name") + rs.getString("last_name");
                int id = rs.getInt("id");
                SiteUser cur = new SiteUser(id, rs.getString("username"),
                        rs.getString("password"), name,
                        rs.getString("phone"), rs.getString("role"),
                        rs.getString("avatar"));
                String gmail = rs.getString("gmail");
                String adress = rs.getString("address_user");
                cur.setAddress(adress);
                cur.setGmail(gmail);
                list.add(cur);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public SiteUser getUserById(int id) {
        String sql = "select * from site_user where id= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String name = rs.getString("first_name") + rs.getString("last_name");
                SiteUser cur = new SiteUser(id, rs.getString("username"),
                        rs.getString("password"), name,
                        rs.getString("phone"), rs.getString("role"),
                        rs.getString("avatar"));
                String gmail = rs.getString("gmail");
                String adress = rs.getString("address_user");
                cur.setAddress(adress);
                cur.setGmail(gmail);
                return cur;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from products";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Product cur = new Product(id, rs.getInt("category_id"),
                        rs.getInt("quantity"), rs.getString("name"),
                        rs.getFloat("price"));
                String img = rs.getString("img");
                cur.setImg(img);
                cur.setDecription(rs.getString("description"));

                list.add(cur);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from category";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Category cur = new Category(id, rs.getString("name"));
                list.add(cur);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public void deleteProById(String pid) {
        String sql = "DELETE FROM order_item WHERE product_id = ?; "
                + "DELETE FROM cart_item WHERE product_id = ?; "
                + "DELETE FROM Discount WHERE product_id = ?; "
                + "DELETE FROM Products WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pid);
            st.setString(2, pid);
            st.setString(3, pid);
            st.setString(4, pid);
            st.executeUpdate();
        } catch (SQLException e) {
            // Handle exception properly, such as logging or rethrowing
            e.printStackTrace();
        }
    }

    public List<OrderDetail> getAllOrderDetail() {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "select * from order_detail";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");

                OrderDetail cur = new OrderDetail(id, rs.getInt("site_user_id"),
                        rs.getString("payment"), rs.getString("create_at"),
                        rs.getString("address_user"), rs.getString("status_order"),
                        rs.getString("delivered_at"));
                list.add(cur);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public void updateProduct(String name, String description, int id, int caId,
            int quantity, float price) {
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [name] = ?,\n"
                + "       [category_id] = ?,\n"
                + "       [quantity] = ?,\n"
                + "       [price] = ?,\n"
                + "       [description] = ?\n"
                + " WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, caId);
            ps.setInt(3, quantity);
            ps.setFloat(4, price);
            ps.setString(5, description);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Product getProById(String pid_raw) {
        String sql = "select * from Products where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int pid = Integer.parseInt(pid_raw);
            st.setInt(1, pid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                Product cur = new Product(id, rs.getInt("category_id"),
                        rs.getInt("quantity"), rs.getString("name"),
                        rs.getFloat("price"));
                String img = rs.getString("img");
                cur.setImg(img);
                cur.setDecription(rs.getString("description"));
                return cur;
            }

        } catch (Exception e) {
        }
        return null;
    }

    public void addProduct(String name, int cateId, int quantity, float price,
            String descipt, String img) {
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([name]\n"
                + "           ,[category_id]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[description]\n"
                + "           ,[create_date]\n"
                + "           ,[img])\n"
                + "     VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, cateId);
            ps.setInt(3, quantity);
            ps.setFloat(4, price);
            ps.setString(5, descipt);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String curTime = dtf.format(now);
            ps.setString(6, curTime);
            ps.setString(7, img);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public float getTotalOrderPrice(int odId) {
        String sql = "{CALL GetTotalPrice(?, ?)}";
        try {
            CallableStatement cs = connection.prepareCall(sql);
            cs.setInt(1, odId);
            cs.registerOutParameter(2, java.sql.Types.FLOAT);
            cs.execute();

            float totalPrice = cs.getFloat(2);
            return totalPrice;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public List<OrderDetail> getListByStatus(String status) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "select * from order_detail\n"
                + "where status_order=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                OrderDetail cur = new OrderDetail(id, rs.getInt("site_user_id"),
                        rs.getString("payment"),
                        rs.getString("create_at"),
                        rs.getString("address_user"),
                        rs.getString("status_order"),
                        rs.getString("delivered_at"));
                list.add(cur);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public OrderDetail getOderDeById(int odId) {
        String sql = "select * from order_detail\n"
                + "where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, odId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                OrderDetail cur = new OrderDetail(id, rs.getInt("site_user_id"),
                        rs.getString("payment"),
                        rs.getString("create_at"),
                        rs.getString("address_user"),
                        rs.getString("status_order"),
                        rs.getString("delivered_at"));
                return cur;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<OrderItem> getAllItemById(int odId) {
        List<OrderItem> list = new ArrayList<>();
        String sql = "select * from order_item where detail_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, odId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                OrderItem cur = new OrderItem(id,
                        rs.getInt("detail_id"),
                        rs.getInt("quantity"),
                        rs.getInt("product_id"),
                        rs.getInt("discount_id"));
                list.add(cur);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;

    }

    public List<Discount> getAllDiscount() {
        List<Discount> list = new ArrayList<>();
        String sql = "select * from discount";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Discount cur = new Discount(rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getFloat("discount_percent"));
                list.add(cur);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public void setOrderStatus(int odId, String newSta) {
        String sql = "UPDATE [dbo].[order_detail] SET [status_order] = ? WHERE id = ?";

        try {
            if (newSta.equalsIgnoreCase("Finish")) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime now = LocalDateTime.now();
                String curTime = dtf.format(now);

                sql = "UPDATE [dbo].[order_detail] SET [delivered_at] = ?, [status_order] = ? WHERE id = ?";
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, curTime);
                st.setString(2, newSta);
                st.setInt(3, odId);
                st.executeUpdate();
                st.close();  // Close the statement after execution
            } else {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, newSta);
                st.setInt(2, odId);
                st.executeUpdate();
                st.close();  // Close the statement after execution
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public SiteUser getUserByUserName(String username, String password) {
        String sql = "SELECT * FROM site_user WHERE username = ? AND password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String name = rs.getString("first_name") + " " + rs.getString("last_name");
                SiteUser cur = new SiteUser(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        name,
                        rs.getString("phone"),
                        rs.getString("role"),
                        rs.getString("avatar")
                );
                String gmail = rs.getString("gmail");
                String adress = rs.getString("address_user");
                cur.setAddress(adress);
                cur.setGmail(gmail);
                return cur;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkAccExist(String username, String gmail) {
        String sql = "SELECT id FROM site_user WHERE username = ? OR gmail = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, username);
            st.setString(2, gmail);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next(); // Returns true if there's at least one result, meaning the account exists
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception stack trace for debugging
            return false; // Return false in case of exception, indicating the check couldn't be completed
        }
    }

    public void insertNewCustomer(String firstName, String lastName, String userName, String password, String gmail) {
        String sql = "INSERT INTO [dbo].[site_user] "
                + "(username, password, first_name, last_name, role, avatar, gmail) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, userName);
            st.setString(2, password); // You should hash the password before storing it
            st.setString(3, firstName);
            st.setString(4, lastName);
            st.setString(5, "customer"); // Fixed index
            st.setString(6, "https://www.kindpng.com/picc/m/24-248253_user-profile-default-image-png-clipart-png-download.pngseeTaE4kxPL2FmOQ&s"); // Fixed index
            st.setString(7, gmail);

            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception stack trace for debugging
        }
    }

    public List<Product> getProductsByPageAndCategory(int offset, int noOfRecords, int categoryId) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Products ";
        if (categoryId != -1) {
            query += "WHERE category_id = ? ";
        }
        query += "ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            int index = 1;
            if (categoryId != -1) {
                ps.setInt(index++, categoryId);
            }
            ps.setInt(index++, offset);
            ps.setInt(index, noOfRecords);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Product cur = new Product(
                        id,
                        rs.getInt("category_id"),
                        rs.getInt("quantity"),
                        rs.getString("name"),
                        rs.getFloat("price")
                );
                String img = rs.getString("img");
                cur.setImg(img);
                cur.setDecription(rs.getString("description"));
                list.add(cur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getProductCountByCategory(int categoryId) {
        String sql = "SELECT COUNT(*) FROM Products";
        if (categoryId != -1) {
            sql += " WHERE category_id = ?";
        }
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            if (categoryId != -1) {
                st.setInt(1, categoryId);
            }
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getProductCount() {
        String sql = "SELECT COUNT(*) FROM products";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Product> getProductsByName(String name, int offset, int noOfRecords) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Products WHERE name LIKE ?  ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, offset);
            ps.setInt(3, noOfRecords);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Product cur = new Product(id, rs.getInt("category_id"),
                        rs.getInt("quantity"), rs.getString("name"),
                        rs.getFloat("price"));
                String img = rs.getString("img");
                cur.setImg(img);
                cur.setDecription(rs.getString("description"));
                list.add(cur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getProductCountByName(String name) {
        String sql = "SELECT COUNT(*) FROM Products WHERE name LIKE ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Product> getProductsByPage(int offset, int noOfRecords) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Products ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, offset);
            ps.setInt(2, noOfRecords);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Product cur = new Product(
                        id,
                        rs.getInt("category_id"),
                        rs.getInt("quantity"),
                        rs.getString("name"),
                        rs.getFloat("price")
                );
                String img = rs.getString("img");
                cur.setImg(img);
                cur.setDecription(rs.getString("description"));
                list.add(cur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> getAllProByCaIdAndName(int caId, String proName) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category_id = ? AND name LIKE ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, caId);
            st.setString(2, "%" + proName + "%");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Product cur = new Product(id, rs.getInt("category_id"),
                        rs.getInt("quantity"), rs.getString("name"),
                        rs.getFloat("price"));
                String img = rs.getString("img");
                cur.setImg(img);
                cur.setDecription(rs.getString("description"));
                list.add(cur);
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Product> getAllProByCaId(int caId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, caId);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Product cur = new Product(id, rs.getInt("category_id"),
                        rs.getInt("quantity"), rs.getString("name"),
                        rs.getFloat("price"));
                String img = rs.getString("img");
                cur.setImg(img);
                cur.setDecription(rs.getString("description"));
                list.add(cur);
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Product> getAllProByNa(String proName) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE  name LIKE ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, "%" + proName + "%");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Product cur = new Product(id, rs.getInt("category_id"),
                        rs.getInt("quantity"), rs.getString("name"),
                        rs.getFloat("price"));
                String img = rs.getString("img");
                cur.setImg(img);
                cur.setDecription(rs.getString("description"));
                list.add(cur);
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public Discount getDiscountByPrID(int prId) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String curTime = dtf.format(now);
        String sql = "SELECT TOP 1 *\n"
                + "FROM discount\n"
                + "WHERE product_id = ? \n"
                + "AND ? BETWEEN start_date AND end_date\n"
                + "ORDER BY discount_percent DESC;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, prId);
            st.setString(2, curTime);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Discount cur = new Discount(rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getFloat("discount_percent"));
                return cur;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Discount> getAllDiscAvail() {
        List<Discount> list = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String curTime = dtf.format(now);
        String sql = "SELECT TOP 1 *\n"
                + "FROM discount\n"
                + "WHERE ? BETWEEN start_date AND end_date\n"
                + "ORDER BY discount_percent DESC;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, curTime);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Discount cur = new Discount(rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getFloat("discount_percent"));
                list.add(cur);

            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public Cart getCartByUserId(int userId) {
        String sql = "select * from Cart where site_user_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cart cur = new Cart(rs.getInt("id"), rs.getInt("site_user_id"));
                return cur;
            }
        } catch (Exception e) {
        }
        return null;

    }

    public List<CartItem> getAllCartItemByCartId(int cartId) {
        List<CartItem> list = new ArrayList<>();
        String sql = "SELECT [id] ,[cart_id] ,[product_id] ,[quantity],[discount_id]\n"
                + "               FROM [dbo].[cart_item] where cart_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CartItem cur = new CartItem(rs.getInt("id"),
                        rs.getInt("cart_id"),
                        rs.getInt("product_id"), rs.getInt("quantity"));
                cur.setDisId(rs.getInt("discount_id"));
                list.add(cur);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Product> getAllProInCart(int cartId) {
        List<Product> list = new ArrayList<>();
        String sql = "select p.id, p.name, p.category_id,p.quantity,p.price,p.description,p.create_date,p.img\n"
                + "               from Products p right join cart_item ci \n"
                + "               on ci.product_id = p.id\n"
                + "              where cart_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Product cur = new Product(id, rs.getInt("category_id"),
                        rs.getInt("quantity"), rs.getString("name"),
                        rs.getFloat("price"));
                String img = rs.getString("img");
                cur.setImg(img);
                cur.setDecription(rs.getString("description"));

                list.add(cur);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Discount> getAllDisByCart(int cartId) {
        List<Discount> list = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String curTime = dtf.format(now);
        String sql = "select * \n"
                + "from discount d\n"
                + "where d.product_id in (select p.id\n"
                + "from Products p right join cart_item ci \n"
                + "on p.id = ci.product_id \n"
                + "where ci.cart_id=?) and ? BETWEEN start_date AND end_date";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartId);
            st.setString(2, curTime);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Discount cur = new Discount(rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getFloat("discount_percent"));
                list.add(cur);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public void removeCartItemById(int itemId) {
        String sql = "DELETE FROM [dbo].[cart_item]\n"
                + "      WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, itemId);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateQuanCartItemById(int itemId, int quantity) {
        String sql = "UPDATE [dbo].[cart_item]\n"
                + "   SET  [quantity] = ?\n"
                + "        WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, quantity);
            st.setInt(2, itemId);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void createCartForUser(int userId) {
        String sql = "INSERT INTO [dbo].[cart]\n"
                + "           ([site_user_id])\n"
                + "     VALUES\n"
                + "           (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public float getTotalCartId(int cartId) {
        String sql = "SELECT "
                + "  SUM(p.price * ci.quantity * (1 - COALESCE(d.discount_percent, 0) / 100)) AS discounted_total "
                + "FROM "
                + "  cart_item ci "
                + "RIGHT JOIN "
                + "  Products p ON p.id = ci.product_id "
                + "LEFT JOIN "
                + "  discount d ON d.product_id = p.id "
                + "WHERE "
                + "  ci.cart_id = ?;";
        float total = 0.0f;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, cartId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    total = rs.getFloat("discounted_total");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  // Consider logging the exception or handling it as needed
        }
        return total;
    }

    public void insertItemToCart(int cartId, int proId) {
        String sql = "INSERT INTO [dbo].[cart_item]\n"
                + "           ([cart_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity]\n"
                + "           ,[discount_id])\n"
                + "     VALUES\n"
                + "           (?,? ,?,?)";
        DAO dao = new DAO();
        Discount discount = dao.getDiscountByPrID(proId);
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartId);
            st.setInt(2, proId);
            st.setInt(3, 1);
            if (discount != null) {
                st.setInt(4, discount.getId());
            } else {
                st.setInt(4, 0);
            }
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertOrderDetail(int userId, String payment, String address) {
        String sql = "INSERT INTO [dbo].[order_detail]\n"
                + "           ([site_user_id]\n"
                + "           ,[payment]\n"
                + "           ,[address_user]\n"
                + "           ,[status_order]\n"
                + "           ,[create_at])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String curTime = dtf.format(now);
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setString(2, payment);
            st.setString(3, address);
            st.setString(4, "Waiting accept");
            st.setString(5, curTime);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public int getRecentOrderDetailId() {
        String sql = "SELECT TOP 1 *\n"
                + "FROM order_detail\n"
                + "ORDER BY id DESC;";
        try (
                PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            if (rs.next()) { // Move cursor to the first row
                return rs.getInt("id"); // Get the id column value
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace for debugging
            throw new RuntimeException("Error retrieving recent order detail ID", e); // Optionally rethrow the exception
        }
        return 0; // Return 0 if no rows were found
    }

    public void insertItemToOrderDetail(CartItem cur, int odId) {
        String sql = "INSERT INTO [dbo].[order_item]\n"
                + "           ([detail_id]\n"
                + "           ,[quantity]\n"
                + "           ,[product_id]\n"
                + "           ,[discount_id])\n"
                + "     VALUES\n"
                + "           (?,?,?,?) ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, odId);
            st.setInt(2, cur.getQuantity());
            st.setInt(3, cur.getPrId());
            st.setInt(4, cur.getDisId());
            st.executeUpdate();
            subtractQuantity(cur);
        } catch (Exception e) {
        }
    }

    public void subtractQuantity(CartItem cur) {
        DAO dao = new DAO();
        String prId = String.valueOf(cur.getId());
        Product newProduct = dao.getProById(prId);
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET \n"
                + "      [quantity] = ?\n"
                + "      \n"
                + " WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, newProduct.getQuantity() - cur.getQuantity());
            st.setInt(2, newProduct.getId());
        } catch (Exception e) {
        }
    }

    public void removeCartItemByCartId(int cartId) {
        String sql = "DELETE FROM [dbo].[cart_item]\n"
                + "      WHERE cart_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartId);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertCartByUserId(int userId) {
        String sql = "INSERT INTO [dbo].[cart]\n"
                + "           ([site_user_id])\n"
                + "     VALUES\n"
                + "           (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateUserProById(String username, String fname, String lname,
            String address, String phone, String gmail, int userId) {
        String sql = "UPDATE [dbo].[site_user]\n"
                + "   SET [username] = ?\n"
                + "      ,[first_name] = ?\n"
                + "      ,[last_name] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[gmail] = ?\n"
                + "      ,[address_user] =?\n"
                + " WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, fname);
            st.setString(3, lname);
            st.setString(4, phone);
            st.setString(5, gmail);
            st.setString(6, address);
            st.setInt(7, userId);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void setNewPassByUserId(int userId, String newPass) {
        String sql = "UPDATE [dbo].[site_user]\n"
                + "   SET \n"
                + "      [password] =?\n"
                + "      \n"
                + " WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newPass);
            st.setInt(2, userId);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Product> get5NewestProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "select top 5* from Products\n"
                + "order by create_date desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Product cur = new Product(id, rs.getInt("category_id"),
                        rs.getInt("quantity"), rs.getString("name"),
                        rs.getFloat("price"));
                String img = rs.getString("img");
                cur.setImg(img);
                cur.setDecription(rs.getString("description"));

                list.add(cur);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Product> get5MostSaleProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT TOP 5 p.id, p.name, p.price, p.img, p.category_id, p.create_date, p.description, p.quantity, SUM(oi.quantity) AS total_quantity\n"
                + "FROM order_item oi\n"
                + "LEFT JOIN Products p ON oi.product_id = p.id\n"
                + "GROUP BY p.id, p.name, p.price, p.img, p.category_id, p.create_date, p.description, p.quantity\n"
                + "ORDER BY total_quantity DESC;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Product cur = new Product(id, rs.getInt("category_id"),
                        rs.getInt("quantity"), rs.getString("name"),
                        rs.getFloat("price"));
                String img = rs.getString("img");
                cur.setImg(img);
                cur.setDecription(rs.getString("description"));
                list.add(cur);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> get5SaleProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT TOP 5 *\n"
                + "FROM Products p\n"
                + "RIGHT JOIN discount d ON p.id = d.product_id\n"
                + "WHERE d.id != 0 AND ? BETWEEN d.start_date AND d.end_date;";
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String curTime = dtf.format(now);
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, curTime);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Product cur = new Product(id, rs.getInt("category_id"),
                        rs.getInt("quantity"), rs.getString("name"),
                        rs.getFloat("price"));
                String img = rs.getString("img");
                cur.setImg(img);
                cur.setDecription(rs.getString("description"));

                list.add(cur);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public void updateProImgById(int proId, String img) {
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET\n"
                + "      [img] = ?\n"
                + " WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, img);
            st.setInt(2, proId);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<OrderDetail> getAllOrdDetByUserId(int userId) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "select * from order_detail \n"
                + "where site_user_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");

                OrderDetail cur = new OrderDetail(id, rs.getInt("site_user_id"),
                        rs.getString("payment"), rs.getString("create_at"),
                        rs.getString("address_user"), rs.getString("status_order"),
                        rs.getString("delivered_at"));
                list.add(cur);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public void deleteOrderItemByOd(int odId) {
        String sql = "DELETE FROM [dbo].[order_item]\n"
                + "      WHERE detail_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, odId);
            st.executeUpdate(); // Execute the update statement
        } catch (Exception e) {
            System.out.println("Error deleting order items: " + e.getMessage());
        }
    }

    public void deleteCartItemByCart(int cartId) {
        String sql = "DELETE FROM [dbo].[cart_item]\n"
                + "      WHERE cart_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartId);
            st.executeUpdate(); // Execute the update statement
        } catch (Exception e) {
            System.out.println("Error deleting cart items: " + e.getMessage());
        }
    }

    public void deleteCartByUserId(int userId) {
        String sql = "DELETE FROM [dbo].[cart]\n"
                + "      WHERE site_user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.executeUpdate(); // Execute the update statement
        } catch (Exception e) {
            System.out.println("Error deleting cart: " + e.getMessage());
        }
    }

    public void deleteOrderDetailByUserId(int userId) {
        String sql = "DELETE FROM [dbo].[order_detail]\n"
                + "      WHERE site_user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.executeUpdate(); // Execute the update statement
        } catch (Exception e) {
            System.out.println("Error deleting order detail: " + e.getMessage());
        }
    }

    public void deleteUserById(int userId) {
        String sql = "DELETE FROM [dbo].[site_user]\n"
                + "      WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.executeUpdate(); // Execute the update statement
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    public int countTotalProduct() {
        String sql = "select count(*)  as total\n"
                + "from Products";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int total = rs.getInt("total");
                return total;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int countTotalOrder() {
        String sql = "SELECT COUNT(*) AS total_orders\n"
                + "FROM order_detail;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int total = rs.getInt("total_orders");
                return total;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public float averageProductPrice() {
        String sql = "SELECT AVG(p.price) as averagePrice\n"
                + "FROM Products p";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                float total = rs.getInt("averagePrice");
                return total;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Float> getMonthlyIncome(int year) {
        List<Float> monthlyIncome = new ArrayList<>();
        String sql = "SELECT MONTH(od.delivered_at) AS month, SUM(oi.quantity * p.price) AS total "
                + "FROM order_item oi "
                + "LEFT JOIN Products p ON oi.product_id = p.id "
                + "LEFT JOIN order_detail od ON od.id = oi.detail_id "
                + "WHERE YEAR(od.delivered_at) = ? "
                + "GROUP BY MONTH(od.delivered_at)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, year);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int month = rs.getInt("month");
                float total = rs.getFloat("total");
                while (monthlyIncome.size() < month) {
                    monthlyIncome.add(0.0f); // Add zeros for missing months
                }
                monthlyIncome.set(month - 1, total); // Set the income for the correct month
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthlyIncome;
    }

    public Map<String, Float> getTotalIncomeByCategory() {
        Map<String, Float> categoryIncomeMap = new HashMap<>();
        String sql = "SELECT c.id, c.name, SUM(oi.quantity * p.price) AS total "
                + "FROM order_item oi "
                + "LEFT JOIN Products p ON oi.product_id = p.id "
                + "LEFT JOIN Category c ON p.category_id = c.id "
                + "GROUP BY c.id, c.name";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String categoryName = rs.getString("name");
                float totalIncome = rs.getFloat("total");
                categoryIncomeMap.put(categoryName, totalIncome);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return categoryIncomeMap;
    }

    public static void main(String[] args) {
        DAO act = new DAO();
        List<Product> list = act.get5MostSaleProduct();
        List<Product> list1 = act.getAllProInCart(1);
        Map<String, Float> list4 = act.getTotalIncomeByCategory();
        System.out.println(list4.get("Fruit"));
    }
}
