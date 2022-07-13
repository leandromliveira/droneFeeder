package com.example.demo.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Teste;
import com.example.demo.service.TesteService;

@RestController
public class TesteController {
  @Autowired
  TesteService service;

  @PostMapping("/teste")
  public Blob handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
      throws IOException, SerialException, SQLException {
    byte[] bytes = file.getBytes();
    return new javax.sql.rowset.serial.SerialBlob(service.create(bytes).getVideo());
  }

  @GetMapping("/teste")
  public List<String> getAll() {
    return service.getAll();
  }

  @GetMapping("/teste/{id}")
  @ResponseBody
  void getgetFile(@PathVariable("id") int id, HttpServletResponse response, Optional<Teste> product)
      throws ServletException, IOException {
    product = service.getFile(id);
    response.setContentType("video/mp4, video/wmv");
    response.getOutputStream().write(product.get().getVideo());
    response.getOutputStream().close();

  }
}
