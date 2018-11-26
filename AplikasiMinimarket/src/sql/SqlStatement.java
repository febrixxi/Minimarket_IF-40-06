
/* * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.SQLException;

/**
 *
 * @author KURING
 */
public final class SqlStatement {
    private Statement statement;
    
    public SqlStatement() throws SQLException{
        makeStatement();
    }
    
    public Statement makeStatement() throws SQLException{
        Connect c = new Connect();
        Connection conn = c.makeConnection();
        statement = (Statement) conn.createStatement();
        return statement;
    }
}
