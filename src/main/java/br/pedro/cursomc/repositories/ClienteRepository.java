package br.pedro.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pedro.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Integer>{

}
