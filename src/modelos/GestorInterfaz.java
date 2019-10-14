/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.awt.Component;
import vista.ModeloInicio;

/**
 *
 * @author Carmen
 */

public class GestorInterfaz {
     ModeloInicio interfazes=null;
    
    public GestorInterfaz(ModeloInicio interfazes){
        this.interfazes=interfazes;
    };

  
    public void cambiarinterfazes(Component c){
        interfazes.PanelAPP.removeAll();
        interfazes.PanelAPP.add(c);
        interfazes.PanelAPP.repaint();
        interfazes.PanelAPP.revalidate();
        interfazes.pack();
    }
    
}
