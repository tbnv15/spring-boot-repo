package com.learn.tbnv15.demo1.controllers;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class DemoController {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/upload", headers = ("content-type=multipart/*"))
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("Inside" + file.getOriginalFilename());
        File convFile = new File("G:\\Pro1\\spring-boot-repo\\demo1\\tmp\\" + file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(convFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return "Success";
    }

    @GetMapping(value = "/download/{fileName}")
    public ResponseEntity downloadFile(@PathVariable("fileName") String fileName) throws IOException {
        String path = "G:\\Pro1\\spring-boot-repo\\demo1\\tmp\\" + fileName;
        System.out.println(path);
        File file = new File(path);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
    }
}
