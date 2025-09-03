package br.com.wakax.consulta.fipe.domain;

public enum TipoVeiculo {
  CARROS("carros"),
  MOTOS("motos"),
  CAMINHOES("caminhoes");

  private final String valor;

  TipoVeiculo(String valor) {
    this.valor = valor;
  }

  public String getValor() {
    return valor;
  }
}
