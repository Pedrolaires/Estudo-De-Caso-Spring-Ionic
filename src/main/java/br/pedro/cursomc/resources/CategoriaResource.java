package br.pedro.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.pedro.cursomc.domain.Categoria;
import br.pedro.cursomc.dto.CategoriaDTO;
import br.pedro.cursomc.services.CategoriaService;
// Controlador Rest - Acessa o serviço
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired // Acessando o serviço - Instancia automaticamente.
	private CategoriaService service;
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id){
		
		// Response entity é um objeto complexo com várias informações do protocolo http. Encapsula informações da resposta http para o rest service	
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping						//@RequestBody converte o json para um obj java
	public ResponseEntity<Void> insert(@Validated @RequestBody CategoriaDTO objDto){
		Categoria obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		//recebe a uri do novo recurso inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		//gerar resposta 201 (created)
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value ="/{id}")
	public ResponseEntity<Void> update(@Validated @RequestBody CategoriaDTO objDto, @PathVariable Integer id){
		
		Categoria obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		
		List<Categoria> objList = service.findAll();
		List<CategoriaDTO> objListDto = objList.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(objListDto);
	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page", defaultValue = "0")Integer page,
			@RequestParam(value="linesPerPage", defaultValue = "24")Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue = "nome")String orderBy,
			@RequestParam(value="direction", defaultValue = "ASC")String direction){
		
		Page<Categoria> objList = service.findPage(page,linesPerPage,orderBy,direction);
		
		Page<CategoriaDTO> objListDto = objList.map(obj -> new CategoriaDTO(obj));
		
		return ResponseEntity.ok().body(objListDto);
	}
}
