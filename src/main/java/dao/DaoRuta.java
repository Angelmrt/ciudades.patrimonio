// Clase DaoRuta (en package dao)
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import entidades.Ruta;

public class DaoRuta {

    // Constructor vacío
    public DaoRuta() {}

    // Método para encontrar rutas por id de ciudad
    public List<Ruta> findRutasByCiudad(int idCiudad) throws SQLException, Exception {
        List<Ruta> listaRutas = new ArrayList<Ruta>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "SELECT * FROM RUTA WHERE CIUDAD = ?";
            st = con.prepareStatement(ordenSQL);
            st.setInt(1, idCiudad);
            rs = st.executeQuery();

            DaoPunto daoPunto = new DaoPunto();  // Instancia de DaoPunto para calcular la media de puntos

            while (rs.next()) {
                Ruta miRuta = new Ruta();
                miRuta.setId(rs.getInt("ID"));
                miRuta.setCiudad(rs.getInt("CIUDAD"));
                miRuta.setNombre(rs.getString("NOMBRE"));
                miRuta.setImagen(rs.getString("IMAGEN"));
                miRuta.setDescripcion(rs.getString("DESCRIPCION"));
                miRuta.setLink(rs.getString("LINK"));

                // Calcular y asignar la media de puntos
                double mediaPuntos = daoPunto.calculaMediaPuntos(miRuta.getId());
                miRuta.setMediaPuntos(mediaPuntos);

                listaRutas.add(miRuta);
            }
        } catch (SQLException se) {
            throw se;
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return listaRutas;
    }

    // Método para insertar una nueva ruta en la base de datos
    public boolean addRuta(Ruta ruta) throws SQLException, Exception {
        boolean isAdded = false;
        Connection con = null;
        PreparedStatement st = null;

        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "INSERT INTO RUTA (ID, CIUDAD, NOMBRE, IMAGEN, DESCRIPCION, LINK) VALUES (?, ?, ?, ?, ?, ?)";
            st = con.prepareStatement(ordenSQL);

            st.setInt(1, ruta.getId());
            st.setInt(2, ruta.getCiudad());
            st.setString(3, ruta.getNombre());
            st.setString(4, ruta.getImagen());
            st.setString(5, ruta.getDescripcion());
            st.setString(6, ruta.getLink());

            int rowsAffected = st.executeUpdate();
            isAdded = (rowsAffected > 0);
        } catch (SQLException se) {
            throw se;
        } catch (Exception e) {
            throw e;
        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return isAdded;
    }

    // Método para obtener una ruta por su ID (ahora retorna un solo objeto Ruta)
    public Ruta findRutaById(int idRuta) throws SQLException, Exception {
        Ruta miRuta = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "SELECT * FROM RUTA WHERE ID = ?";
            st = con.prepareStatement(ordenSQL);
            st.setInt(1, idRuta);
            rs = st.executeQuery();

            DaoPunto daoPunto = new DaoPunto();  // Instancia para calcular media de puntos

            if (rs.next()) {
                miRuta = new Ruta();
                miRuta.setId(rs.getInt("ID"));
                miRuta.setCiudad(rs.getInt("CIUDAD"));
                miRuta.setNombre(rs.getString("NOMBRE"));
                miRuta.setImagen(rs.getString("IMAGEN"));
                miRuta.setDescripcion(rs.getString("DESCRIPCION"));
                miRuta.setLink(rs.getString("LINK"));

                // Calcular y asignar la media de puntos
                double mediaPuntos = daoPunto.calculaMediaPuntos(miRuta.getId());
                miRuta.setMediaPuntos(mediaPuntos);
            }
        } catch (SQLException se) {
            throw se;
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return miRuta;
    }

    // Método para actualizar los detalles de una ruta existente
    public boolean updateRuta(Ruta ruta) throws SQLException, Exception {
        boolean isUpdated = false;
        Connection con = null;
        PreparedStatement st = null;

        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "UPDATE RUTA SET CIUDAD = ?, NOMBRE = ?, IMAGEN = ?, DESCRIPCION = ?, LINK = ? WHERE ID = ?";
            st = con.prepareStatement(ordenSQL);

            st.setInt(1, ruta.getCiudad());
            st.setString(2, ruta.getNombre());
            st.setString(3, ruta.getImagen());
            st.setString(4, ruta.getDescripcion());
            st.setString(5, ruta.getLink());
            st.setInt(6, ruta.getId());

            int rowsAffected = st.executeUpdate();
            isUpdated = (rowsAffected > 0);
        } catch (SQLException se) {
            throw se;
        } catch (Exception e) {
            throw e;
        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return isUpdated;
    }

    // Método para eliminar una ruta por su ID
    public boolean deleteRuta(int idRuta) throws SQLException, Exception {
        boolean isDeleted = false;
        Connection con = null;
        PreparedStatement st = null;

        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "DELETE FROM RUTA WHERE ID = ?";
            st = con.prepareStatement(ordenSQL);
            st.setInt(1, idRuta);

            int rowsAffected = st.executeUpdate();
            isDeleted = (rowsAffected > 0);
        } catch (SQLException se) {
            throw se;
        } catch (Exception e) {
            throw e;
        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return isDeleted;
    }
}
