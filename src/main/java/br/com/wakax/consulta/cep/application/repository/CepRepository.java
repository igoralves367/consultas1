package br.com.wakax.consulta.cep.application.repository;

import br.com.wakax.consulta.cep.domain.Endereco;

public interface CepRepository {
  Endereco buscaEndereco(String cep);
}
