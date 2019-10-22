/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.GestorInterfaz;
import modelos.Libros;
import modelos.Prestamo;
import modelos.usuario;
import oracle.net.aso.e;
import oracle.net.aso.i;
import static oracle.sql.NUMBER.e;
import vista.ModeloInicio;

/**
 *
 * @author Carmen
 */
public class controlador implements ActionListener,MouseListener  {

    usuario u = new usuario();
    Libros l = new Libros();
    GestorInterfaz g ;
    ModeloInicio vista ;
    Prestamo p= new Prestamo();
    
     public enum AccionMVC {
		__GOTO_USUARIOS,__MOSTRAR_USUARIO, __INSERTAR_LECTOR,__GOTO_CREARLECTOR, __ELIMINAR_USER,__EDITAR_USER,
                //-----------------------------------LIBROS----------------------------------------------\\\\\
               __MOSTRAR_LIBROS, __CREAR_LIBROS, __ELIMINAR_LIBROS, __VISUALIZAR_LIBRO_ID,
               //----------------------------------PRESTAMO----------------------------
               __PRESTAMO, __IR_CREAR_PRESTAMO, __NUEVO_PRESTAMO, MOSTRAR_USER_P, MOSTRAR_LIBROS_P, 
               __MODIFICA_PRESTAMO, __DEVOLVER,__VER_HISTORICO,
              // ------------------------------volver-------------------------
               __VOLVER_INICIO, __VOLVER_PRESTAMO, __DESPENALIZAR,
                       
		}
    public controlador(ModeloInicio v){
        this.vista= v;
        this.g= new GestorInterfaz(v);
    }
   
        public void iniciar(){
           // Skin tipo WINDOWS
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {}
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}
 //declara una acción y añade un escucha al evento producido por el componente
     
      this.vista.bt_visualizar_usuario.setActionCommand("__GOTO_USUARIOS");
      this.vista.bt_visualizar_usuario.addActionListener(this);
      
   
      
      this.vista.btnInsertlector.setActionCommand("__INSERTAR_LECTOR");
      this.vista.btnInsertlector.addActionListener(this);
      
      this.vista.Visualizar.setActionCommand("__MOSTRAR_USUARIO");
      this.vista.Visualizar.addActionListener(this);
      
      this.vista.btn_eliminar_l.setActionCommand("__ELIMINAR_USER");
      this.vista.btn_eliminar_l.addActionListener(this);
      
      this.vista.bt_visualizar_LIBROS.setActionCommand("__MOSTRAR_LIBROS");
      this.vista.bt_visualizar_LIBROS.addActionListener(this);
      
      this.vista.btnInsertlibro.setActionCommand("__CREAR_LIBROS");
      this.vista.btnInsertlibro.addActionListener(this);
      
      this.vista.btn_eliminar_libro.setActionCommand("__ELIMINAR_LIBROS");
      this.vista.btn_eliminar_libro.addActionListener(this);
       this.vista.btn_ir_PanelPrestamo.setActionCommand("__PRESTAMO");
       this.vista.btn_ir_PanelPrestamo.addActionListener(this);
       
       this.vista.BTN_NUEVO_PRESTAMO.setActionCommand("__IR_CREAR_PRESTAMO");
       this.vista.BTN_NUEVO_PRESTAMO.addActionListener(this);
    
       this.vista.ID_USUARIOS_P.setActionCommand("MOSTRAR_USER_P");
       this.vista.ID_USUARIOS_P.addActionListener(this);
       
       this.vista.btn_editar_User.setActionCommand("__EDITAR_USER");
       this.vista.btn_editar_User.addActionListener(this);
       
       this.vista.ID_LIBRO_P.setActionCommand("MOSTRAR_LIBROS_P");
       this.vista.ID_LIBRO_P.addActionListener(this);
       
       this.vista.btn_crear_prestamo.setActionCommand("__NUEVO_PRESTAMO");
       this.vista.btn_crear_prestamo.addActionListener(this);
       
       this.vista.Modi_Prestamo.setActionCommand("__MODIFICA_PRESTAMO");
       this.vista.Modi_Prestamo.addActionListener(this);
       this.vista.devolucion_libro.setActionCommand("__DEVOLVER");
       this.vista.devolucion_libro.addActionListener(this);
       
       this.vista.btn_ver_historicos.setActionCommand("__VER_HISTORICO");
       this.vista.btn_ver_historicos.addActionListener(this);
       
       this.vista.VOLVER_LIBRO.setActionCommand("__VOLVER_INICIO");
       this.vista.VOLVER_LIBRO.addActionListener(this);
       
       this.vista.VOLVER_P.setActionCommand("__VOLVER_PRESTAMO");
       this.vista.VOLVER_P.addActionListener(this);
       
       this.vista.Volver_inicioP.setActionCommand("__VOLVER_INICIO");
       this.vista.Volver_inicioP.addActionListener(this);
       
       this.vista.VOLVER_USER.setActionCommand("__VOLVER_INICIO");
       this.vista.VOLVER_USER.addActionListener(this);
       
       this.vista.Volver_Crear_Prestamo.setActionCommand("__VOLVER_PRESTAMO");
       this.vista.Volver_Crear_Prestamo.addActionListener(this);
       
       this.vista.BTM_DESPENALIZAR.setActionCommand("__DESPENALIZAR");
       this.vista.BTM_DESPENALIZAR.addActionListener(this);
       
       this.vista.Buscar_libro.setActionCommand("__VISUALIZAR_LIBRO_ID");
       this.vista.Buscar_libro.addActionListener(this);
               }
      
 
        	@Override
	public void actionPerformed(ActionEvent e) {
		switch (AccionMVC.valueOf(e.getActionCommand())) {
		
		case __GOTO_USUARIOS:
                 
                     g.cambiarinterfazes(vista.pUsuario);
                
                this.vista.tabla_usuario.setModel(u.listarlectores());
                break;
               
			
			
                case __INSERTAR_LECTOR:
                  
               
                    try{
                        String nombre=this.vista.nombre_usuariotxt.getText();
                        String dni=this.vista.dnitext.getText();
                        String calle= this.vista.calletxt.getText();
                        String ciudad=this.vista.ciudadtext.getText();
                       int cod_post=Integer.parseInt( this.vista.codig_postext.getText().trim());
                     
             
                      u.crear_lector(nombre,dni, calle, ciudad, cod_post);
                    }catch(NumberFormatException a){
                      JOptionPane.showMessageDialog(null, "Datos incorrectos, por favor rellene todos los campos");
                      a.printStackTrace();
                 
	            } 
                    g.cambiarinterfazes(vista.pUsuario);
                
                this.vista.tabla_usuario.setModel(u.listarlectores());
                    break;
                case __MOSTRAR_USUARIO:
                    String valor[]= new String [6];
                     int id= Integer.parseInt(vista.NCARNETtxt.getText()) ;
                    
                   valor=  u.listar_por_id(id);
                
             vista.nombre_usuariotxt.setText(valor[0]);
              vista.dnitext.setText(valor[1]);
             vista.calletxt.setText(valor[2]);
              vista.ciudadtext.setText(valor[3]);
              vista.codig_postext.setText(valor[4]);
          
         break;
                case __EDITAR_USER:
                    String nombre, calle,dni,ciudad;
                    int cod_pos, id_us;
                    
                    nombre=vista.nombre_usuariotxt.getText();
                    calle=vista.calletxt.getText();
                    dni=vista.dnitext.getText();
                    ciudad=vista.ciudadtext.getText();
                    cod_pos=Integer.parseInt(vista.codig_postext.getText());
                    id_us=Integer.parseInt(vista.NCARNETtxt.getText());
                    u.Editar_User(nombre, dni, calle, ciudad, cod_pos, id_us);
                      this.vista.tabla_usuario.setModel(u.listarlectores());
                    break;
                case __ELIMINAR_USER:
                  
                 int ncarnet= Integer.parseInt(vista.text_eliminar_libro.getText().trim());
                 
               
                    try {
                        u.eliminar_user(ncarnet);
                     
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                
                  g.cambiarinterfazes(vista.pUsuario);
                
                this.vista.tabla_usuario.setModel(u.listarlectores());
                    break;
                case __DESPENALIZAR:
      
                   u.despenalizar(Integer.parseInt( vista.txt_despenalizar_id.getText()));
                     this.vista.tabla_usuario.setModel(u.listarlectores());
                    break;
              /////////////////////libro\\\\\\\\\\\\\\\\
                case __MOSTRAR_LIBROS:
                    g.cambiarinterfazes(vista.pLIBROS);
                    this.vista.clases_libro_combo.setModel(l.Listar_Clases());
                     this.vista.tabla_libro.setModel( l.listarlibros());
                    
                    break;
                    
                case __VISUALIZAR_LIBRO_ID:
                       String valor_l[]= new String [6];
                    int id_l_m;
                   id_l_m= Integer.parseInt(vista.id_librotxt.getText());
                  valor_l= l.listar_por_id(id_l_m);
                   vista.titulotxt.setText(valor_l[0]);
                   vista.nombre_autortxt.setText(valor_l[2]);
                   vista.apellido_autor_txt.setText(valor_l[3]);
                   vista.editorialtxt.setText(valor_l[1]);
                   vista.clases_libro_combo.setSelectedItem(valor_l[4]);
                           
                  vista.disponibilidadtxt.setText(valor_l[5]);
                    break;
                case  __CREAR_LIBROS:
                    String titulo= vista.titulotxt.getText();
                    String editorial= vista.editorialtxt.getText();
                    String nombre_autor= vista.nombre_autortxt.getText();
                    String apellido= vista.apellido_autor_txt.getText();
                    String clase = (String) vista.clases_libro_combo.getSelectedItem();
                    int disponibilidad= Integer.parseInt( vista.disponibilidadtxt.getText());
                    l.crear_libro(titulo, editorial, nombre_autor, apellido, clase, disponibilidad);
                    g.cambiarinterfazes(vista.pLIBROS);
                     this.vista.tabla_libro.setModel( l.listarlibros());
                    break;
                case __ELIMINAR_LIBROS:
                    int iden= Integer.parseInt(vista.text_eliminarlibro.getText());
                
                    try {
                        l.eliminar_libro(iden);
                        
                    } catch (SQLException ex) {
                        
                        Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                           g.cambiarinterfazes(vista.pLIBROS);
                     this.vista.tabla_libro.setModel( l.listarlibros());
                     
                    break;
                    
                case __PRESTAMO:
                    g.cambiarinterfazes(vista.P_Prestamo);
                 
                 vista.T_Prestamo.setModel( p.listar_prestamo());
           
         
                    break;
                case  __IR_CREAR_PRESTAMO:
     
                    Date inicio = new Date(); 
                    g.cambiarinterfazes(vista.Crear_Prestamo);
             DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                    vista.fecha_iniciio.setText(hourdateFormat.format(inicio));
                        vista.ID_LIBRO_P.setModel(p.listarIDlibros());
                        
                        this.vista.ID_USUARIOS_P.setModel(p.listarIDUsuarios());
                       
                       
                    break;
                case MOSTRAR_USER_P:
                    String valor_User[]= new String [6];
   
                    int id_usuario;
                     id_usuario=Integer.parseInt((String)this.vista.ID_USUARIOS_P.getSelectedItem()) ;
                   
                     valor_User=u.listar_por_id(id_usuario);
                      vista.DNI_P.setText(valor_User[1]);
                      vista.Nombre_U_P.setText(valor_User[0]);
                      vista.Id_userP.setText(Integer.toString(id_usuario));
                    break;
                case MOSTRAR_LIBROS_P:
                    String valor_libro[]= new String [7];
                     Date sumdate = new Date(); 
                     int id_libro = 0;
                     id_libro=Integer.parseInt((String)this.vista.ID_LIBRO_P.getSelectedItem()) ;
                    
                    valor_libro=l.listar_por_id_LIBRO(id_libro);
                      vista.Clase_Libro_P.setText(valor_libro[4]);
                      vista.Titulo_P.setText(valor_libro[0]);
                      String clase_l= vista.Clase_Libro_P.getText();
                     vista.Id_libroP.setText(Integer.toString(id_libro));
                      vista.dias_prestamo.setText(Integer.toString(p.listar_DIAS_CLASE(clase_l)));
                      int d;
                d=p.listar_DIAS_CLASE(valor_libro[4]);
          
                                Calendar calendar = Calendar.getInstance();
              calendar.setTime(sumdate);
              calendar.add(Calendar.DAY_OF_YEAR , d );
              vista.fecha_FinPrestamo.setText(calendar.getTime().toString());
          
                    break;
                case __NUEVO_PRESTAMO:
                   
              int id_u;
              int id_l; 
              String c;
              id_u=Integer.parseInt((String)vista.ID_USUARIOS_P.getSelectedItem()) ;
              id_l=Integer.parseInt((String)vista.ID_LIBRO_P.getSelectedItem());
              c=vista.Clase_Libro_P.getText();
              int dis= p.penalizaciones(id_l, id_u);
                    System.out.println("diss "+dis);
              if(dis==0){
                  JOptionPane.showMessageDialog(vista,"No Se Puede Crear El Prestamo Solicitado");
              }else{
                  p.Crear_Prestamo(id_l, id_u, c);
              } 
           
      g.cambiarinterfazes(vista.P_Prestamo);
                 
                 vista.T_Prestamo.setModel( p.listar_prestamo());
            
                    break;
                    
                case __MODIFICA_PRESTAMO:
                    String [] arrayP = new String[7];
                 
                    String id_prestamo=  vista.text_pres_modifi.getText();
               
                    int fila= vista.T_Prestamo.getRowCount();
                    for(int i=0 ; i< fila ; ){
                    if(vista.T_Prestamo.getValueAt(i, 0).equals(id_prestamo)){
                      arrayP[0]=id_prestamo;
                      arrayP[1]=vista.T_Prestamo.getValueAt(i, 1).toString();
                      arrayP[2]=vista.T_Prestamo.getValueAt(i, 2).toString();
                      arrayP[3]=vista.T_Prestamo.getValueAt(i, 3).toString();
                      arrayP[4]=vista.T_Prestamo.getValueAt(i, 4).toString();
                      arrayP[5]=vista.T_Prestamo.getValueAt(i, 5).toString();
                    
                      //COGEMOS LOS DATOS DEL CLIENTE MEDIANTE EL PROCEDIMIENTO LISTAR_POR_ID
                       valor_User=u.listar_por_id(Integer.parseInt(arrayP[3]));
                       vista.ID_PRESTAMOTEXT.setText(id_prestamo);
                      vista.DNI_P.setText(valor_User[1]);
                      vista.Nombre_U_P.setText(valor_User[0]);
                       //COGEMOS LOS DATOS DEL LIBRO MEDIANTE EL PROCEDIMIENTO LISTAR_POR_ID
                      valor_libro=l.listar_por_id_LIBRO(Integer.parseInt(arrayP[5]));
                      vista.Clase_Libro_P.setText(valor_libro[4]);
                      vista.Titulo_P.setText(valor_libro[0]);
                         vista.fecha_iniciio.setText(arrayP[1]);
                    vista.fecha_FinPrestamo.setText(arrayP[2]);
                    vista.Id_libroP.setText(Integer.toString( Integer.parseInt(arrayP[5])));
                    vista.Id_userP.setText(arrayP[3]);
                   
                    vista.ID_LIBRO_P.setVisible(false);
                    vista.ID_USUARIOS_P.setVisible(false);
                    vista.dias_prestamo.setVisible(false);
                    vista.jLabel11.setVisible(false);
                    vista.btn_crear_prestamo.setVisible(false);
                    g.cambiarinterfazes(vista.Crear_Prestamo);
                     vista.ID_LIBRO_P.setVisible(true);
                    vista.ID_USUARIOS_P.setVisible(true);
                    vista.dias_prestamo.setVisible(true);
                    vista.jLabel11.setVisible(true);
                    vista.btn_crear_prestamo.setVisible(true);
                 
                    }
                    i++;
                }
                    break;
                    
                case __VER_HISTORICO:
                    vista.T_HISTORICOS.setModel(p.listar_historicos());
                    g.cambiarinterfazes(vista.P_Historicos);
                    break;
                case __DEVOLVER:
                    System.out.println("id prestamo: "+vista.ID_PRESTAMOTEXT.getText());
           
                 l.devolver_libro(Integer.parseInt(vista.Id_libroP.getText()) ,Integer.parseInt(vista.ID_PRESTAMOTEXT.getText()) );
                 g.cambiarinterfazes(vista.P_Prestamo);
                    vista.T_Prestamo.setModel( p.listar_prestamo());
                    break;
                case __VOLVER_PRESTAMO:
                    g.cambiarinterfazes(vista.P_Prestamo);
                    break;
                case  __VOLVER_INICIO:
                    g.cambiarinterfazes(vista.Incio);
                    break;
                }
                
                
        }
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
    
}
