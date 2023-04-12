package interfaceenginev2;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import org.grlea.log.*;
// import net.sf.ehcache.Ehcache;




/**
 * Title:		 Portal Servlet
 * Description:	 Portal Opening Page
 * Copyright:    Copyright (c) 2008
 * Company:		 Aunwesha Knowledge Technologies Pvt. Ltd.
 * @author		Shibaji Chatterjee
*/

@WebServlet("/PortalServlet") 
public class PortalServlet extends HttpServlet  {

	
	private static InterfaceCachePojo ICP=null;
	private static String useInterfaceCaching = "";
	private static String defaultCacheName = "";
	private static final long ONE_SECOND_IN_MILLIS = TimeUnit.SECONDS.toMillis(1);
   
	/*public void init(ServletConfig config) throws ServletException {
	   
		super.init(config);
		ServletContext sc = config.getServletContext();
	    useInterfaceCaching = (String)sc.getAttribute("useInterfaceCaching");
	    defaultCacheName = (String)sc.getAttribute("DefaultCacheName");
	    ICP=(InterfaceCachePojo)sc.getAttribute("ICP");
	}*/

	public void doPost(HttpServletRequest request, HttpServletResponse response)
  			 throws ServletException, IOException {
		//System.out.println("1111111");
		ServletOutputStream out=response.getOutputStream();
	     
		String InterfaceID=request.getParameter("IID");
		//System.out.println("IID======="+InterfaceID);
		if (InterfaceID == null)
		{
			out.println("No Interface ID sent as parameter");
		    //System.out.println("No Interface ID sent as parameter");
			return;
		}
		boolean result = InterfaceID.matches("[a-zA-Z]+");
		if (false == result)
		{
			out.println("Interface \"" + InterfaceID + "\" not found");
		    //System.out.println("Interface \"" + InterfaceID + "\" not found");
			return;
		}
			
		if (false == NewDataBaseLayer.checkIfInterfaceExists(InterfaceID))
		{
			out.println("Interface \"" + InterfaceID + "\" not found");
		    //System.out.println("Interface \"" + InterfaceID + "\" not found");
			return;
		}

							
		String getContentTypeHtml=NewDataBaseLayer.getContentTypeHtml(InterfaceID);
		if((getContentTypeHtml==null)||getContentTypeHtml.equals(""))
			response.setContentType("text/html; charset=UTF-8");
		else
			response.setContentType(getContentTypeHtml);

		String getcachecontrol=NewDataBaseLayer.getcachecontrol(InterfaceID);
		//System.out.println("getcachecontrol==="+getcachecontrol);
		if((getcachecontrol==null) || getContentTypeHtml.equals(""))
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");  //enable browser caching
		else
			response.setHeader("Cache-Control", getcachecontrol);


		String getexpire=NewDataBaseLayer.getexpires(InterfaceID);
		//Long  exp=Long.parseLong(getexpire) ;
		if(getexpire!=null)
			response.setHeader("Expires",getexpire);

		Timestamp interfaceLastModified=NewDataBaseLayer.getLastModified(InterfaceID);
		long timeLastModified = interfaceLastModified.getTime();
		response.setDateHeader("Last-Modified", timeLastModified);
			
		response.setDateHeader("Expires",0);  //for Chrome

		//long ifModifiedSince = request.getDateHeader("If-Modified-Since");

						
		String getRoleCheck=NewDataBaseLayer.getRoleCheck(InterfaceID);
		if(getRoleCheck==null)
			getRoleCheck="";
			

		HttpSession mysession = null;
		String getSessionCheck=NewDataBaseLayer.getSessionCheck(InterfaceID);
		//System.out.println("getSessionCheck==="+getSessionCheck);
		if (getSessionCheck.equals("true"))
			mysession=request.getSession(true);
		else 
			mysession=request.getSession(false);

        if(mysession!=null && mysession.getAttribute("namepairvalue")!=null)
		{
        	mysession.removeAttribute("namepairvalue");		 // for xmlcreator
		}
		String page = null;
		boolean cacheRunning = isCacheRunning(); 
		InputStream in= null;
		    

		//System.out.println("mysession==="+mysession+"===getRoleCheck==="+getRoleCheck);
		if(getRoleCheck.equals("") || getRoleCheck.equals("true"))
		{
			if(mysession==null)
			{
				//System.out.println("role=true and invalid session ????");
				out.println("role=true and invalid session ????");
				return;
			}
			String  user_id = (String)mysession.getAttribute("user_id");
			//System.out.println("user_id==="+user_id);
			if(user_id==null)
			{
				//System.out.println("role=true and user id = null ????");
				out.println("role=true and user id = null ????");
				response.sendRedirect("./PortalServlet?IID=LoginPage");
				return;
			}
			else
			{
				//if ((true == cacheRunning) && (true == ICP.checkCachingRequired(InterfaceID)))
				//per interface cache enablement checking is too expensive performance wise					
				if ((true == cacheRunning))
				{
					//System.out.println("role=user and caching=true");
					//String cache_name = ICP.getCacheName(InterfaceID);
					String cache_name = defaultCacheName;   // per interface cache name is too expensive performance wise
					String cache_key = user_id+InterfaceID;
					page = ICP.getInterfaceFromCacheName(cache_name,cache_key);
					if(page==null)
						page="";
					if(page.equals(""))
					{
						//System.out.println("cache is empty");
						String role_id=NewDataBaseLayer.getRoleID(user_id);
						String interfaceengine_role_id=NewDataBaseLayer.getInterfaceengineRoleID(role_id.toUpperCase());
						Vector imagefile=NewDataBaseLayer.getHTMLAsString(interfaceengine_role_id,InterfaceID);
						if (imagefile!=null) 
						{
							for(int j=0;j<imagefile.size();j=j+1)
							{
								String page_string = (String)imagefile.elementAt(0);
								ICP.setInterfaceFromCacheName(cache_name,cache_key,page_string);
								in = org.apache.commons.io.IOUtils.toInputStream(page_string);
								int len = 0;
								byte buffer[]= new byte[1024];
								try {
									while ((in != null) && ((len = in.read(buffer)) != -1)) 
									{
										out.write(buffer,0,len);
									}
								}
								finally 
								{
									if (in != null) in.close();
								}
							}
							return;
						}
						out.println("Cache is empty but markup not avalible from database");   //This should not happen
						//System.out.println("Cache is empty but markup not avalible from database");
						return;
					}
					else  //send the contents of the cache
					{
						//System.out.println("cache is not empty; send markup from cache");
						in = org.apache.commons.io.IOUtils.toInputStream(page);
						int len = 0;
						byte buffer[]= new byte[1024];
						try {
							while ((in != null) && ((len = in.read(buffer)) != -1)) {
								out.write(buffer,0,len);
							}
						}
						finally {
							if (in != null) in.close();
						}
						return;
					}
				}
				//No caching
				long ifModifiedSince = request.getDateHeader("If-Modified-Since");
				if (ifModifiedSince != -1L) {
					boolean notModified = ifModifiedSince + ONE_SECOND_IN_MILLIS > timeLastModified;
					if (notModified) {
						response.sendError(HttpServletResponse.SC_NOT_MODIFIED); //browser should used cached version
						return;  //no further processing required
					}
				}
				//System.out.println("role=user and caching=false");
				String role_id=NewDataBaseLayer.getRoleID(user_id);
				String interfaceengine_role_id=NewDataBaseLayer.getInterfaceengineRoleID(role_id.toUpperCase());
				//System.out.println(".................interfaceengine_role_id........"+interfaceengine_role_id);
				Vector imagefile=NewDataBaseLayer.getHTML(interfaceengine_role_id,InterfaceID);
				if (imagefile!=null) 
				{
					for(int j=0;j<imagefile.size();j=j+1){
						in = (InputStream)imagefile.elementAt(0);
						int len = 0;
						byte buffer[]= new byte[1024];
						try {
							while ((in != null) && ((len = in.read(buffer)) != -1)) {
								out.write(buffer,0,len);
							}
						}
						finally {
							if (in != null) in.close();
						}
					}
					return;
				}
				else    //This should never happen
				{				
					out.println("Markup not avalible from database");   //This should not happen
					//System.out.println("Markup not avalible from database");
					return;
				}	
			}
		}


		if(getRoleCheck.equals("nouser"))
		{
			if ((true == cacheRunning))  // && (true == ICP.checkCachingRequired(InterfaceID)))
			{
				//System.out.println("role=nouser and caching=true");
				//String cache_name = ICP.getCacheName(InterfaceID);
				String cache_name = defaultCacheName;   // per interface cache name is too expensive performance wise
				String cache_key = InterfaceID;
				page = ICP.getInterfaceFromCacheName(cache_name,cache_key);
				if(page==null)
					page="";
				if(page.equals(""))
				{
					//System.out.println("cache is empty");
					String role=(String)mysession.getAttribute("role_id");
					String role_id=NewDataBaseLayer.getDefaultRoleID(role);
					Vector imagefile=NewDataBaseLayer.getHTMLAsString(role_id,InterfaceID);
					if (imagefile!=null) 
					{
						for(int j=0;j<imagefile.size();j=j+1){
							String page_string = (String)imagefile.elementAt(0);
							ICP.setInterfaceFromCacheName(cache_name,cache_key,page_string);
							in = org.apache.commons.io.IOUtils.toInputStream(page_string);
							int len = 0;
							byte buffer[]= new byte[1024];
							try {
								while ((in != null) && ((len = in.read(buffer)) != -1)) {
									out.write(buffer,0,len);
								}
							}
							finally {
								if (in != null) in.close();
							}
						}
						return;
					}
					else
					{
						out.println("Cache is empty but markup not avalible from database");   //This should not happen
						//System.out.println("Cache is empty but markup not avalible from database");
						return;
					}
				}
				else //send the contents of the cache
				{
					//System.out.println("cache is not empty; send markup from cache");
					in = org.apache.commons.io.IOUtils.toInputStream(page);
					int len = 0;
					byte buffer[]= new byte[1024];
					try {
						while ((in != null) && ((len = in.read(buffer)) != -1)) {
							out.write(buffer,0,len);
						}
					}
					finally {
						if (in != null) in.close();
					}
					return;
				}					
			}	
			// no caching
			long ifModifiedSince = request.getDateHeader("If-Modified-Since");
			if (ifModifiedSince != -1L) {
				boolean notModified = ifModifiedSince + ONE_SECOND_IN_MILLIS > timeLastModified;
					if (notModified) {
					response.sendError(HttpServletResponse.SC_NOT_MODIFIED); //browser should used cached version
					return;  //no further processing required
				}
			}
			//System.out.println("role=nouser and caching=false");
			ResourceBundle rb = ResourceBundle.getBundle("portal",Locale.getDefault());      
			String defaultroletitle= "defaultroletitle"; 
			String role = rb.getString(defaultroletitle);
			//String role=(String)mysession.getAttribute("role_id");
			String role_id=NewDataBaseLayer.getDefaultRoleID(role);
			//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>role_id>>>>>>>>>>"+role);
			Vector imagefile=NewDataBaseLayer.getHTML(role_id,InterfaceID);
			if (imagefile!=null) {
				for(int j=0;j<imagefile.size();j=j+1){
					in = (InputStream)imagefile.elementAt(0);
					int len = 0;
					byte buffer[]= new byte[1024];
					try {
						while ((in != null) && ((len = in.read(buffer)) != -1)) {
							out.write(buffer,0,len);
						}
					}
					finally {
						if (in != null) in.close();
					}
				}
				return;
			}
			else
			{
				out.println("Markup not avalible from database");   //This should not happen
				//System.out.println("Markup not avalible from database");
				return;
			}
		}
		String roleString = "DEFAULT";
		if(getRoleCheck.equals("false"))
		{
			if(mysession==null) {
				response.sendRedirect("./PortalServlet?IID=LoginPage");
			} else {
				String  user_id = (String)mysession.getAttribute("user_id");
				//System.out.println("user_id==="+user_id);
				if(user_id==null)
				{
					//System.out.println("role=false and user id = null ????");
					out.println("role=false and user id = null ????");
					response.sendRedirect("./PortalServlet?IID=LoginPage");
					return;
				}
				else
				{
					if ((true == cacheRunning))// && (true == ICP.checkCachingRequired(InterfaceID)))
					{
						//System.out.println("role=false and caching=true");
						//String cache_name = ICP.getCacheName(InterfaceID);
						String cache_name = defaultCacheName;   // per interface cache name is too expensive performance wise
						String cache_key = InterfaceID;
						page = ICP.getInterfaceFromCacheName(cache_name,cache_key);
						if(page==null)
							page="";
						if(page.equals(""))  //cache is empty
						{
							//System.out.println("cache is empty");					
							String role=NewDataBaseLayer.getDefaultRoleID(roleString);
							Vector imagefile=NewDataBaseLayer.getHTMLAsString(role,InterfaceID); //read from database
							if (imagefile!=null) {
								for(int j=0;j<imagefile.size();j=j+1){
									String page_string = (String)imagefile.elementAt(0);
									ICP.setInterfaceFromCacheName(cache_name,cache_key,page_string); //write to cache
									in = org.apache.commons.io.IOUtils.toInputStream(page_string);
									int len = 0;
									byte buffer[]= new byte[1024];
									try {
										while ((in != null) && ((len = in.read(buffer)) != -1)) {
											out.write(buffer,0,len);
										}
									}
									finally {
										if (in != null) in.close();
									}
								}
								return;
							}
							else
							{
								out.println("Cache is empty but markup not avalible from database");   //This should not happen
								//System.out.println("Cache is empty but markup not avalible from database");
								return;
							}
						}
						else  //send the contents of the cache
						{
							//System.out.println("cache is not empty; send markup from cache");					
							in = org.apache.commons.io.IOUtils.toInputStream(page);
							int len = 0;
							byte buffer[]= new byte[1024];
							try {
								while ((in != null) && ((len = in.read(buffer)) != -1)) {
									out.write(buffer,0,len);
								}
							}
							finally {
								if (in != null) in.close();
							}
							return;
						}
					}
					// no caching
					long ifModifiedSince = request.getDateHeader("If-Modified-Since");
					if (ifModifiedSince != -1L) {
						boolean notModified = ifModifiedSince + ONE_SECOND_IN_MILLIS > timeLastModified;
						//System.out.println(notModified);
						if (notModified) {
							response.sendError(HttpServletResponse.SC_NOT_MODIFIED); //browser should used cached version
							return;  //no further processing required
						}
					}
					//System.out.println("role=false and caching=false");
					String role=NewDataBaseLayer.getDefaultRoleID(roleString);
					Vector imagefile=NewDataBaseLayer.getHTML(role,InterfaceID);
					if (imagefile!=null) {
						for(int j=0;j<imagefile.size();j=j+1){
							in = (InputStream)imagefile.elementAt(0);
							int len = 0;
							byte buffer[]= new byte[1024];
							try {
								while ((in != null) && ((len = in.read(buffer)) != -1)) {
									out.write(buffer,0,len);
								}
							}
							finally {
								if (in != null) in.close();
							}
						}
						return;
					}
					else
					{
						out.println("Markup not avalible from database");   //This should not happen
						//System.out.println("Markup not avalible from database");
						return;
					}
				}
			}
		}        
		out.println("No information about role - Default or otherwise");   //This should not happen
		//System.out.println("No information about role - Default or otherwise");
		return;
	}

	public void doGet(HttpServletRequest request,HttpServletResponse response)
				throws ServletException,IOException
	{
		//System.out.println("222222");	
		doPost(request, response);
	}


	public boolean isCacheRunning()
	{
		boolean flag = false;
		String cacheStatus = "";
		if (ICP != null) cacheStatus = ICP.getStatus();
		//System.out.println("cacheStatus==="+cacheStatus);
		if(useInterfaceCaching.equals("true") && cacheStatus.equals("STATUS_ALIVE"))
			flag = true;
		return flag;
	}		
}					                                                                                                 									
