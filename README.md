# API F1 Web Flux

API com Spring WebFlux que utiliza o endpoint da ergast para consumir os dados de F1 e salvar em base para posterior consulta.  

## Estrutura do Projeto

```
DDD
Spring Web Flux
Spring Cloud Feign
Hystrix
JUnit
Mockito
MapStruct
Spring Data 
Exception Handler
Docker
Kafka
```

## Autores

* **Guilherme Vilela** - *Versão Inicial* - [gvilela1108](https://github.com/gvilela1108)
* **Angevaldo Peixoto da Rocha Junior** - *1.1* - [angevaldo](https://github.com/angevaldo)
* **Renato Rodrigues de Mello** - *1.1* - [rerodriguis](https://github.com/rerodriguis)


## Versão
1.1


# Installation

```
cd docker
docker-compose up -d 

Use winpty antes dos comandos para o Windows

=== create topic ==== 
docker-compose exec kafka \kafka-topics --create --topic f1_scheduling --partitions 100 --replication-factor 1 --if-not-exists --zookeeper zookeeper:2181

=== verify topic === 
docker-compose exec kafka \kafka-topics --describe --topic f1_scheduling --zookeeper zookeeper:2181

=== Producer message ===
docker-compose exec kafka \bash -c "seq 100 | kafka-console-producer --request-required-acks 1 --broker-list localhost:9092 --topic f1_scheduling && echo 'Produced 100 messages.'"

=== Consumer message === 
docker-compose exec kafka \kafka-console-consumer --bootstrap-server localhost:9092 --topic f1_scheduling --from-beginning --max-messages 100

=== Verificação de logs === 
docker-compose logs kafka 
docker-compose logs zookeeper

```