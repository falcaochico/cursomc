package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired private PedidoRepository repo;

    public Pedido buscar(Integer id){
        return repo.findById(id).orElseThrow(()->
           new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+
               Pedido.class.getName())
        );
    }
}
