package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import entidades.Punto;

public class DaoPunto {

    // Constructor vacío
    public DaoPunto() {}

    // Método para obtener todos los puntos de la tabla PUNTO
    public List<Punto> listadoPuntos() throws SQLException, Exception {
        List<Punto> listaPuntos = new ArrayList<Punto>();
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;

        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            st = con.createStatement();
            String ordenSQL = "SELECT * FROM PUNTO ORDER BY ID";
            rs = st.executeQuery(ordenSQL);

            while (rs.next()) {
                Punto miPunto = new Punto();
                miPunto.setId(rs.getInt("ID"));
                miPunto.setRuta(rs.getInt("RUTA"));
                miPunto.setPuntos(rs.getInt("PUNTOS"));
                listaPuntos.add(miPunto);
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
        return listaPuntos;
    }

    // Método para insertar un nuevo punto en la tabla PUNTO
    public void insertaPunto(Punto p) throws SQLException, Exception {
        Connection con = null;
        PreparedStatement st = null;

        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            con.setAutoCommit(false);
            String ordenSQL = "INSERT INTO PUNTO (ID, RUTA, PUNTOS) VALUES (S_PUNTO.NEXTVAL, ?, ?)";
            st = con.prepareStatement(ordenSQL);
            st.setInt(1, p.getRuta());
            st.setInt(2, p.getPuntos());
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

    // Método para buscar un punto por ID en la tabla PUNTO
    public Punto findPuntoById(int id) throws SQLException, Exception {
        Punto punto = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "SELECT * FROM PUNTO WHERE ID = ?";
            st = con.prepareStatement(ordenSQL);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                punto = new Punto();
                punto.setId(rs.getInt("ID"));
                punto.setRuta(rs.getInt("RUTA"));
                punto.setPuntos(rs.getInt("PUNTOS"));
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
        return punto;
    }

    // Método para obtener el número total de registros en la tabla PUNTO
    public int getTotalRegistros() throws SQLException, Exception {

        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        int numeroRegistros = 0;

        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            st = con.createStatement();
            String ordenSQL = "SELECT COUNT(*) AS NUMEROREGISTROS FROM PUNTO";
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

    public double calculaMediaPuntos(int idRuta) throws SQLException, Exception {
        double media = 0;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Conexion miconex = new Conexion();
            con = miconex.getConexion();
            String ordenSQL = "SELECT AVG(puntos) FROM PUNTO WHERE ruta = ?";
            st = con.prepareStatement(ordenSQL);
            st.setInt(1, idRuta);
            rs = st.executeQuery();

            if (rs.next()) {
                media = rs.getDouble(1);
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
        return media;
    }
    


}
