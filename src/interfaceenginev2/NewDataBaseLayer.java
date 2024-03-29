package interfaceenginev2;
import interfaceenginev2.bean.ApplicationTemplate;
import interfaceenginev2.bean.StyleInformation;
import interfaceenginev2.display.bean.GridProperty;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.sql.DataSource;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import comv2.aunwesha.lfutil.GenericUtil;
import comv2.aunwesha.param.CoreAdminInitHostInfo;


/**
 * Title:        LearnITy  Display Engine
 * Description:
 * Copyright:    Copyright (c) 2007
 * Company:      Aunwesha
 * @author 		 Shibaji   Chatterjee
 * @version 	 1.0
 */

public class NewDataBaseLayer {

public static DataSource ds=CoreAdminInitHostInfo.ds;
public static DataSource ds1=CoreAdminInitHostInfo.ds1;
 ////////////////////////////////////////SHIBAJI  NEW LAYOUT//////////////////////////////////
 
 public static  String getRoleID(String user_id)
 {
	 String role_id = "";
        
	// Connection oConn = null;
	 //PreparedStatement statement=null;
	 //ResultSet resultset =null;	     
	 try(Connection oConn = ds.getConnection()) {
		 
		 ResourceBundle rb = ResourceBundle.getBundle("portal",Locale.getDefault());      
		 String key1= "rolesql"; 
		 String rolesql = rb.getString(key1);
		 
		 //oConn = ds1.getConnection();
		 PreparedStatement pstmt = oConn.prepareStatement(rolesql+"=?");
		 pstmt.setString(1,user_id);
		 ResultSet resultset = pstmt.executeQuery();
		 //resultset = statement.executeQuery(rolesql+"='"+user_id+"'");
		 resultset.next();
		 role_id=resultset.getString(1);
		 resultset.close();
		 pstmt.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 
	 return role_id;
 } 
 
 
 public static  String getInterfaceengineRoleID(String role)
 {
	 String role_id = "";
        
	 //Connection oConn = null;
	 //Statement statement=null;
	 //ResultSet resultset =null;	     
	 try(Connection oConn = ds.getConnection()) {
		
		 PreparedStatement statement = oConn.prepareStatement("select role_id from role where title=?");
		 statement.setString( 1, role);
		 //resultset = statement.executeQuery("select role_id from role where title='"+role+"'");
		 ResultSet resultset = statement.executeQuery();
		 resultset.next();
		 role_id=resultset.getString(1);
		 resultset.close();
		 statement.close();
		 //oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 
	 return role_id;
 } 
 
 
 public static  String resourcetype(String resource_id,String interface_id)
 {
	 String loginno = null;
    //Connection oConn = null;
	 //Statement statement =null;
	 //ResultSet resultset =null;   
	 try(Connection oConn = ds.getConnection()) {
		 PreparedStatement statement = oConn.prepareStatement("select type from resource where resource_id=? and interface_id=?");
		 statement.setString(1,resource_id);
		 statement.setString(2,interface_id);
		 //  System.out.println("select type from resource where resource_id='"+resource_id+"' and interface_id='"+interface_id+"'");
		 ResultSet resultset = statement.executeQuery();
		 while(resultset.next())
		 {
			 loginno=resultset.getString(1);
				
		 }	
		 resultset.close();
		 statement.close();
		 oConn.close();
		  
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
           // System.out.println("Inside NewDataBaseLayer getPluginName(), Exception !!!!");
		 exception.printStackTrace();
	 }
	 
	 return loginno;
 } 
    
 
 public static Vector getimage1(String resource_id,String interface_id)
 {
	   
       // String str[]=new String[2];
	 Vector vSrcFile = new Vector();
	 //Statement  oStmt=null;
	 //Connection oConn = null;
	 //ResultSet oRset =null;
	 try
	 {
		 Connection oConn = ds.getConnection();
		 PreparedStatement oStmt = oConn.prepareStatement("select value from resource where resource_id=? and interface_id=?");
		 oStmt.setString(1,	resource_id);
		 oStmt.setString(2,	interface_id);
		 ResultSet oRset = oStmt.executeQuery();
		 while(oRset.next())
		 {
			 vSrcFile.addElement(oRset.getAsciiStream(1));
			
	            
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }
	 catch (Exception ex) {
		 ex.printStackTrace();
	 }
	 
	 return vSrcFile;		
 } 


 public static Vector getcss1(String resource_id,String interface_id)
 {
	   
	// String str[]=new String[2];
	 Vector vSrcFile = new Vector();
	 //Statement  oStmt=null;
	 //Connection oConn = null;
	 //ResultSet oRset =null;
	 try
	 {
		 Connection oConn = ds.getConnection();
		 PreparedStatement oStmt = oConn.prepareStatement("select value from resource where resource_id=? and interface_id=?");
		 oStmt.setString(1,	resource_id);
		 oStmt.setString(2,	interface_id);
		 ResultSet oRset = oStmt.executeQuery();
		 while(oRset.next())
		 {
			 vSrcFile.addElement(oRset.getString(1));
		
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }
	 catch (Exception ex) {
		 ex.printStackTrace();
	 }
	 
	 return vSrcFile;		
 } 
	
	
 public static  String getlinkfromfunction(String behaviourvalue)
 {
	 String loginno = null;
        
	 //Connection oConn = null;
	 //Statement statement =null;
	 //ResultSet resultset =null;    
	 try
	 {
		 Connection oConn = ds.getConnection();
            //checkConnection();
		 PreparedStatement statement = oConn.prepareStatement("select program from function  where function_id=?");
		 statement.setString(1, behaviourvalue);
		 ResultSet resultset = statement.executeQuery();
		 resultset.next();
		 loginno=resultset.getString(1);
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 /*finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }*/
	 return loginno;
 } 
    
 public static  String resourcekeyvalue(String resource_id,String interface_id)
 {
	 String keyvalue = null;
        
	 Connection oConn = null;
	 Statement statement =null;
	 ResultSet resultset =null;    
	 try
	 {
		 oConn = ds.getConnection();
            //checkConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select keyvalue from resource  where resource_id='"+resource_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {	 
		 keyvalue=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return keyvalue;
 } 




 public static  String getParent(String part_id)
 {
	 String parent_id = null;
        
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
       statement = oConn.createStatement();
		 resultset = statement.executeQuery("select parent_id from layout  where part_id='"+part_id+"'");
		 while(resultset.next())
		 {
		 parent_id=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
        exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return parent_id;
 } 



 public  static Vector getallchild(String part_id) {
	 Statement  oStmt1=null;
	 ResultSet  oRset1=null;
	 Vector vAdministratorList = new Vector();
	 Connection oConn = null;
        
	 try
	 {
		 oConn = ds.getConnection();
		 oConn.createStatement();
		 oStmt1 = oConn.createStatement();
		 oConn.createStatement();
		 for(oRset1=oStmt1.executeQuery("select part_id from structure where parent_id='"+part_id+"'");oRset1.next();)
		 {
                	
               
			 String part_id1=oRset1.getString(1);
			 vAdministratorList.addElement(part_id1);
                
		 }
            		
		 oRset1.close();
		 oStmt1.close();		
		 oConn.close();
	 }
	 catch (SQLException e){
		 e.printStackTrace();
	 }
	 catch (Exception ex){
      		//System.out.println(" error due to java.lang.exception");
		 ex.printStackTrace();
      		
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset1.close();
				 oStmt1.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vAdministratorList;
 }
	
	
 public static  String getparametervalue(String part_id,String interface_id)
 {
	 String parametervalue = null;
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select parameter from behaviour  where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
		 parametervalue=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return parametervalue;
 } 
				
 
 ////////////////////////////////////////SHIBAJI  NEW LAYOUT//////////////////////////////////
 
 
 
 ////////////////////////////////////////////////NEW TECH//////////////////////////////////////
 
 public  static Vector<String> getParentlayout(String layout,String interface_id) {
	 Statement  oStmt=null;
	 Statement  oStmt1=null;
	 Statement  oStmt2=null;
	 Statement  oStmt4=null;
	 ResultSet  oRset1=null;
	 ResultSet  oRset2=null;
	 ResultSet  oRset3=null;
	 ResultSet  oRset4=null;
	 String layout_id;
	 Vector<String> vAdministratorList = new Vector<>();
	 Connection oConn = null;
        
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 oStmt1 = oConn.createStatement();
		 oStmt2 = oConn.createStatement();
		 oConn.createStatement();
		 oStmt4 = oConn.createStatement();
		 String partclass="";
		 String resize="";
		 String border="";
		 String cols="";
		 String rows="";
		 String scrolling="";
		 String spacing="";
		 String colspan="";
		 String maxlength="";
		 String size="";
		 String tabindex="";
		 String archieve="";
		 String codebase="";
		 String mayscript="";
		 String position="";
		 String x="";
		 String y="";
		 String width="";
		 String height="";

//       Changed by Diptendu 19-NOV-2015
//       Added a 'distinct' to the select to avoid repeated code generation for the same layoutid. This
//       arises when the interfacerole.xml file refers to the same layout id for multiple roles
//	 which is quite possible since more than one role may be associated with the same layout.

//		 oRset4=oStmt4.executeQuery("select layout_id from roleassociation where layout_id='"+layout+"' and interface_id='"+interface_id+"'");

		 oRset4=oStmt4.executeQuery("select distinct layout_id from roleassociation where layout_id='"+layout+"' and interface_id='"+interface_id+"'");
		 while(oRset4.next())
		 {
			 layout_id=oRset4.getString(1);
			 for(oRset3=oStmt.executeQuery("select  a.part_id from layout a where a.layout_id='"+layout_id+"' and a.parent_id=' ' and a.interface_id='"+interface_id+"' order by layout_interface_id");oRset3.next();)
			 {
				 String part_id=oRset3.getString(1);
				 oRset1=oStmt1.executeQuery("select a.part_id ,a.position,a.x,a.y,a.width,a.height from layout a where a.layout_id='"+layout_id+"' and a.part_id='"+part_id+"'and a.interface_id='"+interface_id+"' group by a.part_id");
				 while(oRset1.next())
				 {    
                
					 position=oRset1.getString(2);
					 x=oRset1.getString(3);
					 y=oRset1.getString(4);
					 width=oRset1.getString(5);
					 height=oRset1.getString(6);
				 }             
                 
				 oRset2=oStmt2.executeQuery("select part_class,resize,border,textarea_cols,textarea_rows,scrolling,spacing,colspan,length,size,tabindex, archieve, codebase, mayscript from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
				 while(oRset2.next())
				 {	
					 partclass=oRset2.getString(1);
					 resize=oRset2.getString(2);
					 border=oRset2.getString(3);
					 cols=oRset2.getString(4);
					 rows=oRset2.getString(5);
					 scrolling=oRset2.getString(6);
					 spacing=oRset2.getString(7);
					 colspan=oRset2.getString(8);
					 maxlength=oRset2.getString(9);
					 size=oRset2.getString(10);
					 tabindex=oRset2.getString(11);
					 archieve=oRset2.getString(12);
					 codebase=oRset2.getString(13);
					 mayscript=oRset2.getString(14);
				 }
				 vAdministratorList.addElement(part_id);
				 vAdministratorList.addElement(position);
				 vAdministratorList.addElement(x);
				 vAdministratorList.addElement(y);
				 vAdministratorList.addElement(width);
				 vAdministratorList.addElement(height);
				 vAdministratorList.addElement(partclass);
				 vAdministratorList.addElement(interface_id);
				 vAdministratorList.addElement(resize);
				 vAdministratorList.addElement(border);
				 vAdministratorList.addElement(cols);
				 vAdministratorList.addElement(rows);
				 vAdministratorList.addElement(scrolling);
				 vAdministratorList.addElement(spacing);
				 vAdministratorList.addElement(colspan);
				 vAdministratorList.addElement(maxlength);
				 vAdministratorList.addElement(size);
				 vAdministratorList.addElement(tabindex);
				 vAdministratorList.addElement(archieve);
				 vAdministratorList.addElement(codebase);
				 vAdministratorList.addElement(mayscript);
			 }

//Modified by Diptendu 01-Dec-2015 - added null checks before closing
			 
			 if (oRset2 != null) oRset2.close();	
//			 oStmt2 .close();
			 if (oRset1 != null) oRset1.close();	
//			 oStmt1.close();
			 if (oRset3 != null) oRset3.close();
			 //oStmt.close(); 
		 }


		 if (oStmt2 != null) oStmt2.close();         
		 if (oStmt1 != null) oStmt1.close();         
		 if (oStmt != null) oStmt.close();         
		
		 
		 if (oRset4 != null) oRset4.close();
		 if (oStmt4 != null) oStmt4.close();			
		 if (oConn != null) oConn.close();	

//End of Modification by Diptendu 01-Dec-2015
	 }
	 catch (SQLException e){
		 e.printStackTrace();		
	 }
	 catch (Exception ex){
		 ex.printStackTrace();
      		
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
//Modified by Diptendu 02-Nov-2015
				 
				 if(oRset2!=null) oRset2.close();	
				 if(oStmt2!=null) oStmt2 .close();
				 if(oRset1!=null) oRset1.close();	
				 if(oStmt1!=null) oStmt1.close();
				 if(oRset3!=null) oRset3.close();
				 if(oStmt!=null) oStmt.close();
				 if(oRset4!=null) oRset4.close();
				 if(oStmt4!=null) oStmt4.close();	
				 if (oConn != null) oConn.close();
// end modification				 
			 }catch(Exception e){}	
		 }
	 }
	 return vAdministratorList;
 }
 
 
 
 public  static Vector getlayoutinformationchild(String layout,String interface_id,String parent) {
	 Statement  oStmt=null;
	 Statement  oStmt1=null;
	 Statement  oStmt2=null;
	 ResultSet  oRset1=null;
	 ResultSet  oRset2=null;
	 Vector vAdministratorList = new Vector();
	 Connection oConn = null;
        
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 oStmt1 = oConn.createStatement();
		 oStmt2 = oConn.createStatement();
		 oConn.createStatement();
		 String partclass="";
		 String resize="";
		 String border="";
		 String cols="";
		 String rows="";
		 String scrolling="";
		 String spacing="";
		 String colspan="";
		 String maxlength="";
		 String size="";
		 String tabindex="";
		 String archieve="";
		 String codebase="";
		 String mayscript="";
		 String autocomplete="";
		 String multiple="";

		 for(oRset1=oStmt1.executeQuery("select  part_id,position,x,y,width,height from layout  where layout_id='"+layout+"' and parent_id='"+parent+"' and interface_id='"+interface_id+"' order by layout_interface_id");oRset1.next();)
		 {
                	
               
			 String part_id=oRset1.getString(1);
			 String position=oRset1.getString(2);
			 String x=oRset1.getString(3);
			 String y=oRset1.getString(4);
			 String width=oRset1.getString(5);
			 String height=oRset1.getString(6);
                             
                 
			 oRset2=oStmt2.executeQuery("select part_class,resize,border,textarea_cols,textarea_rows,scrolling,spacing,colspan,length,size,tabindex,archieve,codebase,mayscript,autocomplete,multiple from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"' ");
			 while(oRset2.next())
			 {	
				 partclass=oRset2.getString(1);
				 resize=oRset2.getString(2);
				 border=oRset2.getString(3);
				 cols=oRset2.getString(4);
				 rows=oRset2.getString(5);
				 scrolling=oRset2.getString(6);
				 spacing=oRset2.getString(7);
				 colspan=oRset2.getString(8);
				 maxlength=oRset2.getString(9);
				 size=oRset2.getString(10);
				 tabindex=oRset2.getString(11);
                 
				 archieve=oRset2.getString(12);
				 codebase=oRset2.getString(13);
				 mayscript=oRset2.getString(14);
				 autocomplete=oRset2.getString(15);
				 multiple=oRset2.getString(16);
			 }
			
			 vAdministratorList.addElement(part_id);
			 vAdministratorList.addElement(position);
			 vAdministratorList.addElement(x);
			 vAdministratorList.addElement(y);
			 vAdministratorList.addElement(width);
			 vAdministratorList.addElement(height);
			 vAdministratorList.addElement(partclass);
			 vAdministratorList.addElement(interface_id);
			 vAdministratorList.addElement(resize);
			 vAdministratorList.addElement(border);
			 vAdministratorList.addElement(cols);
			 vAdministratorList.addElement(rows);
			 vAdministratorList.addElement(scrolling);
			 vAdministratorList.addElement(spacing);
			 vAdministratorList.addElement(colspan);
			 vAdministratorList.addElement(maxlength);
			 vAdministratorList.addElement(size);
			 vAdministratorList.addElement(tabindex);
			 vAdministratorList.addElement(archieve);
			 vAdministratorList.addElement(codebase);
			 vAdministratorList.addElement(mayscript);
			 vAdministratorList.addElement(autocomplete);
			 vAdministratorList.addElement(multiple);
			 oRset2.close();
			 
		 }
		
   		
		 
		 oRset1.close();
		 oStmt2.close();
		 oStmt1.close();
		 oConn.close();
	 }
	 catch (SQLException e){
		 e.printStackTrace();       		
	 }
	 catch (Exception ex){
		 ex.printStackTrace();
  		
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset2.close();
				 oRset1.close();
				 oStmt2.close();
				 oStmt1.close();
				 oStmt.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }     
	 return vAdministratorList;
 }	
	
	
 public static  String getChild(String parent_id,String interface_id)
 {
	 String part_id = null;
        
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
     	 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select part_id from layout  where parent_id='"+parent_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
		 part_id=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
        exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return part_id;
 } 	
    
    
 public static Vector getimage(String resource_id,String interface_id)
 {
	    
       // String str[]=new String[2];
	 Vector vSrcFile = new Vector();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset = null;
	     
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
              
       //System.out.println("select value from resource where resource_id='"+resource_id+"'");
		 for(oRset = oStmt.executeQuery("select value from resource where resource_id='"+resource_id+"' and interface_id='"+interface_id+"'");oRset.next();)
		 {
      	 vSrcFile.addElement(oRset.getAsciiStream(1));
		 }
		 oRset.close();  
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }
	 catch (Exception ex) {
		 ex.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 


 public static Vector getcss(String resource_id,String interface_id)
 {
	   
       // String str[]=new String[2];
	 Vector vSrcFile = new Vector();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset=null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
              
             // System.out.println("select value from resource where resource_id='"+resource_id+"'");
		 for(oRset = oStmt.executeQuery("select value from resource where resource_id='"+resource_id+"' and interface_id='"+interface_id+"'");oRset.next();)
		 {
              		
			
			 vSrcFile.addElement(oRset.getString(1));
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }
	 catch (Exception ex) {
		 ex.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 
 
 public static Vector<String> getjs(String resource_id,String interface_id)
 {
	   
	 Vector<String> vSrcFile = new Vector<String>();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset=null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 for(oRset = oStmt.executeQuery("select value from resource where resource_id='"+resource_id+"' and interface_id='"+interface_id+"'");oRset.next();)
		 {
			 vSrcFile.addElement(oRset.getString(1));
				
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }
	 catch (Exception ex) {
		 ex.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 
 
    
  
 public static  String getType(String part_id)
 {
	 String part_id_type = null;
        
	 Connection oConn = null;
	 Statement statement =null;
	 ResultSet resultset =null;    
	 try
	 {
		 oConn = ds.getConnection();
       statement = oConn.createStatement();
		 resultset = statement.executeQuery("select part_class from structure  where part_id='"+part_id+"'");
		 resultset.next();
		 part_id_type=resultset.getString(1);
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return part_id_type;
 } 	 
 
 ////////////////////////////////////////////////////FOR DEFAULT LAYOUT//////////////////////////////
 public  static Vector getlayoutinformationchild(String interface_id,String parent) {
	 Statement  oStmt1=null;
	 Statement  oStmt2=null;
	 ResultSet  oRset1=null;
	 ResultSet  oRset2=null;
	 Vector vAdministratorList = new Vector();
	 Connection oConn = null;
        
	 try
	 {
		 oConn = ds.getConnection();
		 oConn.createStatement();
		 oStmt1 = oConn.createStatement();
		 oStmt2 = oConn.createStatement();
		 oConn.createStatement();
		 String partclass="";
		 String resize="";
		 String border="";
		 String cols="";
		 String rows="";
		 String scrolling="";
		 String spacing="";
		 String colspan="";
		 String maxlength="";
		 String size="";
		 String tabindex="";
      	        
      	       // oRset4=oStmt4.executeQuery("select interface_id from interface where interface_title='Delivery Engine'");
      	       // while(oRset4.next())
      		//{
      	         //String interface_id=oRset4.getString(1);
      	        
      		//System.out.println("select  a.part_id,a.position,a.x,a.y,a.width,a.height,b.value,b.contenttype,c.value,d.value,d.valuetype,d.target,d.behaviourevent,c.styletype from layout a,content b,style c,behaviour d where  a.part_id=b.part_id and b.part_id=c.part_id and c.part_id=d.part_id and a.parent_id='"+parent+"' and a.interface_id='"+interface_id+"' and a.interface_id=b.interface_id and b.interface_id=c.interface_id and c.interface_id=d.interface_id");
		 for(oRset1=oStmt1.executeQuery("select  a.part_id,a.position,a.x,a.y,a.width,a.height from layout a where  a.parent_id='"+parent+"' and a.interface_id='"+interface_id+"' ");oRset1.next();)
		 {
                	
               
			 String part_id=oRset1.getString(1);
			 String position=oRset1.getString(2);
			 String x=oRset1.getString(3);
			 String y=oRset1.getString(4);
			 String width=oRset1.getString(5);
			 String height=oRset1.getString(6);
			 oRset2=oStmt2.executeQuery("select part_class,resize,border,textarea_cols,textarea_rows,scrolling,spacing,colspan,length,size,tabindex from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
			 while(oRset2.next())
			 {	
				 partclass=oRset2.getString(1);
				 resize=oRset2.getString(2);
				 border=oRset2.getString(3);
				 cols=oRset2.getString(4);
				 rows=oRset2.getString(5);
				 scrolling=oRset2.getString(6);
				 spacing=oRset2.getString(7);
				 colspan=oRset2.getString(8);
				 maxlength=oRset2.getString(9);
				 size=oRset2.getString(10);
				 tabindex=oRset2.getString(11);
                 
			 }
			 vAdministratorList.addElement(part_id);
			 vAdministratorList.addElement(position);
			 vAdministratorList.addElement(x);
			 vAdministratorList.addElement(y);
			 vAdministratorList.addElement(width);
			 vAdministratorList.addElement(height);
                 
			 vAdministratorList.addElement(partclass);
                 
                
			 vAdministratorList.addElement(interface_id);
			 vAdministratorList.addElement(resize);
			 vAdministratorList.addElement(border);
			 vAdministratorList.addElement(cols);
			 vAdministratorList.addElement(rows);
			 vAdministratorList.addElement(scrolling);
			 vAdministratorList.addElement(spacing);
			 vAdministratorList.addElement(colspan);
			 vAdministratorList.addElement(maxlength);
			 vAdministratorList.addElement(size);
			 vAdministratorList.addElement(tabindex);
                 
		 }
       
                
	//}			
		 oRset2.close();
		 oStmt2.close();	
		 oRset1.close();	
		 oStmt1.close();	
		 oConn.close();
	 }
	 catch (SQLException e){
		 e.printStackTrace();
	 }
	 catch (Exception ex){
		 ex.printStackTrace();
      		
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset2.close();
				 oStmt2.close();	
				 oRset1.close();	
				 oStmt1.close();	
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vAdministratorList;
 }
	
	
	
 public  static Vector getParentlayout(String interfacename) {
	 Statement  oStmt= null;
	 Statement  oStmt1= null;
	 Statement  oStmt2= null;
	 Statement  oStmt4= null;
	 ResultSet  oRset1= null;
	 ResultSet  oRset2= null;
	 ResultSet  oRset3= null;
	 ResultSet  oRset4= null;
	 String interface_id;
	 Vector vAdministratorList = new Vector();
	 Connection oConn = null;
        
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 oStmt1 = oConn.createStatement();
		 oStmt2 = oConn.createStatement();
		 oConn.createStatement();
		 oStmt4 = oConn.createStatement();
		 String partclass="";
		 String resize="";
		 String border="";
		 String cols="";
		 String rows="";
		 String scrolling="";
		 String spacing="";
		 String colspan="";
		 String maxlength="";
		 String size="";
		 String tabindex="";
      	        
      	       // String interfacetype=
		 oRset4=oStmt4.executeQuery("select interface_id from interface where interface_title='"+interfacename+"'");
		 while(oRset4.next())
		 {
			 interface_id=oRset4.getString(1);
      	 oRset3=oStmt.executeQuery("select distinct a.part_id from layout a where  a.parent_id=' ' and a.interface_id='"+interface_id+"'");
			 oRset3.next();
			 String part_id=oRset3.getString(1);
          oRset1=oStmt1.executeQuery("select a.part_id ,a.position,a.x,a.y,a.width,a.height,b.value,b.contenttype,c.value,d.value,d.valuetype,d.target,d.behaviourevent,c.styletype from layout a,content b,style c,behaviour d where  a.part_id=b.part_id and b.part_id=c.part_id and c.part_id=d.part_id and a.part_id='"+part_id+"' and a.interface_id='"+interface_id+"' group by a.part_id");
			 oRset1.next();
                     
                
			 String position=oRset1.getString(2);
			 String x=oRset1.getString(3);
			 String y=oRset1.getString(4);
			 String width=oRset1.getString(5);
			 String height=oRset1.getString(6);
			 String value=oRset1.getString(7);
			 String valuetype=oRset1.getString(8);
			 String stylevalue=oRset1.getString(9);
			 String behaviourvalue=oRset1.getString(10);
			 String behaviourvaluetype=oRset1.getString(11);
			 String behaviourtarget=oRset1.getString(12);
			 String behaviourevent=oRset1.getString(13);
                
			 String styletype=oRset1.getString(14);
                 
			 oRset2=oStmt2.executeQuery("select part_class,resize,border,textarea_cols,textarea_rows,scrolling,spacing,colspan,length,size,tabindex from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
			 while(oRset2.next())
			 {	
				 partclass=oRset2.getString(1);
				 resize=oRset2.getString(2);
				 border=oRset2.getString(3);
				 cols=oRset2.getString(4);
				 rows=oRset2.getString(5);
				 scrolling=oRset2.getString(6);
				 spacing=oRset2.getString(7);
				 colspan=oRset2.getString(8);
				 maxlength=oRset2.getString(9);
				 size=oRset2.getString(10);
				 tabindex=oRset2.getString(11);
                 
			 }
			 vAdministratorList.addElement(part_id);
			 vAdministratorList.addElement(position);
			 vAdministratorList.addElement(x);
			 vAdministratorList.addElement(y);
			 vAdministratorList.addElement(width);
			 vAdministratorList.addElement(height);
			 vAdministratorList.addElement(value);
			 vAdministratorList.addElement(valuetype);	
			 vAdministratorList.addElement(stylevalue);
			 vAdministratorList.addElement(behaviourvalue);
			 vAdministratorList.addElement(behaviourvaluetype);
			 vAdministratorList.addElement(behaviourtarget);
			 vAdministratorList.addElement(behaviourevent);
			 vAdministratorList.addElement(partclass);
			 vAdministratorList.addElement(styletype);
			 vAdministratorList.addElement(interface_id);
			 vAdministratorList.addElement(resize);
			 vAdministratorList.addElement(border);
			 vAdministratorList.addElement(cols);
			 vAdministratorList.addElement(rows);
			 vAdministratorList.addElement(scrolling);
			 vAdministratorList.addElement(spacing);
			 vAdministratorList.addElement(colspan);
			 vAdministratorList.addElement(maxlength);
			 vAdministratorList.addElement(size);
			 vAdministratorList.addElement(tabindex);
		 }
       	
		 oConn.close();
		
	 }
	 catch (SQLException e){
		 e.printStackTrace();
	 }
	 catch (Exception ex){
		 ex.printStackTrace();
      		
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vAdministratorList;
 }		
 ////////////////////////////////////////////////////////DEFAULT LAYOUT//////////////////////////////////
 
 public static  List<String> getEvent(String layout,String behaviour,String interface_id)
 {
	 List<String> rootevent = null;
        
	 Connection oConn = null;
	 Statement statement =null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
       statement = oConn.createStatement();
		 resultset = statement.executeQuery("select a.value,a.valuetype,a.behaviourevent from behaviour a,roleassociation b  where a.part_id='root' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"'and b.behaviour_id='"+behaviour+"' and a.behaviour_id=b.behaviour_id and a.behaviourevent!='domready'");
		 while(resultset.next())
		 {
			 rootevent=new ArrayList<>();
			 rootevent.add(resultset.getString(1));
			 rootevent.add(resultset.getString(2));
			 rootevent.add(resultset.getString(3));
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return rootevent;
 } 
    
 public static  String getbehaviourvalueforroot(String root ,String layout,String behaviour,String interface_id)
 {
	 String value = null;
        
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
       statement = oConn.createStatement();
		 resultset = statement.executeQuery("select a.resourceid from behaviour a,roleassociation b  where a.part_id='"+root+"' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and b.behaviour_id='"+behaviour+"' and a.behaviour_id=b.behaviour_id and a.behaviourevent!='domready'");
		 while(resultset.next())
		 {
			 value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return value;
 }  

 public static  String getCols(String part_id)
 {
	 String col = "";
        
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
       statement = oConn.createStatement();
		 resultset = statement.executeQuery("select cols from structure  where part_id='"+part_id+"'");
		 resultset.next();
		 col=resultset.getString(1);
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return col;
 } 
    	
 ////////////////////////////////////////////////NEW TECH//////////////////////////////////////
 
 
//  public  static Vector getParentlayout(String student_id,String interfacename,String part_id) {
// 	 Statement  oStmt=null;
// 	 Statement  oStmt1=null;
// 	 Statement  oStmt2=null;
// 	 Statement  oStmt3=null;
// 	 Statement  oStmt4=null;
// 	 ResultSet  oRset=null;
// 	 ResultSet  oRset1=null;
// 	 ResultSet  oRset2=null;
// 	 ResultSet  oRset3=null;
// 	 ResultSet  oRset4=null;
// 	 String interface_id;
// 	 Vector vAdministratorList = new Vector();
// 	 Connection oConn = null;
//         
// 	 try
// 	 {
// 		 oConn = ds.getConnection();
// 		 oStmt = oConn.createStatement();
// 		 oStmt1 = oConn.createStatement();
// 		 oStmt2 = oConn.createStatement();
// 		 oStmt3 = oConn.createStatement();
// 		 oStmt4 = oConn.createStatement();
// 		 String partclass="";
// 		 String parent_id="";
// 		 String resize="";
// 		 String border="";
// 		 String cols="";
// 		 String rows="";
// 		 String scrolling="";
// 		 String spacing="";
// 		 String colspan="";
// 		 String maxlength="";
// 		 String size="";
// 		 String tabindex="";
//       	        
//       	       // String interfacetype=
// 		 oRset4=oStmt4.executeQuery("select interface_id from interface where interface_title='"+interfacename+"'");
// 		 while(oRset4.next())
// 		 {
// 			 interface_id=oRset4.getString(1);
//       	        
//       	        
// 			 oRset=oStmt.executeQuery("select role_id from user_role where user_id='"+student_id+"'");
// 			 oRset.next();
//       		
// 			 String role_id=oRset.getString(1);
//                 
// 			 oRset3=oStmt.executeQuery("select  a.part_id from layout a where a.role_id='"+role_id+"' and a.part_id='"+part_id+"' and a.interface_id='"+interface_id+"'");
// 			 oRset3.next();
//                 
// 			 String part_id1=oRset3.getString(1);
//                 
//                 
// 			 oRset1=oStmt1.executeQuery("select a.part_id ,a.position,a.x,a.y,a.width,a.height,b.value,b.contenttype,c.value,d.value,d.valuetype,d.target,d.behaviourevent,c.styletype from layout a,content b,style c,behaviour d where a.role_id='"+role_id+"' and a.part_id=b.part_id and b.part_id=c.part_id and c.part_id=d.part_id and a.part_id='"+part_id1+"' and a.interface_id='"+interface_id+"' group by a.part_id");
// 			 oRset1.next();
//                 	
//                
//                 
// 			 String position=oRset1.getString(2);
// 			 String x=oRset1.getString(3);
// 			 String y=oRset1.getString(4);
// 			 String width=oRset1.getString(5);
// 			 String height=oRset1.getString(6);
// 			 String value=oRset1.getString(7);
// 			 String valuetype=oRset1.getString(8);
// 			 String stylevalue=oRset1.getString(9);
// 			 String behaviourvalue=oRset1.getString(10);
// 			 String behaviourvaluetype=oRset1.getString(11);
// 			 String behaviourtarget=oRset1.getString(12);
// 			 String behaviourevent=oRset1.getString(13);
//                 
// 			 String styletype=oRset1.getString(14);
//                  
// 			 oRset2=oStmt2.executeQuery("select part_class,resize,border,cols,rows,scrolling,spacing,colspan,length,size,tabindex from structure where part_id='"+part_id1+"' and interface_id='"+interface_id+"'");
// 			 while(oRset2.next())
// 			 {	
// 				 partclass=oRset2.getString(1);
// 				 resize=oRset2.getString(2);
// 				 border=oRset2.getString(3);
// 				 cols=oRset2.getString(4);
// 				 rows=oRset2.getString(5);
// 				 scrolling=oRset2.getString(6);
// 				 spacing=oRset2.getString(7);
// 				 colspan=oRset2.getString(8);
// 				 maxlength=oRset2.getString(9);
// 				 size=oRset2.getString(10);
// 				 tabindex=oRset2.getString(11);
//                  
// 			 }
// 			 vAdministratorList.addElement(part_id);
// 			 vAdministratorList.addElement(position);
// 			 vAdministratorList.addElement(x);
// 			 vAdministratorList.addElement(y);
// 			 vAdministratorList.addElement(width);
// 			 vAdministratorList.addElement(height);
// 			 vAdministratorList.addElement(value);
// 			 vAdministratorList.addElement(valuetype);	
// 			 vAdministratorList.addElement(stylevalue);
// 			 vAdministratorList.addElement(behaviourvalue);
// 			 vAdministratorList.addElement(behaviourvaluetype);
// 			 vAdministratorList.addElement(behaviourtarget);
// 			 vAdministratorList.addElement(behaviourevent);
// 			 vAdministratorList.addElement(partclass);
// 			 vAdministratorList.addElement(styletype);
// 			 vAdministratorList.addElement(interface_id);
// 			 vAdministratorList.addElement(resize);
// 			 vAdministratorList.addElement(border);
// 			 vAdministratorList.addElement(cols);
// 			 vAdministratorList.addElement(rows);
// 			 vAdministratorList.addElement(scrolling);
// 			 vAdministratorList.addElement(spacing);
// 			 vAdministratorList.addElement(colspan);
// 			 vAdministratorList.addElement(maxlength);
// 			 vAdministratorList.addElement(size);
// 			 vAdministratorList.addElement(tabindex);
// 		 }
//             
// 		 oRset2.close();
// 		 oRset1.close();
// 		 oRset3.close();
// 		 oRset.close();
// 		 oRset4.close();
// 		 oConn.close();		
// 				
// 		
// 	 }
// 	 catch (SQLException e){
// 		 String errMessage = e.getMessage();
//       	//	System.out.println(" error due to SQL getlayoutinformation..............."+errMessage);
//       		
// 	 }
// 	 catch (Exception ex){
//       	//	System.out.println(" error due to java.lang.exception");
// 		 ex.printStackTrace();
//       		
// 	 }
// 	 finally
// 	 {
// 		 if(oConn!=null)
// 		 {
// 			 try
// 			 {
// 				 oRset2.close();
// 				 oRset1.close();
// 				 oRset3.close();
// 				 oRset.close();
// 				 oRset4.close();
// 				 oConn.close();	
// 			 } catch(Exception e){}	
// 		 }
// 	 }
// 	 return vAdministratorList;
//  }
 
 public static  String getJavaClass(String layout,String behaviour,String interface_id,String part_id)
 {
	 String javaclass = "";
        
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select a.javaclass from behaviour a,roleassociation b  where a.part_id='"+part_id+"' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and a.behaviour_id=b.behaviour_id and a.behaviour_id='"+behaviour+"'");
            
		while(resultset.next())
		{
		 javaclass=resultset.getString(1);
		}
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return javaclass;
 } 
 public static  String getbehaviourtotalvalueforroot(String resource_id)
 {
	 String value = "";
        
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
       statement = oConn.createStatement();
		 resultset = statement.executeQuery("select value from resource  where resource_id='"+resource_id+"'");
		 while(resultset.next())
		 {
		 value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
            //System.out.println("Inside NewDataBaseLayer getPluginName(), Exception !!!!");
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return value;
 }  
 
 public static  String ajaxcomponentExitorNot(String interface_id)
 {
	 String value = "";
        
	 Connection oConn = null;
	 Statement statement = null;  
	 ResultSet resultset = null;  
	 try
	 {
		 oConn = ds.getConnection();
       statement = oConn.createStatement();
       resultset = statement.executeQuery("select part_id from structure  where interface_id='"+interface_id+"' and part_class='ajaxcomponent' ");
		 while(resultset.next())
		 {
			 value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
            //System.out.println("Inside NewDataBaseLayer getPluginName(), Exception !!!!");
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return value;
 }  

/*public static  String getSnippetXml(String snippet_id)
 {
 String value = "";
        
 DBConnectionManager connMgr = DBConnectionManager.getInstance();
 Connection oConn = connMgr.getConnection("mysql"); 
        
 try
 {
            //checkConnection();
 Statement statement = oConn.createStatement();
 ResultSet resultset = statement.executeQuery("select value from snippet  where snippet_id='"+snippet_id+"' ");
 resultset.next();
 value=resultset.getString(1);
 resultset.close();
 statement.close();
}
 catch(SQLException sqlexception)
 {
 System.out.println("Inside NewDataBaseLayer getPluginName(), SQLException !!!!");
 System.out.println("Inside NewDataBaseLayer getPluginName(), the error message - " + sqlexception.getMessage());
 System.out.println("Inside NewDataBaseLayer getPluginName(), the error message - " + sqlexception.getErrorCode());
}
 catch(Exception exception)
 {
            //System.out.println("Inside NewDataBaseLayer getPluginName(), Exception !!!!");
 exception.printStackTrace();
}
 connMgr.freeConnection("mysql", oConn);
 return value;
}  
*/
 public static Vector getSnippetXml(String snippet_id)
 {

	 Vector vSrcFile = new Vector();
	 Statement  oStmt= null;
	 Connection oConn = null;
	 ResultSet oRset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 for(oRset = oStmt.executeQuery("select value from snippet  where snippet_id='"+snippet_id+"' ");oRset.next();)
		 {
			 vSrcFile.addElement(oRset.getAsciiStream(1));
			 oRset.close();
		 }
	    
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
	 }
	 catch (Exception ex) {
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 
 public static  String getParentType(String parent_id)
 {
	 String value = "";
        
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;  
	 try
	 {
		 oConn = ds.getConnection();
       statement = oConn.createStatement();
		 resultset = statement.executeQuery("select part_class from structure  where part_id='"+parent_id+"' ");
		 while(resultset.next())
		 {
		 value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
         
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return value;
 }  
 public static  String getChildType(String Child_id)
 {
	 String value = "";
        
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
       statement = oConn.createStatement();
		 resultset = statement.executeQuery("select part_class from structure  where part_id='"+Child_id+"' ");
		 while(resultset.next())
		 {
		 value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
         
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return value;
 }  

 public static  String getContentType(String layout,String content,String part_id,String interface_id)
 {
	 String contentype = "";
	 Connection oConn = null;
	 Statement statement=null;
	 ResultSet resultset=null;
	 try
	 {
		 oConn = ds.getConnection();
       statement = oConn.createStatement();
		 resultset = statement.executeQuery("select a.contenttype from content a,roleassociation b where a.part_id='"+part_id+"' and a.interface_id='"+interface_id+"'and b.layout_id='"+layout+"' and b.content_id='"+content+"' and a.content_id=b.content_id and a.interface_id=b.interface_id");
		 while(resultset.next())
		 {
			 contentype=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
				
			 } catch(Exception e){}	
		 }
	 }
	 return contentype;
 } 

 public static  String getContentvalue(String layout,String content,String part_id,String interface_id)
 {
	 String contentvalue = "";
	 Connection oConn = null;
	 Statement statement= null;    
	 ResultSet resultset = null; 
	 try
	 {
		 oConn = ds.getConnection();
            //checkConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select a.value from content a,roleassociation b where a.part_id='"+part_id+"' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and b.content_id='"+content+"' and a.content_id=b.content_id and a.interface_id=b.interface_id");
		 while(resultset.next())
		 {
			 contentvalue=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return contentvalue;
 } 


 public static  String getDropDownContentvalue(String layout,String content,String part_id,String interface_id)
 {
	 String contentvalue = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
       statement = oConn.createStatement();
		 resultset = statement.executeQuery("select a.active_value from content a,roleassociation b where a.part_id='"+part_id+"' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and b.content_id='"+content+"' and a.content_id=b.content_id and a.interface_id=b.interface_id");
		 while(resultset.next())
		 {
		 contentvalue=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 } 
	 return contentvalue;
 } 


 public static  String getBehaviourEvent(String layout,String behaviour,String part_id,String interface_id)
 {
	 String contentvalue = "";
	 Connection oConn = null;
	 Statement statement = null;  
	 ResultSet resultset = null;  
	 try
	 {
		 oConn = ds.getConnection();
       statement = oConn.createStatement();
		 resultset = statement.executeQuery("select a.behaviourevent from behaviour a,roleassociation b where a.part_id='"+part_id+"' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and b.behaviour_id='"+behaviour+"' and a.behaviour_id=b.behaviour_id and a.interface_id=b.interface_id");
		 while( resultset.next())
		 {
			 contentvalue=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return contentvalue;
 } 
 public static  String getBehaviourValueType(String layout,String behaviour,String part_id,String interface_id)
 {
	 String contentvalue = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
       statement = oConn.createStatement();
		 resultset = statement.executeQuery("select a.valuetype from behaviour a, roleassociation b where a.part_id='"+part_id+"' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and b.behaviour_id='"+behaviour+"' and a.behaviour_id=b.behaviour_id and a.interface_id=b.interface_id");
		 while(resultset.next())
		 {
			 contentvalue=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 
		 sqlexception.printStackTrace();
		 
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return contentvalue;
 } 

 public static  String getBehaviourValue(String layout,String behaviour,String part_id,String interface_id)
 {
	 String contentvalue = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
       statement = oConn.createStatement();
		 resultset = statement.executeQuery("select a.value from behaviour a,roleassociation b where a.part_id='"+part_id+"' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and b.behaviour_id='"+behaviour+"' and a.behaviour_id=b.behaviour_id and a.interface_id=b.interface_id");
		 while(resultset.next())
		 {
			 contentvalue=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return contentvalue;
 } 
 public static  String getBehaviourTarget(String layout,String behaviour,String part_id,String interface_id)
 {
		String contentvalue = "";
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;    
		try
		{
			oConn = ds.getConnection();
			statement = oConn.createStatement();
			resultset = statement.executeQuery("select a.target from behaviour a,roleassociation b where a.part_id='"+part_id+"' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and b.behaviour_id='"+behaviour+"' and a.behaviour_id=b.behaviour_id and a.interface_id=b.interface_id");
			while(resultset.next())
			{
				contentvalue=resultset.getString(1);
			}
			resultset.close();
			statement.close();
			oConn.close();
		}
		catch(SQLException sqlexception)
		{
			
			sqlexception.printStackTrace();
		}
		finally{
			if(oConn!=null)
			{
				try
				{
					resultset.close();
					statement.close();
					oConn.close();
				}catch(Exception e){}	
			}
		}
		return contentvalue;
 } 
 public static  String getBehaviourresourceID(String layout,String behaviour,String part_id,String interface_id)
 {
	 String contentvalue = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select a.resourceid from behaviour a,roleassociation b where a.part_id='"+part_id+"' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and b.behaviour_id='"+behaviour+"' and a.behaviour_id=b.behaviour_id and a.interface_id=b.interface_id");
		 while(resultset.next())
		 {
			 contentvalue=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return contentvalue;
 } 
 public static  List<StyleInformation> getStyleValue(String layout,String style,String part_id,String interface_id)
 {
	 List<StyleInformation> styles=new ArrayList<>();
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
       statement = oConn.createStatement();
		 resultset = statement.executeQuery("select a.value,a.styletype,a.resource_id from style a,roleassociation b where a.part_id='"+part_id+"' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and b.style_id='"+style+"' and a.style_id=b.style_id and a.interface_id=b.interface_id");
		 while(resultset.next())
		 {
			 StyleInformation styleInformation=new StyleInformation();
			 styleInformation.setValue(resultset.getString(1));
			 styleInformation.setType(resultset.getString(2));
			 styleInformation.setResourceId(resultset.getString(3));
			 
			 styles.add(styleInformation);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();

	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return styles;
 } 

 /*public static  Pair<String,String> getStyleValueType(String layout,String style,String part_id,String interface_id)
 {
	 Pair<String, String> styleRefPair=new Pair<>();
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;   
	 try
	 {
		 oConn = ds.getConnection();
       statement = oConn.createStatement();
		 resultset = statement.executeQuery("select a.styletype,a.resource_id from style a,roleassociation b where a.part_id='"+part_id+"' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and b.style_id='"+style+"' and a.style_id=b.style_id and a.interface_id=b.interface_id");
		 while(resultset.next())
		 {
			 styleRefPair.setFirst(resultset.getString(1));
			 styleRefPair.setSecond(resultset.getString(2));
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return styleRefPair;
 } */

 public static  String getDefaultRoleID(String title)
 {
	 String role_id = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset =null;
	     
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select role_id from role where title='"+title+"'");
		 while(resultset.next())
		 {
			 role_id=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return role_id;
 } 
 
 public static Vector getvoption(String part_id,String interface_id)
 {
	    
	 Vector vSrcFile = new Vector();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset =null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 for(oRset = oStmt.executeQuery("select option_id,labelname,labelvalue,eventname,eventvalue  from optionmenu where part_id='"+part_id+"' and interface_id='"+interface_id+"'");oRset.next();)
		 {
			 vSrcFile.addElement(oRset.getString(1));
			 vSrcFile.addElement(oRset.getString(2));
			 vSrcFile.addElement(oRset.getString(3));
			 vSrcFile.addElement(oRset.getString(4));
			 vSrcFile.addElement(oRset.getString(5));
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 
		 sqlexception.printStackTrace();
	 }	
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 

 public static Vector GenerateFixedDropDownString(String part_id,String interface_id)
 {
	    
	 Vector vSrcFile = new Vector();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset=null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 for(oRset = oStmt.executeQuery("select name,value  from dropdownmenu where part_id='"+part_id+"' and interface_id='"+interface_id+"' order by  menu_item_id ");oRset.next();)
		 {
			 vSrcFile.addElement(oRset.getString(1));
			 vSrcFile.addElement(oRset.getString(2));
			
		 }

		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }	
	 return vSrcFile;		
 } 

 public static Vector<String> getColnames(String part_id,String interface_id)
 {
	    
	 Vector<String> vSrcFile = new Vector<String>();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset=null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 for(oRset = oStmt.executeQuery("select col_head   from columnmodel where part_id='"+part_id+"' and interface_id='"+interface_id+"' order by col_index asc");oRset.next();)
		 {
			 vSrcFile.addElement(oRset.getString(1));
	
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }	
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 

 public static Vector<String> getColModel(String part_id,String interface_id)
 {
	    
	 Vector<String> vSrcFile = new Vector<>();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset=null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 for(oRset = oStmt.executeQuery("select col_name,col_index,col_width,col_editable,col_hidden,key_value,required,minval,maxval,edithidden,influence,email,number_check,custom,custom_func,default_type,default_value,formatter,searchoptions from columnmodel where part_id='"+part_id+"' and interface_id='"+interface_id+"' order by col_index asc");oRset.next();)
		 {
			 vSrcFile.addElement(oRset.getString(1));
			 vSrcFile.addElement(oRset.getString(2));
			 vSrcFile.addElement(oRset.getString(3));
			 vSrcFile.addElement(oRset.getString(4));
			 vSrcFile.addElement(oRset.getString(5));
			 vSrcFile.addElement(oRset.getString(6));
			 vSrcFile.addElement(oRset.getString(7));
			 vSrcFile.addElement(oRset.getString(8));
			 vSrcFile.addElement(oRset.getString(9));
			 vSrcFile.addElement(oRset.getString(10));
			 vSrcFile.addElement(oRset.getString(11));
			 vSrcFile.addElement(oRset.getString(12));
			 vSrcFile.addElement(oRset.getString(13));
			 vSrcFile.addElement(oRset.getString(14));
			 vSrcFile.addElement(oRset.getString(15));
			 vSrcFile.addElement(oRset.getString(16));
			 vSrcFile.addElement(oRset.getString(17));
			 vSrcFile.addElement(oRset.getString(18));
			 vSrcFile.addElement(oRset.getString(19));
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }	
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 

 public static  String getkeyColumns(String interface_id,String part_id)
 {
	 String keycolumns = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select keycolumn_value from keycolumn where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
		 keycolumns=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return keycolumns;
 } 


 public static  String getDeleteParam(String interface_id,String part_id,int query_id)
 {
	 String delete_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;  
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select delete_param_value from delete_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"'");
		 while(resultset.next())
		 {
		 delete_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return delete_param_value;
 } 

 public static  String getDeleteParameter(String interface_id,String part_id,int query_id)
 {
	 String delete_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;   
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select delete_parameter from delete_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"'");
		 while(resultset.next())
		 {
		 delete_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return delete_param_value;
 } 

 public static  String getAddParam(String interface_id,String part_id,int query_id)
 {
	 String add_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select add_param_value from add_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"'");
		 while(resultset.next())
		 {
		 add_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return add_param_value;
 } 
    
    
 public static  String getAddParameter(String interface_id,String part_id,int query_id)
 {
	 String add_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;    
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select add_parameter from add_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"'");
		 while(resultset.next())
		 {
		 add_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return add_param_value;
 }   
 public static  String getModifyParam(String interface_id,String part_id,int query_id)
 {
	 String modify_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select modify_param_value from modify_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"'");
		 while(resultset.next())
		 {
		 modify_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return modify_param_value;
 } 

 public static  String getModifyParameter(String interface_id,String part_id,int query_id)
 {
	 String modify_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select modify_parameter from modify_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"'");
		 while(resultset.next())
		 {
		 modify_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return modify_param_value;
 } 

 public static  String GridQuery(String interface_id,String part_id)
 {
	 String gridquery_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;  
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select active_query from gridquery where part_id='"+part_id+"' and interface_id='"+interface_id+"' ");
		 while(resultset.next())
		 {
		 gridquery_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return gridquery_value;
 } 

 public static Vector<String> getEditOption(String part_id,String interface_id,String colname)
 {
	    
	 Vector<String> vSrcFile = new Vector<>();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset=null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 String query = "select type,size,textarea_rows,textarea_cols,editdomaintype,value,multiple, imageAccessURL from edit_type where part_id='"+part_id+"' and interface_id='"+interface_id+"' and col_name='"+colname+"'";
//		 for(oRset = oStmt.executeQuery("select type,size,rows,cols,editdomaintype,value,multiple from edit_type where part_id='"+part_id+"' and interface_id='"+interface_id+"' and col_name='"+colname+"'");oRset.next();)
		 for(oRset = oStmt.executeQuery(query);oRset.next();)
		 {
			 vSrcFile.addElement(oRset.getString(1));
			 vSrcFile.addElement(oRset.getString(2));
			 vSrcFile.addElement(oRset.getString(3));
			 vSrcFile.addElement(oRset.getString(4));
			 vSrcFile.addElement(oRset.getString(5));
			 vSrcFile.addElement(oRset.getString(6));
			 vSrcFile.addElement(oRset.getString(7));
			 vSrcFile.addElement(oRset.getString(8));
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }	
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 
	
 public static  String getLoadURL(String interface_id,String part_id)
 {
	 String loadurl = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select loadurl from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
		 loadurl=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return loadurl;
 } 
    
 public static  String getEditURL(String interface_id,String part_id)
 {
	 String editurl = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;   
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select editurl from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
		 editurl=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return editurl;
 }  

 public static  String getCaption(String interface_id,String part_id)
 {
	 String caption = "";
	 Connection oConn = null;
	 Statement statement = null;    
	 ResultSet resultset =null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select caption from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
		 caption=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return caption;
 }  

 public static  String getSortname(String interface_id,String part_id)
 {
	 String sortname = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select sortname from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
		 sortname=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return sortname;
 }  

 public static  String getSortOrder(String interface_id,String part_id)
 {
	 String sortorder = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select sortorder from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
		 sortorder=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return sortorder;
 }  

 public static  String getNavBar(String interface_id,String part_id)
 {
	 String sortorder = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;   
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select gridnavbar from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
		 sortorder=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return sortorder;
 }  


 public static Vector getAllColumnNames(String part_id,String interface_id)
 {
	    
	 Vector vSrcFile = new Vector();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset =null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 for(oRset = oStmt.executeQuery("select col_name    from columnmodel where part_id='"+part_id+"' and interface_id='"+interface_id+"' and col_hidden='false' ");oRset.next();)
		 {
			 vSrcFile.addElement(oRset.getString(1));
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }	
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 


 public static Vector<String> getKeyColumnsValue(String interface_id,String part_id)
 {
	    
	 Vector<String> vSrcFile = new Vector<String>();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset=null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 for(oRset = oStmt.executeQuery("select col_name from columnmodel where part_id='"+part_id+"' and interface_id='"+interface_id+"' and key_value='true' ");oRset.next();)
		 {
			 vSrcFile.addElement(oRset.getString(1));
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }	
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 
	
 public static  int getTotalDeleteQuery(String interface_id,String part_id)
 {
	 int keycolumns = 0;
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;   
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select count(query_id) from delete_param where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
		 keycolumns=resultset.getInt(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return keycolumns;
 } 	
	
 public static  int getTotalAddQuery(String interface_id,String part_id)
 {
	 int keycolumns = 0;
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null ;   
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select count(query_id) from add_param where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
		 keycolumns=resultset.getInt(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return keycolumns;
 } 	

 public static  int getTotalModifyQuery(String interface_id,String part_id)
 {
	 int keycolumns =0;
	 Connection oConn = null;
	 Statement statement =null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select count(query_id) from modify_param where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
		 keycolumns=resultset.getInt(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return keycolumns;
 } 	


 public static  String returndropdownstring(String sql_query)
 {
	 String dropdownstring = "";
        
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery(sql_query);
		 while(resultset.next())
		 {
			 if(resultset.isLast())
			 {
				 dropdownstring=dropdownstring+resultset.getString(1)+":"+resultset.getString(2);
		//System.out.println("=============dropdownstring==if======="+dropdownstring);
			 }
			 else
			 {
				 dropdownstring=dropdownstring+resultset.getString(1)+":"+resultset.getString(2)+";";
	//	System.out.println("=============dropdownstring==else======="+dropdownstring);
			 }
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();

	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return dropdownstring;
 } 


 public static  Vector returnpagedropdown(String sql_query)
 {
	 Connection oConn = null;
	 Vector dropdown=new Vector();
	 Statement statement =null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds1.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery(sql_query);
		 while(resultset.next())
		 {
			 dropdown.addElement(resultset.getString(1));
			 dropdown.addElement(resultset.getString(2));
		 }
//		 resultset.close();
//		 statement.close();
//		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 //sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally
	 {
		  try
		  {
		  if (resultset!=null) resultset.close();
		  if (statement!=null) statement.close();
		  if (oConn!=null) oConn.close();
		  
		} catch(Exception e){}		 }
	 return dropdown;
 }


 public static  String getColumnNamefromIndex(String interface_id,String part_id,String  index)
 {
	 String modify_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select col_name from columnmodel where part_id='"+part_id+"' and interface_id='"+interface_id+"' and col_index='"+index+"'");
		 while(resultset.next())
		 {
		 modify_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return modify_param_value;
 } 
	


 public static  String getDBColumnName(String interface_id,String part_id,String  colname)
 {
	 String modify_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;   
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select dbcolumnname from columnmodel where part_id='"+part_id+"' and interface_id='"+interface_id+"' and col_name='"+colname+"'");
		 while(resultset.next())
		 {
		 modify_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return modify_param_value;
 } 

 public static  String getTreeDataRemoteFunction(String interface_id,String part_id)
 {
	 String value = "";
	 Connection oConn = null;
	 Statement statement =null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select treedataremotefunction from tree_structure where part_id='"+part_id+"' and interface_id='"+interface_id+"' ");
		 while(resultset.next())
		 {
		 value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return value;
 } 

 public static  String getOnselectRemoteFunction(String interface_id,String part_id)
 {
	 String value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select onselectremotefunction from tree_structure where part_id='"+part_id+"' and interface_id='"+interface_id+"' ");
		 while(resultset.next())
		 {
		 value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return value;
 } 	
 
 public static  String getOnPostInitFunction(String interface_id,String part_id)
 {
	 String value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select onpostinitfunction from tree_structure where part_id='"+part_id+"' and interface_id='"+interface_id+"' ");
		 while(resultset.next())
		 {
		 value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return value;
 } 

 public static  String getAutoCollapse(String interface_id,String part_id)
 {
	 String value = "";
	 Connection oConn = null;
	 Statement statement =null;
	 ResultSet resultset =null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select autocollapse from tree_structure where part_id='"+part_id+"' and interface_id='"+interface_id+"' ");
		 while(resultset.next())
		 {
		 value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return value;
 } 	


 public static  String getloadinitialize(String interface_id,String part_id)
 {
	 String value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select initialiseonload from tree_structure where part_id='"+part_id+"' and interface_id='"+interface_id+"' ");
		 while(resultset.next())
		 {
		 value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return value;
 } 

 public static Vector<String> getSelector(String interface_id,String part_id)
 {
	   

	 Vector<String> vSrcFile = new Vector<>();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset =null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 //System.out.println("select selector_id,gridrefresh,influence,influencegridcolumn from selector where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 for(oRset = oStmt.executeQuery("select selector_id,gridrefresh,influence,influencegridcolumn from selector where part_id='"+part_id+"' and interface_id='"+interface_id+"'");oRset.next();)
		 {
			 vSrcFile.addElement(oRset.getString(1));
			 vSrcFile.addElement(oRset.getString(2));
			 vSrcFile.addElement(oRset.getString(3));
			 vSrcFile.addElement(oRset.getString(4));
		
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
	 
		 sqlexception.printStackTrace();
	 }
	 catch (Exception ex) {
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 	

 public static  int NoofAddValidationQuery(String interface_id,String part_id)
 {
	 int keycolumns =0;
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select count(query_id) from add_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and type='query'");
		 while(resultset.next())
		 {
		 keycolumns=resultset.getInt(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return keycolumns;
 } 	

 public static  int NoofModifyValidationQuery(String interface_id,String part_id)
 {
	 int keycolumns =0;
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select count(query_id) from modify_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and type='query'");
		 while(resultset.next())
		 {
		 keycolumns=resultset.getInt(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return keycolumns;
 } 	

 public static  int NoofDeleteValidationQuery(String interface_id,String part_id)
 {
	 int keycolumns =0;
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select count(query_id) from delete_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and type='query'");
		 while(resultset.next())
		 {
		 keycolumns=resultset.getInt(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return keycolumns;
 } 	



 public static  String getAddValidationQuery(String interface_id,String part_id,int query_id)
 {
	 String add_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select add_param_value from add_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"'  and type='query'");
		 while(resultset.next())
		 {
		 add_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return add_param_value;
 } 

 public static  String getModifyValidationQuery(String interface_id,String part_id,int query_id)
 {
	 String add_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select modify_param_value from modify_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"' and type='query'");
		 while(resultset.next())
		 {
		 add_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return add_param_value;
 } 

 public static  String getDeleteValidationQuery(String interface_id,String part_id,int query_id)
 {
	 String add_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select delete_param_value from delete_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"' and type='query'");
		 while(resultset.next())
		 {
		 add_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return add_param_value;
 } 



 public static  String getAddValidationMessage(String interface_id,String part_id,int query_id)
 {
	 String add_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select message from add_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"' and type='query'");
		 while(resultset.next())
		 {
		 add_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return add_param_value;
 } 

 public static  String getModifyValidationMessage(String interface_id,String part_id,int query_id)
 {
	 String add_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset =null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select message from modify_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"' and type='query'");
		 while(resultset.next())
		 {
		 add_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return add_param_value;
 } 

 public static  String getDeleteValidationMessage(String interface_id,String part_id,int query_id)
 {
	 String add_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select message from delete_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"' and type='query'");
		 while(resultset.next())
		 {
		 add_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return add_param_value;
 } 


 public static  String getAddValidationParameter(String interface_id,String part_id,int query_id)
 {
	 String param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select add_parameter from add_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"' and type='query'");
		 while(resultset.next())
		 {
		 param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return param_value;
 } 


 public static  String getModifyValidationParameter(String interface_id,String part_id,int query_id)
 {
	 String param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select modify_parameter from modify_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"' and type='query'");
		 while(resultset.next())
		 {
		 param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return param_value;
 } 

 public static  String getDeleteValidationParameter(String interface_id,String part_id,int query_id)
 {
	 String param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select delete_parameter from delete_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"' and type='query'");
		 while(resultset.next())
		 {
		 param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return param_value;
 } 
 /************************************** Partha ***********************************/	
public static int getSqlQueryCount(String sql_query)
 {
	 ResultSet oRset=null;
	 int count=0;
	 Connection oConn = null;
	 Statement statement =null;    
	 try
	 {
		 oConn = ds1.getConnection();
			
		 statement = oConn.createStatement();
		 oRset = statement.executeQuery(sql_query);
		 while(oRset.next())
		 {
			 count=count+1;
		 }	
		 oRset.close();	
		 oConn.close();		
	 }
	 catch(SQLException sqlexception)
	 {
		 //sqlexception.printStackTrace();
	 }
	
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }   
	 return count;
 }
	
	
 public static ResultSet getTableDataFromQuery(String sql_query,int query_limit_start,int int_limit,String sord,String sidx)
 {
	 ResultSet oRset=null;
	 Connection oConn = null;
        
	 try
	 {
		 oConn = ds1.getConnection();
		 Statement statement = oConn.createStatement();
		 if((sord.equals("")) && (sidx.equals("")))
			 oRset = statement.executeQuery(sql_query+" limit "+query_limit_start+","+int_limit);
		 else if((sord.equals("")) && !(sidx.equals("")))
			 oRset = statement.executeQuery(sql_query+" order by "+sidx+" limit "+query_limit_start+","+int_limit);
		 else if(!(sord.equals("")) && (sidx.equals("")))
			 oRset = statement.executeQuery(sql_query+" limit "+query_limit_start+","+int_limit);
		 else
			 oRset = statement.executeQuery(sql_query+" order by "+sidx+" "+sord+" limit "+query_limit_start+","+int_limit);
            	//oConn.close();		
	 }
	 catch(SQLException sqlexception)
	 {
		 //sqlexception.printStackTrace();
	 }
	
        
	 return oRset;
 }	
	
 
	
	

 public static String getSqlQuery(String interface_id,String part_id)
 {
	 ResultSet oRset=null;
	 String s_query="";
	 Connection oConn = null;
	 Statement statement = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 oRset = statement.executeQuery("select grid_query from gridquery where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		 while(oRset.next())
		 {
			 s_query=oRset.getString(1);
		 }	
		 oRset.close();	
		 oConn.close();		
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 if(oRset!=null)
				 {
					 oRset.close();
					 oRset=null;
				 }
				 if(statement!=null)
				 {
					 statement.close();
					 statement=null;
				 }
				 oConn.close();oConn=null;
			 } catch(Exception e){}	
		 }
	 }
	
        
	 return s_query;
 }


 public static void SqlDeleteQuery(String sql_query)
 {
		
	 Connection oConn = null;
	 Statement statement = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 statement.execute(sql_query);
			
		 oConn.close();		
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }    
 }
	
 public static void SqlInsertQuery(String sql_query)
 {
		
	 Connection oConn = null;
	 Statement statement =null;    
	 try
	 {
		 oConn = ds.getConnection();
			
		 statement = oConn.createStatement();
		 statement.execute(sql_query);
			
		 oConn.close();		
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }    
 }
		
 public static void SqlUpdateQuery(String sql_query)
 {
		
	 Connection oConn = null;
	 Statement statement = null;    
	 try
	 {
		 oConn = ds.getConnection();
			
		 statement = oConn.createStatement();
		 statement.execute(sql_query);
			
		 oConn.close();		
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 //System.out.println("DB Grid SQL:  "+sql_query);
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }        
 }
	
 public static boolean ExecuteSqlQuery(String sql_query)
 {
	 Connection oConn = null;
	 Statement statement = null;
	 boolean returnStatus = true;
	 try
	 {
		 oConn = ds1.getConnection();
		 statement = oConn.createStatement();
		// pp=oConn.prepareStatement(sql_query);
		 //pp.execute();
		 statement.execute(sql_query);
		 oConn.close();       			
	 }
	 catch(SQLException sqlexception)
	 {
		 
		 sqlexception.printStackTrace();
		 returnStatus = false;
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }        
	return returnStatus;
 }
 
 
 public static void ExecuteSqlQueryForForm(String sql_query,Vector vv)
 {
	 Connection oConn = null;
	// Statement statement = null;
	 PreparedStatement pp=null;
	 try
	 {
		 oConn = ds1.getConnection();
		 //System.out.println("...................INSERT SQL......................"+sql_query);
		 //System.out.println("...................INSERT SQL....VECTOR.................."+vv.size());
		 pp=oConn.prepareStatement(sql_query);
		
		 for(int i=0;i<vv.size();i++)
		 {
			 //System.out.println("..................... "+(String)vv.elementAt(i));
			 pp.setString((i+1),(String)vv.elementAt(i));
			 
		 }
		 pp.execute();
		 			
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				
				 pp.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }        
	
 }


 public static void updateactiveQuery(String interface_id,String part_id,String sql_query)
 {
	 try
	 {
		 Connection oConn= ds.getConnection();
		 PreparedStatement pstmt = oConn.prepareStatement("update gridquery set active_query=? where interface_id=? and part_id=?");
                 	//statement.execute("update gridquery set active_query='"+sql_query+"' where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		 pstmt.setString(1, sql_query);
		 pstmt.setString(2, interface_id);
		 pstmt.setString(3, part_id);	
		 pstmt.execute();
		 oConn.close();
            			
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	
       
	
 }


 public static String getSqlActiveQuery(String interface_id,String part_id)
 {
		//ResultSet oRset=null;
	 String s_query="";
	 Connection oConn=null;
	 Statement statement = null;	
	 ResultSet oRset = null;
	 try
	 {
		 oConn= ds.getConnection();
		 statement = oConn.createStatement();
		 //System.out.println("select active_query from gridquery where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		 oRset = statement.executeQuery("select active_query from gridquery where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		 while(oRset.next())
		 {
			 s_query=oRset.getString(1);
		 }	
		 //System.out.println("===========s_query=========="+s_query);
		 oRset.close();
		 oConn.close();	
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
        
	 return s_query;
 }

 static String getDropdownSqlQuery(String interface_id,String part_id)
 {
		//ResultSet oRset=null;
	 String s_query="";
	 Connection oConn= null;	
	 Statement statement = null;    
	 ResultSet oRset = null;
	 try
	 {
		 oConn= ds.getConnection();
		 statement = oConn.createStatement();
		 //System.out.println("===update==select value from content where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		 oRset = statement.executeQuery("select value from content where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		 while(oRset.next())
		 {
			 s_query=oRset.getString(1);
		 }	
		 //System.out.println("=====update======s_query=========="+s_query);
		 oRset.close();
		 oConn.close();	
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }    
	 return s_query;
 }

 public static void updatedropdownactiveQuery(String interface_id,String part_id,String sql_query)
 {
	 try
	 {
		 Connection oConn= ds.getConnection();
		 PreparedStatement pstmt = oConn.prepareStatement("update content set active_value=? where interface_id=? and part_id=?");
                 	//statement.execute("update gridquery set active_query='"+sql_query+"' where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		 pstmt.setString(1, sql_query);
		 pstmt.setString(2, interface_id);
		 pstmt.setString(3, part_id);	
		 pstmt.execute();	
		 oConn.close();
            			
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	
      
	
 }


 public static  String returnpagedropdownstring(String sql_query)
 {
	 String dropdownstring = "";
	 Connection oConn=null;
	 Statement statement = null;
	 ResultSet resultset = null;
	           
	 try
	 {
		 oConn= ds1.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery(sql_query);
		 while(resultset.next())
		 {
			 dropdownstring=dropdownstring+"<option value=\""+resultset.getString(1)+"\">"+resultset.getString(2);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace(); 
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
        
	 return dropdownstring;
 }
 /************************************** End of Partha ***********************************/

 /*********************************** Partha on 24.11.08********************/

 public static boolean ExecuteValidationSqlQuery(String sql_query)
 {
	 Connection oConn = null;
	 boolean flag=false;
	 int rowCount=0;
	 Statement statement = null;
	 ResultSet rs=null;  
	 try
	 {
		 oConn = ds1.getConnection();
		 statement = oConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		 rs=statement.executeQuery(sql_query);
		 rs.last();
		 rowCount=rs.getRow();
		 //System.out.println("==============rowCount in ExecuteValidationSqlQuery==="+rowCount);
		 rs.close();
		 oConn.close();       			
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 rs.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
		
	 if(rowCount==0)
		 flag=true;
	 else
		 flag=false;
		
	 
	 return flag;
 }

 /***********************************End Partha on 24.11.08********************/    

		
		 /************************ Partha on 27.11.08 *********************/
				
		 public static  int NoofCustomValidationMethod(String oper,String interface_id,String part_id)
 {
	 int keycolumns =0;
	 ResultSet resultset=null;
	 Connection oConn = null;
	 Statement statement = null;
	 //System.out.println("================oper========"+oper);
	
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 if(oper.equals("Add"))
		 {
			 //System.out.println("select count(query_id) from add_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and type='custom' ");
			 resultset = statement.executeQuery("select count(query_id) from add_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and type='custom' ");
		 }
		 else if(oper.equals("Edit"))
		 {
			 //System.out.println("select count(query_id) from modify_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and type='custom' ");
			
			 resultset = statement.executeQuery("select count(query_id) from modify_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and type='custom' ");
		 }
		 else
		 {
			 //System.out.println("select count(query_id) from delete_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and type='custom' ");
			 resultset = statement.executeQuery("select count(query_id) from delete_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and type='custom' ");
		 }
		 resultset.next();
		 keycolumns=resultset.getInt(1);
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
			
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return keycolumns;
 } 	
		
		
 public static  String getCustomValidationClass(String oper,String interface_id,String part_id,int query_id)
 {
	 ResultSet resultset=null;
	 String add_param_value = "";
	 Connection oConn = null;
	 Statement statement =null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 if(oper.equals("Add"))
			 resultset = statement.executeQuery("select function_name from add_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"' and type='custom' ");
		 else if(oper.equals("Edit"))
			 resultset = statement.executeQuery("select function_name from modify_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"' and type='custom' ");
		 else
			 resultset = statement.executeQuery("select function_name from delete_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"' and type='custom' ");
		 resultset.next();
		 add_param_value=resultset.getString(1);
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return add_param_value;
 }
 		
 		
 public static  String getCustomValidationMessage(String oper,String interface_id,String part_id,int query_id)
 {
	 ResultSet resultset=null;
	 String add_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 if(oper.equals("Add"))
			 resultset = statement.executeQuery("select message from add_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"' and type='custom' ");
		 else if(oper.equals("Edit"))
			 resultset = statement.executeQuery("select message from modify_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"' and type='custom' ");
		 else
			 resultset = statement.executeQuery("select message from delete_validation_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"' and type='custom' ");
		 resultset.next();
		 add_param_value=resultset.getString(1);
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return add_param_value;
 } 		
		
 /************************ End of Partha on 27.11.08 ***************/		
		
public static  String getAddnavbarexistcheck(String interface_id,String part_id)
 {
	 String status = "";
	 Connection oConn= null;
	 Statement statement = null;
	 ResultSet resultset =null;
	 try
	 {
		 oConn= ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select query_id from add_param where interface_id='"+interface_id+"' and part_id='"+part_id+"' ");
		 while(resultset.next())
		 {
			 status=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return status;
 }

 public static  String getModifynavbarexistcheck(String interface_id,String part_id)
 {
	 String status = "";
	 Connection oConn= null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn= ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select query_id from modify_param where interface_id='"+interface_id+"' and part_id='"+part_id+"' ");
		 while(resultset.next())
		 {
			 status=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return status;
 }

 public static  String getDeletenavbarexistcheck(String interface_id,String part_id)
 {
	 String status = "";
	 Connection oConn=null;
	 Statement statement =null;
	 ResultSet resultset =null;
	 try
	 {
		 oConn= ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select query_id from delete_param where interface_id='"+interface_id+"' and part_id='"+part_id+"' ");
		 while(resultset.next())
		 {
			 status=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return status;
 }



 /**************************** Partha on 01.12.2008 ***********************/
				    
		 public static  String getActionSequence(String oper,String interface_id,String part_id)
 {
	 ResultSet resultset=null;
	 String add_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 if(oper.equals("Add"))
			 resultset = statement.executeQuery("select action_sequence from add_action_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' ");
		 else if(oper.equals("Edit"))
			 resultset = statement.executeQuery("select action_sequence from modify_action_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' ");
		 else
			 resultset = statement.executeQuery("select action_sequence from delete_action_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' ");
		 boolean result = resultset.next();
//		 add_param_value=resultset.getString(1);
		 if (result) add_param_value=resultset.getString(1);
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return add_param_value;
 }							
				
 public static  String getCustomActionClass(String oper,String interface_id,String part_id)
 {
	 ResultSet resultset=null;
	 String add_param_value = "";
	 Connection oConn = null;
	 Statement statement =null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 if(oper.equals("Add"))
			 resultset = statement.executeQuery("select action_name from add_action_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' ");
		 else if(oper.equals("Edit"))
			 resultset = statement.executeQuery("select action_name from modify_action_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' ");
		 else
			 resultset = statement.executeQuery("select action_name from delete_action_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' ");
		 resultset.next();
		 add_param_value=resultset.getString(1);
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return add_param_value;
 }				
				
 /**************************** End of Partha on 01.12.2008 ***********************/

		 /************************* Partha on 26.11.08 ***********************/
				
		 public static String getGridSelectSqlQuery(String interface_id,String part_id,String column_id)
 {
		//ResultSet oRset=null;
	 String s_query="";
	 Connection oConn =null;
	 Statement statement = null;
	 ResultSet oRset = null;
		
        
	 try
	 {
		 oConn =ds.getConnection();
		 statement = oConn.createStatement();
		 //System.out.println("select active_value from edit_type where interface_id='"+interface_id+"' and part_id='"+part_id+"' and col_name='"+column_id+"' ");
		 oRset = statement.executeQuery("select active_value from edit_type where interface_id='"+interface_id+"' and part_id='"+part_id+"' and col_name='"+column_id+"' ");
		 while(oRset.next())
		 {
			 s_query=oRset.getString(1);
		 }	
			
		 oRset.close();
		 statement.close();
		 oConn.close();	
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
		
	 return s_query;
 }		
			
	
	
 public static void updateGridSelectActiveQuery(String interface_id,String part_id,String column_id,String sql_query)
 {
	 Connection oConn = null;	
		
		
	 try
	 {
		 oConn = ds.getConnection();
		
		 PreparedStatement pstmt = oConn.prepareStatement("update edit_type set active_value=? where interface_id=? and part_id=? and col_name=?");
                 	//statement.execute("update gridquery set active_query='"+sql_query+"' where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		 pstmt.setString(1, sql_query);
		 pstmt.setString(2, interface_id);
		 pstmt.setString(3, part_id);	
		 pstmt.setString(4, column_id);
		 pstmt.execute();
            			
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	
 }	
			
	
 public static String getSelectOption(String interface_id,String part_id,String column_id)
 {
		//ResultSet oRset=null;
	 String s_query="";
	 Connection oConn = null;
	 Statement statement =null;
	 ResultSet oRset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 //System.out.println("select value from edit_type where interface_id='"+interface_id+"' and part_id='"+part_id+"' and col_name='"+column_id+"' ");
		 oRset = statement.executeQuery("select active_value from edit_type where interface_id='"+interface_id+"' and part_id='"+part_id+"' and col_name='"+column_id+"' ");
		 while(oRset.next())
		 {
			 s_query=oRset.getString(1);
		 }	
			
		 oRset.close();
		 statement.close();
		 oConn.close();		
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
		
	 return s_query;
 }		
	

	
 /*************************** End of Partha on 26.11.08 ******************/
		 public static  String getPageTitle(String interface_id)
 {
	 String pagetitle = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;   
	 try
	 {
		 oConn = ds.getConnection();
            //checkConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select interface_title from interface where interface_id='"+interface_id+"' ");
		 while(resultset.next())
		 {
			 pagetitle=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return pagetitle;
 } 


 public static  String getSessionCheck(String interface_id)
 {
	 String createsession = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		//checkConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select createsession from configuration_item where interface_id='"+interface_id+"' ");
		 //System.out.println("select createsession from configuration_item where interface_id='"+interface_id+"' ");
		 while(resultset.next())
		 {
			 createsession=resultset.getString(1);
			 if (createsession==null) createsession="";
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return createsession;
 } 

 public static  String getRoleCheck(String interface_id)
 {
	 String createsession = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		//checkConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select checkrole from configuration_item where interface_id='"+interface_id+"' ");
		 while(resultset.next())
		 {
			 createsession=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return createsession;
 } 

 public static  String getContentTypeHtml(String interface_id)
 {
	 String contentType = null;
	 //Connection oConn = null;
	 //Statement statement = null;
	 //ResultSet resultset = null;
	 try(Connection oConn = ds.getConnection()) {
		//checkConnection();
		 PreparedStatement statement = oConn.prepareStatement("select contenttype from configuration_item where interface_id=?");
		 //resultset = statement.executeQuery("select contenttype from configuration_item where interface_id='"+interface_id+"' ");
		 statement.setString(1,interface_id);
		 ResultSet resultset = statement.executeQuery();
		 while(resultset.next())
		 {
			 contentType = resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 
	 return contentType;
 }

 public static  String getStyleValueroot(String layout,String style,String interface_id)
 {
	 String value = null;
        
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
            //checkConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select a.value from style a,roleassociation b  where a.part_id='root' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and b.style_id='"+style+"' and a.style_id=b.style_id");
		 while(resultset.next())
		 {
			 value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
            //System.out.println("Inside NewDataBaseLayer getPluginName(), Exception !!!!");
		 exception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return value;
 }  
 
 public static  String getFormControl(String interface_id,String part_id)
 {
	 String value = null;
        
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select jscontrol from formelement where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		 while(resultset.next())
		 {
			 value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return value;
 }  

 public static  String[] getFormMethod(String interface_id,String part_id)
 {
	 String[] formMethods = new String[3];
        
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset =null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select formmethod,success_method,failure_method from formelement where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		 while(resultset.next())
		 {
			 formMethods[0]=(resultset.getString(1));
			 formMethods[1]=(resultset.getString(2));
			 formMethods[2]=(resultset.getString(3));
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return formMethods;
 }  


 public static  String getFormAction(String interface_id,String part_id)
 {
	 String value = null;
        
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select formaction from formelement where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		 while(resultset.next())
		 {
			 value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return value;
 }  

 public static  String getParentFormName(String interface_id,String part_id)
 {
	 String value = null;
        
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select part_id from form_element where interface_id='"+interface_id+"' and element_id='"+part_id+"'");
		 while(resultset.next())
		 {
			 value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return value;
 }  
	 
 public static Vector getHTML(String role,String interface_id)
 {
	         
	 Vector vSrcFile = new Vector();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		//System.out.println("select a.valueblob from interfaceenginecalling a,roleassociation b where a.layout_id=b.layout_id and a.content_id=b.content_id and a.behaviour_id=b.behaviour_id and a.style_id=b.style_id and b.interface_id='"+interface_id+"' and b.role_id='"+role+"' and a.interface_id=b.interface_id");
		for(oRset = oStmt.executeQuery("select a.valueblob from interfaceenginecalling a,roleassociation b where a.layout_id=b.layout_id and a.content_id=b.content_id and a.behaviour_id=b.behaviour_id and a.style_id=b.style_id and b.interface_id='"+interface_id+"' and b.role_id='"+role+"' and a.interface_id=b.interface_id");oRset.next();)
		 {
              		
			
			 vSrcFile.addElement(oRset.getAsciiStream(1));
			 
	            
		 }
		 oRset.close();  
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	
	 }
	 catch (Exception ex) {
		 ex.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 
 
 public static  String getcachecontrol(String interface_id)
 {
	 String cachecontrol = null;
	 //Connection oConn = null;
	 //Statement statement = null;
	 //ResultSet resultset = null;
	 try(Connection oConn = ds.getConnection()) {
		//checkConnection();
		 PreparedStatement statement = oConn.prepareStatement("select cachecontrol from configuration_item where interface_id=?");
		 statement.setString(1,interface_id);
		 ResultSet resultset = statement.executeQuery();
		 while(resultset.next())
		 {
			 cachecontrol=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 
	 return cachecontrol;
 }
 public static  String getexpires(String interface_id)
 {
	 String expires ="0";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		//checkConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select expires from configuration_item where interface_id='"+interface_id+"' ");
		 //System.out.println("select expires from configuration_item where interface_id='"+interface_id+"' ");
		 
		 while(resultset.next())
		 {
			 expires=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return expires;
 }
 
 public static  Timestamp getLastModified(String interface_id)
 {
	 Timestamp interfaceLastModified =null;
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		//checkConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select last_updated from interfaceenginecalling where interface_id='"+interface_id+"' ");
		 while(resultset.next())
		 {
			 interfaceLastModified=resultset.getTimestamp(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return interfaceLastModified;
 }

 public static  Timestamp getResourceLastModified(String interface_id, String resource_id)
 {
	 Timestamp resourceLastModified =null;
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		//checkConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select date from resource where interface_id='"+interface_id+"' and resource_id='"+resource_id+"' ");
		 while(resultset.next())
		 {
			 resourceLastModified=resultset.getTimestamp(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return resourceLastModified;
 }

 public static  String checkinlinecss(String interface_id)
 {
	 String check = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select inlinecss from framework_file where framework_file_id='"+interface_id+"'");
		 while(resultset.next())
		 {
			 check=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();

	 }
	 catch(SQLException sqlexception)
	 {
	 
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return check;
 } 
 
 public static  String checkinlinejs(String interface_id)
 {
	 String check = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select inlinejs from framework_file where framework_file_id='"+interface_id+"'");
		 while(resultset.next())
		 {
			 check=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();

	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();    
		 
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return check;
 } 
 
 
 public static String  getcssandjs(String resource_id,String interface_id)
 {
	    
   
	 String vSrcFile ="";
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset = null;
	   
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
       for(oRset = oStmt.executeQuery("select value from resource where resource_id='"+resource_id+"' and interface_id='"+interface_id+"'");oRset.next();)
		 {
			 vSrcFile=(oRset.getString(1));
			
		 }
		 oRset.close();
	    oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }
	 catch (Exception ex) {
		 ex.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 

 public static  String checkimagefrompath(String interface_id)
 {
	 String check = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select imagepath from framework_file where framework_file_id='"+interface_id+"'");
		 while(resultset.next())
		 {
			 check=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();

	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return check;
 } 
 
 public static  String getImageName(String resource_id,String interface_id)
 {
	 String imagename = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select href from resource where interface_id='"+interface_id+"' and resource_id='"+resource_id+"'");
		 while(resultset.next())
		 {
			 imagename=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();

	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return imagename;
 } 
 
 public static  String templateexist(String interface_id)
 {
	 String checkjquery = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select template from configuration_item where interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
			 checkjquery=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();

	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return checkjquery;
 } 
 
 public static Vector<String> getTemplateAssestDetails(String template)
 {
    Vector<String> vtemplate = new Vector<String>();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset =null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 for(oRset = oStmt.executeQuery("select asset_type,file_name,location,delivery_mode,asset_path from application_template_asset where application_template_id='"+template+"' order by sequence_no desc");oRset.next();)
		 {
			 vtemplate.addElement(oRset.getString(1));
			 vtemplate.addElement(oRset.getString(2));
			 vtemplate.addElement(oRset.getString(3));  
			 vtemplate.addElement(oRset.getString(4)); 
			 vtemplate.addElement(oRset.getString(5));     
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }
	 catch (Exception ex) {
		
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vtemplate;		
 } 

 public static  String gettemplate_id(String template)
 {
	 String template_id = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select application_template_id from application_template_master where application_template_title='"+template+"'");
		 while(resultset.next())
		 {
			 template_id=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();

	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return template_id;
 } 
 
 public static  List<String> get_default_template_id()
 {
	 String template_id = "";
	 String templateName = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select application_template_id,application_template_title from application_template_master where  default_value='yes'");
		 while(resultset.next())
		 {
			 template_id=resultset.getString(1);
			 templateName=resultset.getString(2);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 List<String> returnValues=new ArrayList<>();
	 returnValues.add(template_id);
	 returnValues.add(templateName);
	 return returnValues;
 } 
 
 
 
 public static  String getmultiselect(String interface_id,String part_id)
 {
	 String multiselect = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select multiselect from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
			 multiselect=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return multiselect;
 }  
 
 
 public static  String getmultiboxonly(String interface_id,String part_id)
 {
	 String multiboxonly = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select multiboxonly from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
			 multiboxonly=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return multiboxonly;
 }  
 
 public static  String resetSearchOnClose(String interface_id,String part_id)
 {
	 String searchonclose = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select resetSearchOnClose from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
			 searchonclose=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return searchonclose;
 }  
 
 
 public static  String multiplesearch(String interface_id,String part_id)
 {
	 String multiplesearch = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select multiplesearch from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
			 multiplesearch=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return multiplesearch;
 }  
 
 public static  String GetCustomEditButton(String interface_id,String part_id)
 {
	 String customeditbutton = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select customeditbutton from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
			 customeditbutton=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return customeditbutton;
 }  
 
 
 /*public static  String getbehaviourlocationforroot(String layout,String behaviour,String interface_id)
 {
	 String value = null;
        
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select a.resource_location from behaviour a,roleassociation b  where a.part_id='root' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and b.behaviour_id='"+behaviour+"' and a.behaviour_id=b.behaviour_id");
		 while(resultset.next())
		 {
			 value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
            //System.out.println("Inside NewDataBaseLayer getPluginName(), Exception !!!!");
		 exception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return value;
 }  */
 
 
 public static  String getBehaviourresource_location(String layout,String behaviour,String part_id,String interface_id)
 {
	 String contentvalue = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
            //checkConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select a.resource_location from behaviour a,roleassociation b where a.part_id='"+part_id+"' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and b.behaviour_id='"+behaviour+"' and a.behaviour_id=b.behaviour_id");
		 while(resultset.next())
		 {
			 contentvalue=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return contentvalue;
 } 
 
 
 public static  String getForm_element_type(String interface_id,String part_id)
 {
	 String contentvalue = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
            //checkConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select element_type from form_element where interface_id='"+interface_id+"' and element_id='"+part_id+"' ");
		 while(resultset.next())
		 {
			 contentvalue=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return contentvalue;
 } 
 
 public static  String getForm_element_placeholder(String interface_id,String part_id)
 {
	 String contentvalue = null;
//	 Connection oConn = null;
//	 Statement statement = null;
	 ResultSet resultset = null;    
	 try (Connection oConn = ds.getConnection();
		   Statement statement = oConn.createStatement(); )
	 {
		 
		 resultset = statement.executeQuery("select placeholder from form_element where interface_id='"+interface_id+"' and element_id='"+part_id+"' ");
		 while(resultset.next())
		 {
			 contentvalue=resultset.getString(1);
		 }
		 resultset.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }

	 return contentvalue;
 } 

 public static  String getFormlabel_for(String interface_id,String part_id)
 {
	 String forlabel = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
            //checkConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select forlabel from form_element where interface_id='"+interface_id+"' and element_id='"+part_id+"' ");
		 while(resultset.next())
		 {
			 forlabel=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return forlabel;
 } 
 
 public static  int getNo_of_AddQuery_Form(String interface_id,String part_id)
 {
	 int keycolumns =0;
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 //System.out.println("select count(query_id) from form_add_param where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 resultset = statement.executeQuery("select count(query_id) from form_add_param where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
			 keycolumns=resultset.getInt(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return keycolumns;
 }
  	
 
 public static  int getNo_of_ModifyQuery_Form(String interface_id,String part_id)
 {
	 int keycolumns =0;
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select count(query_id) from form_modify_param where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
			 keycolumns=resultset.getInt(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return keycolumns;
 }
 public static  String get_Add_Query(String interface_id,String part_id,int query_id)
 {
	 String add_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select add_param_value from form_add_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"'");
		 while(resultset.next())
		 {
			 add_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return add_param_value;
 } 
 
 public static  String get_Modify_Query(String interface_id,String part_id,int query_id)
 {
	 String add_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select modify_param_value from form_modify_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"'");
		 while(resultset.next())
		 {
			 add_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return add_param_value;
 } 
 
 public static Vector getdbform_element(String interface_id,String part_id)
 {
	   
       // String str[]=new String[2];
	 Vector vSrcFile = new Vector();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset =null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 for(oRset = oStmt.executeQuery("select element_id,element_type from form_element where  interface_id='"+interface_id+"' and part_id='"+part_id+"' and (element_type='auto' or element_type='entered') order by insertindex");oRset.next();)
		 {
			 vSrcFile.addElement(oRset.getString(1));
			 vSrcFile.addElement(oRset.getString(2));
	            
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();		 
	 }
	 catch (Exception ex) {
		  //  System.out.println("Exception in ConfDatabaseLayer.getFileDetails()");
		  //  System.out.println("The Error Message - " + ex.getMessage());
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 
 
 public static Vector getdbform_element_modify(String interface_id,String part_id)
 {
	   
       // String str[]=new String[2];
	 Vector vSrcFile = new Vector();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset =null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 for(oRset = oStmt.executeQuery("select element_id,element_type from form_element where  interface_id='"+interface_id+"' and part_id='"+part_id+"' and (element_type='auto' or element_type='entered') order by modifyindex");oRset.next();)
		 {
			 vSrcFile.addElement(oRset.getString(1));
			 vSrcFile.addElement(oRset.getString(2));
	            
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }
	 catch (Exception ex) {
		 ex.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 
 
 
 public static Vector getdbform_element_select(String interface_id,String part_id)
 {
	   
       // String str[]=new String[2];
	 Vector vSrcFile = new Vector();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset =null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 for(oRset = oStmt.executeQuery("select element_id,element_type from form_element where  interface_id='"+interface_id+"' and part_id='"+part_id+"' and (element_type='auto' or element_type='entered') order by selectindex");oRset.next();)
		 {
			 vSrcFile.addElement(oRset.getString(1));
			 vSrcFile.addElement(oRset.getString(2));
	            
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }
	 catch (Exception ex) {
		 ex.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 
 
 public static  String getAddSqlForm(String interface_id,String part_id)
 {
	 String sqlvalue = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select query_id from form_add_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='1'");
		 while(resultset.next())
		 {
			 sqlvalue=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return sqlvalue;
 } 
 
 
 public static  String getModifySqlForm(String interface_id,String part_id)
 {
	 String sqlvalue = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select query_id from form_modify_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='1'");
		 while(resultset.next())
		 {
			 sqlvalue=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return sqlvalue;
 } 
 
 
 public static  String getSelectSqlForm(String interface_id,String part_id)
 {
	 String sqlvalue = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select query_id from form_select_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='1'");
		 while(resultset.next())
		 {
			 sqlvalue=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return sqlvalue;
 } 
 
 
 public static String[] getdbform_element1(String interface_id,String part_id)
 {
	 String [] arr; 
	 Statement statement1 = null;
	 ResultSet oRset1 = null;
	
	 arr = null; 
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset =null;
	 int i=0;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 statement1 = oConn.createStatement();
		 oRset1=statement1.executeQuery("select count(element_id) from form_element where interface_id='"+interface_id+"' and part_id='"+part_id+"' and (element_type='auto' or element_type='entered')");
		 oRset1.next();
		 int countElement=oRset1.getInt(1);
		 
		 arr=new String[countElement];
		 
		 for(oRset = oStmt.executeQuery("select element_id from form_element where  interface_id='"+interface_id+"' and part_id='"+part_id+"' and (element_type='auto' or element_type='entered') order by selectindex");oRset.next();)
		 {
			 arr[i]=oRset.getString(1);
			 i++;    
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }
	 catch (Exception ex) {
		 ex.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return arr;		
 } 
 
 public static  int getNo_of_SelectQuery_Form(String interface_id,String part_id)
 {
	 int keycolumns =0;
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select count(query_id) from form_select_param where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
			 keycolumns=resultset.getInt(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return keycolumns;
 }
 
 public static  String get_Select_Query(String interface_id,String part_id,int query_id)
 {
	 String add_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select select_param_value from form_select_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"'");
		 while(resultset.next())
		 {
			 add_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return add_param_value;
 } 
 
 public static String[] ExecuteSqlQueryForm(String interface_id,String part_id,String sql_query)
 {
	 Connection oConn = null;
	 Connection oConn1 = null;
	 Statement statement = null;
	 Statement statement1 = null;
	 ResultSet oRset1 = null;
	 String arr[]=null;
	 ResultSet oRset = null;
	 int i=0;
	 try
	 {
		 oConn = ds.getConnection();
		 oConn1 = ds1.getConnection();
		 statement = oConn1.createStatement();
		 statement1 = oConn.createStatement();
		 oRset1=statement1.executeQuery("select count(element_id) from form_element where interface_id='"+interface_id+"' and part_id='"+part_id+"' and (element_type='auto' or element_type='entered')");
		 oRset1.next();
		 int countElement=oRset1.getInt(1);
		 arr=new String[countElement];
		 for(oRset = statement.executeQuery(sql_query);oRset.next();)
		 {
			 for(int j=1;j<=countElement;j++)
			 {
			 arr[i]=oRset.getString(j);
			 i++;    
			 }
		 }
		
		 oConn.close();       			
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null || oConn1!=null)
		 {
			 try
			 {
				 oRset.close();
				 statement.close();
				 oConn.close();
				 oConn1.close();
			 } catch(Exception e){}	
		 }
	 }        
	 return arr;
 }
 
 public static  String getAddQueryParameterForm(String interface_id,String part_id,int query_id)
 {
	 String modify_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select add_parameter from form_add_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"'");
		 while(resultset.next())
		 {
			 modify_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return modify_param_value;
 } 
 
 public static  String getSelectQueryParameterForm(String interface_id,String part_id,int query_id)
 {
	 String modify_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select select_parameter from form_select_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"'");
		 while(resultset.next())
		 {
			 modify_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return modify_param_value;
 } 
 
 public static  String getModifyQueryParameterForm(String interface_id,String part_id,int query_id)
 {
	 String modify_param_value = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select modify_parameter from form_modify_param where part_id='"+part_id+"' and interface_id='"+interface_id+"' and query_id='"+query_id+"'");
		 while(resultset.next())
		 {
			 modify_param_value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return modify_param_value;
 } 
 
 public static  String getClass(String interface_id,String part_id)
 {
	 String sqlvalue = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select part_class from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"' ");
		 while(resultset.next())
		 {
			 sqlvalue=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return sqlvalue;
 } 
 
 
 public  static Vector getDatePartID(String interface_id) {
	 Statement  oStmt1=null;
	 ResultSet  oRset1=null;
	 Vector vAdministratorList = new Vector();
	 Connection oConn = null;
      
	 try
	 {
		 oConn = ds.getConnection();
		 oConn.createStatement();
		 oStmt1 = oConn.createStatement();
		 oConn.createStatement();
		 for(oRset1=oStmt1.executeQuery("select part_id,dateformat from structure where interface_id='"+interface_id+"' and part_class='formdate'");oRset1.next();)
		 {
         
			 String part_id1=oRset1.getString(1);
			 String dateformat=oRset1.getString(2);
			 vAdministratorList.addElement(part_id1);
			 vAdministratorList.addElement(dateformat);      
		 }
            		
		 oRset1.close();
		 oStmt1.close();		
		 oConn.close();
	 }
	 catch (SQLException e){
		 e.getMessage();
		 e.printStackTrace();      		
	 }
	 catch (Exception ex){
      		//System.out.println(" error due to java.lang.exception");
		 ex.printStackTrace();
      		
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset1.close();
				 oStmt1.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vAdministratorList;
 }
 
 
 public  static Vector getDB_Form_ID(String interface_id) {
	 Statement  oStmt1=null;
	 ResultSet  oRset1=null;
	 Vector vAdministratorList = new Vector();
	 Connection oConn = null;
      
	 try
	 {
		 oConn = ds.getConnection();
		 oConn.createStatement();
		 oStmt1 = oConn.createStatement();
		 oConn.createStatement();
		 for(oRset1=oStmt1.executeQuery("select part_id from structure where interface_id='"+interface_id+"' and part_class='DBform'");oRset1.next();)
		 {
         
			 String part_id1=oRset1.getString(1);
			 vAdministratorList.addElement(part_id1);
                
		 }
            		
		 oRset1.close();
		 oStmt1.close();		
		 oConn.close();
	 }
	 catch (SQLException e){
		 e.getMessage();
		 e.printStackTrace();      		
	 }
	 catch (Exception ex){
      		//System.out.println(" error due to java.lang.exception");
		 ex.printStackTrace();
      		
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset1.close();
				 oStmt1.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vAdministratorList;
 }

 public  static Vector getFormElementForJs(String interface_id,String part_id) {
	 Statement  oStmt1=null;
	 Statement  oStmt2=null;
	 ResultSet  oRset1=null;
	 ResultSet  oRset2=null;
	 Vector vAdministratorList = new Vector();
	 Connection oConn = null;
      
	 try
	 {
		 oConn = ds.getConnection();
		 oConn.createStatement();
		 oStmt1 = oConn.createStatement();
		 oStmt2 = oConn.createStatement();
		 for(oRset1=oStmt1.executeQuery("select element_id,forlabel,required,minlength,maxlength,equalto,numbercheck,email from form_element a where interface_id='"+interface_id+"' and part_id='"+part_id+"' and (element_type='auto' or element_type='entered')");oRset1.next();)
		 {
           
			 vAdministratorList.addElement(oRset1.getString(1));
			 oRset2=oStmt2.executeQuery("select part_class from structure where interface_id='"+interface_id+"' and part_id='"+oRset1.getString(1)+"'");
			 while(oRset2.next())
			 {
				 vAdministratorList.addElement(oRset2.getString(1));
			 }
			 vAdministratorList.addElement(oRset1.getString(2));
			 vAdministratorList.addElement(oRset1.getString(3));
			 vAdministratorList.addElement(oRset1.getString(4));
			 vAdministratorList.addElement(oRset1.getString(5));
			 vAdministratorList.addElement(oRset1.getString(6));
			 vAdministratorList.addElement(oRset1.getString(7));
			 vAdministratorList.addElement(oRset1.getString(8));
		 }
            		
	 }
	 catch (SQLException e){
		 e.getMessage();
		 e.printStackTrace();      		
	 }
	 catch (Exception ex){
      		//System.out.println(" error due to java.lang.exception");
		 ex.printStackTrace();
      		
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 if(oRset2!=null && !oRset2.isClosed()){
					 oRset2.close();
				 }
				 if(oStmt2!=null && !oStmt2.isClosed()){
					 oStmt2.close();
				 }
				 
				 if(oRset1!=null && !oRset1.isClosed()){
					 oRset1.close();
				 }
				 if(oStmt1!=null && !oStmt1.isClosed()){
					 oStmt1.close();
				 }
				 if(oConn!=null && !oConn.isClosed()){
					 oConn.close();
				 }
				 
			 } catch(Exception e){
				 e.printStackTrace();
			 }	
		 }
	 }
	 return vAdministratorList;
 }
 
 public  static Vector getFormElementForJsMessage(String interface_id,String part_id) {
	 Statement  oStmt1=null;
	 Statement  oStmt2=null;
	 ResultSet  oRset1=null;
	 ResultSet  oRset2=null;
	 Vector vAdministratorList = new Vector();
	 Connection oConn = null;
      
	 try
	 {
		 oConn = ds.getConnection();
		 oConn.createStatement();
		 oStmt1 = oConn.createStatement();
		 oStmt2 = oConn.createStatement();
		 for(oRset1=oStmt1.executeQuery("select element_id,requiredmess,minlengthmess,maxlengthmess,equaltomess,numbercheckmess,emailmess from form_element a where interface_id='"+interface_id+"' and part_id='"+part_id+"' and (element_type='auto' or element_type='entered')");oRset1.next();)
		 {
           
			 vAdministratorList.addElement(oRset1.getString(1));
			 oRset2=oStmt2.executeQuery("select part_class from structure where interface_id='"+interface_id+"' and part_id='"+oRset1.getString(1)+"'");
			 while(oRset2.next())
			 {
				 vAdministratorList.addElement(oRset2.getString(1));
			 }
			 vAdministratorList.addElement(oRset1.getString(2));
			 vAdministratorList.addElement(oRset1.getString(3));
			 vAdministratorList.addElement(oRset1.getString(4));
			 vAdministratorList.addElement(oRset1.getString(5));
			 vAdministratorList.addElement(oRset1.getString(6));
			 vAdministratorList.addElement(oRset1.getString(7));
			 
		 }
            		
		 oRset2.close();
		 oStmt2.close();		
		 oRset1.close();
		 oStmt1.close();	
		 oConn.close();
	 }
	 catch (SQLException e){
		 e.getMessage();
		 e.printStackTrace();      		
	 }
	 catch (Exception ex){
      		//System.out.println(" error due to java.lang.exception");
		 ex.printStackTrace();
      		
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset2.close();
				 oStmt2.close();
				 oRset1.close();
				 oStmt1.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vAdministratorList;
 }
 
 public static  String getbehaviourfunctionnameforGrid(String interface_id,String part_id)
 {
	 String name = null;
        
	 Connection oConn = null;
	 Statement statement =null;
	 ResultSet resultset =null;   
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select value from behaviour where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
			 name=resultset.getString(1);
				
		 }	
		 resultset.close();
		 statement.close();
		 oConn.close();
		  
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
           // System.out.println("Inside NewDataBaseLayer getPluginName(), Exception !!!!");
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return name;
 } 
 
 
 public static  String getbehavioureventnameforGrid(String interface_id,String part_id)
 {
	 String name = null;
        
	 Connection oConn = null;
	 Statement statement =null;
	 ResultSet resultset =null;   
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select behaviourevent from behaviour where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
			 name=resultset.getString(1);
				
		 }	
		 resultset.close();
		 statement.close();
		 oConn.close();
		  
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
           // System.out.println("Inside NewDataBaseLayer getPluginName(), Exception !!!!");
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return name;
 } 
 
 public static  Vector<String> getbehaviourforGrid(String interface_id,String part_id)
 {
	 Connection oConn = null;
	 Statement statement =null;
	 ResultSet resultset =null;   
	 Vector<String> behaviour = new Vector<String>();
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select behaviourevent,value,valuetype,resourceid from behaviour where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 while(resultset.next())
		 {
			 behaviour.addElement(resultset.getString(1));
			 behaviour.addElement(resultset.getString(2));
			 behaviour.addElement(resultset.getString(3));
			 behaviour.addElement(resultset.getString(4));	
		 }	
		 resultset.close();
		 statement.close();
		 oConn.close();
	 
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
           // System.out.println("Inside NewDataBaseLayer getPluginName(), Exception !!!!");
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return behaviour;
 }  
 
 public static Vector getAllSelectorId(String interface_id,String part_id)
 {
	   

	 Vector vSrcFile = new Vector();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset =null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
//		 System.out.println("select selector_id from selector where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		 for(oRset = oStmt.executeQuery("select selector_id from selector where part_id='"+part_id+"' and interface_id='"+interface_id+"'");oRset.next();)
		 {
			 vSrcFile.addElement(oRset.getString(1));
			 
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }
	 catch (Exception ex) {
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return vSrcFile;		
 } 
 
 public static  String requiredcheck(String interface_id,String part_id)
 {
	 String required = "";
	 Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
            //checkConnection();
		 statement = oConn.createStatement();
		 //System.out.println("select required from form_element where interface_id='"+interface_id+"' and element_id='"+part_id+"' ");
		 resultset = statement.executeQuery("select required from form_element where interface_id='"+interface_id+"' and element_id='"+part_id+"' ");
		 while(resultset.next())
		 {
			 required=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 finally{
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return required;
 } 
 
 public static Vector getBehaviourAll(String layout,String behaviour,String part_id,String interface_id)
 {
	 Vector BehaviourAll = new Vector();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset =null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 for(oRset = oStmt.executeQuery("select a.behaviourevent,a.valuetype,a.value,a.target,a.resourceid from behaviour a,roleassociation b where a.part_id='"+part_id+"' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and b.behaviour_id='"+behaviour+"' and a.behaviour_id=b.behaviour_id");oRset.next();)
		 {
			 BehaviourAll.addElement(oRset.getString(1));
			 BehaviourAll.addElement(oRset.getString(2));
			 BehaviourAll.addElement(oRset.getString(3));
			 BehaviourAll.addElement(oRset.getString(4));
			 BehaviourAll.addElement(oRset.getString(5));
			// BehaviourAll.addElement(oRset.getString(6)); 
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }
	 catch (Exception ex) {
		  //  System.out.println("Exception in ConfDatabaseLayer.getFileDetails()");
		  //  System.out.println("The Error Message - " + ex.getMessage());
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return BehaviourAll;		
 } 
 
 public static Vector<String> getRowNumandList(String interface_id,String part_id)
 {
	 Vector<String> BehaviourAll = new Vector<String>();
	 Statement  oStmt=null;
	 Connection oConn = null;
	 ResultSet oRset =null;
	 try
	 {
		 oConn = ds.getConnection();
		 oStmt = oConn.createStatement();
		 for(oRset = oStmt.executeQuery("select gridrowNum,gridrowList from structure where interface_id='"+interface_id+"' and part_id='"+part_id+"'");oRset.next();)
		 {
			 BehaviourAll.addElement(oRset.getString(1));
			 BehaviourAll.addElement(oRset.getString(2));
			
		 }
		 oRset.close();
		 oStmt.close();
		 oConn.close();
	 }
	 catch (SQLException sqlexception) {
		 sqlexception.printStackTrace();
	 }
	 catch (Exception ex) {
		  //  System.out.println("Exception in ConfDatabaseLayer.getFileDetails()");
		  //  System.out.println("The Error Message - " + ex.getMessage());
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 oRset.close();
				 oStmt.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return BehaviourAll;		
 } 
 
 public static  String Getresourcelocation(String interface_id,String resource_id)
 {
	 String location = null;
        
	 Connection oConn = null;
	 Statement statement =null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select resource_location from resource where interface_id='"+interface_id+"' and resource_id='"+resource_id+"'");
		 while(resultset.next())
		 {
			 location=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
            //System.out.println("Inside NewDataBaseLayer getPluginName(), Exception !!!!");
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return location;
 } 
 
 
 public static  String Getcontentlocation(String interface_id,String content_id,String part_id)
 {
	 String contentlocation = null;
        
	 Connection oConn = null;
	 Statement statement =null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select contentlocation from content where interface_id='"+interface_id+"' and part_id='"+part_id+"' and content_id='"+content_id+"'");
		 while(resultset.next())
		 {
			 contentlocation=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
            //System.out.println("Inside NewDataBaseLayer getPluginName(), Exception !!!!");
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 } catch(Exception e){}	
		 }
	 }
	 return contentlocation;
 } 
 
 public static  String getStyleValueWindow(String layout,String style,String interface_id)
 {
	 String value = null;
    Connection oConn = null;
	 Statement statement = null;
	 ResultSet resultset = null;    
	 try
	 {
		 oConn = ds.getConnection();
		 statement = oConn.createStatement();
		 resultset = statement.executeQuery("select a.value from style a,roleassociation b  where a.part_id='window' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and b.style_id='"+style+"' and a.style_id=b.style_id");
		 while(resultset.next())
		 {
			 value=resultset.getString(1);
		 }
		 resultset.close();
		 statement.close();
		 oConn.close();
	 }
	 catch(SQLException sqlexception)
	 {
		 sqlexception.printStackTrace();
	 }
	 catch(Exception exception)
	 {
		 exception.printStackTrace();
	 }
	 finally
	 {
		 if(oConn!=null)
		 {
			 try
			 {
				 resultset.close();
				 statement.close();
				 oConn.close();
			 }catch(Exception e){}	
		 }
	 }
	 return value;
  }  
  
  public static  String getContentlocationvalueforroot(String layout,String content,String interface_id_name)
  {
	  String value = null;
        
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;    
	  try
	  {
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select a.contentlocation from content a,roleassociation b  where a.part_id='root' and a.interface_id='"+interface_id_name+"' and b.layout_id='"+layout+"' and a.content_id='"+content+"' and a.content_id=b.content_id");
		  while(resultset.next())
		  {
			  value=resultset.getString(1);
		  }
		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  catch(Exception exception)
	  {
            //System.out.println("Inside NewDataBaseLayer getPluginName(), Exception !!!!");
		  exception.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return value;
  }  
  
  public static Vector getStaticTreeDetails(String interface_id,String part_id)
  {
	  Vector statictreedetails = new Vector();
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset =null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		  for(oRset = oStmt.executeQuery("select onselectremotefunction,initialiseonload,tooltip,islazynode from tree_structure where interface_id='"+interface_id+"' and part_id='"+part_id+"'");oRset.next();)
		  {
			  statictreedetails.addElement(oRset.getString(1));
			  statictreedetails.addElement(oRset.getString(2));
			  statictreedetails.addElement(oRset.getString(3));
			  statictreedetails.addElement(oRset.getString(4));
		  }
		  oRset.close();
		  oStmt.close();
		  oConn.close();
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return statictreedetails;		
  } 
  
  public static InputStream getNodeStructure(String interface_id,String part_id)
  {
	   
	  InputStream InputStream1 = null;
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset=null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		  for(oRset = oStmt.executeQuery("select nodestructure from tree_structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");oRset.next();)
		  {
			  InputStream1=oRset.getAsciiStream(1);
				
		  }
		  oRset.close();
		  oStmt.close();
		  oConn.close();
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	  }
	  catch (Exception ex) {
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return InputStream1;		
  } 
  
  public static int getSqlQueryCount(String sql_query,int query_limit_start,int int_limit)
  {
	  ResultSet oRset=null;
	  ResultSet oRset1=null;
	  int count=0;
	  Connection oConn = null;
	  Statement statement =null;    
	  Statement statement1 =null;    
	  try
	  {
		  oConn = ds1.getConnection();
			
		  statement = oConn.createStatement();
		  statement1 = oConn.createStatement();
		  //		  System.out.println("test================="+test);
		  oRset = statement.executeQuery(sql_query+" limit "+query_limit_start+","+int_limit);
		  oRset1 = statement1.executeQuery("select found_rows()");
		  while(oRset1.next())
		  {
			  count=oRset1.getInt(1);
		  }
		  
		  //System.out.println("=================query=============="+sql_query);
		  //System.out.println("=================count=============="+count);
		 
//		  oRset.close();
//		  oRset1.close();
//		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	
	  finally
	  {
		  try
		  {
		  if (oRset!=null) oRset.close();
		  if (oRset1!=null) oRset1.close();
		  if (statement!=null) statement.close();
		  if (oConn!=null) oConn.close();
		  
		} catch(Exception e){}	
		  
	  }   
	  return count;
  }
 
  
  
  
  /////////////////////////////////////Nayna Start////////////////////////////////////////////
  public static String[] getDynamicTreeDetails(String interface_id,String part_id)
  {
	  String str[]=new String[4];
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset =null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		  oRset = oStmt.executeQuery("select onselectremotefunction,initialiseonload,tooltip,islazynode from tree_structure where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		  while(oRset.next())
		  {
			  str[0]=oRset.getString(1);
			  str[1]=oRset.getString(2);
			  str[2]=oRset.getString(3);
			  str[3]=oRset.getString(4);
		  }
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return str;		
  } 
  public static String[] getDynamicTreeQueries(String interface_id,String part_id)
  {
	  String str[]=new String[2];
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset =null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		  oRset = oStmt.executeQuery("select parentsql,childnodesql from tree_structure where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		  while(oRset.next())
		  {
			  str[0]=oRset.getString(1);
			  str[1]=oRset.getString(2);
		  }
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return str;		
  } 
  
  public static Vector getRootNodes(String parentquery)
  {
	  Connection oConn =null;
	  Statement  oStmt=null;
	  ResultSet oRset =null;
	  Vector vrootnode=new Vector();
	
	  try
	  {
		  oConn = ds.getConnection();
		  
		  oStmt = oConn.createStatement();
		  oRset = oStmt.executeQuery(parentquery);
		  while(oRset.next())
		  {
			  String str[]=new String[2];
			  str[0]=oRset.getString(1);
			  str[1]=oRset.getString(2);
			  vrootnode.addElement(str);
		  }
		  		  
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  catch(Exception ex)
	  {
		  //System.out.println("Exception in getRootNodes()");
		  //System.out.println("The Error Message - " + ex.getMessage());
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return vrootnode;
  }
  public static boolean hasChildNodes(String strkey, String childquery)
  {
	  Connection oConn =null;
	  PreparedStatement pStatement=null;
	  ResultSet rs =null;
	  boolean flag = false;
	  try
	  {
		  oConn = ds.getConnection();
		  pStatement = oConn.prepareStatement(childquery);
		  pStatement.setString(1, strkey);
		  rs = pStatement.executeQuery();
		  
		  if(rs.next())
		  {
			  flag = true;
		  }  	  
		  
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  catch(Exception ex)
	  {
		  //System.out.println("Exception in hasChildNodes()");
		  //System.out.println("The Error Message - " + ex.getMessage());
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  rs.close();
				  pStatement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return flag;
  }
  public static Vector getChildNodes(String childquery,String parentid)
  {
	  Connection oConn =null;
	  PreparedStatement pStatement=null;
	  ResultSet rs =null;
	 
	  Vector vchildnode=new Vector();
	  try
	  {
		  oConn = ds.getConnection();
		  
		  pStatement = oConn.prepareStatement(childquery);
		  pStatement.setString(1, parentid);
		  rs = pStatement.executeQuery();
		  
		  while(rs.next())
		  {
			  String str[]=new String[2];
			  str[0]=rs.getString(1);
			  str[1]=rs.getString(2);
			  vchildnode.addElement(str);
		  }  		  
		  
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  catch(Exception ex)
	  {
		  //System.out.println("Exception in getChildNodes()");
		  //System.out.println("The Error Message - " + ex.getMessage());
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  rs.close();
				  pStatement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return vchildnode;
  }
  
  /////////////////////////////////////30-11-2009///////////////////////////////////////////
  public static String getQParameters(String interface_id,String part_id)
  {
	  String param="";
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset =null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		  //System.out.println("select treeparameters from tree_structure where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		  oRset = oStmt.executeQuery("select tree_parameter from tree_structure where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		  while(oRset.next())
		  {
			  param=oRset.getString(1);
		  }
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return param;		
  } 
  /////////////////////////////////////Nayna End////////////////////////////////////////////
  
  public static Vector getChoose_one_information(String content,String child_id,String interface_id)
  {
	  Vector choose_one_information = new Vector();
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset =null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		  for(oRset = oStmt.executeQuery("select a.show_choose_one,a.choose_one_label,a.choose_one_value from content a,roleassociation b where a.part_id='"+child_id+"' and a.interface_id='"+interface_id+"' and b.content_id='"+content+"' and a.content_id=b.content_id and a.interface_id=b.interface_id");oRset.next();)
		  {
			  choose_one_information.addElement(oRset.getString(1));
			  choose_one_information.addElement(oRset.getString(2));
			  choose_one_information.addElement(oRset.getString(3));    
		  }
		  oRset.close();
		  oStmt.close();
		  oConn.close();
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return choose_one_information;		
  } 

  /**************************** Partha ****************************/
		   
 public static ResultSet getTableDataFromQuerySearch(String sql_query,int query_limit_start,int int_limit,String sord,String sidx)
  {
	  ResultSet oRset=null;
	  Connection oConn = null;
	  
	  try
	  {
		  oConn = ds1.getConnection();
		  Statement statement = oConn.createStatement();
		  oRset = statement.executeQuery(sql_query+" limit "+query_limit_start+","+int_limit);
					 	
	  }
	  catch(SQLException sqlexception)
	  {
		  //sqlexception.printStackTrace();
	  }
	  
	  return oRset;
  }		  
		  
  /*************************** End of Partha *******************/
  
	public static  String get_rptdesign_file_name(String interface_id,String content,String child_id)
  		{
			String rptdesign_file_name = null;
			Connection oConn = null;
			Statement statement = null;
			ResultSet resultset = null;    
	  try
	  {
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select rptdesign_file_name from interface_report_content where interface_id='"+interface_id+"' and content_id='"+content+"' and part_id='"+child_id+"'");
		  while(resultset.next())
		  {
			  rptdesign_file_name=resultset.getString(1);
		  }
		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  catch(Exception exception)
	  {
		  exception.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return rptdesign_file_name;
  }  		  
		  
  
  public static  String get_viewer_type(String interface_id,String content,String child_id)
  {
	  String viewer_type = null;
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;    
	  try
	  {
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select viewer_type from interface_report_content where interface_id='"+interface_id+"' and content_id='"+content+"' and part_id='"+child_id+"'");
		  while(resultset.next())
		  {
			  viewer_type=resultset.getString(1);
		  }
		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  catch(Exception exception)
	  {
		  exception.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return viewer_type;
  }  		
    
  public static  String get_report_chooser(String interface_id,String content,String child_id)
  {
	  String report_chooser = null;
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;    
	  try
	  {
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  //System.out.println("select report_chooser from interface_report_content where interface_id='"+interface_id+"' and content_id='"+content+"' and part_id='"+child_id+"'");
		  resultset = statement.executeQuery("select report_chooser from interface_report_content where interface_id='"+interface_id+"' and content_id='"+content+"' and part_id='"+child_id+"'");
		  while(resultset.next())
		  {
			  report_chooser=resultset.getString(1);
		  }
		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  catch(Exception exception)
	  {
		  exception.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return report_chooser;
  }  	
  	  
  public static Vector getParameterList(String interface_id,String content,String child_id)
  {
	
	  Vector vParameterList = new Vector();
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset =null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		  for(oRset = oStmt.executeQuery("select parameter_name,parameter_value,value_source_item_name,parameter_value_type from report_parameter where interface_id='"+interface_id+"' and content_id='"+content+"' and part_id='"+child_id+"'");oRset.next();)
		  {
			  vParameterList.addElement(oRset.getString(1));
			  vParameterList.addElement(oRset.getString(2));
			  vParameterList.addElement(oRset.getString(3)); 
			  vParameterList.addElement(oRset.getString(4));    
		  }
		  oRset.close();
		  oStmt.close();
		  oConn.close();
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	  }
	  catch (Exception ex) {

	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return vParameterList;		
  } 
		  
  /****************************** Partha For new grid generation***************************/
		  
	public  static String ResultSet2DOM(ResultSet rs,String page,String limit,int total_row_count) throws SQLException {
		
	  String xmldoc = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		//just a hack to make things easier
		/*root.setAttribute(
	  "xmlns:xsi",
	  "http://www.w3.org/2001/XMLSchema-instance");*/
	  int page_no=0;
	  ResultSetMetaData rsmd = rs.getMetaData();
	  String value;
//                 log.debug("====================rs========="+rs);
                 //System.out.println("====================rsmdount column c========="+rsmd.getColumnCount());
	  String xdoc="";
	  int k=0;
		    
	  while (rs.isLast() == false) {
		  rs.next();
		  //xdoc=xdoc+"<row id=\""+m+"\">";
		  xdoc=xdoc+"<row>";
		  for (int i = 1; i <= rsmd.getColumnCount(); i++) {
// 				log.debug("====================rsmd column========="+rsmd.getColumnLabel(i));
			  value = rs.getString(i);
				
// 				log.debug("===============value============="+value);
				
			  if (value == null) {
				  value="";
			  }
			  xdoc=xdoc+"<cell><![CDATA["+value+"]]></cell>";
		  }
		  k=k+1;
			
			
			
		  xdoc=xdoc+"</row>";
	  }
		
	  int row_per_page = Integer.parseInt(limit);
		
// 		System.out.println("============row_per_page========="+row_per_page);
// 		System.out.println("============k========="+k);
	  if((total_row_count%row_per_page)==0)
		  page_no=total_row_count/row_per_page;
	  else
		  page_no=total_row_count/row_per_page+1;
		
	  xmldoc=xmldoc+"<rows><page>"+page+"</page><total>"+page_no+"</total><records>"+total_row_count+"</records>"+xdoc;
	  xmldoc=xmldoc+"</rows>";
	  return xmldoc;
		  }
  
  
		  public static String getTableDataFromQuery(String sql_query,int query_limit_start,int int_limit,String sord,String sidx,String page,String limit,int total_row_count)
		  {
			  ResultSet oRset=null;
			  Connection oConn = null;
			  String d = "";
			  Statement statement = null;
			  try
			  {
				  //System.out.println("sidx======="+sidx+"====sord===="+sord);
				  oConn = ds1.getConnection();
				  statement = oConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);			  
				  if((sord.equals("")) && (sidx.equals("")))
					  oRset = statement.executeQuery(sql_query+" limit "+query_limit_start+","+int_limit);
				  else if((sord.equals("")) && !(sidx.equals("")))
					  oRset = statement.executeQuery(sql_query+" order by "+sidx+" limit "+query_limit_start+","+int_limit);
				  else if(!(sord.equals("")) && (sidx.equals("")))
					  oRset = statement.executeQuery(sql_query+" limit "+query_limit_start+","+int_limit);
				  else
					  oRset = statement.executeQuery(sql_query+" order by "+sidx+" "+sord+" limit "+query_limit_start+","+int_limit);
            	//oConn.close();
		 		
				  oRset.last();
				  int rowCount=oRset.getRow();
					//System.out.println("=================xml query =============="+sql_query+" order by "+sidx+" limit "+query_limit_start+","+int_limit);
 			//System.out.println("=======================rowCount=========="+rowCount);
				  oRset.beforeFirst();
			
				  if(rowCount!=0)
					  d = ResultSet2DOM(oRset,page,limit,total_row_count);
				  else
				  {
					  d=d+"<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
					  d=d+"<rows>";
					  d=d+"<row><cell>No Records Found</cell></row></rows>";
				  }
		 
			  }
			  catch(SQLException sqlexception)
			  {
		 sqlexception.printStackTrace();
			  }
			  finally
			  {
				  if(oConn!=null)
				  {
					  try
					  {
						  if(oRset!=null)
							  oRset.close();
						  if(statement!=null)
							  statement.close();
						  oConn.close();
					  } catch(Exception e){}	
				  }
			  			
			  }
        
			  return d;
		  }
  
		  public static String getTableDataFromQuerySearch(String sql_query,int query_limit_start,int int_limit,String sord,String sidx,String page,String limit,int total_row_count)
		  {
			  ResultSet oRset=null;
			  //Connection oConn = null;
			  String d="";
			  //Statement statement = null;
			  try(Connection oConn = ds1.getConnection();
					Statement  statement = oConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);	  )
					  //Statement  statement = oConn.createStatement();	  )
			  {
				  //oConn = ds1.getConnection();
				  //statement = oConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				  oRset = statement.executeQuery(sql_query+" limit "+query_limit_start+","+int_limit);
				  //System.out.println("=================xml query=============="+sql_query+" limit "+query_limit_start+","+int_limit);
				  oRset.last();
				  int rowCount=oRset.getRow();
// 			System.out.println("=======================rowCount=========="+rowCount);
				  oRset.beforeFirst();
			
				  if(rowCount!=0)
					  d = ResultSet2DOM(oRset,page,limit,total_row_count);
				  else
				  {
					  d=d+"<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
					  d=d+"<rows>";
					  d=d+"<row><cell>No Records Found</cell></row></rows>";
				  }			 	
			  }
			  catch(SQLException sqlexception)
			  {
				  sqlexception.printStackTrace();
			  }
			  
			  return d;
		  }
		  
		  
  /************************ End of Partha **************************************/
 
public static  String getDomReadyFunctionName(String layout,String behaviour,String interface_id)
  {
	  String value = null;
        
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;    
	  try
	  {
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select a.value from behaviour a,roleassociation b  where a.part_id='root' and a.interface_id='"+interface_id+"' and b.layout_id='"+layout+"' and b.behaviour_id='"+behaviour+"' and a.behaviour_id=b.behaviour_id and a.behaviourevent='domready'");
		  while(resultset.next())
		  {
			  value=resultset.getString(1);
		  }
		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  catch(Exception exception)
	  {
		  exception.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return value;
  } 
   
  public static  String getApplicationDefaultValue(String template_id,String classtype,String attribute_name)
  {
	  String default_value = "";
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;    
	  try
	  {
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  //System.out.println("select default_value from application_template_default where class_name='"+classtype+"' and attribute_name='"+attribute_name+"' and application_template_id='"+template_id+"'");
		  resultset = statement.executeQuery("select default_value from application_template_default where class_name='"+classtype+"' and attribute_name='"+attribute_name+"' and application_template_id='"+template_id+"'");
		  while(resultset.next())
		  {
			  default_value=resultset.getString(1);
		  }
		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return default_value;
  } 
		  
  /******************************** Partha for Load Query Parameter **************************/
		  
		  public static  String getLoadQueryParameterTitle(String interface_id,String part_id)
  {
	  String param_title = "";
        
	  Connection oConn = null;
	  Statement statement=null;
	  ResultSet resultset =null;	     
	  try
	  {
		 
		
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select load_parameter from gridquery where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		  while(resultset.next())
		  {
			  param_title=resultset.getString(1);
		  }
		 
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  catch(Exception exception)
	  {
		  exception.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  if(resultset!=null)
					  resultset.close();
				  if(statement!=null)
					  statement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return param_title;
  } 
  /****************************** End of Partha ***********************/
  /***************************Partha for Caching*****************/		  		 
		  public static Vector getHTMLAsString(String role,String interface_id)
  {
	         
	  Vector vSrcFile = new Vector();
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
//		  System.out.println("select a.valueblob from interfaceenginecalling a,roleassociation b where a.layout_id=b.layout_id and a.content_id=b.content_id and a.behaviour_id=b.behaviour_id and a.style_id=b.style_id and b.interface_id='"+interface_id+"' and b.role_id='"+role+"' and a.interface_id=b.interface_id");
		  for(oRset = oStmt.executeQuery("select a.valueblob from interfaceenginecalling a,roleassociation b where a.layout_id=b.layout_id and a.content_id=b.content_id and a.behaviour_id=b.behaviour_id and a.style_id=b.style_id and b.interface_id='"+interface_id+"' and b.role_id='"+role+"' and a.interface_id=b.interface_id");oRset.next();)
		  {
              		
			
			  vSrcFile.addElement(oRset.getString(1));
			 
	            
		  }
		  oRset.close();  
		  oStmt.close();
		  oConn.close();
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return vSrcFile;		
  }
   
  
  public static Vector getCacheDefinition()
  {
	         
	  Vector vCache = new Vector();
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		  //System.out.println("select cache_id,cache_name,cache_type,max_element,overflowtodisk from cache_definition");
		  for(oRset = oStmt.executeQuery("select cache_id,cache_name,cache_type,max_element,overflowtodisk,timetoliveseconds,timetoidleseconds,diskpersistent,eternal,diskexpirythreadintervalseconds,memorystoreevictionpolicy,diskstorepath from cache_definition");oRset.next();)
		  {
			  Vector vCacheSub = new Vector();
			  vCacheSub.addElement(oRset.getString(1));
			  vCacheSub.addElement(oRset.getString(2));
			  vCacheSub.addElement(oRset.getString(3));
			  vCacheSub.addElement(oRset.getString(4));
			  vCacheSub.addElement(oRset.getBoolean(5));
			  
			  vCacheSub.addElement(oRset.getInt(6));
			  vCacheSub.addElement(oRset.getInt(7));
			  vCacheSub.addElement(oRset.getBoolean(8));
			  vCacheSub.addElement(oRset.getBoolean(9));
			  vCacheSub.addElement(oRset.getInt(10));
			  vCacheSub.addElement(oRset.getString(11));
			  vCacheSub.addElement(oRset.getString(12));
			  vCache.addElement(vCacheSub);
			
		  }
		 
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  if(oRset!=null)
					  oRset.close();
				  if(oStmt!=null)
					  oStmt.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return vCache;		
  } 
  
  
  public static String getDefaultCache()
  {
	         
	  String CacheName = "";
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
//		  System.out.println("select cache_name from cache_definition where default_cache='Yes'");
		  for(oRset = oStmt.executeQuery("select cache_name from cache_definition where default_cache='Yes'");oRset.next();)
		  {
			  CacheName = oRset.getString(1);
			  	
		  }
		 
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  if(oRset!=null)
					  oRset.close();
				  if(oStmt!=null)
					  oStmt.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return CacheName;		
  } 
  
  
  public static String getInterfaceCachingStatus(String interface_id)
  {
	         
	  String cache_status = "";
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
//		  System.out.println("select enable_caching from configuration_item where interface_id='"+interface_id+"'");
		  oRset = oStmt.executeQuery("select enable_caching from configuration_item where interface_id='"+interface_id+"'");
		  while(oRset.next())
		  {
			  cache_status=oRset.getString(1);
			  	
		  }
		 
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  if(oRset!=null)
					  oRset.close();
				  if(oStmt!=null)
					  oStmt.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return cache_status;		
  }
   
  public static String getInterfaceCacheName(String interface_id)
  {
	         
	  String cache_name = "";
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
//		  System.out.println("select a.cache_name from configuration_item a,cache_definition b where a.interface_id='"+interface_id+"' and a.cache_name=b.cache_name");
		  oRset = oStmt.executeQuery("select a.cache_name from configuration_item a,cache_definition b where a.interface_id='"+interface_id+"' and a.cache_name=b.cache_name");
		  while(oRset.next())
		  {
			  cache_name=oRset.getString(1);
			  
		  }
		 
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  if(oRset!=null)
					  oRset.close();
				  if(oStmt!=null)
					  oStmt.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return cache_name;		
  } 
  
  
  public static boolean getGridCachingStatus(String interface_id,String part_id)
  {
	         
	  boolean flag = false;
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		  //System.out.println("select server_cache_grid from gridquery where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		  oRset = oStmt.executeQuery("select server_cache_grid from gridquery where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		  while(oRset.next())
		  {
			  String cache_status=oRset.getString(1);
			  if(cache_status.equalsIgnoreCase("true"))
				  flag=true;
			  	
		  }
		 
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  if(oRset!=null)
					  oRset.close();
				  if(oStmt!=null)
					  oStmt.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return flag;		
  }
  
  
  public static String getGridCacheIncludeUserID(String interface_id,String part_id)
  {
	         
	  String user_id_included_cache = "";
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
//		  System.out.println("select cachekey_includeuserid from gridquery where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		  oRset = oStmt.executeQuery("select cachekey_includeuserid from gridquery where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		  while(oRset.next())
		  {
			  user_id_included_cache=oRset.getString(1);
			  
		  }
		 
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  if(oRset!=null)
					  oRset.close();
				  if(oStmt!=null)
					  oStmt.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return user_id_included_cache;		
  }
   
  
  public static String getApplicationTemplateId(String interface_id)
  {
	         
	  String template_id = "";
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
//		  System.out.println("select template from configuration_item where interface_id='"+interface_id+"'");
		  oRset = oStmt.executeQuery("select template from configuration_item where interface_id='"+interface_id+"'");
		  while(oRset.next())
		  {
			  template_id=oRset.getString(1);
			  	
		  }
		 
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  if(oRset!=null)
					  oRset.close();
				  if(oStmt!=null)
					  oStmt.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return template_id;		
  }
  
  
  public static String getGridCacheStatus(String interface_id,String part_id)
  {
	         
	  String cache_status = "";
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		  //System.out.println("select server_cache_grid from gridquery where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		  oRset = oStmt.executeQuery("select server_cache_grid from gridquery where interface_id='"+interface_id+"' and part_id='"+part_id+"'");
		  while(oRset.next())
		  {
			  cache_status=oRset.getString(1);
			  	
		  }
		 
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  if(oRset!=null)
					  oRset.close();
				  if(oStmt!=null)
					  oStmt.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return cache_status;		
  }
  
  
  public static String getResourceJSCachingStatus(String interface_id)
  {
	         
	  String cache_status = "";
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		  //System.out.println("select cache_dynamic_js from configuration_item where interface_id='"+interface_id+"'");
		  oRset = oStmt.executeQuery("select cache_dynamic_js from configuration_item where interface_id='"+interface_id+"'");
		  while(oRset.next())
		  {
			  cache_status=oRset.getString(1);
			  	
		  }
		 
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  if(oRset!=null)
					  oRset.close();
				  if(oStmt!=null)
					  oStmt.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return cache_status;		
  }
  
  
  public static String getResourceCSSCachingStatus(String interface_id)
  {
	         
	  String cache_status = "";
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		  //System.out.println("select cache_dynamic_css from configuration_item where interface_id='"+interface_id+"'");
		  oRset = oStmt.executeQuery("select cache_dynamic_css from configuration_item where interface_id='"+interface_id+"'");
		  while(oRset.next())
		  {
			  cache_status=oRset.getString(1);
			  	
		  }
		 
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  if(oRset!=null)
					  oRset.close();
				  if(oStmt!=null)
					  oStmt.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return cache_status;		
  }
  
  public static String getResourceImageCachingStatus(String interface_id)
  {
	         
	  String cache_status = "";
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		  //System.out.println("select cache_dynamic_image from configuration_item where interface_id='"+interface_id+"'");
		  oRset = oStmt.executeQuery("select cache_dynamic_image from configuration_item where interface_id='"+interface_id+"'");
		  while(oRset.next())
		  {
			  cache_status=oRset.getString(1);
			  	
		  }
		 
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  if(oRset!=null)
					  oRset.close();
				  if(oStmt!=null)
					  oStmt.close();
				  oConn.close();
			  }catch(Exception e){}	
		  }
	  }
	  return cache_status;		
  }
  
  public static Vector getImageAsString(String resource_id,String interface_id)
  {
	    
       // String str[]=new String[2];
	  Vector vSrcFile = new Vector();
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset = null;
	     
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
              
       //System.out.println("select value from resource where resource_id='"+resource_id+"'");
		  for(oRset = oStmt.executeQuery("select value from resource where resource_id='"+resource_id+"' and interface_id='"+interface_id+"'");oRset.next();)
		  {
			  vSrcFile.addElement(oRset.getString(1));
		  }
		  oRset.close();  
		  oStmt.close();
		  oConn.close();
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return vSrcFile;		
  } 
  
  
  
  /****************************** End of Partha ***********************/		  
/* Changes made by Dibyarup
 * Theme picking is made separated.
 * One function for getting the theme from interface xml.
 * One function is for getting the theme from application template.
 * The last one for getting the default one.		  
 */
  public static  String getThemeFromInterfaceId(String interfaceId)
  {
	  String themeId = "";
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select themes_id from configuration_item where interface_id='"+interfaceId+"' ");
		  while(resultset.next())
		  {
			  themeId=resultset.getString(1);
		  }

		  //System.out.println(".....................Interface THEME ID............"+ themeId);

		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return themeId;
  } 	
  
  public static  String getThemeFromApplicationTemplate(String templateId)
  {
	  String themeId = "";
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select default_value from application_template_default where application_template_id='"+templateId+"' and attribute_name='ThemeID'");
		  while(resultset.next())
		  {
			  themeId=resultset.getString(1);
			  //System.out.println(".....................THEMES ID from application Template..........."+ themeId);
		  }

		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return themeId;
  } 	
  public static  String getDefaultTheme()
  {
	  String themeId = "";
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select themes_id from themes where default_value='yes' ");
		  while(resultset.next())
		  {
			  themeId=resultset.getString(1);
			  //System.out.println(".....................Default THEMES ID..........."+ themeId);
		  }

		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return themeId;
  } 
  public static  boolean isThemeExist(String themeId)
  {
	  int themeCount = 0;
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select count(*) from themes where themes_id='"+themeId+"'");
		  while(resultset.next())
		  {
			  themeCount=resultset.getInt(1);
			  //System.out.println(".....................THEME COUNT..........."+ themeCount);
		  }

		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  if(themeCount>0){
		  return true;
	  }else{
		  return false;
	  }
  } 
  /*
   * End
   */
  
/*  
public static  String getThemes(String interface_id,String template_id)
  {
	  String themes_id = "";
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select themes_id from configuration_item where interface_id='"+interface_id+"' ");
		  while(resultset.next())
		  {
			  themes_id=resultset.getString(1);
		  }
		  
		  System.out.println(".....................THEMES ID............"+ themes_id);
		  
		  if(themes_id.equals(""))
		  {
			   themes_id="default";
				resultset = statement.executeQuery("select default_value from application_template_default where application_template_id='"+template_id+"' and attribute_name='ThemeID'");
				while(resultset.next())
				{
					themes_id=resultset.getString(1);
					System.out.println(".....................THEMES ID.1..........."+ themes_id);
				}
		  }
		  if(themes_id.equals("default"))
		  {
				resultset = statement.executeQuery("select themes_id from themes where default_value='yes' ");
				while(resultset.next())
				{
					themes_id=resultset.getString(1);
					System.out.println(".....................THEMES ID.........2..."+ themes_id);
				}
		  }
		  
		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return themes_id;
  } 	  
*/
  
 // public static Vector getAssetFile(String file_name)
  public static Vector getAssetFile(String tid,String file_name)
  {
	  Vector vSrcFile = new Vector();
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset =null;
	  String query = "select template_asset_file from application_template_asset where application_template_id="+tid+" and file_name='"+file_name+"'";
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		 // for(oRset = oStmt.executeQuery("select a.value from framework_asset a where a.file_name='"+file_name+"'");oRset.next();)
			  for(oRset = oStmt.executeQuery(query);oRset.next();)
		  {
			  vSrcFile.addElement(oRset.getAsciiStream(1));
		  }
		  oRset.close();
		  oStmt.close();
		  oConn.close();
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return vSrcFile;		
  } 
  public static  String getThemesClass(String themes_id,String partclass,String type)
  {
	  String themes_class = "";
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;
	  try
	  {
		  oConn = ds.getConnection();
		//checkConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select css_classes from themes_definition where themes_id='"+themes_id+"' and class_type='"+partclass+"' and prop_type='"+type+"'");
		  while(resultset.next())
		  {
			  themes_class=resultset.getString(1);
		  }
		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return themes_class;
  } 	  
  
  public static Vector getThemesFiles(String themes_id)
  {
	  Vector vSrcFile = new Vector();
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset =null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		  for(oRset = oStmt.executeQuery("select css_value from themes_css where themes_id='"+themes_id+"'");oRset.next();)
		  {
			  vSrcFile.addElement(oRset.getString(1));
		  }
		  oRset.close();
		  oStmt.close();
		  oConn.close();
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return vSrcFile;		
  } 
  public static  String css_path_themes(String file_name)
  {
	  String css_path = "";
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;
	  try
	  {
		  oConn = ds.getConnection();
		//checkConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select csspath from themes_css_file where css_file='"+file_name+"' ");
			  while(resultset.next())
			  {
				  css_path=resultset.getString(1);
			  }
		  
		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return css_path;
  } 	  
  /*..............................THIS FUNCTION IS UDE FOR ITEFACE TYPE ........................*/	  
  public static  String GetInterfaceType(String framework_file_id)
  {
	  String customeditbutton = "";
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;    
	  try
	  {
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select type from framework_file where  framework_file_id='"+framework_file_id+"'");
		  while(resultset.next())
		  {
			  customeditbutton=resultset.getString(1);
		  }
		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return customeditbutton;
  }  
  
  
  public static  InputStream GetFragementSnippet(String role_id,String interface_id)
  {
	  InputStream valueblob = null;
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;    
	  try
	  {
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select a.valueblob from interfaceenginecalling a,roleassociation b where a.layout_id=b.layout_id and a.content_id=b.content_id and a.behaviour_id=b.behaviour_id and a.style_id=b.style_id and b.interface_id='"+interface_id+"' and b.role_id='"+role_id+"' and a.interface_id=b.interface_id");
		  while(resultset.next())
		  {
			  valueblob=resultset.getAsciiStream(1);
		  }
		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return valueblob;
  }  
  
  
/*  public static  String GetFragementSnippet(String role_id,String interface_id)
  {
	  String valueblob = "";
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;    
	  try
	  {
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select a.valueblob from interfaceenginecalling a,roleassociation b where a.layout_id=b.layout_id and a.content_id=b.content_id and a.behaviour_id=b.behaviour_id and a.style_id=b.style_id and b.interface_id='"+interface_id+"' and b.role_id='"+role_id+"' and a.interface_id=b.interface_id");
		  while(resultset.next())
		  {
			  valueblob=resultset.getString(1);
		  }
		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return valueblob;
  }  
  */
  public static  String GetGridData(String interface_id,String part_id)
  {
	  String griddata = "";
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;    
	  try
	  {
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select griddata from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		  while(resultset.next())
		  {
			  griddata=resultset.getString(1);
		  }
		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return griddata;
  }  
  
  public static  String GetGridDataType(String interface_id,String part_id)
  {
	  String griddatatype = "";
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;    
	  try
	  {
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select griddatatype from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
		  while(resultset.next())
		  {
			  griddatatype=resultset.getString(1);
		  }
		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return griddatatype;
  }  
  
  public static Vector getParameterList_flashcomponent(String interface_id,String content,String child_id)
  {
	  Vector vSrcFile = new Vector();
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset =null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		  for(oRset = oStmt.executeQuery("select parameter_name,parameter_value from flash_parameter where interface_id='"+interface_id+"' and content_id='"+content+"' and part_id='"+child_id+"'");oRset.next();)
		  {
			  vSrcFile.addElement(oRset.getString(1));
			  vSrcFile.addElement(oRset.getString(2));
		  }
		  oRset.close();
		  oStmt.close();
		  oConn.close();
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return vSrcFile;		
  }
   
  
  public static String  getInlineFrameworkAssetFile(String file_name, String tid)
  {
	  byte[] filestring = null;
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset = null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		 //for(oRset = oStmt.executeQuery("select value from framework_asset where file_name='"+file_name+"'");oRset.next();)
			  for(oRset = oStmt.executeQuery("select template_asset_file from application_template_asset where application_template_id='"+tid+"' and file_name='"+file_name+"'");oRset.next();)
		  {
			  filestring=oRset.getBytes(1);
			
		  }
		  oRset.close();
		  oStmt.close();
		  oConn.close();
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return new String(filestring);		
  } 

  public static boolean  checkIfInterfaceExists(String interfaceID)
  {
	  int count = 0;
	  //Statement  oStmt=null;
	  //Connection oConn = null;
	  //ResultSet oRset = null;
	  try(Connection oConn = ds.getConnection()) {
		  PreparedStatement oStmt = oConn.prepareStatement("select 1 from interface where interface_id = ?");
		  oStmt.setString(1,interfaceID);
		  ResultSet oRset = oStmt.executeQuery();
		  while(oRset.next())
		  {
			  count = oRset.getInt(1);
		  }
		  oRset.close();
		  oStmt.close();
		  oConn.close();
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  
	  if (count == 1) 
		  return true;
	  else 
		  return false;
  } 
  
  public static  String getThemesProperty(String themes_id,String partclass,String type)
  {
	  String themes_class = "";
	  Connection oConn = null;
	  Statement statement = null;
	  ResultSet resultset = null;
	  try
	  {
		  oConn = ds.getConnection();
		//checkConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select properties from themes_definition where themes_id='"+themes_id+"' and class_type='"+partclass+"' and prop_type='"+type+"'");
		  while(resultset.next())
		  {
			  themes_class=resultset.getString(1);
		  }
		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  finally{
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return themes_class;
  } 
  
  public static  ApplicationTemplate getApplicationTemplateConfigValues(String template)
  {
	 ApplicationTemplate applicationTemplate = new ApplicationTemplate();
	 applicationTemplate.setId(template);
 	 Connection oConn = null;
 	 Statement statement = null;
 	 ResultSet resultset = null;    
 	 try
 	 {
 		 oConn = ds.getConnection();
 		 statement = oConn.createStatement();
 		 resultset = statement.executeQuery("select ui_framework,block_ui_timeout from application_template_master where application_template_id='"+template+"'");
 		 while(resultset.next())
 		 {
 			applicationTemplate.setUiFramework(resultset.getString(1));
 			applicationTemplate.setBlockUiTimeout(resultset.getInt(2));
 			 
 		 }
 		 resultset.close();
 		 statement.close();
 		 oConn.close();

 	 }
 	 catch(SQLException sqlexception)
 	 {
 		 sqlexception.printStackTrace();
 	 }
 	 finally{
 		 if(oConn!=null)
 		 {
 			 try
 			 {
 				 resultset.close();
 				 statement.close();
 				 oConn.close();
 			 }catch(Exception e){}	
 		 }
 	 }
 	 return applicationTemplate;
  } 
  
  public static  GridProperty getDBGridStructure(String interface_id,String part_id)
  {
	  GridProperty gridProperty = new GridProperty();
	  Connection oConn = null;
	  Statement statement =null;
	  ResultSet resultset = null;    
	  try
	  { 
		  oConn = ds.getConnection();
		  statement = oConn.createStatement();
		  resultset = statement.executeQuery("select altRows,autowidth, shrinktofit, ignoreCase,rowNumbers,altClass,searchonEnter,columnChooser,toolbarSearch,onSelectRow,searchOperators from grid_structure where part_id='"+part_id+"' and interface_id='"+interface_id+"' ");
		  while(resultset.next())
		  {
			  gridProperty.setAltRows(resultset.getString(1)!=null?GenericUtil.convertStringToBoolean(resultset.getString(1)):null);
			  gridProperty.setAutowidth(resultset.getString(2)!=null?GenericUtil.convertStringToBoolean(resultset.getString(2)):null);
			  gridProperty.setShrinkToFit(resultset.getString(3)!=null?GenericUtil.convertStringToBoolean(resultset.getString(3)):null);
			  gridProperty.setIgnoreCase(resultset.getString(4)!=null?GenericUtil.convertStringToBoolean(resultset.getString(4)):null);
			  gridProperty.setRowNumbers(resultset.getString(5)!=null?GenericUtil.convertStringToBoolean(resultset.getString(5)):null);
			  gridProperty.setAltClass(resultset.getString(6));
			  gridProperty.setSearchOnEnter(resultset.getString(7)!=null?GenericUtil.convertStringToBoolean(resultset.getString(7)):null);
			  gridProperty.setColumnChooser(resultset.getString(8)!=null?GenericUtil.convertStringToBoolean(resultset.getString(8)):null);
			  gridProperty.setToolbarSearch(resultset.getString(9)!=null?GenericUtil.convertStringToBoolean(resultset.getString(9)):null);
			  gridProperty.setOnSelectRow(resultset.getString(10));
			  gridProperty.setSearchOperators(resultset.getString(11)!=null?GenericUtil.convertStringToBoolean(resultset.getString(11)):null);
		  }
		  resultset.close();
		  statement.close();
		  oConn.close();
	  }
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  resultset.close();
				  statement.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return gridProperty;
  } 
  
  public static void getTableDataFromQuery(String sql_query, ServletOutputStream servletOut)
  {
	  ResultSet oRset=null;
	  String d = "";
	  try (Connection oConn = ds1.getConnection();
			//Statement  statement = oConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);	  )
			  Statement  statement = oConn.createStatement();	  )
	  {
		  oRset = statement.executeQuery(sql_query);
		  oRset.last();
		  int rowCount=oRset.getRow();
		  if(rowCount!=0) {
			  oRset.beforeFirst();
			  ResultSet2JSON(oRset,servletOut);
		  }
		  else
		  {
			  d=d+"<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			  d=d+"<rows>";
			  d=d+"<row><cell>No Records Found</cell></row></rows>";
		  }
	  }
		  		  
	  catch(SQLException sqlexception)
	  {
		  sqlexception.printStackTrace();
	  }

  }

  private static void ResultSet2JSON (final ResultSet resultSet, ServletOutputStream servletOut) {
	  
	  
	  ObjectMapper objectMapper = new ObjectMapper();
//	  objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	  objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	  
	  ResourceBundle rb = ResourceBundle.getBundle("portal",Locale.getDefault());      
	  String key= "GridJSONdateFormat"; 
	  String dateFormat = rb.getString(key);
	  DateFormat df = null;
	  if (dateFormat != null)
		  df = new SimpleDateFormat(dateFormat);
	  else
		  df = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss z");
		  

	  objectMapper.setDateFormat(df);
	  
	  try {
		  JsonGenerator jg = objectMapper.getFactory().createGenerator(servletOut, JsonEncoding.UTF8);
		  ResultSetMetaData rsmd = resultSet.getMetaData();
		  int columnCount = rsmd.getColumnCount();
		  jg.writeStartArray();
		  while (resultSet.next()) {
		      jg.writeStartObject();
		      for (int i = 1; i <= columnCount; i++) {
		        jg.writeObjectField(rsmd.getColumnName(i), resultSet.getObject(i));
		      }
		      jg.writeEndObject();
		   }
		   jg.writeEndArray();
		   jg.flush();
		   jg.close();
	  }
	  catch (IOException | SQLException e) {
	      throw new RuntimeException(e);
	    }
	  
  }

  public static  boolean checkIfTemplateUsedOrDefaultTemplateUsed(String template_id)
  {
 	 boolean flag = false;
 	 String templateName = null;
 	 String defaultValue = null;
 	 ResultSet resultset = null;    
 	 try (Connection oConn = ds.getConnection();
 		 Statement statement = oConn.createStatement();)
 	 {
 		 resultset = statement.executeQuery("select application_template_title, default_value from application_template_master where application_template_id ='"+template_id+"'");
 		 while(resultset.next())
 		 {
 			 templateName = resultset.getString(1);
 			 defaultValue = resultset.getString(2);
 		 }

 		 resultset = statement.executeQuery("select count(*) from configuration_item where TRIM(template) ='"+templateName+"'"); //there are interfaces that mentions this template in their xml
 		 while(resultset.next())
 		 {
 			 if (resultset.getInt(1) > 0)
 				 flag =  true;
 			 else {
 				 	if (defaultValue != null) {
 				 			resultset = statement.executeQuery("select count(*) from configuration_item where  template = ''");  //there are interfaces that do not mention any template in their xml 
 				 			while(resultset.next())                                                                              // i.e., they use the default template
 				 			{
 				 				if (resultset.getInt(1) > 0)
 				 					flag =  true;
 				 			}
 				 	}
 				 
 			 }
 		 }


 	 }
 	 catch(SQLException sqlexception)
 	 {
 		 sqlexception.printStackTrace();
 	 }
 	 return flag;
  }
  
  public static  String getAutoEncode(String interface_id,String part_id)
  {
 	 String autoencode = "";
 	 Connection oConn = null;
 	 Statement statement = null;
 	 ResultSet resultset = null;    
 	 try
 	 {
 		 oConn = ds.getConnection();
 		 statement = oConn.createStatement();
 		 resultset = statement.executeQuery("select autoencode from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
 		 //System.out.println("select autoencode from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
 		 while(resultset.next())
 		 {
 			autoencode=resultset.getString(1);
 		 }
 		 resultset.close();
 		 statement.close();
 		 oConn.close();
 	 }
 	 catch(SQLException sqlexception)
 	 {
 		 sqlexception.printStackTrace();
 	 }
 	 finally
 	 {
 		 if(oConn!=null)
 		 {
 			 try
 			 {
 				 resultset.close();
 				 statement.close();
 				 oConn.close();
 			 } catch(Exception e){}	
 		 }
 	 }
 	 return autoencode;
  }
  
  /////////////////////Partha///////////////
  
  public static Vector<String> getGridSelectEditOption(String part_id,String interface_id,String colname)
  {
 	    
 	 Vector<String> vSrcFile = new Vector<>();
 	 Statement  oStmt=null;
 	 Connection oConn = null;
 	 ResultSet oRset=null;
 	 try
 	 {
 		 oConn = ds.getConnection();
 		 oStmt = oConn.createStatement();
 		 String query = "select type,size,textarea_rows,textarea_cols,editdomaintype,active_value,multiple, imageAccessURL from edit_type where part_id='"+part_id+"' and interface_id='"+interface_id+"' and col_name='"+colname+"'";
// 		 for(oRset = oStmt.executeQuery("select type,size,rows,cols,editdomaintype,value,multiple from edit_type where part_id='"+part_id+"' and interface_id='"+interface_id+"' and col_name='"+colname+"'");oRset.next();)
 		 for(oRset = oStmt.executeQuery(query);oRset.next();)
 		 {
 			 vSrcFile.addElement(oRset.getString(1));
 			 vSrcFile.addElement(oRset.getString(2));
 			 vSrcFile.addElement(oRset.getString(3));
 			 vSrcFile.addElement(oRset.getString(4));
 			 vSrcFile.addElement(oRset.getString(5));
 			 vSrcFile.addElement(oRset.getString(6));
 			 vSrcFile.addElement(oRset.getString(7));
 			 vSrcFile.addElement(oRset.getString(8));
 		 }
 		 oRset.close();
 		 oStmt.close();
 		 oConn.close();
 	 }
 	 catch (SQLException sqlexception) {
 		 sqlexception.printStackTrace();
 	 }	
 	 finally{
 		 if(oConn!=null)
 		 {
 			 try
 			 {
 				 oRset.close();
 				 oStmt.close();
 				 oConn.close();
 			 }catch(Exception e){}	
 		 }
 	 }
 	 return vSrcFile;		
  }
  
  public static String getGridColumnSelectSqlQuery(String interface_id,String part_id,String column_id)
  {
 		//ResultSet oRset=null;
 	 String s_query="";
 	 Connection oConn =null;
 	 Statement statement = null;
 	 ResultSet oRset = null;
 		
         
 	 try
 	 {
 		 oConn =ds.getConnection();
 		 statement = oConn.createStatement();
 		 //System.out.println("select value from edit_type where interface_id='"+interface_id+"' and part_id='"+part_id+"' and col_name='"+column_id+"' ");
 		 oRset = statement.executeQuery("select value from edit_type where interface_id='"+interface_id+"' and part_id='"+part_id+"' and col_name='"+column_id+"' ");
 		 while(oRset.next())
 		 {
 			 s_query=oRset.getString(1);
 		 }	
 			
 		 oRset.close();
 		 statement.close();
 		 oConn.close();	
 	 }
 	 catch(SQLException sqlexception)
 	 {
 		 sqlexception.printStackTrace();
 	 }
 	 finally
 	 {
 		 if(oConn!=null)
 		 {
 			 try
 			 {
 				 oRset.close();
 				 statement.close();
 				 oConn.close();
 			 } catch(Exception e){}	
 		 }
 	 }
 		
 	 return s_query;
  }
  
  public static  HashMap<String, String> getGridParameters(String interface_id,String part_id)
  {
 	 
 	 HashMap<String, String> gridParameters = new HashMap<String, String>();
 	 Connection oConn = null;
 	 Statement statement = null;
 	 ResultSet resultset = null;    
 	 try
 	 {
 		 oConn = ds.getConnection();
 		 statement = oConn.createStatement();
 		 resultset = statement.executeQuery("select loadurl, editurl, caption, sortorder, gridnavbar, multiselect, multiboxonly, resetSearchOnClose, multiplesearch, customeditbutton, griddatatype, autoencode, sortname, gridrowNum,gridrowList, generateExcel,showsearchboxonload,searchboxtop from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
 		 //System.out.println("select loadurl, editurl, caption, sortorder, gridnavbar, multiselect, multiboxonly, resetSearchOnClose, multiplesearch, customeditbutton, griddatatype, autoencode, sortname, gridrowNum,gridrowList, generateExcel,showsearchboxonload,searchboxtop from structure where part_id='"+part_id+"' and interface_id='"+interface_id+"'");
 		 while(resultset.next())
 		 {
 			//autoencode=resultset.getString(1);
 			gridParameters.put("loadurl", resultset.getString(1));
 			gridParameters.put("editurl", resultset.getString(2));
 			gridParameters.put("caption", resultset.getString(3));
 			gridParameters.put("sortorder", resultset.getString(4));
 			gridParameters.put("navbar", resultset.getString(5));
 			gridParameters.put("multiselect", resultset.getString(6));
 			gridParameters.put("multiboxonly", resultset.getString(7));
 			gridParameters.put("resetSearchOnClose", resultset.getString(8));
 			gridParameters.put("multiplesearch", resultset.getString(9));
 			gridParameters.put("customeditbutton", resultset.getString(10));
 			gridParameters.put("griddatatype", resultset.getString(11));
 			gridParameters.put("autoencode", resultset.getString(12));
 			gridParameters.put("sortname", resultset.getString(13));
 			gridParameters.put("gridrowNum", resultset.getString(14));
 			gridParameters.put("gridrowList", resultset.getString(15));
 			gridParameters.put("generateExcel", resultset.getString(16));
 			gridParameters.put("showsearchboxonload", resultset.getString(17));
 			gridParameters.put("searchboxtop", resultset.getString(18));
 		 }
 		 resultset.close();
 		 statement.close();
 		 oConn.close();
 	 }
 	 catch(SQLException sqlexception)
 	 {
 		 sqlexception.printStackTrace();
 	 }
 	 finally
 	 {
 		 if(oConn!=null)
 		 {
 			 try
 			 {
 				 resultset.close();
 				 statement.close();
 				 oConn.close();
 			 } catch(Exception e){}	
 		 }
 	 }
 	 return gridParameters;
  }
  
  
  public static Vector getChoose_one_information(String interface_id, String part_id)
  {
	  Vector choose_one_information = new Vector();
	  Statement  oStmt=null;
	  Connection oConn = null;
	  ResultSet oRset =null;
	  try
	  {
		  oConn = ds.getConnection();
		  oStmt = oConn.createStatement();
		  for(oRset = oStmt.executeQuery("select a.show_choose_one,a.choose_one_label,a.choose_one_value from content a where a.part_id='"+part_id+"' and a.interface_id='"+interface_id+"'");oRset.next();)
		  {
			  choose_one_information.addElement(oRset.getString(1));
			  choose_one_information.addElement(oRset.getString(2));
			  choose_one_information.addElement(oRset.getString(3));    
		  }
		  oRset.close();
		  oStmt.close();
		  oConn.close();
	  }
	  catch (SQLException sqlexception) {
		  sqlexception.printStackTrace();
	  }
	  catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(oConn!=null)
		  {
			  try
			  {
				  oRset.close();
				  oStmt.close();
				  oConn.close();
			  } catch(Exception e){}	
		  }
	  }
	  return choose_one_information;		
  } 
  
  public static String getGridColumnDropdownSqlQuery(String interface_id,String part_id,String column_id)
  {
 		//ResultSet oRset=null;
 	 String s_query="";
 	 Connection oConn =null;
 	 Statement statement = null;
 	 ResultSet oRset = null;
 		
         
 	 try
 	 {
 		 oConn =ds.getConnection();
 		 statement = oConn.createStatement();
 		 //System.out.println("select value from edit_type where interface_id='"+interface_id+"' and part_id='"+part_id+"' and col_name='"+column_id+"' ");
 		 oRset = statement.executeQuery("select value from edit_type where interface_id='"+interface_id+"' and part_id='"+part_id+"' and col_name='"+column_id+"' ");
 		 while(oRset.next())
 		 {
 			 s_query=oRset.getString(1);
 		 }	
 			
 		 oRset.close();
 		 statement.close();
 		 oConn.close();	
 	 }
 	 catch(SQLException sqlexception)
 	 {
 		 sqlexception.printStackTrace();
 	 }
 	 finally
 	 {
 		 if(oConn!=null)
 		 {
 			 try
 			 {
 				 oRset.close();
 				 statement.close();
 				 oConn.close();
 			 } catch(Exception e){}	
 		 }
 	 }
 		
 	 return s_query;
  }
  
  ////////End of Partha////////////////////
  
  
}
	
