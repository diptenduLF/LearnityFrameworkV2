package interfaceenginev2;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.grlea.log.*;
import javax.servlet.http.HttpSession;





/**
 * Title:		 DisplayEngine
 * Description:	 DropDown Generate
 * Copyright:    Copyright (c) 2010
 * Company:		 Aunwesha Knowledge Technologies Pvt. Ltd.
 * @author		Shibaji Chatterjee
*/

@WebServlet("/interfaceenginev2.SelectDataProviderServlet") 
public class SelectDataProviderServlet extends HttpServlet  {

   public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
  			 throws ServletException, IOException {
	       ServletOutputStream out=response.getOutputStream();
	       HttpSession mysession=request.getSession();
	       String  user_id = (String)mysession.getAttribute("user_id");
			 String child_id=request.getParameter("child_id");
			 String interface_id=request.getParameter("interface_id");
			 String colname=request.getParameter("colname");
			 String sql_query="";
			 Vector getEditOption=NewDataBaseLayer.getGridSelectEditOption(child_id,interface_id,colname);
			 for(int j=0;j<getEditOption.size();j=j+8)
			 {
				 sql_query=(String)getEditOption.elementAt(j+5);			
			 }
			 if(sql_query.contains("%current_login_user_id%"))
			 {
				 sql_query = sql_query.replace("%current_login_user_id%","'"+user_id+"'");
			 }
			 String html="<option value=\"0\">Choose One</option>";

			 Vector dropdownstring=NewDataBaseLayer.returnpagedropdown(sql_query);
			 for(int i=0;i<dropdownstring.size();i=i+2)
			 {
				 String name=(String)dropdownstring.elementAt(i);
				 String value=(String)dropdownstring.elementAt(i+1);
				 html+="<option value=\""+name+"\">"+value+"</option>";
			 }	
		
			 out.println("<select>"+html+"</select>");
									
}
public void doGet(HttpServletRequest request,HttpServletResponse response)
				throws ServletException,IOException{
			
          doPost(request, response);
                }
}					                                                                                                 									