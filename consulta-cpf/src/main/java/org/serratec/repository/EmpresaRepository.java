package org.serratec.repository;

import java.util.Optional;

import org.serratec.models.Empresa;
import org.springframework.data.repository.CrudRepository;

public interface EmpresaRepository extends CrudRepository<Empresa, Long> {

	Optional<Empresa> findById(String id);
	Optional<Empresa> findByCnpj(String cnpj);
	Optional<Empresa> findByNome(String nome);
	Iterable<Empresa> findAllByNomeContainingIgnoreCase(String nome);
}

