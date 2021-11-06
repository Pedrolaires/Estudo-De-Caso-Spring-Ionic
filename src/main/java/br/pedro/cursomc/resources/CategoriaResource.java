package br.pedro.cursomc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.pedro.cursomc.domain.Categoria;
import br.pedro.cursomc.services.CategoriaService;
// Controlador Rest - Acessa o serviço
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired // Acessando o serviço - Instancia automaticamente.
	private CategoriaService service;
	
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		// Response entity é um objeto complexo com várias informações do protocolo http. Encapsula ( armazena) informações da resposta http para o rest service	
		Optional<Categoria> obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}
