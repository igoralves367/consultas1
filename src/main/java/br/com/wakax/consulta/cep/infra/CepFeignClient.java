package br.com.wakax.consulta.cep.infra;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.wakax.consulta.cep.domain.EnderecoViaCep;

@FeignClient(name = "viacep-api", url = "${viacep.api.base-url}")
public interface CepFeignClient {

  @GetMapping("/ws/{cep}/json/")
  EnderecoViaCep consulta(@PathVariable("cep") String cep);
}
