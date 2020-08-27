package com.webflux.f1.webfluxf1api.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
  F1_CLIENT_ERROR(10000, "Erro ao consultar os dados da corrida no Ergast"),
  SEASON_ERROR(10001, "Temporada informada superior a temporada corrente"),
  RACE_NOT_FOUND(10002, "Dados de corrida não encontrados"),
  DATABASE_ERROR(10003, "Erro ao salvar os dados"),
  SEND_KAFKA_ERROR(10004, "Erro ao enviar para o kafka"),
  KAFKA_PRODUCER_ERROR(10005,"Erro ao executar o kafka producer"),
  GENERIC(99999, "Serviço indisponível");

  private Integer code;

  private String description;

  ErrorCode(Integer code, String description) {
    this.code = code;
    this.description = description;
  }
}
