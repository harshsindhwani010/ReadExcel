package com.demo.ReadExcel.controller;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.httpobjects.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;


@Controller
public class ReadExcel {

    @Autowired
    ServletContext context;
    @GetMapping("/")
    public String get() {

        return "index";
    }
    //C:\Users\Ditsd\Downloads\ReadExcel\ReadExcel\src\main\resources\harsh.xlsx
    //C://Users/Ditsd/Downloads/ReadExcel/ReadExcelsrc/main/resources/static/download/download.txt

    @PostMapping("/upload-file")
    public ResponseEntity<Resource> getExcel(@RequestParam("file") MultipartFile file) throws IOException, InvalidFormatException {
        String project = System.getProperty("user.dir");
        String path = project+"\\src\\main\\resources";
        String download = project+"\\src\\main\\resources\\static\\download\\download.txt";
        InputStream is = file.getInputStream();
        byte data[] = new byte[is.available()];
        is.read(data);
        FileOutputStream fos = new FileOutputStream(path+File.separator+file.getOriginalFilename());
        fos.write(data);
        fos.flush();
        fos.close();
        File f = ResourceUtils.getFile("classpath:downloadReport.txt");
        Resource resource = null;
        try{
            resource = new UrlResource(f.toURI());

        }catch(MalformedURLException e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+resource.getFilename()+"\"")
                .body(resource);
    }


//        StringBuilder sb = new StringBuilder();
//        String fileN = file.getOriginalFilename();
//        File f = new File(fileN);
//        System.out.println("f pa::"+f.getPath());
//        Workbook wb = WorkbookFactory.create(f);
//        System.out.println(wb);
//        Sheet mySheet = wb.getSheetAt(0);
//        Iterator<Row> rowIter = mySheet.rowIterator();
//        for (Iterator<Row> rowIterator = mySheet.rowIterator(); rowIterator.hasNext(); ) {
//            for (Iterator<Cell> cellIterator = ((Row) rowIterator.next()).cellIterator(); cellIterator.hasNext(); ) {
//                sb.append(((Cell) cellIterator.next()).toString());
//                sb.append(",");
//                System.out.print(((Cell) cellIterator.next()).toString());
//                System.out.print(",");
//            }
//            sb.append("\n");
//            System.out.println("***");
//        }
////            System.out.println("END");
//        //sb.append("Id,name,address");
//        //sb.append("\n");
//        //sb.append("1,harsh,India");
//        //sb.append("\n");
//        //sb.append("2,abhishek,India");
//        //sb.append("\n");
//        Path paths = Paths.get("C:\\Users\\Ditsd\\Downloads\\new1.txt");
//        Files.write(paths, Arrays.asList(sb.toString()));
//
////        ModelAndView mv = new ModelAndView();
////        mv.addObject("file",file);
////        mv.setViewName("inserted");
////        return mv;
}

