
package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private static final String INSERT_PRODUCTO = "INSERT INTO productos (nombre, precio, descripcion) VALUES (?, ?, ?)";
    private static final String SELECT_PRODUCTO = "SELECT * FROM productos";
    private static final String UPDATE_PRODUCTO = "UPDATE productos SET nombre=?, precio=?, descripcion=? WHERE id=?";
    private static final String DELETE_PRODUCTO = "DELETE FROM productos WHERE id=?";

    public static boolean insertarProducto(Producto producto) {
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(INSERT_PRODUCTO)) {
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setString(3, producto.getDescripcion());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(SELECT_PRODUCTO);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setId(resultSet.getInt("id"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setPrecio(resultSet.getDouble("precio"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public static boolean actualizarProducto(Producto producto) {
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(UPDATE_PRODUCTO)) {
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setString(3, producto.getDescripcion());
            statement.setInt(4, producto.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarProducto(int id) {
        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(DELETE_PRODUCTO)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}