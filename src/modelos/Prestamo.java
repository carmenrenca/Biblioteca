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
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Carmen
 */
public class Prestamo {
    
     Connection con;
      CallableStatement cstmt = null;
      
              public void Crear_Prestamo(int id_libro, int id_user, String clase){
                       try {

              con=database.getConnection();
              cstmt= con.prepareCall("{call CREAR_PRESTAMO(?,?,?)}");
                    cstmt.setInt(1, id_libro);
              cstmt.setInt(2, id_user);
       
              cstmt.setString(3, clase);
              cstmt.execute();
              cstmt.close();
              con.close();
          } catch (SQLException ex) {
            
          }
        
                   }
              
      public DefaultTableModel listar_prestamo(){
         String aux;
           int t=0;
                 String[] headers = { "ID_PRESTAMO","FECHA_INICIO","FECHA_LIMITE","ID_USUARIO","PENALIZADO","ID_LIBRO"};
                 DefaultTableModel tabla = new DefaultTableModel();
                    try {
              con=database.getConnection();
              this.cstmt=con.prepareCall("{call LISTAR_PRESTAMO.obtener_prestamos(?)}");
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
                 
                 int id_prestamo, id_user, id_libro, penalizado;
                 String fecha_inicio, f_entrega, f_devolucion;
                 
               
                 
          try {
              con=database.getConnection();
               this.cstmt=con.prepareCall("{call LISTAR_PRESTAMO.obtener_prestamos(?)}");
              cstmt.registerOutParameter(1, OracleTypes.CURSOR);
              cstmt.executeQuery();
              
              ResultSet cursor= (ResultSet) cstmt.getObject(1);
              int i=0;
              while(cursor.next()){
                  id_prestamo=cursor.getInt(1);
               
                  fecha_inicio=cursor.getString(2);
                
                  f_entrega= cursor.getString(3);
                  //f_devolucion=cursor.getString(4);
                  id_user=cursor.getInt(6);
           
                  id_libro=cursor.getInt(5);
                   penalizado=cursor.getInt(7);
                  
                
                  filas[i][0]=Integer.toString(id_prestamo);
                    filas[i][1]=fecha_inicio;
                  filas[i][2]=f_entrega;
                  //filas[i][3]=f_devolucion;
            
                  filas[i][5]=Integer.toString( id_libro);
                  //segun salga 1 o 0 vamos a poner si esta o no disponible ese libro en la tabla 
               
                  
                  filas[i][3]=Integer.toString( id_user);
                     if(penalizado==1){
                      aux =" ";
                  }else{
                        aux ="Penalizado";
                  }
                   filas[i][4]=aux;
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
               public DefaultComboBoxModel listarIDlibros(){
                     ArrayList<String> ids = new ArrayList<String>();
        try {
              con=database.getConnection();
               this.cstmt=con.prepareCall("{call CONSULTA_LIBROS.obtener_libros(?)}");
              cstmt.registerOutParameter(1, OracleTypes.CURSOR);
              cstmt.executeQuery();
               ResultSet cursor= (ResultSet) cstmt.getObject(1);
              int i=0;
              int id_prestamo;
            

             while(cursor.next()){
                      id_prestamo=cursor.getInt(1);
                      ids.add(Integer.toString(id_prestamo));
                  }
             cursor.close();
		    cstmt.close();
		    con.close();
           
          } catch (SQLException ex) {
            Logger.getLogger(Prestamo.class.getName()).log(Level.SEVERE, null, ex);
                          }
               
           return new DefaultComboBoxModel(ids.toArray());
               }
               
               
                   public DefaultComboBoxModel listarIDUsuarios(){
                     ArrayList<String> ids = new ArrayList<String>();
        try {
              con=database.getConnection();
               this.cstmt=con.prepareCall("{call CONSULTA_USER.obtener_usuarios(?)}");
              cstmt.registerOutParameter(1, OracleTypes.CURSOR);
              cstmt.executeQuery();
               ResultSet cursor= (ResultSet) cstmt.getObject(1);
              int i=0;
              int id_prestamo;
            

             while(cursor.next()){
                      id_prestamo=cursor.getInt(1);
                      ids.add(Integer.toString(id_prestamo));
                  }
             cursor.close();
		    cstmt.close();
		    con.close();
           
          } catch (SQLException ex) {
            Logger.getLogger(Prestamo.class.getName()).log(Level.SEVERE, null, ex);
                          }
               
           return new DefaultComboBoxModel(ids.toArray());
               }
           
                 public int listar_DIAS_CLASE(String clase){
           
                    String p;
             int dia = 0;
                    try{
                        con=database.getConnection();
              this.cstmt=con.prepareCall("{call SELECT_CLASES.obtener_clases(?)}");
              cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            
              cstmt.execute();
               cstmt.executeQuery();
       
           
                ResultSet cursor= (ResultSet) cstmt.getObject(1);
                  while(cursor.next()){
                   p=cursor.getString(1);
                   
             dia=cursor.getInt(2);
   
                if(p.equalsIgnoreCase(clase)){
                    break;
                }
              }
                
                cursor.close();
		    cstmt.close();
		    con.close();
                    }catch (SQLException ex){
                        
                    }
          return dia;
                    
                }
                 
          public int penalizaciones(int id_cliente, int id_libro){
              int retorno = 0;
         try {
                
                       ResultSet miResultSet = null;
             con=database.getConnection();
             int c= id_cliente;
             int l=id_libro;
             this.cstmt=con.prepareCall("{?=call DISPO_PENALIZACION("+c+","+l+")}");
             cstmt.registerOutParameter(1, Types.INTEGER);
        
           
             cstmt.executeQuery();
          
              retorno =cstmt.getInt(1);
       
         } catch (SQLException ex) {
             Logger.getLogger(Prestamo.class.getName()).log(Level.SEVERE, null, ex);
         }
         return  retorno;
          }
          
        public DefaultTableModel listar_historicos(){
         String aux;
           int t=0;
                 String[] headers = { "ID_PRESTAMO","FECHA_INICIO","FECHA_LIMITE","FECHA_ENTREGA","ID_USUARIO","ID_LIBRO"};
                 DefaultTableModel tabla = new DefaultTableModel();
                    try {
              con=database.getConnection();
              this.cstmt=con.prepareCall("{call VER_HISTORICO.obtener_historico(?)}");
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
                 
                 int id_prestamo, id_user, id_libro;
                 String fecha_inicio, f_entrega, f_limite;
                 
               
                 
          try {
              con=database.getConnection();
               this.cstmt=con.prepareCall("{call VER_HISTORICO.obtener_historico(?)}");
              cstmt.registerOutParameter(1, OracleTypes.CURSOR);
              cstmt.executeQuery();
              
              ResultSet cursor= (ResultSet) cstmt.getObject(1);
              int i=0;
              while(cursor.next()){
                  id_prestamo=cursor.getInt(1);
               
                  fecha_inicio=cursor.getString(2);
                   f_limite=cursor.getString(3);
                  f_entrega= cursor.getString(4);
               
                       id_libro=cursor.getInt(5);
                  id_user=cursor.getInt(6);
          
                  
                
                  filas[i][0]=Integer.toString(id_prestamo);
                    filas[i][1]=fecha_inicio;
                  filas[i][2]=f_limite;
                 filas[i][3]=f_entrega ;
             filas[i][4]=Integer.toString(id_user); 
                  filas[i][5]=Integer.toString(id_libro);
                  //segun salga 1 o 0 vamos a poner si esta o no disponible ese libro en la tabla 
               
           
                
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
    
