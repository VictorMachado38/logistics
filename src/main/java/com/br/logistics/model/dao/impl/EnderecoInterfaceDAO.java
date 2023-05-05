package com.br.logistics.model.dao.impl;

import com.br.logistics.model.entity.Endereco;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EnderecoInterfaceDAO extends JpaRepository<Endereco, Long> {

    List<Endereco> findAll();

    Optional<Endereco> findById(Long id);

    Endereco save(Endereco endereco);

    void delete(Endereco endereco);
}
