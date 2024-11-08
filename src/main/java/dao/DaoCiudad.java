package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import entidades.Ciudad;

public class DaoCiudad {

    // Constructor vacío
    public DaoCiudad() {}

    // Método para obtener todas las ciudades de la tabla CIUDAD
    public List<Ciudad> listadoCiudades() throws SQLException, Exception {
        List<Ciudad> listaCiudades = new ArrayList<Ciudad>();
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;

        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            st = con.createStatement();
            String ordenSQL = "SELECT * FROM CIUDAD ORDER BY NOMBRE";
            rs = st.executeQuery(ordenSQL);

            while (rs.next()) {
                Ciudad miCiudad = new Ciudad();
                miCiudad.setId(rs.getInt("ID"));
                miCiudad.setNombre(rs.getString("NOMBRE"));
                miCiudad.setImagen(rs.getString("IMAGEN"));
                miCiudad.setDescripcion(rs.getString("DESCRIPCION"));
                miCiudad.setLink(rs.getString("LINK"));
                miCiudad.setMapa(rs.getString("MAPA"));
                listaCiudades.add(miCiudad);
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
        return listaCiudades;
    }

    // Método para insertar una nueva ciudad en la tabla CIUDAD
    public void insertaCiudad(Ciudad c) throws SQLException, Exception {
        Connection con = null;
        PreparedStatement st = null;

        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            con.setAutoCommit(false);
            String ordenSQL = "INSERT INTO CIUDAD (ID, NOMBRE, IMAGEN, DESCRIPCION, LINK, MAPA) VALUES (S_CIUDAD.NEXTVAL, ?, ?, ?, ?, ?)";
            st = con.prepareStatement(ordenSQL);
            st.setString(1, c.getNombre());
            st.setString(2, c.getImagen());
            st.setString(3, c.getDescripcion());
            st.setString(4, c.getLink());
            st.setString(5, c.getMapa());
            st.executeUpdate();
            con.commit();
        } catch (SQLException se) {
            if (con != null) con.rollback();  // rollback en caso de error
            throw se;
        } catch (Exception e) {
            throw e;
        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
    }

    // Método para buscar una ciudad por ID en la tabla CIUDAD
    public Ciudad findCiudadById(int id) throws SQLException, Exception {
        Ciudad ciudad = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "SELECT * FROM CIUDAD WHERE ID = ?";
            st = con.prepareStatement(ordenSQL);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                ciudad = new Ciudad();
                ciudad.setId(rs.getInt("ID"));
                ciudad.setNombre(rs.getString("NOMBRE"));
                ciudad.setImagen(rs.getString("IMAGEN"));
                ciudad.setDescripcion(rs.getString("DESCRIPCION"));
                ciudad.setLink(rs.getString("LINK"));
                ciudad.setMapa(rs.getString("MAPA"));
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
        return ciudad;
    }

    // Método para obtener el número total de registros en la tabla CIUDAD
    public int getTotalRegistros() throws SQLException, Exception {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        int numeroRegistros = 0;

        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            st = con.createStatement();
            String ordenSQL = "SELECT COUNT(*) AS NUMEROREGISTROS FROM CIUDAD";
            rs = st.executeQuery(ordenSQL);
            rs.next();
            numeroRegistros = rs.getInt("NUMEROREGISTROS");
        } catch (SQLException se) {
            throw se;
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) con.close();
        }
        return numeroRegistros;
    }
}
