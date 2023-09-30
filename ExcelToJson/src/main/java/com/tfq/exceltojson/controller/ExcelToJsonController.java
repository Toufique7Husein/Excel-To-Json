package com.tfq.exceltojson.controller;

import com.tfq.exceltojson.util.ExcelHandling;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("/api/upload/")
public class ExcelToJsonController {
    @PostMapping("/excel")
    public HashMap<Integer, HashMap<String, String>> uploadPDf(@RequestParam("file") MultipartFile file) throws IOException {
        if (file == null) {
            return null;
        }
        return ExcelHandling.getExcelData(file);
    }

    static void debug(Object...obj) {
        System.err.println(Arrays.deepToString(obj));
    }
}
