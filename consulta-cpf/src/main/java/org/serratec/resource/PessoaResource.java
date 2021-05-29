package org.serratec.resource;


import java.util.Optional;

import org.serratec.models.Pessoa;
import org.serratec.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("API de consulta de pessoa física")
public class PessoaResource {

	@Autowired
	PessoaRepository pessoaRepository;

	@ApiOperation(value = "Pesquisa todas as pessoas físicas")
	@GetMapping("/pessoa/todos")
	
	public Iterable<Pessoa> getTodos() {
		return pessoaRepository.findAll();
	}

	@ApiOperation(value = "Pesquisa todas as pessoas físicas por id")
	@GetMapping("/pessoa/por-id/{id}")
	public Optional<Pessoa> getPorID(@PathVariable Long id) {

		return pessoaRepository.findById(id);
	}

	@ApiOperation(value = "Pesquisa todas as pessoas físicas por cpf")
	@GetMapping("/pessoa/por-cpf/{cpf}")
	public Optional<Pessoa> getPorCpf(@PathVariable String cpf) {
		return pessoaRepository.findByCpf(cpf);
	}

	@ApiOperation(value = "Pesquisa todas as pessoas físicas por nome")
	@GetMapping("/pessoa/{nome}")
	public Iterable<Pessoa> getPessoa(@RequestParam(required = false) String nome) {
		if(nome==null)
			return pessoaRepository.findAll();
		return pessoaRepository.findAllByNomeContainingIgnoreCase(nome);
	}
	/*@ApiOperation(value = "Pesquisa todas as pessoas físicas por nome")
	@GetMapping("/pessoa/por-nome/{nome}")
	public Optional<Pessoa> getPorNome(@PathVariable String nome) {
		return pessoaRepository.findByNome(nome);
	}*/

	@ApiOperation(value = "Cadastre a pessoa física")
	@PostMapping("/pessoa")
	public void postPessoa(@RequestBody Pessoa novo) {
		pessoaRepository.save(novo);
	}
	@ApiOperation(value = "Delete a pessoa física")
	@DeleteMapping("/pessoa/{id}")
	public void deletePessoa(@PathVariable Long id, @RequestBody Pessoa modificado) {

		Optional<Pessoa> opcional = pessoaRepository.findById(id);

		if (opcional.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa inexistente");

		Pessoa existente = opcional.get();
		pessoaRepository.delete(existente);
	}


}

/*
 * http://localhost:8080/por-numero/13688827732 parecido com
 * http://localhost:8080/busca?13688827732
 */
