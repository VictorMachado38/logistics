package com.br.logistics.model.dao.impl;

import com.br.logistics.model.entity.Cliente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ClientInterfaceDAO extends JpaRepository<Cliente, Long> {

    List<Cliente> findAll();

    Optional<Cliente> findById(Long id);

    Cliente save(Cliente cliente);

    void delete(Cliente cliente);
}
