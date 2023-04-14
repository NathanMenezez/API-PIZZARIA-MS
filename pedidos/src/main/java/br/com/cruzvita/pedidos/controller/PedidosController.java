package br.com.cruzvita.pedidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cruzvita.pedidos.dto.PedidosDTO;
import br.com.cruzvita.pedidos.service.PedidosService;

@RestController
public class PedidosController {

    @Autowired
    PedidosService service;

    @GetMapping
    public String teste(){
        return service.teste();
    }

    @PostMapping
    public ResponseEntity<String> gerarPedido(@RequestBody PedidosDTO pedido){
        return service.gerarPedido(pedido);
    }
}
