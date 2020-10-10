package com.nelioalves.cursomc.services.validations;

import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.resources.exceptions.FieldMessage;
import com.nelioalves.cursomc.services.validations.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Override
    public void initialize(ClienteInsert clienteInsert) {
    }



    @Override
    public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> list = new ArrayList<>();

        if(clienteNewDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod())
            && !BR.isValidCPF(clienteNewDTO.getCpfOuCnpj())){

            list.add(new FieldMessage("cpfOuCnpj","CPF inválido"));
        }

        if(clienteNewDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod())
            && !BR.isValidCNPJ(clienteNewDTO.getCpfOuCnpj())){

            list.add(new FieldMessage("cpfOuCnpj","CNPJ inválido"));
        }

        for (FieldMessage e : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage()).
                    addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
