package br.com.wakax.consulta.fipe.infra;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.wakax.consulta.fipe.domain.*;

@FeignClient(
    name = "fipe-api",
    url = "${fipe.api.base-url}",
    configuration = FipeFeignConfiguration.class)
public interface FipeFeignClient {

  @GetMapping("/{tipoVeiculo}/marcas")
  List<Marca> buscaMarcas(@PathVariable("tipoVeiculo") String tipoVeiculo);

  @GetMapping("/{tipoVeiculo}/marcas/{codigoMarca}/modelos")
  ModelosResponse buscaModelos(
      @PathVariable("tipoVeiculo") String tipoVeiculo,
      @PathVariable("codigoMarca") String codigoMarca);

  @GetMapping("/{tipoVeiculo}/marcas/{codigoMarca}/modelos/{codigoModelo}/anos")
  List<Ano> buscaAnos(
      @PathVariable("tipoVeiculo") String tipoVeiculo,
      @PathVariable("codigoMarca") String codigoMarca,
      @PathVariable("codigoModelo") String codigoModelo);

  @GetMapping("/{tipoVeiculo}/marcas/{codigoMarca}/modelos/{codigoModelo}/anos/{codigoAno}")
  VeiculoFipe buscaValor(
      @PathVariable("tipoVeiculo") String tipoVeiculo,
      @PathVariable("codigoMarca") String codigoMarca,
      @PathVariable("codigoModelo") String codigoModelo,
      @PathVariable("codigoAno") String codigoAno);
}
