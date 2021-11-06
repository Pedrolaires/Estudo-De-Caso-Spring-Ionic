package br.pedro.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pedro.cursomc.domain.Categoria;
import br.pedro.cursomc.repositories.CategoriaRepository;

// Camada de serviços
// Chama uma operação da camada de acesso a dados (repository)

@Service
public class CategoriaService {
	
	
	// Acessando a camada de acesso a dados(repository) 
	@Autowired // <- a dependência é automaticamente instanciada pelo mecânismo de injeção de dependência
	private CategoriaRepository repo;
	
	 
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
			return obj.orElse(null);
	}
	
}
