package kr.or.ddit.mvc.view;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;


public class ExcelDownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// model에서 header와 data 얻기
		List<String> header = (List<String>) model.get("header");
		List<Map<String, String>> data = (List<Map<String, String>>) model.get("data");
		
		// excel파일 contentType : application/vnd.ms-excel; UTF-8
		response.setContentType("application/vnd.ms-excel; utf-8");
		
		// 첨부파일임을 암시
		response.setHeader("Content-Disposition", "attachment; filename=test.xlsx");
		
		// poi라이브러리를 이용하여 엑셀 파일을 생성 sheet안에 row  row안에 sell
		Workbook workbook = new XSSFWorkbook();
		
		// sheet생성  
		Sheet sheet = workbook.createSheet("lineFriends"); // sheet이름
		
		// row(행) 생성 column(sell) 
		int rownum = 0;
		int column = 0;
		Row row = sheet.createRow(rownum++);
		
		// 헤더 설정
		for(String h : header) {
			row.createCell(column++).setCellValue(h);
		}
		
		// 데이터 설정
		for(Map<String, String> map : data) {
			row = sheet.createRow(rownum++);
			
			column = 0;
			row.createCell(column++).setCellValue(map.get("userid"));
			row.createCell(column++).setCellValue(map.get("usernm"));
		}
		// OutputStream
		OutputStream os = response.getOutputStream();
		workbook.write(os);
		
		os.flush();
		os.close();
		
		workbook.close();
		
		
	}

}
