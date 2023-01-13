package interfaceenginev2;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetJSONforGrid
 */
@WebServlet("/interfaceenginev2.GetJSONforGrid")
public class GetJSONforGrid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetJSONforGrid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json; charset=UTF-8");
		
		 HttpSession mysession = request.getSession(false);
//		 String[] namepairvalueforexcel = (String[])mysession.getAttribute("namepairvalueforexcel");
		 String[] namepairvalue = (String[])mysession.getAttribute("namepairvalue");
		 String user_id = (String)mysession.getAttribute("user_id");
		 String interface_id = request.getParameter("interface_id");
		 String part_id = request.getParameter("part_id");
		 
		 String sql_query = "";
		 Vector param_title_vector = new Vector();
		 Vector param_title_value = new Vector();
		 String sidx = NewDataBaseLayer.getSortname(interface_id,part_id);
		 String sord = NewDataBaseLayer.getSortOrder(interface_id,part_id);
		 if(sord==null)
			  sord="";

		  if(sidx==null)
			  sidx="";
		  
		 String parameter_title=NewDataBaseLayer.getLoadQueryParameterTitle(interface_id,part_id);

		  if(parameter_title.equals(""))
		  {
			  sql_query = PortalEngine.ChangeVectorGridLoadQueryForGrid(interface_id,part_id,namepairvalue);  
		  }
		  else
		  {
			  param_title_vector = StringtoVector(parameter_title);
			  for(int j=0;j<param_title_vector.size();j++)
			  {
				  String param_value =(String)mysession.getAttribute((String)param_title_vector.elementAt(j));
				  param_title_value.addElement((String)param_title_vector.elementAt(j));
				  param_title_value.addElement(param_value);
			  }
			  
			  sql_query = PortalEngine.ChangeVectorGridLoadQueryForGrid(interface_id,part_id,namepairvalue,param_title_value);
			  
		  }
		  if(sql_query.contains("%current_login_user_id%"))
		  {
			  sql_query = sql_query.replace("%current_login_user_id%","'"+user_id+"'");
		  }
		  System.out.println("===sql_query==="+sql_query);
		  String modified_search_query = "";
		  if((sord.equals("")) && (sidx.equals("")))
			  modified_search_query = sql_query;
		  else if((sord.equals("")) && !(sidx.equals("")))
			  modified_search_query = sql_query+" order by "+sidx;
		  else if(!(sord.equals("")) && (sidx.equals("")))
			  modified_search_query = sql_query;
		  else
			  modified_search_query = sql_query+" order by "+sidx+" "+sord;

		  ServletOutputStream servletOut = response.getOutputStream();
		  NewDataBaseLayer.getTableDataFromQuery(modified_search_query,servletOut);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private  Vector StringtoVector(String StringtoConvert)
	  {
		  int index = 0;
		  Vector vv = new Vector();
	  	
		  String remain_string = "";
		  String table_name="";
	  	
		  for(int i=0;i<StringtoConvert.length();i++)
		  {
			  if(StringtoConvert.charAt(i)==',')
			  {
				  index= index+1;
			  }
		  }
	  	
		  if(index == 0)
			  vv.addElement(StringtoConvert);
		  else
		  {
			  for(int j=0;j<index;j++)
			  {
				  table_name = StringtoConvert.substring(0,StringtoConvert.indexOf(","));
				  StringtoConvert = StringtoConvert.substring(StringtoConvert.indexOf(",")+1);
				  vv.addElement(table_name);
			  }
			  vv.addElement(StringtoConvert);
		  }  	
		  return vv;
	  }


}
