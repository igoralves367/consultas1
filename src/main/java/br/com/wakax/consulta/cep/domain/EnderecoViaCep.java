package br.com.wakax.consulta.cep.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EnderecoViaCep(
    String cep,
    String logradouro,
    String complemento,
    String unidade,
    String bairro,
    String localidade,
    String uf,
    String ibge,
    String gia,
    String ddd,
    String siafi,
    Boolean erro) {}
