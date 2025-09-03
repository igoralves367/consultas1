package br.com.wakax.consulta.fipe.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VeiculoFipe(
    @JsonProperty("TipoVeiculo") Integer tipoVeiculo,
    @JsonProperty("Valor") String valor,
    @JsonProperty("Marca") String marca,
    @JsonProperty("Modelo") String modelo,
    @JsonProperty("AnoModelo") Integer anoModelo,
    @JsonProperty("Combustivel") String combustivel,
    @JsonProperty("CodigoFipe") String codigoFipe,
    @JsonProperty("MesReferencia") String mesReferencia,
    @JsonProperty("SiglaCombustivel") String siglaCombustivel) {}
