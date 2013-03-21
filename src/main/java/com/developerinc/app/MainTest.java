package com.developerinc.app;

import com.developerinc.config.*;

import com.developerinc.ds.*;
import com.mysql.jdbc.Connection;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;



public class MainTest {

	final static Logger logger = LoggerFactory.getLogger(MainTest.class);
	
	private final ConcurrentMap <String,Database> sources =  (ConcurrentMap<String, Database>) new XMLConfig("/resources/config.xml").getSources();
	private final String query [] = {"BEGIN execute immediate 'truncate table temp_hlab_activity_neph drop storage'; END;", 
    		"BEGIN execute immediate 'truncate table hlab_neph_results drop storage'; END;",
    		"insert into temp_hlab_activity_neph " + 
    		"SELECT  h.accession_no, h.requisition_id, SUBSTR (h.receiving_facility2, 0, 5) as receiving_facility2 " +
    		"FROM result_repuser.hlab_activity h " +
    		"WHERE SUBSTR (h.receiving_facility2, 0, 5) IN ('77004') " + 
            "AND h.last_update_time > ?", 
            "BEGIN SP_CREATE_NEPH(); END;",
            "DELETE FROM hlab_NEPH_results " + 
            "WHERE  hlab_NEPH_results.ORDER_METHOD IN ( 'MICRO','LAL','CC','ENV','ENVCH','STUDY') " +
            "OR LENGTH(accession_no) < 9 " +
            "OR textual_numeric_result IN ('PENDING', 'CANCELLED') " +
            "OR result_comments LIKE 'Conflicting or overlapping test%' " +
            "OR order_number IS NULL " +
            "OR test_code IN ('107J','108J','COLLB','BC','TNT','CC','CR','CS','101H','150H', " +
            "'200Z','98F','378','328','328A','328D','62H','125Q','80M','128Q','156Q','131Z', " +
            "'129Q','301V','18T','18U','104X','104Y','104Z','335X','770D','770L','770M', " +
            "'321','379','104O') " +
            "OR NPI NOT IN ('1982697298','1922091248','1588657886','1902808389', " +
            "'1710969688','18910222141','1538202064','1811943434') " +
            "OR NPI IS NULL", 
            "select to_char(systimestamp, 'dd-MON-yyyy hh.mi.ss.ff9 AM') from dual"};
	
	public static void main(String[] args) {
		new MainTest();

	}
	
	MainTest(){
				
		//testOracle("oracle_prod");
		testOracle("oracle_prod");
		
		
	}
	
	public void testOracle(String key){
		Statement stmt;
		ResultSet rs ;
		PreparedStatement ps [] = new PreparedStatement[6];
		
		Database db = sources.get(key);
		
		logger.debug(db.getUsername());
		
		
		AbstractDataSource ads = new DataSourceFactory();
		
		try {
			ads.setDatabaseBehavior(new OracleDBClass(db) );
			
			
			OracleConnection con = (OracleConnection) ads.getConnection();
			
			db = null;
			
			db = sources.get("mysql_njapp01");
			
			//ads.setDatabaseBehavior(new MySqlDBClass(db));
			
			//Connection mysql_con = (Connection) ads.getConnection();
			
			//logger.debug(mysql_con.getCatalog());
			
			
			logger.debug("$$$  Start Connection ::: " );	
			
		
			stmt = con.createStatement();
			
			ps[0] = con.prepareStatement(query[0]);
			ps[1] = con.prepareStatement(query[1]);
			
			ps[2] = con.prepareStatement(query[2]);
			ps[3] = con.prepareStatement(query[3]);
			ps[4] = con.prepareStatement(query[4]);
			ps[5] = con.prepareStatement(query[5]);
			
			//rs = stmt.executeQuery("select to_char(systimestamp, 'dd-MON-yyyy hh.mi.ss.ff9 AM') from dual");
			//rs = stmt.executeQuery("select '23-NOV-2012 06.32.48.050292000 PM' from dual");
			//while(rs.next()){
				//System.out.println(rs.getString(1));
			
			//28-JAN-2013 10.08.07.759016000 AM
				ps[2].setString(1, "20-FEB-2013 09.20.06.643556000 AM");
				
			//}
			
			//rs.close();
				
				
			rs = ps[5].executeQuery();
			while(rs.next())
				System.out.println("Server: " + rs.getString(1));
			
			for(int i = 0; i < 5; i++) ps[i].execute();
			
			//rs = stmt.executeQuery("SELECT SYS_CONTEXT ('USERENV', 'SERVER_HOST') FROM DUAL");
			
			
			rs.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
