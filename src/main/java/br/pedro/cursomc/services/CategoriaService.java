package br.pedro.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.pedro.cursomc.domain.Categoria;
import br.pedro.cursomc.dto.CategoriaDTO;
import br.pedro.cursomc.repositories.CategoriaRepository;
import br.pedro.cursomc.services.exception.DataIntegrityException;
import br.pedro.cursomc.services.exception.ObjectNotFoundException;

// Camada de serviços
// Chama uma operação da camada de acesso a dados (repository)

@Service
public class CategoriaService {
	
	
	// Acessando a camada de acesso a dados(repository) 
	@Autowired // <- a dependência é automaticamente instanciada pelo mecânismo de injeção de dependência
	private CategoriaRepository repo;
	
	 
	public Categoria find(Integer id){
		Optional<Categoria> obj = repo.findById(id);
		
			return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + " Tipo: " + Categoria.class.getName()));
	}	
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj,obj);
		return repo.save(newObj);
	}
	public void delete(Integer id) {
		find(id);
		try{
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos!");
		}
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(),objDTO.getNome());
	}
	
	private void updateData(Categoria newObj,Categoria obj) {
		
		newObj.setNome(obj.getNome());
		
	}
}
