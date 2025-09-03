package br.com.wakax.consulta.cep.infra;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import br.com.wakax.consulta.cep.application.repository.CepRepository;
import br.com.wakax.consulta.cep.domain.Endereco;
import br.com.wakax.consulta.cep.domain.EnderecoViaCep;
import br.com.wakax.consulta.cep.domain.UfInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CepRepositoryFeign implements CepRepository {

  private final CepFeignClient cepFeignClient;

  @Override
  public Endereco buscaEndereco(String cep) {
    log.info("[start] CepRepositoryFeign - buscaEndereco");
    EnderecoViaCep viaCep = cepFeignClient.consulta(cep);

    if (viaCep == null || Boolean.TRUE.equals(viaCep.erro())) {
      log.warn("CEP não encontrado: {}", cep);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CEP não encontrado");
    }

    String estado = UfInfo.estadoPorUf(viaCep.uf());
    String regiao = UfInfo.regiaoPorUf(viaCep.uf());

    Endereco endereco =
        new Endereco(
            viaCep.cep(),
            viaCep.logradouro(),
            viaCep.complemento(),
            viaCep.unidade(),
            viaCep.bairro(),
            viaCep.localidade(),
            viaCep.uf(),
            estado,
            regiao,
            viaCep.ibge(),
            viaCep.gia(),
            viaCep.ddd(),
            viaCep.siafi());

    log.info("[finish] CepRepositoryFeign - buscaEndereco");
    return endereco;
  }
}
