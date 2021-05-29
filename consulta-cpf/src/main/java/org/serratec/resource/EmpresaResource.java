package org.serratec.resource;

import java.util.Optional;

import org.serratec.models.Empresa;
import org.serratec.repository.EmpresaRepository;
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
@Api("API de consulta de pessoa juridica")
public class EmpresaResource {

	@Autowired
	EmpresaRepository empresaRepository;

	@ApiOperation(value = "Pesquisa todas as empresas")
	@GetMapping("/empresa/todos")

	public Iterable<Empresa> getTodos() {
		return empresaRepository.findAll();
	}

	@ApiOperation(value = "Pesquisa todas as empresa por id")
	@GetMapping("/empresa/por-id/{id}")
	public Optional<Empresa> getPorID(@PathVariable Long id) {

		return empresaRepository.findById(id);
	}

	@ApiOperation(value = "Pesquisa todas as empresa por cnpj")
	@GetMapping("/empresa/por-cnpj/{cnpj}")
	public Optional<Empresa> getPorCpf(@PathVariable String cnpj) {
		return empresaRepository.findByCnpj(cnpj);
	}

	@ApiOperation(value = "Pesquisa todas as empresas por nome")
	@GetMapping("/empresa/{nome}")
	public Iterable<Empresa> getEmpresa(@RequestParam(required = false) String nome) {
		if (nome == null)
		return empresaRepository.findAll();
		return empresaRepository.findAllByNomeContainingIgnoreCase(nome);
	}

	/* @ApiOperation(value = "Pesquisa todas as empresa por nome")
	@GetMapping("/empresa/por-nome/{nome}")
	public Optional<Empresa> getPorNome(@PathVariable String nome) {
		return empresaRepository.findByNome(nome); 
	}*/

	@ApiOperation(value = "Cadastre a empresa")
	@PostMapping("/empresa")
	public void postEmpresa(@RequestBody Empresa novo) {
		empresaRepository.save(novo);
	}

	@ApiOperation(value = "Delete a pessoa física")
	@DeleteMapping("/empresa/{id}")
	public void deleteEmpresa(@PathVariable Long id, @RequestBody Empresa modificado) {

		Optional<Empresa> opcional = empresaRepository.findById(id);

		if (opcional.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa inexistente");

		Empresa existente = opcional.get();
		empresaRepository.delete(existente);
	}

}
