package br.com.wakax.consulta.cep.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wakax.consulta.cep.domain.Endereco;

@RestController
@RequestMapping("/cep")
public interface CepAPI {
  @GetMapping("/{cep}")
  Endereco consultaCep(@PathVariable String cep);
}
