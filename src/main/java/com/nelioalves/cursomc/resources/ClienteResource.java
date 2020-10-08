package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    ClienteService categoriaService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){

        Cliente obj = categoriaService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id,@Valid @RequestBody ClienteDTO objDto){
        Cliente obj = categoriaService.fromDto(objDto);
        obj.setId(id);
        categoriaService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll(){
        List<ClienteDTO> obj = categoriaService.findAll();
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/pages",method = RequestMethod.GET)
    public ResponseEntity<?> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        Page<Cliente> list = categoriaService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> listDto = list.map(obj-> new ClienteDTO(obj));

        return ResponseEntity.ok().body(listDto);
    }
}
