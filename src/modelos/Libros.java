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
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Carmen
 */
public class Libros extends database {
     Connection con;
      CallableStatement cstmt = null;
       public String[] listar_por_id_LIBRO(int id){
                   
                    String[] info = new String[6];
                    try{
                        con=database.getConnection();
              this.cstmt=con.prepareCall("{call SELECT_LIBRO_ID.obtener_libros_ID(?,?)}");
              cstmt.registerOutParameter(1, OracleTypes.CURSOR);
              cstmt.setInt(2, id);
              cstmt.execute();
               cstmt.executeQuery();
       
             int p;
                ResultSet cursor= (ResultSet) cstmt.getObject(1);
                  while(cursor.next()){
                   p=cursor.getInt(1);
                   System.out.print("id libro "+p);
             info[0]=cursor.getString(2);
               info[1]=cursor.getString(3);
                        
                 info[2]=cursor.getString(4);
                 info[3]=cursor.getString(5);
                
                info[4]=cursor.getString(6);
                info[5]=Integer.toString(cursor.getInt(7));
                System.out.print("1"+info[1]);
                if(p==id){
                    break;
                }
              }
                
                cursor.close();
		    cstmt.close();
		    con.close();
                    }catch (SQLException ex){
                        
                    }
          return info;
                    
                }  
      
      
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
               this.cstmt=con.prepareCall("{call CONSULTA_LIBROS.obtener_libros(?)}");
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
                System.out.print("id "+ID_LIBRO);
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
                ///////////metodo para crear un nuevo libro\\\\\\\\\\\\
               public void crear_libro( String titulo,String editorial, String nombre_autor, String apellido, String clase, int disponibilidad){
          try {
     
              con=database.getConnection();
              cstmt= con.prepareCall("{call CREAR_LIBRO(?,?,?,?,?,?)}");
                    cstmt.setString(1, titulo);
              cstmt.setString(2, editorial);
       
              cstmt.setString(3, nombre_autor);
              cstmt.setString(4, apellido);
              cstmt.setString(5, clase);
              cstmt.setInt(6, disponibilidad);
              cstmt.execute();
              cstmt.close();
              con.close();
          } catch (SQLException ex) {
              System.out.print("error "+ex);
          }
        
                  
}
                 public void eliminar_libro(int id_libro) throws SQLException {
           
              con=database.getConnection();
                System.out.print("id a eliminar "+id_libro);
              cstmt= con.prepareCall("{call ELIMINAR_LIBRO(?)}");
                cstmt.setInt(1, id_libro);
             	  
         cstmt.execute();
         cstmt.close();
	 con.close();
 
         } 
                
                 public DefaultComboBoxModel Listar_Clases(){
                     
                     ArrayList<String> ids = new ArrayList<String>();
        try {
              con=database.getConnection();
               this.cstmt=con.prepareCall("{call SELECT_CLASES.obtener_clases(?)}");
              cstmt.registerOutParameter(1, OracleTypes.CURSOR);
              cstmt.executeQuery();
               ResultSet cursor= (ResultSet) cstmt.getObject(1);
           
               String genero;
               

             while(cursor.next()){
                 genero=cursor.getString(1);
                  
                      ids.add(genero);
                  }
             cursor.close();
		    cstmt.close();
		    con.close();
           
          } catch (SQLException ex) {
            Logger.getLogger(Prestamo.class.getName()).log(Level.SEVERE, null, ex);
                          }
               
           return new DefaultComboBoxModel(ids.toArray());
                 }
                 
               public void devolver_libro(int id_libro, int id_prestamo){
         try {
             con=database.getConnection();
             this.cstmt=con.prepareCall("{call DEVOLVER_LIBRO(?,?)}");
               cstmt.setInt(1, id_libro);
               cstmt.setInt(2,id_prestamo);
              cstmt.executeQuery();
              JOptionPane.showMessageDialog(null,"Libro: "+id_libro+" Ha sido devuelto");
         } catch (SQLException ex) {
             Logger.getLogger(Libros.class.getName()).log(Level.SEVERE, null, ex);
         }
               
               }
}

