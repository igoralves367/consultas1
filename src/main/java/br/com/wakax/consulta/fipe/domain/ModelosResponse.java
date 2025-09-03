package br.com.wakax.consulta.fipe.domain;

import java.util.List;

public record ModelosResponse(List<Modelo> modelos, List<Ano> anos) {}
