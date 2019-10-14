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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import modelos.GestorInterfaz;
import modelos.usuario;
import oracle.net.aso.e;
import static oracle.sql.NUMBER.e;
import vista.ModeloInicio;

/**
 *
 * @author Carmen
 */
public class controlador implements ActionListener,MouseListener  {

    usuario u = new usuario();
    GestorInterfaz g ;
    ModeloInicio vista ;
    
    
     public enum AccionMVC {
		__GOTO_USUARIOS,__MOSTRAR_USUARIO, __INSERTAR_LECTOR,__GOTO_CREARLECTOR, __ELIMINAR_USER,
                //-----------------------------------LIBROS----------------------------------------------\\\\\
               __MOSTRAR_LIBROS,
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
      
      this.vista.Buscar_usuer.setActionCommand("__MOSTRAR_USUARIO");
      this.vista.Buscar_usuer.addActionListener(this);
      
      this.vista.btn_eliminar.setActionCommand("__ELIMINAR_USER");
      this.vista.btn_eliminar.addActionListener(this);
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
                        String nombre=this.vista.NombrePersonatxt.getText();
                        String dni=this.vista.dnitext.getText();
                        String calle= this.vista.calletext.getText();
                        String ciudad=this.vista.ciudadtext.getText();
                       int cod_post=Integer.parseInt( this.vista.codig_postext.getText().trim());
                       int penalizacion= Integer.parseInt( this.vista.penalizacointxt.getText().trim());
             
                      u.crear_lector(nombre,dni, calle, ciudad, cod_post, penalizacion);
                    }catch(NumberFormatException a){
                      JOptionPane.showMessageDialog(null, "Datos incorrectos, por favor rellene todos los campos");
                      a.printStackTrace();
                 
	            } break;
                case __MOSTRAR_USUARIO:
                    String valor[]= new String [6];
                     int id= Integer.parseInt(vista.NCarnetEdit.getText()) ;
                    
                   valor=  u.listar_por_id(id);
                
             vista.NombrePersonatxt.setText(valor[0]);
              vista.dnitext.setText(valor[1]);
             vista.calletext.setText(valor[2]);
              vista.ciudadtext.setText(valor[3]);
              vista.codig_postext.setText(valor[4]);
              vista.penalizacointxt.setText(valor[5]);
         break;
                    
                case __ELIMINAR_USER:
                  
                 int ncarnet= Integer.parseInt(vista.text_eliminar.getText().trim());
                 
               
                    try {
                        u.eliminar_user(ncarnet);
                         System.out.print(ncarnet+"tres");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                
                  
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
