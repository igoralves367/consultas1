package br.com.wakax.consulta.fipe.application.repository;

import java.util.List;

import br.com.wakax.consulta.fipe.domain.*;

public interface FipeRepository {
  List<Marca> buscaMarcas(TipoVeiculo tipoVeiculo);

  ModelosResponse buscaModelos(TipoVeiculo tipoVeiculo, String codigoMarca);

  List<Ano> buscaAnos(TipoVeiculo tipoVeiculo, String codigoMarca, String codigoModelo);

  VeiculoFipe buscaValor(
      TipoVeiculo tipoVeiculo, String codigoMarca, String codigoModelo, String codigoAno);
}
