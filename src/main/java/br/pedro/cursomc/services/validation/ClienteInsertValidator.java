package br.pedro.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.pedro.cursomc.domain.enums.TipoCliente;
import br.pedro.cursomc.dto.ClienteNewDTO;
import br.pedro.cursomc.resources.exception.FieldMessage;
import br.pedro.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
	List<FieldMessage> list = new ArrayList<>();

 // Testes
	
	if((objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo())) && (!BR.isValidCPF(objDto.getCpfOuCnpj()))) {
		list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
	}
	if((objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo())) && (!BR.isValidCNPJ(objDto.getCpfOuCnpj()))) {
		list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
	}

	for (FieldMessage e : list) {
	context.disableDefaultConstraintViolation();
	context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
	 }
	 return list.isEmpty();
 }
}