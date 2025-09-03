package br.com.wakax.consulta.cep.api;

import org.springframework.web.bind.annotation.RestController;

import br.com.wakax.consulta.cep.application.service.CepService;
import br.com.wakax.consulta.cep.domain.Endereco;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class CepController implements CepAPI {

  private final CepService cepService;

  @Override
  public Endereco consultaCep(String cep) {
    log.info("[start] CepController - consultaCep");
    Endereco endereco = cepService.consultaCep(cep);
    log.info("[finish] CepController - consultaCep");
    return endereco;
  }
}
