package br.com.wakax.consulta.fipe.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.wakax.consulta.fipe.application.repository.FipeRepository;
import br.com.wakax.consulta.fipe.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FipeRepositoryFeign implements FipeRepository {
  private final FipeFeignClient fipeFeignClient;

  @Override
  public List<Marca> buscaMarcas(TipoVeiculo tipoVeiculo) {
    log.info("[start] FipeRepositoryFeign - buscaMarcas");
    List<Marca> marcas = fipeFeignClient.buscaMarcas(tipoVeiculo.getValor());
    log.info("[finish] FipeRepositoryFeign - buscaMarcas");
    return marcas;
  }

  @Override
  public ModelosResponse buscaModelos(TipoVeiculo tipoVeiculo, String codigoMarca) {
    log.info("[start] FipeRepositoryFeign - buscaModelos");
    ModelosResponse modelos = fipeFeignClient.buscaModelos(tipoVeiculo.getValor(), codigoMarca);
    log.info("[finish] FipeRepositoryFeign - buscaModelos");
    return modelos;
  }

  @Override
  public List<Ano> buscaAnos(TipoVeiculo tipoVeiculo, String codigoMarca, String codigoModelo) {
    log.info("[start] FipeRepositoryFeign - buscaAnos");
    List<Ano> anos = fipeFeignClient.buscaAnos(tipoVeiculo.getValor(), codigoMarca, codigoModelo);
    log.info("[finish] FipeRepositoryFeign - buscaAnos");
    return anos;
  }

  @Override
  public VeiculoFipe buscaValor(
      TipoVeiculo tipoVeiculo, String codigoMarca, String codigoModelo, String codigoAno) {
    log.info("[start] FipeRepositoryFeign - buscaValor");
    VeiculoFipe veiculo =
        fipeFeignClient.buscaValor(tipoVeiculo.getValor(), codigoMarca, codigoModelo, codigoAno);
    log.info("[finish] FipeRepositoryFeign - buscaValor");
    return veiculo;
  }
}
