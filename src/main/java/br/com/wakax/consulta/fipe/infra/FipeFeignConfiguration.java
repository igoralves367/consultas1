package br.com.wakax.consulta.fipe.infra;

import java.net.URI;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.wakax.consulta.handler.APIException;
import br.com.wakax.consulta.handler.ErrorCode;
import feign.Logger;
import feign.Response;
import feign.codec.ErrorDecoder;

@Configuration
public class FipeFeignConfiguration {

  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

  @Bean
  ErrorDecoder fipeErrorDecoder() {
    return new ErrorDecoder() {
      @Override
      public Exception decode(String methodKey, Response response) {
        int status = response.status();
        String url = response.request() != null ? response.request().url() : "";
        EndpointParts parts = EndpointParts.parse(url);

        return switch (status) {
          case 400 -> new APIException(
              org.springframework.http.HttpStatus.BAD_REQUEST, ErrorCode.FIPE_REQUISICAO_INVALIDA);
          case 404 -> switch (parts.type) {
            case MARCAS -> new APIException(
                org.springframework.http.HttpStatus.NOT_FOUND,
                ErrorCode.FIPE_DADOS_NAO_ENCONTRADOS);
            case MODELOS -> new APIException(
                org.springframework.http.HttpStatus.NOT_FOUND,
                ErrorCode.FIPE_MARCA_NAO_ENCONTRADA,
                parts.codigoMarca);
            case ANOS -> new APIException(
                org.springframework.http.HttpStatus.NOT_FOUND,
                ErrorCode.FIPE_MODELO_NAO_ENCONTRADO,
                parts.codigoModelo);
            case VALOR -> new APIException(
                org.springframework.http.HttpStatus.NOT_FOUND,
                ErrorCode.FIPE_ANO_NAO_ENCONTRADO,
                parts.codigoAno);
            case UNKNOWN -> new APIException(
                org.springframework.http.HttpStatus.NOT_FOUND,
                ErrorCode.FIPE_DADOS_NAO_ENCONTRADOS);
          };
          case 429 -> new APIException(
              org.springframework.http.HttpStatus.TOO_MANY_REQUESTS,
              ErrorCode.FIPE_LIMITE_EXCEDIDO);
          case 503 -> new APIException(
              org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE,
              ErrorCode.FIPE_SERVICO_INDISPONIVEL);
          default -> new APIException(
              org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR,
              ErrorCode.FIPE_API_ERRO,
              response.reason());
        };
      }
    };
  }

  static class EndpointParts {
    enum Type {
      MARCAS,
      MODELOS,
      ANOS,
      VALOR,
      UNKNOWN
    }

    final Type type;
    final String codigoMarca;
    final String codigoModelo;
    final String codigoAno;

    EndpointParts(Type type, String codigoMarca, String codigoModelo, String codigoAno) {
      this.type = type;
      this.codigoMarca = codigoMarca;
      this.codigoModelo = codigoModelo;
      this.codigoAno = codigoAno;
    }

    static EndpointParts parse(String rawUrl) {
      try {
        URI uri = URI.create(rawUrl);
        List<String> segments = List.of(uri.getPath().split("/"));
        int marcasIdx = segments.lastIndexOf("marcas");
        int modelosIdx = segments.lastIndexOf("modelos");
        int anosIdx = segments.lastIndexOf("anos");

        if (marcasIdx >= 0 && modelosIdx == -1) {
          return new EndpointParts(Type.MARCAS, null, null, null);
        }
        if (modelosIdx >= 0 && anosIdx == -1) {
          String marca = safeGet(segments, modelosIdx - 1);
          return new EndpointParts(Type.MODELOS, marca, null, null);
        }
        if (anosIdx >= 0 && anosIdx == segments.size() - 1) {
          String marca = safeGet(segments, anosIdx - 3);
          String modelo = safeGet(segments, anosIdx - 1);
          return new EndpointParts(Type.ANOS, marca, modelo, null);
        }
        if (anosIdx >= 0 && anosIdx < segments.size() - 1) {
          String marca = safeGet(segments, anosIdx - 3);
          String modelo = safeGet(segments, anosIdx - 1);
          String ano = safeGet(segments, anosIdx + 1);
          return new EndpointParts(Type.VALOR, marca, modelo, ano);
        }
      } catch (Exception ignored) {
      }
      return new EndpointParts(Type.UNKNOWN, null, null, null);
    }

    private static String safeGet(List<String> list, int idx) {
      return idx >= 0 && idx < list.size() ? list.get(idx) : null;
    }
  }
}
