package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

	private String upload_path = "D:\\hello_world_upload";
	
	
	
	@GetMapping("/file_control")
	public String file(HttpSession session,Model model) {
		String useraccount = (String) session.getAttribute("loginUser");
		File userfolder = new File(upload_path+"\\"+useraccount);
		if(!userfolder.exists()) {
			//return null;
			model.addAttribute("files_num",0);
			return "file_control";
		}
		int files_num = userfolder.list().length;
		model.addAttribute("files_num",files_num);
		return "file_control";
	}
	
	@ResponseBody
	@GetMapping("/getUserFiles")//載入此頁面時，由ajax發請求 回傳該帳戶使用者的資料夾中的所有檔按
	public ResponseEntity<Object> getUserFiles(HttpSession session){
		String useraccount = (String) session.getAttribute("loginUser");
		File userfolder = new File(upload_path+"\\"+useraccount);
		if(!userfolder.exists()) {
			return null;
		}
		String[] files = userfolder.list();
		return new ResponseEntity<>(files, HttpStatus.OK);
	}
	
	
	@PostMapping("/deleteFiles") //此方法接收複選框結果 //@RequestBody String[] del_files報錯，注意參數接收 //@RequestParam Map<String,Object>
	public String deleteFiles(@RequestParam List<String> del_files,HttpSession session) {
		String useraccount = (String) session.getAttribute("loginUser");
		//這兩種參數可以:@RequestParam String[] 
		//@RequestParam Map<String,Object>接不到全部值 、@RequestBody String[]不行

		for(String del_file:del_files) {
			File file = new File(upload_path+"\\"+useraccount+"\\"+del_file);
			file.delete();
			System.out.println("你刪除了"+del_file);
		}
		
		//del_files=1.txt & del_files=2.txt & del_files=3.pptx
		return "redirect:/file_control";
	}
	
	@ResponseBody
	@PostMapping("/downloadTxt")
	public ResponseEntity<byte[]> downloadTxt(HttpSession session,@RequestParam String filename) throws IOException{
		//@RequestBody(多用來接收json) 與 @RequestParam 須注意
//		ServletContext servletcontext = session.getServletContext();		
//		servletcontext.getRealPath找不到路徑
//		String realPath = servletcontext.getRealPath("/static/download_files/"+filename+".txt");///static/download_files/"+filename+".txt
//		String realPath = FileController.class.getResourceAsStream("/main/resources/download_files"+filename+".txt");
//		System.out.println(realPath);
//		InputStream is = new FileInputStream(realPath);
//		byte[] bytes = new byte[is.available()];
//		is.read(bytes);//一次讀完文件中的bytes,還有一種做法是用迴圈讀到回傳-1為止
		
		InputStream is = this.getClass().getResourceAsStream("/static/download_files/"+filename+".txt");
		byte[] bytes = new byte[is.available()];
		is.read(bytes);		
		MultiValueMap<String,String> header = new HttpHeaders();
		//為什麼這邊響應頭只需要設定幾個屬性?其他的內容像是編碼or其他響應頭資訊都不用設定?也沒看到HttpHeaders的空參建構子有先設定好
		header.set("Content-Disposition", "attachment;filename="+filename+".txt");
		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> responseentity = new ResponseEntity<>(bytes,header,statusCode);
		is.close();
		return responseentity;	

	}
	
	@PostMapping("/uploadFile")//每個會員有各自專屬資料夾
	public String uploadFile(@RequestParam MultipartFile[] uploadfiles,HttpSession session) throws IllegalStateException, IOException {
		String useraccount = (String) session.getAttribute("loginUser");
		String uploadpath ="D:\\hello_world_upload\\"+useraccount;
		File dir = new File(uploadpath);
		if(!dir.exists()) {
			dir.mkdir();
		}	
		
		for(MultipartFile uploadfile:uploadfiles) {
			String filename = uploadfile.getOriginalFilename();
			String filepreffix = filename.substring(0,filename.lastIndexOf("."));
			String filesuffix = filename.substring(filename.lastIndexOf("."));
			//uploadfile.transferTo(new File(dir.getAbsolutePath()+File.separator+uploadfile.getOriginalFilename()));
			//String finalfilename = UUID.randomUUID().toString()+filesuffix;
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			String finalfilename = filepreffix+dtf.format(LocalDateTime.now())+filesuffix;
			uploadfile.transferTo(new File(dir.getAbsolutePath()+File.separator+finalfilename));
			System.out.println("上傳成功，將文件上傳至"+dir.getAbsolutePath()+File.separator+finalfilename);
			session.setAttribute("uploadmsg","上傳成功!您最後一次上傳的檔案為："+filename);
			
		}				
		//model.addAttribute("files_num",uploadfiles.length);
		return "redirect:/file_control";
	}
}
