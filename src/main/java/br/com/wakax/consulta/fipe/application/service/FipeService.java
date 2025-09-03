package br.com.wakax.consulta.fipe.application.service;

import java.util.List;

import br.com.wakax.consulta.fipe.domain.*;

public interface FipeService {
  List<Marca> consultaMarcas(TipoVeiculo tipoVeiculo);

  ModelosResponse consultaModelos(TipoVeiculo tipoVeiculo, String codigoMarca);

  List<Ano> consultaAnos(TipoVeiculo tipoVeiculo, String codigoMarca, String codigoModelo);

  VeiculoFipe consultaValor(
      TipoVeiculo tipoVeiculo, String codigoMarca, String codigoModelo, String codigoAno);
}
