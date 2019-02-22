package second;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
 


import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class MatchingEngine {
	public synchronized static void matchUpExits(PrintWriter out){
		
		 ArrayList <UnmatchedProtectExit>uPList = new ArrayList<UnmatchedProtectExit>();
		 ArrayList <UnmatchedTradeExit>uTList = new ArrayList<UnmatchedTradeExit>();
		 ArrayList <Protect>protectList2 = new ArrayList<Protect>();
		 ArrayList <Trade>tradeList2 = new ArrayList<Trade>();


		 final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   final String DB_URL="jdbc:mysql://3.16.62.1307/cl";

		   //  Database credentials
		   final String USER = "cl";
		  final String PASS = "Ra.cQH&&ZrFG(44e)Uf";
		  Connection conn=null;
		  Statement stmt=null;
		  try {
		     // Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		     // Open a connection
		     conn = DriverManager.getConnection(DB_URL, USER, PASS);

		     // Execute SQL query
		     stmt = conn.createStatement();
		     String sql="Select * from unmatchedexitprotect ;";
	         ResultSet rs = stmt.executeQuery(sql);
	         String javaCalcLogic="";
	         // Extract data from result set
	         while(rs.next()){
	            //Retrieve by column name
	            int id  = rs.getInt("id");
	            //int age = rs.getInt("age");
	            String email = rs.getString("email");
	            //String password = rs.getString("password");
	           
	            double amount = rs.getDouble("amount");
	            double trade_side = rs.getDouble("trade_side");
	            int match_id  = rs.getInt("match_id");
	            UnmatchedProtectExit upe = new UnmatchedProtectExit(id,  email,
	    				 amount, match_id, trade_side);
	            uPList.add(upe);
	         }
	         rs.close();
		     sql="Select * from unmatchedexittrade ;";
	          rs = stmt.executeQuery(sql);
	     
	         // Extract data from result set
	         while(rs.next()){
	        	 //Retrieve by column name
		            int id  = rs.getInt("id");
		            //int age = rs.getInt("age");
		            String email = rs.getString("email");
		            //String password = rs.getString("password");
		           
		            double amount = rs.getDouble("amount");
		            double 	protect_side = rs.getDouble("protect_side");
		            int match_id  = rs.getInt("match_id");
		            UnmatchedTradeExit ute = new UnmatchedTradeExit(id,  email,
		    				 amount, match_id, 	protect_side);
		            uTList.add(ute);
	         }
	         rs.close();
	         ArrayList <Pair>matchIdList = new ArrayList<Pair>();
	         double uTradeTotal=0;
	         double uProtectTotal=0;
	         for(UnmatchedTradeExit ute: uTList)
	        	 for(UnmatchedProtectExit upe: uPList){
	        		 if(ute.protect_side>=upe.trade_side*3){//protect side is smaller than trade side
	        			 
	        			 //----ute trade----------
		        		 sql="select * from matched where match_id = "+ute.match_id+" ;";
		        		 rs = stmt.executeQuery(sql);
		        		 rs.next();
		        		 int match_id = rs.getInt("match_id");
				    	 int protect_id = rs.getInt("protect_id");
				    	 int trade_id = rs.getInt("trade_id");
				    	 String protect_email = rs.getString("protect_email");
				    	 String trade_email = rs.getString("trade_email");
				    	 double trade_amount_matched = rs.getDouble("trade_amount_matched");
				    	 double protect_amount_matched =rs.getDouble("protect_amount_matched");
				    	 double bitcion_price_at_match =rs.getDouble("bitcion_price_at_match");
				    	 String time_stamp = rs.getString("time_stamp");
				    	 rs.close();
				    	 //---------upe trade----------------------------
		        		 sql="select * from matched where match_id = "+ute.match_id+" ;";
		        		 rs = stmt.executeQuery(sql);
		        		 rs.next();
		        		 int pmatch_id = rs.getInt("match_id");
				    	 int pprotect_id = rs.getInt("protect_id");
				    	 int ptrade_id = rs.getInt("trade_id");
				    	 String pprotect_email = rs.getString("protect_email");
				    	 String ptrade_email = rs.getString("trade_email");
				    	 double ptrade_amount_matched = rs.getDouble("trade_amount_matched");
				    	 double pprotect_amount_matched =rs.getDouble("protect_amount_matched");
				    	 double pbitcion_price_at_match =rs.getDouble("bitcion_price_at_match");
				    	 String ptime_stamp = rs.getString("time_stamp");
				    	 rs.close();
				    	 //create new orders
                         double a =trade_amount_matched;
                         double c=protect_amount_matched;
                         double b=ptrade_amount_matched;
                         double d = pprotect_amount_matched;
                         a=a-(d/3);
                         double temp=c;
                         c=3*a;
                         d=temp-c;
					    	 double bitCoinValue=getBitCoinPrice(out);
					          sql="INSERT INTO matched ( match_id, protect_email, trade_email, protect_id, trade_id, 	trade_amount_matched, protect_amount_matched, time_stamp, bitcion_price_at_match ) values (0, '"+protect_email+"', '"+trade_email+"', "+protect_id+", "+ptrade_id+", "+a+", "+c+", NULL, "+bitCoinValue+"  );";
				              stmt.executeUpdate(sql); 
					          sql="INSERT INTO matched ( match_id, protect_email, trade_email, protect_id, trade_id, 	trade_amount_matched, protect_amount_matched, time_stamp, bitcion_price_at_match ) values (0, '"+protect_email+"', '"+ptrade_email+"', "+protect_id+", "+ptrade_id+", "+b+", "+d+", NULL, "+bitCoinValue+"  );";
				              stmt.executeUpdate(sql); 
			        		//refund users their bitcoin-----------------------
				              //trade exit user
				              sql = "SELECT btc_holdings FROM users where email = '"+ trade_email + "'  ;" ;
				    	       rs = stmt.executeQuery(sql);				    	      
				    	       rs.next();
				    	       double btc_holdings = rs.getDouble("btc_holdings");
				    	       rs.close();
				              sql = "UPDATE users " +
								"SET btc_holdings = "+(btc_holdings+(pprotect_amount_matched/3))+" "+
								"  where email = '"+ trade_email + "' ;" ;
					          stmt.executeUpdate(sql);
					          out.println("<h3>returning "+(pprotect_amount_matched/3)+trade_email+"</h3>");
					          ////protect exit user
				              sql = "SELECT btc_holdings FROM users where email = '"+ pprotect_email + "'  ;" ;
				    	       rs = stmt.executeQuery(sql);				    	      
				    	       rs.next();
				    	       btc_holdings = rs.getDouble("btc_holdings");
				    	       rs.close();	
				    	       out.println("<h3>returning "+pprotect_amount_matched+pprotect_email+"</h3>");
				              sql = "UPDATE users " +
								"SET btc_holdings = "+(btc_holdings+(pprotect_amount_matched))+" "+
								"  where email = '"+ pprotect_email + "' ;" ;
					          stmt.executeUpdate(sql);
					          //end refunds------------------------------------------
					          //invaldate orignal orders
						      sql = "UPDATE matched " +
									"SET finished = 1 "+
									"  where match_id = "+ match_id + " ;" ;
						      stmt.executeUpdate(sql);
							  sql = "UPDATE matched " +
										"SET finished = 1 "+
										"  where match_id = "+ pmatch_id + " ;" ;
					          stmt.executeUpdate(sql);
					    	 
						      sql= "DELETE FROM unmatchedexitprotect WHERE id="+upe.id+" ;";
						      stmt.executeUpdate(sql);
						      sql="UPDATE unmatchedexittrade set amount="+a+" where id="+ute.id+" ;";
						      stmt.executeUpdate(sql);
	        		 }
	        		 if(ute.protect_side==upe.trade_side*3){//protect side is smaller than trade side
	        			 
	        			 //----ute trade----------
		        		 sql="select * from matched where match_id = "+ute.match_id+" ;";
		        		 rs = stmt.executeQuery(sql);
		        		 rs.next();
		        		 int match_id = rs.getInt("match_id");
				    	 int protect_id = rs.getInt("protect_id");
				    	 int trade_id = rs.getInt("trade_id");
				    	 String protect_email = rs.getString("protect_email");
				    	 String trade_email = rs.getString("trade_email");
				    	 double trade_amount_matched = rs.getDouble("trade_amount_matched");
				    	 double protect_amount_matched =rs.getDouble("protect_amount_matched");
				    	 double bitcion_price_at_match =rs.getDouble("bitcion_price_at_match");
				    	 String time_stamp = rs.getString("time_stamp");
				    	 rs.close();
				    	 //---------upe trade----------------------------
		        		 sql="select * from matched where match_id = "+ute.match_id+" ;";
		        		 rs = stmt.executeQuery(sql);
		        		 rs.next();
		        		 int pmatch_id = rs.getInt("match_id");
				    	 int pprotect_id = rs.getInt("protect_id");
				    	 int ptrade_id = rs.getInt("trade_id");
				    	 String pprotect_email = rs.getString("protect_email");
				    	 String ptrade_email = rs.getString("trade_email");
				    	 double ptrade_amount_matched = rs.getDouble("trade_amount_matched");
				    	 double pprotect_amount_matched =rs.getDouble("protect_amount_matched");
				    	 double pbitcion_price_at_match =rs.getDouble("bitcion_price_at_match");
				    	 String ptime_stamp = rs.getString("time_stamp");
				    	 rs.close();
				    	 //create new orders
                         double a =trade_amount_matched;
                         double c=protect_amount_matched;
                         double b=ptrade_amount_matched;
                         double d = pprotect_amount_matched;
                        
					    	 double bitCoinValue=getBitCoinPrice(out);
					          sql="INSERT INTO matched ( match_id, protect_email, trade_email, protect_id, trade_id, 	trade_amount_matched, protect_amount_matched, time_stamp, bitcion_price_at_match ) values (0, '"+protect_email+"', '"+ptrade_email+"', "+protect_id+", "+ptrade_id+", "+trade_amount_matched+", "+protect_amount_matched+", NULL, "+bitCoinValue+"  );";
				              stmt.executeUpdate(sql); 
			        		//refund users their bitcoin-----------------------
				              //trade exit user
				              sql = "SELECT btc_holdings FROM users where email = '"+ trade_email + "'  ;" ;
				    	       rs = stmt.executeQuery(sql);				    	      
				    	       rs.next();
				    	       double btc_holdings = rs.getDouble("btc_holdings");
				    	       rs.close();
				              sql = "UPDATE users " +
								"SET btc_holdings = "+(btc_holdings+trade_amount_matched)+" "+
								"  where email = '"+ trade_email + "' ;" ;
					          stmt.executeUpdate(sql);
					          
					          ////protect exit user
				              sql = "SELECT btc_holdings FROM users where email = '"+ pprotect_email + "'  ;" ;
				    	       rs = stmt.executeQuery(sql);				    	      
				    	       rs.next();
				    	       btc_holdings = rs.getDouble("btc_holdings");
				    	       rs.close();					          
				              sql = "UPDATE users " +
								"SET btc_holdings = "+(btc_holdings+(pprotect_amount_matched))+" "+
								"  where email = '"+ pprotect_email + "' ;" ;
					          stmt.executeUpdate(sql);
					          //end refunds------------------------------------------
					          //invaldate orignal orders
						      sql = "UPDATE matched " +
									"SET finished = 1 "+
									"  where match_id = "+ match_id + " ;" ;
						      stmt.executeUpdate(sql);
							  sql = "UPDATE matched " +
										"SET finished = 1 "+
										"  where match_id = "+ pmatch_id + " ;" ;
					          stmt.executeUpdate(sql);
					    	 
						      sql= "DELETE FROM unmatchedexittrade WHERE id="+ute.id+" ;";
						      stmt.executeUpdate(sql);
						      sql="DELETE FROM unmatchedexitprotect WHERE id="+upe.id+" ;";
						      stmt.executeUpdate(sql);
	        		 }

	        	 }
	        
	        	 for(UnmatchedProtectExit upe: uPList)
	        		 for(UnmatchedTradeExit ute: uTList){
		        		 if(upe.trade_side*3>ute.protect_side){//trade side is smaller than protect side
		        			/* Pair pair = new Pair(ute, upe);
		        			 matchIdList.add(pair);
		        			 ute.protect_side=0;
		        			 upe.trade_side-=ute.protect_side/3;
		        			 */
		        			 //----ute trade----------
			        		 sql="select * from matched where match_id = "+ute.match_id+" ;";
			        		 rs.next();
			        		 int match_id = rs.getInt("match_id");
					    	 int protect_id = rs.getInt("protect_id");
					    	 int trade_id = rs.getInt("trade_id");
					    	 String protect_email = rs.getString("protect_email");
					    	 String trade_email = rs.getString("trade_email");
					    	 double trade_amount_matched = rs.getDouble("trade_amount_matched");
					    	 double protect_amount_matched =rs.getDouble("protect_amount_matched");
					    	 double bitcion_price_at_match =rs.getDouble("bitcion_price_at_match");
					    	 String time_stamp = rs.getString("time_stamp");
					    	 rs.close();
					    	 //---------upe trade----------------------------
			        		 sql="select * from matched where match_id = "+upe.match_id+" ;";
			        		 rs = stmt.executeQuery(sql);	
			        		 rs.next();
			        		 int pmatch_id = rs.getInt("match_id");
					    	 int pprotect_id = rs.getInt("protect_id");
					    	 int ptrade_id = rs.getInt("trade_id");
					    	 String pprotect_email = rs.getString("protect_email");
					    	 String ptrade_email = rs.getString("trade_email");
					    	 double ptrade_amount_matched = rs.getDouble("trade_amount_matched");
					    	 double pprotect_amount_matched =rs.getDouble("protect_amount_matched");
					    	 double pbitcion_price_at_match =rs.getDouble("bitcion_price_at_match");
					    	 String ptime_stamp = rs.getString("time_stamp");
					    	 rs.close();
					    	 double bitCoinValue=getBitCoinPrice(out);
					          sql="INSERT INTO matched ( match_id, protect_email, trade_email, protect_id, trade_id, 	trade_amount_matched, protect_amount_matched, time_stamp, bitcion_price_at_match ) values (0, '"+protect_email+"', '"+ptrade_email+"', "+protect_id+", "+ptrade_id+", "+trade_amount_matched+", "+(trade_amount_matched*3)+", NULL, "+bitCoinValue+"  );";
				              stmt.executeUpdate(sql); 
					          sql="INSERT INTO matched ( match_id, protect_email, trade_email, protect_id, trade_id, 	trade_amount_matched, protect_amount_matched, time_stamp, bitcion_price_at_match ) values (0, '"+pprotect_email+"', '"+ptrade_email+"', "+pprotect_id+", "+ptrade_id+", "+(ptrade_amount_matched-trade_amount_matched)+", "+(((ptrade_amount_matched-trade_amount_matched))*3)+", NULL, "+bitCoinValue+"  );";
				              stmt.executeUpdate(sql); 
			        		//refund users their bitcoin-----------------------
				              //trade exit user
				              sql = "SELECT btc_holdings FROM users where email = '"+ trade_email + "'  ;" ;
				    	       rs = stmt.executeQuery(sql);				    	      
				    	       rs.next();
				    	       double btc_holdings = rs.getDouble("btc_holdings");
				    	       rs.close();
				              sql = "UPDATE users " +
								"SET btc_holdings = "+(btc_holdings+trade_amount_matched)+" "+
								"  where email = '"+ trade_email + "' ;" ;
					          stmt.executeUpdate(sql);
					          out.println("<h3>2returning "+trade_amount_matched+trade_amount_matched+"</h3>");
					          ////protect exit user
				              sql = "SELECT btc_holdings FROM users where email = '"+ pprotect_email + "'  ;" ;
				    	       rs = stmt.executeQuery(sql);				    	      
				    	       rs.next();
				    	       btc_holdings = rs.getDouble("btc_holdings");
				    	       rs.close();					          
				              sql = "UPDATE users " +
								"SET btc_holdings = "+(btc_holdings+(3*trade_amount_matched))+" "+
								"  where email = '"+ pprotect_email + "' ;" ;
					          stmt.executeUpdate(sql);
					          out.println("<h3>2returning "+pprotect_amount_matched+pprotect_email+"</h3>");
					          //end refunds------------------------------------------
					          //invaldate orignal orders
						      sql = "UPDATE matched " +
									"SET finished = 1 "+
									"  where match_id = "+ match_id + " ;" ;
						      stmt.executeUpdate(sql);
							  sql = "UPDATE matched " +
										"SET finished = 1 "+
										"  where match_id = "+ pmatch_id + " ;" ;
					          stmt.executeUpdate(sql);
					    	 
						      sql= "DELETE FROM unmatchedexittrade WHERE id="+ute.id+" ;";
						      stmt.executeUpdate(sql);
						      sql="UPDATE unmatchedexitprotect set amount="+(upe.trade_side*3-ute.protect_side)+" where id="+upe.id+" ;";
						      stmt.executeUpdate(sql);
		        		 }
	        	 }
	        	 
/*	        	 for(Pair pair: matchIdList){
	        		 //----ute trade----------
	        		 sql="select * from matched where match_id = "+pair.ute.match_id+" ;";
	        		 rs.next();
	        		 int match_id = rs.getInt("match_id");
			    	 int protect_id = rs.getInt("protect_id");
			    	 int trade_id = rs.getInt("trade_id");
			    	 String protect_email = rs.getString("protect_email");
			    	 String trade_email = rs.getString("trade_email");
			    	 double trade_amount_matched = rs.getDouble("trade_amount_matched");
			    	 double protect_amount_matched =rs.getDouble("protect_amount_matched");
			    	 double bitcion_price_at_match =rs.getDouble("bitcion_price_at_match");
			    	 String time_stamp = rs.getString("time_stamp");
			    	 rs.close();
			    	 //---------upe trade----------------------------
	        		 sql="select * from matched where match_id = "+pair.ute.match_id+" ;";
	        		 rs.next();
	        		 int pmatch_id = rs.getInt("match_id");
			    	 int pprotect_id = rs.getInt("protect_id");
			    	 int ptrade_id = rs.getInt("trade_id");
			    	 String pprotect_email = rs.getString("protect_email");
			    	 String ptrade_email = rs.getString("trade_email");
			    	 double ptrade_amount_matched = rs.getDouble("trade_amount_matched");
			    	 double pprotect_amount_matched =rs.getDouble("protect_amount_matched");
			    	 double pbitcion_price_at_match =rs.getDouble("bitcion_price_at_match");
			    	 String ptime_stamp = rs.getString("time_stamp");
			    	 
			          sql="INSERT INTO matched ( match_id, protect_email, trade_email, protect_id, trade_id, 	trade_amount_matched, protect_amount_matched, time_stamp, bitcion_price_at_match ) values (0, '"+pro.name+"', '"+tradeList.get(i).name+"', "+pro.protect_id+", "+tradeList.get(i).trade_id+", "+tradeList.get(i).unmatched+", "+(tradeList.get(i).unmatched*3)+", NULL, "+bitCoinValue+"  );";
		              stmt.executeUpdate(sql); 

			    	 rs.close();
		        	// sql="INSERT INTO matched ( match_id, protect_email, trade_email, protect_id, trade_id, 	trade_amount_matched, protect_amount_matched, time_stamp, bitcion_price_at_match ) values (0, '"+pro.name+"', '"+tradeList.get(i).name+"', "+pro.protect_id+", "+tradeList.get(i).trade_id+", "+tradeList.get(i).unmatched+", "+(tradeList.get(i).unmatched*3)+", NULL, "+bitCoinValue+"  );";
		              stmt.executeUpdate(sql);
		               
	        	 }
*/		  
		     stmt.close();
		     conn.close();
		  }catch(Exception e){
			 out.println(e);
		  }
	}
	
	
	public synchronized static void match(PrintWriter out){
		 ArrayList <Protect>protectList = new ArrayList<Protect>();
		 ArrayList <Trade>tradeList = new ArrayList<Trade>();
		 ArrayList <Protect>protectList2 = new ArrayList<Protect>();
		 ArrayList <Trade>tradeList2 = new ArrayList<Trade>();
		// PrintWriter out = response.getWriter();
		 double btcTradeAmount ;
		    
		//	double fourTimesTradeAmount = btcTradeAmount*4;
		    //JDBC driver name and database URL
		   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   final String DB_URL="jdbc:mysql://3.16.62.130/cl";

		   //  Database credentials
		   final String USER = "cl";
		  final String PASS = "Ra.cQH&&ZrFG(44e)Uf";
		  Connection conn=null;
		  Statement stmt=null;
		  try {
		     // Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		     // Open a connection
		     conn = DriverManager.getConnection(DB_URL, USER, PASS);

		     // Execute SQL query
		     stmt = conn.createStatement();
		     String sql="Select * from Protect ;";
	         ResultSet rs = stmt.executeQuery(sql);
	         String javaCalcLogic="";
	         // Extract data from result set
	         while(rs.next()){
	            //Retrieve by column name
	            int protect_id  = rs.getInt("protect_id");
	            //int age = rs.getInt("age");
	            String name = rs.getString("name");
	            String password = rs.getString("password");
	           
	            double unmatched = rs.getDouble("unmatched");
	            double matched = rs.getDouble("matched");
	            Protect p = new Protect(protect_id, name, password,
	    				 unmatched, matched);
	            protectList.add(p);
	         }
	         rs.close();
		     sql="Select * from Trading ;";
	          rs = stmt.executeQuery(sql);
	     
	         // Extract data from result set
	         while(rs.next()){
	            //Retrieve by column name
	            int trade_id  = rs.getInt("trade_id");
	            //int age = rs.getInt("age");
	            String name = rs.getString("name");
	            String password = rs.getString("password");
	           
	            double unmatched = rs.getDouble("unmatched");
	            double matched = rs.getDouble("matched");
	            Trade p = new Trade(trade_id, name, password,
	    				 unmatched, matched);
	            tradeList.add(p);
	         }
	         rs.close();
				//--------------get BitCoin price---------------------------------------------------------------------
				double bitCoinValue=0;
				try{
			          InputStream is = new URL("https://api.coindesk.com/v1/bpi/currentprice.json").openStream();
					  JsonReader jsonReader =  Json.createReader(new InputStreamReader(is, "UTF-8"));
					  JsonObject object = jsonReader.readObject();
					
					
					  JsonObject bpiObject = object.getJsonObject("bpi");
		               //  out.println(usdObject.getString("rate"));
					  JsonObject usdObject = bpiObject.getJsonObject("USD");
					  //JsonObject rateObject = usdObject.getJsonObject("rate");
					
					  //System.out.println(usdObject.getString("rate"));
					  bitCoinValue = Double.parseDouble(usdObject.getString("rate").replaceAll(",", ""));
					  System.out.println("bitvalue="+bitCoinValue);
					  jsonReader.close();

			       }
			       catch(IOException e){
				       out.println("<h1>UNABLE TO GET BITCION PRICE</h1>");
				       out.println("</body></html>");
				       rs.close();
				       stmt.close();
				       conn.close();
					   e.printStackTrace();
					   return;
			       }
			//--------------------------------------------------------------------------------------------------------------	
		         for(Protect pro: protectList){

		        	 for(int i=0; i<tradeList.size(); i++){
				     		

		        		 if(pro.unmatched>0&&pro.unmatched>=(tradeList.get(i).unmatched*3)&&tradeList.get(i).unmatched>0){
		        			 out.println("<br/><br/><br/><br/><h5>ProUnMatched ="+pro.unmatched+" Trade unmatched="+tradeList.get(i).unmatched+"</h5>");
		        			 pro.unmatched=pro.unmatched-(tradeList.get(i).unmatched*3);
		        			 pro.matched+=(tradeList.get(i).unmatched*3);
			 	                sql="UPDATE Protect set unmatched="+pro.unmatched+", matched="+ pro.matched+" where protect_id="+pro.protect_id+";";
				                stmt.executeUpdate(sql);
			 	                sql="INSERT INTO matched ( match_id, protect_email, trade_email, protect_id, trade_id, 	trade_amount_matched, protect_amount_matched, time_stamp, bitcion_price_at_match ) values (0, '"+pro.name+"', '"+tradeList.get(i).name+"', "+pro.protect_id+", "+tradeList.get(i).trade_id+", "+tradeList.get(i).unmatched+", "+(tradeList.get(i).unmatched*3)+", NULL, "+bitCoinValue+"  );";
				               stmt.executeUpdate(sql);
				                tradeList.get(i).matched+=tradeList.get(i).unmatched;  
		        			 tradeList.get(i).unmatched=0;
		        			 
		 	                sql="UPDATE Trading set unmatched= "+0.0+", matched="+ tradeList.get(i).matched+"  where trade_id="+tradeList.get(i).trade_id+";";
			                stmt.executeUpdate(sql);
		        		 }
	/*start here*/	     else if(pro.unmatched>0&&tradeList.get(i).unmatched>=pro.unmatched/3){
		out.println("<br/><br/><br/><br/><h5>ProUnMatched ="+pro.unmatched+" Trade unmatched="+tradeList.get(i).unmatched+"</h5>");
		        			 tradeList.get(i).unmatched=tradeList.get(i).unmatched-round(pro.unmatched/3,6);
		        			 tradeList.get(i).matched+=round(pro.unmatched/3,6);
			 	                sql="UPDATE Trading set unmatched= "+tradeList.get(i).unmatched+", matched="+tradeList.get(i).matched+"  where trade_id="+tradeList.get(i).trade_id+";";
				                stmt.executeUpdate(sql);
			 	                sql="INSERT INTO matched ( match_id, protect_email, trade_email, protect_id, trade_id, 	trade_amount_matched, protect_amount_matched, time_stamp, bitcion_price_at_match ) values (0, '"+pro.name+"', '"+tradeList.get(i).name+"', "+pro.protect_id+", "+tradeList.get(i).trade_id+", "+round(pro.unmatched/3,6)+", "+pro.unmatched+", NULL, "+bitCoinValue+"  );";
				               stmt.executeUpdate(sql);
	                         pro.matched+=pro.unmatched;
		        			 pro.unmatched=0;
			 	                sql="UPDATE Protect set unmatched="+0.0+", matched="+ pro.matched+" where protect_id="+pro.protect_id+";";
				                stmt.executeUpdate(sql);
		        			 break;
		        		 }
		        		 else if(pro.unmatched<=0){
		        			 break;
		        		 }
		        	 }
/*			         for(Protect pro2: protectList){
			        	 
			        	 for(int i=0; i<tradeList.size(); i++){
			        		 if(pro2.unmatched>0&&pro2.unmatched>=(tradeList.get(i).unmatched*3)&&tradeList.get(i).unmatched>0){
			        			 pro2.unmatched=pro2.unmatched-(tradeList.get(i).unmatched*3);
			        			 pro2.matched+=(tradeList.get(i).unmatched*3);
				 	                sql="UPDATE Protect set unmatched="+pro2.unmatched+", matched="+ pro2.matched+" where protect_id="+pro2.protect_id+";";
					                stmt.executeUpdate(sql);
				 	                sql="INSERT INTO matched ( match_id, protect_email, trade_email, protect_id, trade_id, 	trade_amount_matched, protect_amount_matched, time_stamp, bitcion_price_at_match ) values (0, '"+pro2.name+"', '"+tradeList.get(i).name+"', "+pro2.protect_id+", "+tradeList.get(i).trade_id+", "+tradeList.get(i).unmatched+", "+(tradeList.get(i).unmatched*3)+", NULL, "+bitCoinValue+"  );";
					               stmt.executeUpdate(sql);
					                tradeList.get(i).matched+=tradeList.get(i).unmatched;  
			        			 tradeList.get(i).unmatched=0;
			        			 
			 	                sql="UPDATE Trading set unmatched= "+0.0+", matched="+ tradeList.get(i).matched+"  where trade_id="+tradeList.get(i).trade_id+";";
				                stmt.executeUpdate(sql);
			        		 }
		            	     else if(pro2.unmatched>0&&tradeList.get(i).unmatched>=pro2.unmatched/3){
			        			 tradeList.get(i).unmatched=tradeList.get(i).unmatched-round(pro2.unmatched/3,6);
			        			 tradeList.get(i).matched+=round(pro2.unmatched/3,6);
				 	                sql="UPDATE Trading set unmatched= "+tradeList.get(i).unmatched+", matched="+tradeList.get(i).matched+"  where trade_id="+tradeList.get(i).trade_id+";";
					                stmt.executeUpdate(sql);
				 	                sql="INSERT INTO matched ( match_id, protect_email, trade_email, protect_id, trade_id, 	trade_amount_matched, protect_amount_matched, time_stamp, bitcion_price_at_match ) values (0, '"+pro2.name+"', '"+tradeList.get(i).name+"', "+pro2.protect_id+", "+tradeList.get(i).trade_id+", "+round(pro2.unmatched/3,6)+", "+pro.unmatched+", NULL, "+bitCoinValue+"  );";
					               stmt.executeUpdate(sql);
		                         pro2.matched+=pro.unmatched;
			        			 pro2.unmatched=0;
				 	                sql="UPDATE Protect set unmatched="+0.0+", matched="+ pro2.matched+" where protect_id="+pro2.protect_id+";";
					                stmt.executeUpdate(sql);
			        			 break;
			        		 }
			        		 else if(pro2.unmatched<=0){
			        			 break;
			        		 }
			        	 }
*/	        	 if(pro.unmatched>0){
	        	     out.println("<h1>UnMatched "+pro.unmatched+" Protect</h1>");
	        	 }

	         }



	        
	        
		     stmt.close();
		     conn.close();
		  }catch(Exception e){
			 out.println(e);
		  }
		
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	 
	    BigDecimal bd = new BigDecimal(Double.toString(value));
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	private static double getBitCoinPrice(PrintWriter out){
		double bitCoinValue=0;
		try{
	          InputStream is = new URL("https://api.coindesk.com/v1/bpi/currentprice.json").openStream();
			  JsonReader jsonReader =  Json.createReader(new InputStreamReader(is, "UTF-8"));
			  JsonObject object = jsonReader.readObject();
			
			
			  JsonObject bpiObject = object.getJsonObject("bpi");
	           //  out.println(usdObject.getString("rate"));
			  JsonObject usdObject = bpiObject.getJsonObject("USD");
			  //JsonObject rateObject = usdObject.getJsonObject("rate");
			
			  //System.out.println(usdObject.getString("rate"));
			  bitCoinValue = Double.parseDouble(usdObject.getString("rate").replaceAll(",", ""));
			  System.out.println("bitvalue="+bitCoinValue);
			  jsonReader.close();
	          return bitCoinValue;
	       }
	       catch(IOException e){
		       out.println("<h1>UNABLE TO GET BITCION PRICE</h1>");
		       out.println("</body></html>");
		      // rs.close();
		      // stmt.close();
		       //conn.close();
			   //e.printStackTrace();
			   return -1;
	       }
	}
}
	class Protect{
		 int protect_id;;
	   String name;
	   String password ;
	  
	   double unmatched;
	   double matched ;
		public Protect(int protect_id, String name, String password,
				double unmatched, double matched) {
			super();
			this.protect_id = protect_id;
			this.name = name;
			this.password = password;
			this.unmatched = unmatched;
			this.matched = matched;
		}
	   
	}
	class UnmatchedProtectExit{
		 int id;
	   String email;
	   double amount ;
	   int match_id;
	   double trade_side;
		public UnmatchedProtectExit(int id, String email,
				double amount, int match_id, double trade_side) {
			super();
			this.id = id;
			this.email = email;
			
			this.amount = amount;
			this.match_id = match_id;
			this.trade_side=trade_side;
		}
	   
	}
	class UnmatchedTradeExit{
		 int id;
	   String email;
	   double amount ;
	   int match_id;
	   double 	protect_side;

		public UnmatchedTradeExit(int id, String email,
				double amount, int match_id,  double 	protect_side) {
			super();
			this.id = id;
			this.email = email;
			
			this.amount = amount;
			this.match_id = match_id;
			this.	protect_side=	protect_side;
		}
	   
	}
class Trade{
	 int trade_id;;
   String name;
   String password ;
  double 	protect_side;
   double unmatched;
   double matched ;
	public Trade(int trade_id, String name, String password,
			double unmatched, double matched) {
		super();
		this.trade_id = trade_id;
		this.name = name;
		this.password = password;
		this.unmatched = unmatched;
		this.matched = matched;
		
	}
   
}
class Pair{
	UnmatchedTradeExit ute;
	UnmatchedProtectExit upe;
	Pair(UnmatchedTradeExit ute, UnmatchedProtectExit upe){
		this.ute=ute;
		this.upe=upe;
	}
}

