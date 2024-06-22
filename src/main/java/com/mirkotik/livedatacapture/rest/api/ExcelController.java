package com.mirkotik.livedatacapture.rest.api;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.mirkotik.livedatacapture.dto.ExcelDto;
import com.mirkotik.livedatacapture.repo.RepositoryEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {
	
	
	@Autowired
	private RepositoryEntity repository;
	
	@PostMapping("/read")
	public ResponseEntity<?> readExcelFile(@RequestParam("file") MultipartFile file) {
	    List<String> data = new ArrayList<>();
	    ExcelDto dto = new ExcelDto();
	    try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
	        XSSFSheet sheet = workbook.getSheetAt(0);
	        // Iterate through rows and cells to read data
	        for (Row row : sheet) {
	            List<String> rowData = new ArrayList<>();
	            for (Cell cell : row) {
	                switch (cell.getCellType()) {
	                    case STRING:
	                        rowData.add(cell.getStringCellValue());
	                        break;
	                    case NUMERIC:
	                        rowData.add(String.valueOf(cell.getNumericCellValue()));
	                        break;
	                    case BOOLEAN:
	                        rowData.add(String.valueOf(cell.getBooleanCellValue()));
	                        break;
	                    default:
	                        rowData.add("Unknown Cell Type");
	                        break;
	                }
	            }
	            // Assume the first cell in each row contains the name
	            if (!rowData.isEmpty()) {
	                dto.setName(rowData.get(0).toLowerCase()); // Set the name in dto
	                dto.setAge(rowData.get(1).toLowerCase());  // Set the age in dto
	                dto.setDpt(rowData.get(2).toLowerCase());  // Set the dpt in dto
	               System.out.println(dto.getAge()+ "_" + dto.getName() + "_" + dto.getDpt());
	               repository.save(dto);
	            }
	        }
	        return new ResponseEntity<>("Sucess", HttpStatus.OK);

	    } catch (IOException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@GetMapping("/read")
	public List<ExcelDto> read()
	{
		return repository.findAll();
	}
}