package coreadministrationv2.sysmgmt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

/**
 * Title:        Learnity Application Template Management   
 * Description:
 * Copyright:    Copyright (c) 2010
 * Company:      Aunwesha
 * @author Shibaji Chatterjee
 */




import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ecs.html.Body;
import org.apache.ecs.html.Form;
import org.apache.ecs.html.Head;
import org.apache.ecs.html.Html;
import org.apache.ecs.html.IMG;
import org.apache.ecs.html.Input;
import org.apache.ecs.html.Link;
import org.apache.ecs.html.Script;
import org.apache.ecs.html.TBody;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;
import org.apache.ecs.html.Title;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.oreilly.servlet.MultipartRequest;
import comv2.aunwesha.JSPGrid.JSPGridPro2;
import comv2.aunwesha.lfutil.FileUtil;
import comv2.aunwesha.lfutil.GenericUtil;
import comv2.aunwesha.lfutil.Pair;
import coreadministrationv2.dbconnection.DataBaseLayer;
import coreadministrationv2.sysmgmt.xml.util.SchemaValidatation;
import coreadministrationv2.utility.TableExtension;

@WebServlet("/coreadministrationv2.sysmgmt.ApplicationTemplateManagement") 
public class ApplicationTemplateManagement extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3293251296563669861L;
	private static final String _DEFAULT_VALUE_YES = "yes";
	private static final String LOGIN_SESSION_NAME = "ADMIN_LOG_ON";
	//private static final String OBJ = "OBJ";
	//private static final SimpleLogger log = new SimpleLogger(ApplicationTemplateManagement.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
    	String statusMessage="";
        response.setContentType("text/html");
        response.setHeader("Pragma", "no-cache");
	     response.setHeader("Cache-Control", "no-cache");
	     response.setDateHeader("Expires", 0);
        PrintWriter out = response.getWriter();
        /***************************************************************************************************/
        /*                                      Check Authentication                                       */
        /***************************************************************************************************/
        HttpSession mysession=request.getSession(true);
		  Object obj = mysession.getAttribute(LOGIN_SESSION_NAME);
		  if (obj ==null)
			response.sendRedirect("coreadmin/login.html");
        else {
        	String strAdminId = obj.toString();
        	String strPrmAddModDel = request.getParameter("prmAddModify");

        	//String strAdministratorPreviledge = coreadministration.dbconnection.DataBaseLayer.getAdminstratorPreviledge(strAdminId);
        	//if (strAdministratorPreviledge != null) {
	        	if (strPrmAddModDel!=null) {
		        	int iPrmAddModify = Integer.parseInt(strPrmAddModDel);
			        switch(iPrmAddModify) {

			        	case 0:
				        	
			        		statusMessage=add(request, strAdminId, out);
				        	        break;
			        	case 1:
			        		
			        		statusMessage=modify(request, strAdminId,out);
			        		        break;
			        	case 2:
			        		
			        		statusMessage=delete(request, out);
			        		        break;
			        							}
				}
	        	getResult(request, response, out, strAdminId,statusMessage);
        	
        }
    }

    public void getResult(HttpServletRequest request, HttpServletResponse response, PrintWriter out, String strAdminId,String statusMessage)
    		throws IOException, ServletException {
    	/***************************************************************************************************/
        /*                                        For Date And Time										   */
        /***************************************************************************************************/
        Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		calendar.setTime(trialTime);

		//create array of string to hold days.
		String months[]={"January","Feburary","March", "April", "May","June",
							"July","August","September","October","November","December"};

		String strTime = calendar.get(Calendar.HOUR)+":"+ calendar.get(Calendar.MINUTE);
		String strDate = months[calendar.get(Calendar.MONTH)]+" "+ calendar.get(Calendar.DATE)+", "+
																calendar.get(Calendar.YEAR);

        /********************************************End of Date and Time***********************************/

        /***************************************************************************************************/
        /*                                        Get Parameter Value									   */
        /***************************************************************************************************/
		  String template_id = request.getParameter("template_id");
         
 
        Body body = new Body();
        Form form = new Form();
	     Input inputButton1 = new Input();
	     Input inputButton2 = new Input();
	     Input inputButton3 = new Input();
		  Input inputButton4 = new Input();
		  Input inputButton5 = new Input();
	     Input viewButton = new Input();
		  inputButton1.setOnClick("addLayout_onclick();");
		  inputButton2.setOnClick("download_onclick();");
		  inputButton3.setOnClick("deleteLayout_onclick();");
		  inputButton4.setOnClick("setDefault_onclick();");
		  inputButton5.setOnClick("showLayout_onclick();");
	//	  viewButton.setOnClick("viewLayout_onclick();");
	     Html html = new Html()
               .addElement(new Head()
					.addElement(new Title("Template Management"))
					.addElement(new Link()
						.setHref("coreadmin/js/stylesheet.css")
						.setRel("stylesheet"))
					.addElement(new Script()
						.setLanguage("JavaScript")
						.setSrc("coreadmin/js/check.js"))
					.addElement(new Script()
							.setLanguage("JavaScript")
							.setSrc("coreadmin/js/jquery.min.js"))
					.addElement(new Script()
							.setLanguage("JavaScript")
							.setSrc("coreadmin/js/coreadministrationv2.sysmgmt/ApplicationTemplateManagement.js")))

               .addElement(body
					.addElement(form
	              	.setName("frm")
				  	.addElement(new TableExtension()
				  		.headerTable("<b>System Administrator: </b>"+strAdminId, strDate, strTime, "<b>System Administration: </b>Application Template Management"))));
	    
		  
		  
		  form.addElement(new Table()
									 .setBorder(0)
									 .setCellPadding(0)
									 .setCellSpacing(0)
									 .setWidth("100%")
									 .addElement(new TR()
									 .addElement(new TD()
									 .addElement(new Table()
									 .setBorder(0)
									 .setCellPadding(0)
									 .setCellSpacing(0)
									 .setWidth("100%")
									 .addElement(new TBody()
									 .addElement(new TR()
									 .addElement(new TD()
									 .addElement(new IMG()
									 .setBorder(0)
									 .setHeight(8)
									 .setWidth(10)
									 .setSrc("coreadmin/images/T.gif")))
								    )))
									
									
								    )));
		  String sql = "select a.application_template_id as \"Select\", a.application_template_title as \"Template\", "+
				         " a.default_value as \" Default value\", a.ui_framework as \" UI Framework\", a.block_ui_timeout as \" Block UI Timeout\","+
		                 " CONCAT(ROUND(length(a.applivation_xml_value)/1024,2),' KB') as \"File Size\","+
				         " a.upload_on as \"Uploaded On\""+
				        " from application_template_master a ";
		try {
			JSPGridPro2 grid1 = new JSPGridPro2(request,"frm"); 
			grid1.setConnectionParameters(sql);		
			
			grid1.setWidth("100%");
			grid1.setCellPadding(2);
			grid1.setCellSpacing(1);
			grid1.setFontFace("Arial");
			grid1.setFontSize(2);
			grid1.setEvenRowBgColor("#C0C0C0"); 
			grid1.setOddRowBgColor("#F0F0F0");
			grid1.setCaption("Currently Defined Template");
			grid1.setMaxRowsPerPage(5);   		//how many records displayed per page
			grid1.setMaxResultPagesPerLoad(5);  //Page : 1 2 3 4 5 6 7 8 9 10 (max 10 pages displayed)
			grid1.setLineNoHeaderBgColor("#48E6F7");
			grid1.Cols(0).setFieldType(grid1.FIELD_RADIO);
			grid1.Cols(1).setFieldType(grid1.FIELD_HIDDEN);		
			grid1.Cols(2).setFieldType(grid1.FIELD_HIDDEN);	
			grid1.Cols(3).setFieldType(grid1.FIELD_HIDDEN);	
			grid1.Cols(4).setFieldType(grid1.FIELD_HIDDEN);	
			grid1.Cols(5).setFieldType(grid1.FIELD_HIDDEN);	
			grid1.Cols(6).setFieldType(grid1.FIELD_HIDDEN);	
			
			
			
			
			grid1.Cols(0).setFieldName("checkbox");
			grid1.Cols(1).setFieldName("themesid");
			grid1.Cols(2).setFieldName("defaultvalue");
			grid1.Cols(3).setFieldName("fileSize");
			grid1.Cols(4).setFieldName("uiFramework");
			grid1.Cols(5).setFieldName("blockUITimeout");
			grid1.Cols(6).setFieldName("uploadedOn");
			
			
			
			
			grid1.Cols(0).Header().setClassID("swb");
			grid1.Cols(1).Header().setClassID("swb");
			grid1.Cols(2).Header().setClassID("swb");
			grid1.Cols(3).Header().setClassID("swb");
			grid1.Cols(4).Header().setClassID("swb");
			grid1.Cols(5).Header().setClassID("swb");
			grid1.Cols(6).Header().setClassID("swb");
			
			
			grid1.Cols(0).insertFieldScript("onclick=\"CCA();checkbox_onclick();\"");
			grid1.setEachRowHeight("20");
			grid1.canSort(0, false);
			grid1.canSort(1, true);
			grid1.canSort(2, true);
			grid1.canSort(3, true);
			grid1.canSort(4, true);
			grid1.canSort(5, true);
			grid1.canSort(6, true);
			
			
			
			grid1.setSortableColumnsToolTip("Click to Sort");
			grid1.showPageNavigationFirst();
			grid1.showPageNavigationLast();
			grid1.hidePageNavigationHTML();
			grid1.setPageNavigationFontFace("Arial");
			grid1.setPageNavigationFontSize(2);
			grid1.setASCImageSource("coreadmin/images/asc.gif");
			grid1.setDESCImageSource("coreadmin/images/desc.gif");
			grid1.buildGrid(); //result set being processed, and cell values are available			
			
			if (grid1.isResultSetEmpty()) {
				form.addElement("<p id=\"record\">No Records Found");
			}
			else {						
				grid1.countResultSet();				
				form.addElement("<p>Total No. Of Template: " +grid1.getRows());
				form.addElement(grid1.getGrid());
			}	
			//Added by Diptendu 29-Oct-2015
			
			grid1.closeConnection();
			
			
		}
		catch (Exception exp) {
			
		}	
		Input styleid= new Input().setType("file").setName("filename").setClassId("PPRLabelText");

		Table table=new Table();
		Table table2=new Table();
		table2.addElement(new Table(1)
				.setWidth("100%")
				.setBorder(0)
				.setCellPadding(0)
				.setCellSpacing(0)
				
							
				.addElement(new TR()
				.addElement(new TD()
				.setClassId("PPRLabelText")
				.setWidth("160")
				.addElement("Default Value"))
				.addElement(new TD()
				.setWidth("336")
				.addElement(new Input()
				.setClassId("PPRLabelText")
				.setName("defaultvalue1")
				.setValue(_DEFAULT_VALUE_YES)
				.setType("checkbox")
							  )))
							
				
							
				.addElement(new TR()
				.addElement(new TD()
				.setClassId("PPRLabelText")
				.addElement("File ")
							  )
				.addElement(new TD()					
				.setClassId("PPRLabelText")
				.addElement(styleid))
							  )
				.addElement(new TR()
				.addElement(new TD()
				.setWidth(47)
							  ))
					
							  );
						 
				

				
				
		Table table1=new Table(); 
		table1.addElement(new Table()
				.setBorder(0)
				.setCellPadding(0)
				.setCellSpacing(0)
				.addElement(new TR()
				.addElement(new TD()
				.addElement(inputButton1
				.setClassId("sbttn")
				.setName("addGrop")
				.setTabindex(2)
				.setTitleValue("Upload")
				.setType("button")
				.setValue("Upload")))
				.addElement(new TD()
				.setWidth(5))
				.addElement(new TD()
				.addElement(inputButton2
				.setClassId("sbttn")
				.setName("modifyGroup")
				.setTabindex(2)
				.setTitleValue("Download")
				.setType("button")
				.setValue("Download")))
				.addElement(new TD()
				.setWidth(5))
				.addElement(new TD()
				.addElement(inputButton3
				.setClassId("sbttn")
				.setName("deleteGroup")
				.setTabindex(2)
				.setTitleValue("Delete")
				.setType("button")
				.setValue("Delete")))
				
				.addElement(new TD()
				.setWidth(5))
				.addElement(new TD()
				.addElement(inputButton4
				.setClassId("sbttn")
				.setName("deleteGroup")
				.setTabindex(2)
				.setTitleValue("Set As Default")
				.setType("button")
				.setValue("Set As Default")))
				
				        .addElement(new TD()
						.setWidth(5))
						.addElement(new TD()
						.addElement(inputButton5
						.setClassId("sbttn")
						.setName("show")
						.setTabindex(2)
						.setTitleValue("Show")
						.setType("button")
						.setValue("Show")))
						
	/*			.addElement(new TD()
				.setWidth(5))
				.addElement(new TD()
				.addElement(viewButton
				.setClassId("sbttn")
				.setName("view")
				.setTabindex(2)
				.setTitleValue("View")
				.setType("button")
				.setValue("View")))*/

							  ));
		table.addElement(new TR()
				.addElement(new TD()
				.addElement(table2)))
				.addElement(new TR()
				.addElement(new TD()
				.addElement(table1)));
				form.addElement(table);
				form.addElement("<input type=\"hidden\" name=\"template_id\"/>");
				form.addElement("<div id=\"status-message\" style=\"color:red;\">"+statusMessage+"</div>");
				body.addElement(form);			
				//body.setOnLoad("scrollit(100);load()");
			
				out.println(html.toString());
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        doGet(request, response);
    }
    private String add(HttpServletRequest request, String strCreatedBy, PrintWriter out1)
       throws IOException, ServletException 
	 {
    	String statusMessage=null; 
    	String template_id="";
		 boolean isSuccess=true;
				ResourceBundle rb = ResourceBundle.getBundle("portal",Locale.getDefault());      
				String filename="";
				String s7="";
				String key1= "templatexml"; 
				String photopath = rb.getString(key1);
				String path=photopath;
				String strSize="";
				File ff=new File(path);
				ff.mkdir();
				String attachmentname=path;
				File uploadfile = new File(attachmentname);
				String default_value= null;
				try
				{
					MultipartRequest multipartrequest = new MultipartRequest(request,attachmentname,50*1024*1024);
					for(Enumeration enumeration = multipartrequest.getFileNames(); enumeration.hasMoreElements();)
					{	
						String s6 = (String)enumeration.nextElement();
						s7 = multipartrequest.getFilesystemName(s6);
						uploadfile = multipartrequest.getFile(s6);
						Long size=new Long(uploadfile.length());
						strSize=size.toString();	
					}
					filename=multipartrequest.getFilesystemName("filename");
					template_id=multipartrequest.getParameter("template_id");
					default_value=multipartrequest.getParameter("defaultvalue1");
				}	
				catch(IOException ioexception)
				{
					ioexception.printStackTrace();
					isSuccess=false;
				}
				if(isSuccess){
					UnZipApllicationTemplate(attachmentname, s7);
				    String foldername=removeChar(s7);
					String s8=foldername+"/application-template.xml";

					//Added by Diptendu 05-April-2019

					File templateFile = new File(attachmentname+s8);
					long templateFileSize = templateFile.length();
					String templateFileSizeAsString = Long.toString(templateFileSize);

//					statusMessage=uploadTemplateXML(request,template_id,attachmentname,foldername,s8,strSize,default_value).getFirst();

					statusMessage=uploadTemplateXML(request,template_id,attachmentname,foldername,s8,templateFileSizeAsString,default_value).getFirst();

					/*if(_DEFAULT_VALUE_YES.equalsIgnoreCase(default_value)){
						 coreadministrationv2.dbconnection.DataBaseLayer.setDefaultValueTemplate(newTemplateId);
					}
					if(newTemplateId==null){
						statusMessage="Failed to upload Application Template.";
					}else{
						statusMessage="Application Template uploaded successfully.";
					}*/
				}else{
					statusMessage="Failed to upload Application Template.";
				}
			return statusMessage;	
				
     }


        public String removeChar(String s7) 
          {
           if (s7 == null || s7.length() == 0)
             {
               return s7;
             }
            return s7.substring(0, s7.length()-4);
          }


     private String modify(HttpServletRequest request, String strModBy,PrintWriter out1)
        throws IOException, ServletException {
		  String template_id=request.getParameter("template_id");
		  boolean isSuccess=coreadministrationv2.dbconnection.DataBaseLayer.setDefaultValueTemplate(template_id);
		  if(isSuccess){
			  return "Successfully set default Application Template.";
		  }else{
			  return "Failed to set default Application Template.";
		  }
		 
    }
    private String delete(HttpServletRequest request, PrintWriter out1)
        	throws IOException, ServletException {
		 String template_id=request.getParameter("template_id");
		 boolean isSuccess=coreadministrationv2.dbconnection.DataBaseLayer.templateDelete(template_id);
			 if(isSuccess){
			  return "Successfully deleted Application Template.";
		  }else{
			  return "Failed to deleted Application Template.";
		  }
    }

	 public static synchronized Pair<String,String> uploadTemplateXML(HttpServletRequest request,String template_id,String attachmentname,String foldername,String s7,String strSize, String default_value)
	 {
		 String  inFileName=attachmentname+s7; 
		 String pathname=attachmentname+foldername;
		 String statusMessage="";
		 String current_template_id= null;
		 Pair<Boolean, String> validationStatus=SchemaValidatation.validateApplicationTemplateXml(request.getServletContext(),inFileName);
		 boolean isSuccess=validationStatus.getFirst();
		 if(isSuccess){
			 DOMParser parser2 = new DOMParser();
			 try
			 {
				
				 parser2.parse(inFileName);	
				 Document document = parser2.getDocument();
				 NodeList templatelist = document.getElementsByTagName("application-template");
				 for(int x1=0; x1<templatelist.getLength() ; x1++)
				 {
					 Element template = (Element)templatelist.item(x1);
					 String template_title= template.getAttribute("title");
					 String uiFramework= template.getAttribute("UIframework");
					 uiFramework=GenericUtil.hasString(uiFramework)?uiFramework:null;
					 Integer blockUITimeout= GenericUtil.convertStringToInt(template.getAttribute("BlockUItimeout"));
					 
					 System.out.println("..............OLD TEMPLATE ID .............."+template_id);
					 coreadministrationv2.dbconnection.DataBaseLayer.templateDelete(template_id);
					 isSuccess=coreadministrationv2.dbconnection.DataBaseLayer.TemplateInsert(template_title,attachmentname,s7,strSize,uiFramework,blockUITimeout);
					 if(isSuccess){

						 current_template_id=coreadministrationv2.dbconnection.DataBaseLayer.getCurrentTemplate_ID();
						 NodeList application_defaults_list = ((Element)templatelist.item(x1)).getElementsByTagName("application-defaults");
						 for(int x2=0; x2<application_defaults_list.getLength(); x2++)
						 {
							 NodeList configuration_section_list = ((Element)application_defaults_list.item(x2)).getElementsByTagName("configuration-section");
							 for(int a=0; a<configuration_section_list.getLength() ;a++)
							 {
								 //Element configuration_section_list_element= (Element)configuration_section_list.item(x1);
								 //String themes= configuration_section_list_element.getAttribute("ThemeID");
								 NodeList configuration_attribute_list = ((Element)configuration_section_list.item(a)).getElementsByTagName("attribute");
								 for(int g=0; g<configuration_attribute_list.getLength() ; g++)
								 {
									 Element configuration_attribute_element = (Element)configuration_attribute_list.item(g);
									 String  configuration_attribute_name=configuration_attribute_element.getAttribute("name");
									 String  configuration_attribute_value=configuration_attribute_element.getAttribute("defaultvalue");
									 coreadministrationv2.dbconnection.DataBaseLayer.InsertApplicationDefaultValue(current_template_id,"","Configuration",configuration_attribute_name,configuration_attribute_value);
								 }
							 }

							 NodeList nodelistclass = ((Element)application_defaults_list.item(x2)).getElementsByTagName("class");	
							 for(int b=0; b<nodelistclass.getLength() ; b++)
							 {
								 Element classelement = (Element)nodelistclass.item(b);
								 String class_name = classelement.getAttribute("name");
								 NodeList nodelistsection = ((Element)nodelistclass.item(b)).getElementsByTagName("section");
								 for(int c=0;c<nodelistsection.getLength() ; c++)
								 {
									 Element sectionelement = (Element)nodelistsection.item(c);
									 String section_name = sectionelement.getAttribute("name");
									 NodeList nodelistattribute = ((Element)nodelistsection.item(c)).getElementsByTagName("attribute");
									 for(int d=0; d<nodelistattribute.getLength() ; d++)
									 {
										 Element attributeelement = (Element)nodelistattribute.item(d);
										 String  attribute_name=attributeelement.getAttribute("name");
										 String  attribute_value=attributeelement.getAttribute("defaultvalue");
										 coreadministrationv2.dbconnection.DataBaseLayer.InsertApplicationDefaultValue(current_template_id,class_name,section_name,attribute_name,attribute_value);
									 }
								 }

							 }

						 }

						 NodeList framework_asset_delivery_list = ((Element)templatelist.item(x1)).getElementsByTagName("framework-asset-delivery");
						 for(int e=0; e<framework_asset_delivery_list.getLength() ; e++)
						 {
							 NodeList asset_list = ((Element)framework_asset_delivery_list.item(e)).getElementsByTagName("asset");
							 for(int f=0; f<asset_list.getLength() ;f++)
							 {
								 Element asset_list_element= (Element)asset_list.item(f);
								 String type= asset_list_element.getAttribute("type");
								 String deliverymode= asset_list_element.getAttribute("deliverymode");
								 String pagelocation= asset_list_element.getAttribute("pagelocation");
								 String deliverysequence= asset_list_element.getAttribute("deliverysequence");
								 String filename= asset_list_element.getAttribute("filename");
								 String assetpath= asset_list_element.getAttribute("assetpath");
								 String asset_value= asset_list_element.getAttribute("asset_value");
								 
				                  coreadministrationv2.dbconnection.DataBaseLayer.TemplateAsset(current_template_id,type,deliverymode,pagelocation,deliverysequence,filename,assetpath,pathname);
							 }
						 }
					 }else{
						 statusMessage="Failed to upload Application Template. Reason : Application Template with same title '"+template_title+"' already exists";
						 break;
					 }
				 }
				 if(current_template_id!=null && isSuccess){
					 if(_DEFAULT_VALUE_YES.equalsIgnoreCase(default_value)){
						 coreadministrationv2.dbconnection.DataBaseLayer.setDefaultValueTemplate(current_template_id);
					 } 
				 }
				 if(isSuccess){
					 statusMessage="Application Template uploaded successfully.";
				 }
			 }catch (SAXException e) {
				 statusMessage="Failed to upload Application Template. Reason : "+e.getMessage();
				 //current_template_id=null;
				 e.printStackTrace();
			 } 
			 catch (IOException e1) {
				 statusMessage="Failed to upload Application Template. Reason : "+e1.getMessage();
				 //current_template_id=null;
				 e1.printStackTrace();
			 } 	
		 }else{
			 statusMessage=validationStatus.getSecond();
		 }
		 return new Pair<>(statusMessage,current_template_id);
	 }
    
	 private static void UnZipApllicationTemplate(String path, String filename) {

			String attachmentname = path;
			try {
				String inFileName = path + filename;
				String name = filename.substring(filename.lastIndexOf(File.separator) + 1, filename.lastIndexOf("."));
				// Specify destination where file will be unzipped
				String destinationDirectory = attachmentname + name;
				File sourceZipFile = new File(inFileName);
				File unzipDestinationDirectory = new File(destinationDirectory);

				if (unzipDestinationDirectory.exists()) {
					FileUtil.delete(unzipDestinationDirectory);
					unzipDestinationDirectory.mkdirs();
				}
				// Open Zip file for reading
				ZipFile zipFile = new ZipFile(sourceZipFile, ZipFile.OPEN_READ);
				// Create an enumeration of the entries in the zip file
				Enumeration<? extends ZipEntry> zipFileEntries = zipFile.entries();
				// Process each entry
				while (zipFileEntries.hasMoreElements()) {
					// grab a zip file entry
					ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
					String currentEntry = entry.getName();
					// System.out.println("Extracting: " + currentEntry);
					File destFile = new File(unzipDestinationDirectory, currentEntry);

					// grab file's parent directory structure
					File destinationParent = destFile.getParentFile();
					// create the parent directory structure if needed
					destinationParent.mkdirs();
					// extract file if not a directory
					if (!entry.isDirectory()) {
						BufferedInputStream is = new BufferedInputStream(zipFile.getInputStream(entry));
						int currentByte;
						int BUFFER = 2048;
						// establish buffer for writing file
						byte data[] = new byte[BUFFER];
						// write the current file to disk
						FileOutputStream fos = new FileOutputStream(destFile);
						BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
						// read and write until last byte is encountered
						while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
							dest.write(data, 0, currentByte);
						}
						dest.flush();
						dest.close();
						is.close();
					}
				}
				zipFile.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

	}
     

