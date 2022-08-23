package Includes;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * 
 * @author Maja Devic
 *
 */
public class Factory {
	
	protected DataSource ds;

	/**
	 * Constructor
	 */
	public Factory() {
		try {
			InitialContext cxt = new InitialContext();
			if (cxt == null) {

			}
			ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/mysql");
			if (ds == null) {

			}
		} catch (NamingException e) {
            System.out.println(e.getMessage());
		}
	}
	
	
}
