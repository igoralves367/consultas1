# Consulta - Módulo FIPE

## Sobre o Projeto

O **consulta** é um serviço para consultas à API FIPE para preços de veículos, marcas, modelos e anos disponíveis.

## Módulo FIPE

Este módulo integra com a [API FIPE](https://deividfortuna.github.io/fipe/) para consultar preços de veículos, marcas, modelos e anos disponíveis.

### Funcionalidades FIPE

- **Consulta de Marcas**: Lista todas as marcas disponíveis por tipo de veículo
- **Consulta de Modelos**: Lista modelos disponíveis de uma marca específica
- **Consulta de Anos**: Lista anos disponíveis de um modelo específico
- **Consulta de Preço**: Obtém o preço atual FIPE de um veículo específico

### Tipos de Veículos Suportados

- `CARROS` - Automóveis
- `MOTOS` - Motocicletas
- `CAMINHOES` - Caminhões

## Requisitos

- Git
- Java 11
- Lombok
- Maven

## Instalação

1. Clone o repositório:
    ```bash
    git clone <URL_DO_SEU_REPOSITORIO>
    cd consulta
    ```

2. Compile o projeto:
    ```bash
    ./mvnw clean install
    ```

3. Execute a aplicação:
    ```bash
    ./mvnw spring-boot:run
    ```

## Endpoints FIPE

### 1. Consulta de Marcas
```
GET /consulta/api/fipe/marcas?tipoVeiculo=CARROS
```

### 2. Consulta de Modelos
```
GET /consulta/api/fipe/marcas/{codigoMarca}/modelos?tipoVeiculo=CARROS
```

### 3. Consulta de Anos
```
GET /consulta/api/fipe/marcas/{codigoMarca}/modelos/{codigoModelo}/anos?tipoVeiculo=CARROS
```

### 4. Consulta de Preço
```
GET /consulta/api/fipe/marcas/{codigoMarca}/modelos/{codigoModelo}/anos/{codigoAno}?tipoVeiculo=CARROS
```

## Configurações

### Variáveis de Ambiente

```yaml
# FIPE API Configuration
fipe:
  api:
    base-url: https://parallelum.com.br/fipe/api/v1
    timeout: 10000
    max-retries: 3
```

## Arquitetura

```
fipe/
├── domain/
├── application/
├── infra/
└── api/
```

## Testes

```bash
./mvnw test
```
