package br.com.cruzvita.pedidos.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cruzvita.pedidos.dto.PedidosDTO;
import br.com.cruzvita.pedidos.model.Pedidos;
import br.com.cruzvita.pedidos.model.Produto;
import br.com.cruzvita.pedidos.repository.PedidosRepository;

@Service
public class PedidosService {

    @Autowired
    PedidosRepository repository;

    public String teste() {
        RestTemplate template = new RestTemplate();

        String resourceUrl = "http://localhost:8081/listar/1";

        ResponseEntity<String> response = template.getForEntity(resourceUrl, String.class);
        
        String productsJson = response.getBody();
        
        return productsJson;
    }

    public ResponseEntity<String> gerarPedido(PedidosDTO pedido) {
        RestTemplate template = new RestTemplate();

        String resourceUrl = "http://localhost:8081/listar/" + pedido.getIdCliente();

        ResponseEntity<String> response = template.getForEntity(resourceUrl, String.class);
        
        String cliente = response.getBody();

        if(!cliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não existe no sistema!");
        }

        Pedidos pedidoModel = new Pedidos();

        BeanUtils.copyProperties(pedido, pedidoModel);

            String produtosURL = "http://localhost:8085/listar/produtos";
        
            ResponseEntity<Produto> resposta = template.getForEntity(produtosURL, Produto.class);
        
            pedidoModel.setTotal(pedidoModel.getTotal() + resposta.getBody().getValor());



        return null;
    }

    
}
