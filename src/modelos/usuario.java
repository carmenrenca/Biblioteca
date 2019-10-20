/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import static java.awt.SystemColor.info;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Carmen
 */
public class usuario extends database{
      Connection con;
      CallableStatement cstmt = null;
         int Ncarnet;
	 String direccion;
	 String Nombre;
	 int penalizacion;
         String dni;
         public usuario(int Ncarnet, String dni, String direccion, String Nombre, int penalizacion) {
		super();
		this.Ncarnet=Ncarnet;
               this.dni=dni;
                this.direccion=direccion;
                this.Nombre=Nombre;
                this.penalizacion=penalizacion;
	}
         public usuario(){
             super();
         }

                public String[] listar_por_id(int id){
                   
                    String[] info = new String[6];
                    try{
                        con=database.getConnection();
              this.cstmt=con.prepareCall("{call SELECT_USER_ID.obtener_usuarios_ID(?,?)}");
              cstmt.registerOutParameter(1, OracleTypes.CURSOR);
              cstmt.setInt(2, id);
              cstmt.execute();
               cstmt.executeQuery();
       
             int p;
                ResultSet cursor= (ResultSet) cstmt.getObject(1);
                  while(cursor.next()){
                   p=cursor.getInt(1);
                   
             info[0]=cursor.getString(2);
              
          
                info[1]=cursor.getString(3);
         
                 info[2]=cursor.getString(4);
                 info[3]=cursor.getString(5);
                
                info[4]=Integer.toString(cursor.getInt(6));
                info[5]=Integer.toString(cursor.getInt(7));
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
         
               public DefaultTableModel listarlectores(){
                 int t=0;
                 String[] headers = { "Nº Carnet","DNI","Nombre","Calle","Ciudad","Cod_Post","Penalizacion" };
                 DefaultTableModel tabla = new DefaultTableModel();
                
          try {
              con=database.getConnection();
              this.cstmt=con.prepareCall("{call CONSULTA_USER.obtener_usuarios(?)}");
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
                 
                 int Ncarnet,cod_post,penalizacion;
                 String nombre, calle, ciudad,dni;
               
                 
          try {
              con=database.getConnection();
               this.cstmt=con.prepareCall("{call CONSULTA_USER.obtener_usuarios(?)}");
              cstmt.registerOutParameter(1, OracleTypes.CURSOR);
              cstmt.executeQuery();
              
              ResultSet cursor= (ResultSet) cstmt.getObject(1);
              int i=0;
              String aux = null;
              while(cursor.next()){
                  Ncarnet=cursor.getInt(1);
                  dni=cursor.getString(3);
                  nombre= cursor.getString(2);
                  calle=cursor.getString(4);
                  ciudad=cursor.getString(5);
                  cod_post=cursor.getInt(6);
                  penalizacion=cursor.getInt(7);
                
                  filas[i][0]=Integer.toString(Ncarnet);
                    filas[i][1]=dni;
                  filas[i][2]=nombre;
                  filas[i][3]=calle;
                  filas[i][4]=ciudad;
                  filas[i][5]=Integer.toString(cod_post);
                  if(penalizacion==0){
                      aux="Penalizado";
                  }else{
                      aux=" ";
                  }
                  filas[i][6]=aux;
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
               
               
            ///////////metodo para crear un nuevo lector\\\\\\\\\\\\
               public void crear_lector( String dni,String nombre, String calle, String ciudad, int cod_post){
          try {
              System.out.println("entraa "+dni+nombre+calle+ciudad+cod_post);
              con=database.getConnection();
              cstmt= con.prepareCall("{call CREAR_USER(?,?,?,?,?)}");
                    cstmt.setString(1, dni);
              cstmt.setString(2, nombre);
       
              cstmt.setString(3, calle);
              cstmt.setString(4, ciudad);
              cstmt.setInt(5, cod_post);
       
              cstmt.execute();
              cstmt.close();
              con.close();
          } catch (SQLException ex) {
              Logger.getLogger(usuario.class.getName()).log(Level.SEVERE, null, ex);
          }
                   
               }
               
               
           //modificar usuario
               
           public void Editar_User(String nombre, String DNI, String calle,String ciudad, int cod_post, int penalizacion){
                 try {
             
              con=database.getConnection();
              cstmt= con.prepareCall("{call EDITAR_USER(?,?,?,?,?,?)}");
              cstmt.setString(1, nombre);
              cstmt.setString(2, dni);
              cstmt.setString(3, calle);
              cstmt.setString(4, ciudad);
              cstmt.setInt(5, cod_post);
              cstmt.setInt(6, penalizacion);
              cstmt.execute();
              cstmt.close();
              con.close();
          } catch (SQLException ex) {
              Logger.getLogger(usuario.class.getName()).log(Level.SEVERE, null, ex);
          }
           }    
         public void eliminar_user(int ncarnet)throws SQLException {
           
              con=database.getConnection();
              cstmt= con.prepareCall("{call ELIMINAR_USER(?)}");
                cstmt.setInt(1, ncarnet);

         cstmt.execute();
         cstmt.close();
	 con.close();
            listarlectores();

         }
         public void despenalizar(int id){
          try {
              con=database.getConnection();
              cstmt= con.prepareCall("{call DESPENALIZAR(?)}");
              cstmt.setInt(1, id);
              
              cstmt.execute();
              cstmt.close();
              con.close();
              listarlectores();
          } catch (SQLException ex) {
              Logger.getLogger(usuario.class.getName()).log(Level.SEVERE, null, ex);
          }
         }
                
}
