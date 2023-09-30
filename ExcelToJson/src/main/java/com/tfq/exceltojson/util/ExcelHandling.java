package com.tfq.exceltojson.util;

import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ExcelHandling {
    static ArrayList<String> header;
    public static HashMap<Integer, HashMap<String, String>> getExcelData(MultipartFile file) throws IOException {
        header = new ArrayList<>();
        HashMap<Integer, HashMap<String, String>> excelData = new HashMap<>();
        Path tempDir = Files.createTempDirectory("");
        File tempFile = tempDir.resolve(tempDir).toFile();
        file.transferTo(tempFile);

        Workbook workbook = WorkbookFactory.create(tempFile);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        int rowIdx = 1;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            HashMap<String, String> column = new HashMap<>();
            if (rowIdx == 1) {
                for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                    column.put(row.getCell(i).toString(), null);
                    header.add(row.getCell(i).toString());
                }
                for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                    excelData.put(i, column);
                }
                rowIdx++;
                continue;
            }

            for (int i = 0; i < header.size(); i++) {
                column.put(header.get(i), row.getCell(i) != null ? row.getCell(i).toString() : null);
            }
            excelData.put(rowIdx, column);
            rowIdx++;
        }
        return excelData;
    }
}
