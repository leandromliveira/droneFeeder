package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Teste;
import com.example.demo.repository.TesteRepository;

@Service
public class TesteService {
  @Autowired
  private TesteRepository repository;

  public Teste create(byte[] video) {
    Teste teste = new Teste();
    teste.setVideo(video);
    return repository.save(teste);
  }

  public List<String> getAll() {
    return repository.findAll().stream().map(video -> "localhost:8080/teste/" + video.getId())
        .collect(Collectors.toList());
  }

  public Optional<Teste> getFile(int id) {
    return repository.findById(id);
  }
}
