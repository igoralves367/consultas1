package br.com.wakax.consulta.fipe.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.wakax.consulta.fipe.application.repository.FipeRepository;
import br.com.wakax.consulta.fipe.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class FipeApplicationService implements FipeService {
  private final FipeRepository fipeRepository;

  @Override
  public List<Marca> consultaMarcas(TipoVeiculo tipoVeiculo) {
    log.info("[start] FipeApplicationService - consultaMarcas");
    List<Marca> marcas = fipeRepository.buscaMarcas(tipoVeiculo);
    log.info("[finish] FipeApplicationService - consultaMarcas");
    return marcas;
  }

  @Override
  public ModelosResponse consultaModelos(TipoVeiculo tipoVeiculo, String codigoMarca) {
    log.info("[start] FipeApplicationService - consultaModelos");
    ModelosResponse modelos = fipeRepository.buscaModelos(tipoVeiculo, codigoMarca);
    log.info("[finish] FipeApplicationService - consultaModelos");
    return modelos;
  }

  @Override
  public List<Ano> consultaAnos(TipoVeiculo tipoVeiculo, String codigoMarca, String codigoModelo) {
    log.info("[start] FipeApplicationService - consultaAnos");
    List<Ano> anos = fipeRepository.buscaAnos(tipoVeiculo, codigoMarca, codigoModelo);
    log.info("[finish] FipeApplicationService - consultaAnos");
    return anos;
  }

  @Override
  public VeiculoFipe consultaValor(
      TipoVeiculo tipoVeiculo, String codigoMarca, String codigoModelo, String codigoAno) {
    log.info("[start] FipeApplicationService - consultaValor");
    VeiculoFipe veiculo =
        fipeRepository.buscaValor(tipoVeiculo, codigoMarca, codigoModelo, codigoAno);
    log.info("[finish] FipeApplicationService - consultaValor");
    return veiculo;
  }
}
