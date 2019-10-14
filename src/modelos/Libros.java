/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Carmen
 */
public class Libros extends database {
     Connection con;
      CallableStatement cstmt = null;
              public DefaultTableModel listarlibros(){
                 int t=0;
                 String[] headers = { "ID_LIBRO","Titulo","Editorial","Nombre_Autor","Apellido","Clase","Disponibilidad" };
                 DefaultTableModel tabla = new DefaultTableModel();
                
          try {
              con=database.getConnection();
              this.cstmt=con.prepareCall("{call CONSULTA_LIBROS.obtener_libros(?)}");
              cstmt.registerOutParameter(1, OracleTypes.CURSOR);
              cstmt.executeQuery();
              
              ResultSet cursor= (ResultSet) cstmt.getObject(1);
              while(cursor.next()){
                  t++;
              }
               cursor.close();
		    cstmt.close();
		    con.close();
          } catch (SQLException ex) {
              Logger.getLogger(usuario.class.getName()).log(Level.SEVERE, null, ex);
          }
                 String[][] filas = new String[t][7];
                 
                 int ID_LIBRO,disponibilidad;
                 String titulo,editorial,nombre_autor,apellido,clase;
               
                 
          try {
              con=database.getConnection();
               this.cstmt=con.prepareCall("{call CONSULTA_USER.obtener_usuarios(?)}");
              cstmt.registerOutParameter(1, OracleTypes.CURSOR);
              cstmt.executeQuery();
              
              ResultSet cursor= (ResultSet) cstmt.getObject(1);
              int i=0;
              while(cursor.next()){
                  ID_LIBRO=cursor.getInt(1);
                  titulo=cursor.getString(3);
                  editorial= cursor.getString(2);
                  nombre_autor=cursor.getString(4);
                  apellido=cursor.getString(5);
                  clase=cursor.getString(6);
                  disponibilidad=cursor.getInt(7);
                
                  filas[i][0]=Integer.toString(ID_LIBRO);
                    filas[i][1]=titulo;
                  filas[i][2]=editorial;
                  filas[i][3]=nombre_autor;
                  filas[i][4]=apellido;
                  filas[i][5]=clase;
                  filas[i][6]=Integer.toString(disponibilidad);
                    i++;
              }
             cursor.close();
		    cstmt.close();
		    con.close();
              tabla.setDataVector(filas, headers);
          } catch (SQLException ex) {
              Logger.getLogger(usuario.class.getName()).log(Level.SEVERE, null, ex);
          }
             return tabla;
               }
               
}
