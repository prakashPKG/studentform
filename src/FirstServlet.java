

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Connection conn;    
	static int sid;
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost/studentdata", "root", "");
		
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sclass=request.getParameter("sclass");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String mname=request.getParameter("mname");
		String mobile=request.getParameter("mobile");
		String emailid=request.getParameter("emailid");
		
		System.out.println(sclass+fname+lname+mname+mobile+emailid);
		
		try {
		PreparedStatement ps=conn.prepareStatement("insert into student(classid, firstname, lastname, middlename, mobilenumber, emailid) values(?,?,?,?,?,?)");
		ps.setString(1, sclass);
		ps.setString(2, fname);
		ps.setString(3, lname);
		ps.setString(4, mname);
		ps.setString(5, mobile);
		ps.setString(6, emailid);
		ps.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		try {
			PreparedStatement ps=conn.prepareStatement("select * from student");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
			sid=rs.getInt(1);
			}
			}catch(Exception e) {
				System.out.println(e);
			}
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.print("<table align='center' style='border: 1px solid black; margin-top: 50px'>");
		pw.print("<th>"+"StudentID"+"</th>");
		pw.print("<th>"+"ClassID"+"</th>");
		pw.print("<th>"+"FirstName"+"</th>");
		pw.print("<th>"+"LastName"+"</th>");
		pw.print("<th>"+"MiddleName"+"</th>");
		pw.print("<th>"+"MobileNumber"+"</th>");
		pw.print("<th>"+"EmailID"+"</th>");
		pw.print("<tr><td>"+sid+"</td><td>"+sclass+"</td><td>"+fname+"</td><td>"+lname+"</td><td>"+mname+"</td><td>"+mobile+"</td><td>"+emailid+"</td></th>");
		pw.print("</table>");
		pw.print("<br><br><hr><br>");
		pw.print("<h1>student details is sent to email id:"+"<mark>"+emailid+"</mark></h1>");
		
	}

}
