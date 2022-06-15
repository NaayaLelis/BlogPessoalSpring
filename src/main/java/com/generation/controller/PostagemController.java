package com.generation.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.model.Postagem;
import com.generation.repository.PostagemRepository;
import com.generation.repository.TemaRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders="*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById (@PathVariable Long id){
		return postagemRepository.findById(id)
				.map (resposta -> ResponseEntity.ok(resposta))
		        .orElse( ResponseEntity.notFound(). build());
	}
	
	@GetMapping ("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo (@PathVariable("titulo") String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	
	@PostMapping
	public ResponseEntity<Postagem> post ( @RequestBody Postagem postagem){
				return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	
				}
	@PutMapping
	public ResponseEntity <Postagem> putPostagem ( @RequestBody Postagem postagem){
		if (postagemRepository.existsById(postagem.getId())) {
			if (temaRepository.existsById(postagem.getTema(). getId()))
				return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}	
	
	@DeleteMapping("/{id}")
    public ResponseEntity <?> deletePostagem(@PathVariable Long id){
    	return postagemRepository.findById(id)
    			.map(obj-> {
    				postagemRepository.deleteById(id);
    			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    					})
						.orElse(ResponseEntity.notFound(). build());

    			}
    			
    }	


