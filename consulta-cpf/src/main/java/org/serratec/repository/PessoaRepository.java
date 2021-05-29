package org.serratec.repository;

import java.util.Optional;

import org.serratec.models.Pessoa;
import org.springframework.data.repository.CrudRepository;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

	Optional<Pessoa> findById(String id);
	Optional<Pessoa> findByCpf(String cpf);
	Optional<Pessoa> findByNome(String nome);
	Iterable<Pessoa> findAllByNomeContainingIgnoreCase(String nome);
}
