package br.com.wakax.consulta.fipe.api;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import br.com.wakax.consulta.fipe.application.service.FipeService;
import br.com.wakax.consulta.fipe.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class FipeController implements FipeAPI {
  private final FipeService fipeService;

  @Override
  public List<Marca> consultaMarcas(TipoVeiculo tipoVeiculo) {
    log.info("[start] FipeController - consultaMarcas");
    List<Marca> marcas = fipeService.consultaMarcas(tipoVeiculo);
    log.info("[finish] FipeController - consultaMarcas");
    return marcas;
  }

  @Override
  public ModelosResponse consultaModelos(TipoVeiculo tipoVeiculo, String codigoMarca) {
    log.info("[start] FipeController - consultaModelos");
    ModelosResponse modelos = fipeService.consultaModelos(tipoVeiculo, codigoMarca);
    log.info("[finish] FipeController - consultaModelos");
    return modelos;
  }

  @Override
  public List<Ano> consultaAnos(TipoVeiculo tipoVeiculo, String codigoMarca, String codigoModelo) {
    log.info("[start] FipeController - consultaAnos");
    List<Ano> anos = fipeService.consultaAnos(tipoVeiculo, codigoMarca, codigoModelo);
    log.info("[finish] FipeController - consultaAnos");
    return anos;
  }

  @Override
  public VeiculoFipe consultaValor(
      TipoVeiculo tipoVeiculo, String codigoMarca, String codigoModelo, String codigoAno) {
    log.info("[start] FipeController - consultaValor");
    VeiculoFipe veiculo =
        fipeService.consultaValor(tipoVeiculo, codigoMarca, codigoModelo, codigoAno);
    log.info("[finish] FipeController - consultaValor");
    return veiculo;
  }
}
