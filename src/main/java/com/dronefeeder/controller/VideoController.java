package com.dronefeeder.controller;

import com.dronefeeder.service.VideoService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.sql.rowset.serial.SerialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
public class VideoController {
  @Autowired
  VideoService service;

  /**
   * Method to upload a video file.
   */
  @PostMapping("/video/{deliveryId}")
  public String handleFileUpload(@PathVariable("deliveryId") int deliveryId,
      @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
      throws IOException, SerialException, SQLException {
    byte[] bytes = file.getBytes();
    service.create(deliveryId, bytes);
    return "video upload with success!";
  }

  @GetMapping("/video")
  public List<String> getAll() {
    return service.getAll();
  }

  @GetMapping("/video/{id}")
  @ResponseBody
  HttpEntity<byte[]> dowload(@PathVariable("id") int id)
      throws IOException {
    byte[] arquivo = service.getFile(id).getVideo();
    HttpHeaders httpHeaders = new HttpHeaders();

    httpHeaders.add("Content-Disposition", "attachment;filename=\"video-" + id + ".mp4\"");

    HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);

    return entity;

  }
}
