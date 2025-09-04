package br.com.wakax.consulta.fipe.infra;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import br.com.wakax.consulta.fipe.application.repository.FipeRepository;
import br.com.wakax.consulta.fipe.domain.*;
import br.com.wakax.consulta.handler.APIException;
import br.com.wakax.consulta.handler.ErrorCode;
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
    if (marcas == null || marcas.isEmpty()) {
      throw new APIException(
          HttpStatus.NOT_FOUND, ErrorCode.FIPE_DADOS_NAO_ENCONTRADOS);
    }
    log.info("[finish] FipeRepositoryFeign - buscaMarcas");
    return marcas;
  }

  @Override
  public ModelosResponse buscaModelos(TipoVeiculo tipoVeiculo, String codigoMarca) {
    log.info("[start] FipeRepositoryFeign - buscaModelos");
    ModelosResponse modelos = fipeFeignClient.buscaModelos(tipoVeiculo.getValor(), codigoMarca);
    if (modelos == null || modelos.modelos() == null || modelos.modelos().isEmpty()) {
      throw new APIException(
          HttpStatus.NOT_FOUND,
          ErrorCode.FIPE_MARCA_NAO_ENCONTRADA,
          codigoMarca);
    }
    log.info("[finish] FipeRepositoryFeign - buscaModelos");
    return modelos;
  }

  @Override
  public List<Ano> buscaAnos(TipoVeiculo tipoVeiculo, String codigoMarca, String codigoModelo) {
    log.info("[start] FipeRepositoryFeign - buscaAnos");
    List<Ano> anos = fipeFeignClient.buscaAnos(tipoVeiculo.getValor(), codigoMarca, codigoModelo);
    if (anos == null || anos.isEmpty()) {
      throw new APIException(
          HttpStatus.NOT_FOUND,
          ErrorCode.FIPE_MODELO_NAO_ENCONTRADO,
          codigoModelo);
    }
    log.info("[finish] FipeRepositoryFeign - buscaAnos");
    return anos;
  }

  @Override
  public VeiculoFipe buscaValor(
      TipoVeiculo tipoVeiculo, String codigoMarca, String codigoModelo, String codigoAno) {
    log.info("[start] FipeRepositoryFeign - buscaValor");
    VeiculoFipe veiculo =
        fipeFeignClient.buscaValor(tipoVeiculo.getValor(), codigoMarca, codigoModelo, codigoAno);
    if (veiculo == null) {
      throw new APIException(
          HttpStatus.NOT_FOUND,
          ErrorCode.FIPE_ANO_NAO_ENCONTRADO,
          codigoAno);
    }
    log.info("[finish] FipeRepositoryFeign - buscaValor");
    return veiculo;
  }
}
