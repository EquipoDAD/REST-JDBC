
package server;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class ProductosWS {

    @WebMethod
    public boolean agregarProducto(@WebParam(name = "producto") Producto producto) {
        return ProductoDAO.insertarProducto(producto);
    }

    @WebMethod
    public List<Producto> obtenerProductos() {
        return ProductoDAO.obtenerProductos();
    }

    @WebMethod
    public boolean actualizarProducto(@WebParam(name = "producto") Producto producto) {
        return ProductoDAO.actualizarProducto(producto);
    }

    @WebMethod
    public boolean eliminarProducto(@WebParam(name = "id") int id) {
        return ProductoDAO.eliminarProducto(id);
    }
}
