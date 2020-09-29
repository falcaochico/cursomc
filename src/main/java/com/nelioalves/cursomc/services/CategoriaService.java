package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired private CategoriaRepository repo;

    public Categoria buscar(Integer id){
        return repo.findById(id).orElseThrow(()->
           new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+
               Categoria.class.getName())
        );
    }

    public Categoria insert(Categoria obj){
        obj.setId(null);
        return repo.save(obj);
    }
}
