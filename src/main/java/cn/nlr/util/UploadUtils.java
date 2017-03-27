package cn.nlr.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@Controller
@RequestMapping(value = "/file")
public class UploadUtils{
	private static Logger log = LoggerFactory.getLogger(UploadUtils.class);
	//@Autowired
//    private AttachmentService attachmentService;
	//访问路径的前缀
	//@Value("${dbpath:/home/test/}") //TODO::
//	String dbpath;
	//保存服务器路径的前缀
	//@Value("${serverpath:/home/test}") //TODO::
	//String serverpath;
	
    /**
     *  上传文件
     *
     * @param request 当前request
     * @return JSONObject 
     * 		   	success		是否成功
     * 			path		上传路径
     * 			fileName	名字
     * 			useTime		所用时间
     * @throws IOException 
     * @throws IllegalStateException 
     */
	@PostMapping("/uploadAttachments")
	@ResponseBody
    public ResponseEntity<String> upload(HttpServletRequest request){
    	String myFileName = "";
		List<Integer> list=new ArrayList<Integer>();
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			// 记录上传过程起始时的时间，用来计算上传时间
			int pre = (int) System.currentTimeMillis();
			while (iter.hasNext()) {
//				Attachment attachment = new Attachment();
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						// 重命名上传后的文件名
						String fileName = myFileName;
						fileName="at_"+System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."),fileName.length());
						// 定义上传路径
						String relativePath=getDayPath()+File.separator+fileName;
						String path = ""+relativePath;
						String DBpath=""+relativePath;
						File localFile = new File(path);
						// 检测是否存在目录
					    if (!localFile.getParentFile().exists()) {
					    	localFile.getParentFile().mkdirs();
					    }
						int m = (int) (localFile.length() / (1024 * 1024));
						try {
							file.transferTo(localFile);
//							attachment.setSize((int)localFile.length());
//							attachment.setLocation(DBpath);
//							attachmentService.insertAttachment(attachment);
//							log.info("DBpath "+DBpath);
//							list.add(attachment.getId());
					    } catch (IllegalStateException e) {
					      e.printStackTrace();
					    } catch (IOException e) {
					      e.printStackTrace();
					    }
					}
				}
			}
			// 记录上传该文件后的时间
			int finaltime = (int) System.currentTimeMillis();
			log.info("upload time : "+(finaltime-pre));
		}
		JSONArray jsonArray=new JSONArray(list);
		ResponseEntity<String> re = new ResponseEntity<String>(jsonArray.toString(), org.springframework.http.HttpStatus.OK);
		return re;
    }
    /**
     * 星期前三个字母+年后两位+月
     * @return
     */
       public String getDayPath(){
    	  String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    	  Date date=new Date();
          Calendar cal = Calendar.getInstance();
          cal.setTime(date);
          int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
          if (w < 0)
              w = 0;
          SimpleDateFormat dateFm = new SimpleDateFormat("yy");
          SimpleDateFormat dateMM = new SimpleDateFormat("MM");
          return weekDays[w]+"_"+dateFm.format(date)+dateMM.format(date);
       }
}
