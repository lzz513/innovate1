package mju.lzz.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-22 17:26
 **/
@Slf4j
public class FileUtils {

	public static final String PATH = "/Users/weidian/innovate-files/";
	public static final String ALLOW_TYPE =
			"image/jpg,image/png,image/gif,image/jpeg,image/ico,image/x-png,image/pjpeg";

	public static String saveFile(MultipartFile file) {
		if (file == null) {
			return null;
		}
		String contentType = file.getContentType();
		String name = file.getOriginalFilename();
		log.info(name);
		String newFileName = UUID.randomUUID().toString()+name;
		if (contentType != null && ALLOW_TYPE.indexOf(contentType) != -1) {
			try {
//				File dirFile = new File(path+"/"+name);
				File dirFile = new File(PATH+newFileName);
				file.transferTo(dirFile);
				return "/uploads/"+newFileName;
			} catch (IOException e) {
				log.error("e = {}", e);
				return null;
			}
		}
		return null;
	}

}
