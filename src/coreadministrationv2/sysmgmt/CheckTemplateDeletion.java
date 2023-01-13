package coreadministrationv2.sysmgmt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckTemplateDeletion
 */
@WebServlet("/coreadministrationv2.sysmgmt.CheckTemplateDeletion")
public class CheckTemplateDeletion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckTemplateDeletion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        
        String responseString = null;
		String template_id=request.getParameter("template_id");
		boolean flag=interfaceenginev2.NewDataBaseLayer.checkIfTemplateUsedOrDefaultTemplateUsed(template_id);
		if(flag)
			 responseString =  "Not OK";
		else
			  responseString =  "OK";
		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
