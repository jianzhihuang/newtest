import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/UploadingPic")
@MultipartConfig
public class UploadingPic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String saveDir = "/image_uploading_homework";

	Map<String, File> fileMap;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();

		// 確認是否第一次到這個頁面，若是的話要創造一個fileMap物件存放file
		// 若是修改的話則從session 拿出Map
		if (session.getAttribute("pic_value") != null) {
			fileMap = (Map<String, File>) session.getAttribute("pic_value");
		} else {
			fileMap = new HashMap<String, File>();
			session.setAttribute("pic_value", fileMap);
		}

		// delete from disk
		// when client pass values of delete parameter and the fileMap is not empty
		if (req.getParameter("delete") != null && (fileMap.size() > 0)) {
			deletePictureFromDisk(req);
		}

		// if the directory for picture is not exists, making a new one
		String realPath = getServletContext().getRealPath(saveDir);
		File realPathSaveDir = new File(realPath);
		if (!realPathSaveDir.exists()) {
			realPathSaveDir.mkdirs();
		}

		// write into disk
		if (req.getParts() != null) {
			writePictureIntoDisk(req,realPathSaveDir);
		}

		// present picture to client
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Hello</TITLE>" + " <style>\r\n" + ".picture{\r\n"
				+ "  height: 200px;\r\n" + "width: 200px;\r\n" + "}\r\n"
				+ "</style></HEAD>");
		out.println("<BODY>");
		out.println("<form action=\"\" enctype=\"multipart/form-data\" method=\"POST\">");
		out.println("<div>");
		Collection<File> fileCollection = fileMap.values();
		for (File file : fileCollection) {
			if (file.exists()) {
				out.println("<img src=\"" + req.getContextPath()+saveDir+"/" + file.getName()
						+ "\">");
				System.out.println(req.getContextPath());
				System.out.println(saveDir);
				System.out.println(file.getName());
				
				// 下面放專屬小按鈕
				out.println(
						"<button name=\"delete\" value =\"" + file.getName() + "\">" + file.getName() + "</button>");
			}
		}
		out.println("</div>");
		out.println("<div><span>the size of  list is   : </span>" + fileMap.size() + "</div>");
		out.println("</form>");
		out.println("</BODY></HTML>");

		

	}
	
	
	
	
	
	
	// getFileName
	public String getPartName(Part part) {

		String header = part.getHeader("content-disposition");
		File tem = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1));
		String fileName = tem.getName();
		if (fileName.length() == 0) {
			return null;
		}
		return fileName;
	}
	// write
	public void writePictureIntoDisk(HttpServletRequest req, File realPathSaveDir)
			throws IOException, ServletException {
		Collection<Part> parts = req.getParts();
		for (Part part : parts) {
			// preventing from adding delete parameter into Map
			if (!part.getName().equals("delete")) {
				File picturePath = new File(realPathSaveDir, getPartName(part));
				part.write(picturePath.toString());

				fileMap.put(picturePath.getName(), picturePath);
			}
		}
	}

	// delete picture function
	public void deletePictureFromDisk(HttpServletRequest req) {
		String[] deleteValue = req.getParameterValues("delete");
		for (int i = 0; i < deleteValue.length; i++) {
			File deleteFile = fileMap.remove(deleteValue[i]);
			deleteFile.delete();
		}
	}

}