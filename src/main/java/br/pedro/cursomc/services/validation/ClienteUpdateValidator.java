package br.pedro.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.pedro.cursomc.domain.Cliente;
import br.pedro.cursomc.dto.ClienteDTO;
import br.pedro.cursomc.repositories.ClienteRepository;
import br.pedro.cursomc.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request; // Obj que vai permitir obter o parâmetro (id) que virá da URI
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		// Estrutura que vai armazenar as chaves e valores obtidas da requisição (parecido com o JSON), no caso pegando a chave [id] e o valor
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		
		if((aux != null) && !(aux.getId().equals(uriId))) {
			list.add(new FieldMessage("email", "O e-mail já está cadastrado!"));
		}
	
		for (FieldMessage e : list) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		 }
	 return list.isEmpty();
 }
}