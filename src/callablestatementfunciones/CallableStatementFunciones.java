
package callablestatementfunciones;

import modelos.database;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import modelos.usuario;

public class CallableStatementFunciones {

    public static void main(String[] args) {
       // int empleadoId = 60; // indentificadora recuperar salario
     
        usuario u = new usuario();
        try {
            Connection con = database.getConnection();
            CallableStatement cstmt = null;
         
            u.consultarUsuarios();
           
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
