package br.com.wakax.consulta.cep.application.service;

import br.com.wakax.consulta.cep.domain.Endereco;

public interface CepService {
  Endereco consultaCep(String cep);
}
