package br.com.wakax.consulta.fipe.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.wakax.consulta.fipe.domain.*;

@RestController
@RequestMapping("/fipe")
public interface FipeAPI {
  @GetMapping("/marcas")
  List<Marca> consultaMarcas(@RequestParam TipoVeiculo tipoVeiculo);

  @GetMapping("/marcas/{codigoMarca}/modelos")
  ModelosResponse consultaModelos(
      @RequestParam TipoVeiculo tipoVeiculo, @PathVariable String codigoMarca);

  @GetMapping("/marcas/{codigoMarca}/modelos/{codigoModelo}/anos")
  List<Ano> consultaAnos(
      @RequestParam TipoVeiculo tipoVeiculo,
      @PathVariable String codigoMarca,
      @PathVariable String codigoModelo);

  @GetMapping("/marcas/{codigoMarca}/modelos/{codigoModelo}/anos/{codigoAno}")
  VeiculoFipe consultaValor(
      @RequestParam TipoVeiculo tipoVeiculo,
      @PathVariable String codigoMarca,
      @PathVariable String codigoModelo,
      @PathVariable String codigoAno);
}
