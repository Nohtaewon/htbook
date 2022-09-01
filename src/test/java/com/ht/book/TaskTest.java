package com.ht.book;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ht.book.domain.AttachImageVO;
import com.ht.mapper.BookMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.ht.config.RootConfig.class})
@Log4j
public class TaskTest {
	
	@Autowired
	private BookMapper mapper;
	
	
	private String getFolderYesterDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.DATE, 0);
		
		String str = sdf.format(cal.getTime());
		
		
		return str.replace("-", File.separator);
		
		
	}
	
	@Test
	public void taskTests() {
		List<AttachImageVO> fileList = mapper.checkFileList();
		
		System.out.println("fileList : ");
		fileList.forEach( list -> System.out.println(list));
		System.out.println("========================================");
		
		List<Path> checkFilePath = new ArrayList<Path>();
		
		fileList.forEach(vo -> {
			Path path = Paths.get("C:\\storage", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName());
			checkFilePath.add(path);
		});
		
		System.out.println("checkFilePath : ");
		checkFilePath.forEach(list -> System.out.println(list));
		System.out.println("========================================");
		
		fileList.forEach(vo -> {
			Path path = Paths.get("C:\\storage", vo.getUploadPath(),"s_" +  vo.getUuid() + "_" + vo.getFileName());
			checkFilePath.add(path);
		});
		
		System.out.println("checkFilePath(썸네일 이미지 정보 추가 후) : ");
		checkFilePath.forEach(list -> System.out.println(list));
		System.out.println("========================================");
	
		
		File targetDir = Paths.get("C:\\storage", getFolderYesterDay()).toFile();
		File[] targetFile = targetDir.listFiles();
		
		System.out.println("targetFile : ");
		for(File file : targetFile) {
			System.out.println(file);
		}
		System.out.println("========================================");
		
		List<File> removeFileList = new ArrayList<File>(Arrays.asList(targetFile));
		
		System.out.println("removeFileList(필터 전) : ");		
		removeFileList.forEach(file -> {
			System.out.println(file);
		});		
		System.out.println("========================================");		
		
		for(File file : targetFile){
			checkFilePath.forEach(checkFile ->{
				if(file.toPath().equals(checkFile)) 
					removeFileList.remove(file);	
			});
		}
		
		System.out.println("removeFileList(필터 후) : ");
		removeFileList.forEach(file -> {
			System.out.println(file);
		});
		System.out.println("========================================");
		

		// 파일 삭제 
		for(File file : removeFileList) {
			System.out.println("삭제 : " + file);
			file.delete();
		}
		
	}
}