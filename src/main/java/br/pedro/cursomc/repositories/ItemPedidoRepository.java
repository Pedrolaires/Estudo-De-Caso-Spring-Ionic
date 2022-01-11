package br.pedro.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pedro.cursomc.domain.ItemPedido;

// Repositórios - Camada de acesso a dados. - Realiza operações de busca, salvamento, alteração e delete referentes ao objeto categoria.

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{

}
