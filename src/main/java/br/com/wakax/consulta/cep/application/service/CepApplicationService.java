package br.com.wakax.consulta.cep.application.service;

import org.springframework.stereotype.Service;

import br.com.wakax.consulta.cep.application.repository.CepRepository;
import br.com.wakax.consulta.cep.domain.Endereco;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CepApplicationService implements CepService {

  private final CepRepository cepRepository;

  @Override
  public Endereco consultaCep(String cep) {
    log.info("[start] CepApplicationService - consultaCep");
    Endereco endereco = cepRepository.buscaEndereco(cep);
    log.info("[finish] CepApplicationService - consultaCep");
    return endereco;
  }
}
