package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired private ClienteRepository repo;

    public Cliente find(Integer id){
        return repo.findById(id).orElseThrow(()->
           new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+
               Cliente.class.getName())
        );
    }

    public Cliente update(Cliente obj){
        Cliente newObj = find(obj.getId());
        updateData(obj, newObj);
        return repo.save(obj);
    }

    public void delete(Integer id){
        find(id);
        try{
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
        }
    }

    public List<ClienteDTO> findAll(){
        List<Cliente> lista = repo.findAll();
        List<ClienteDTO> listaDto = lista.stream().map(obj ->
                new ClienteDTO(obj)).collect(Collectors.toList());

        return listaDto;
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDto(ClienteDTO objDto){
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    private void updateData(Cliente obj, Cliente newObj){
        obj.setNome(newObj.getNome());
        obj.setEmail(newObj.getEmail());
    }
}
