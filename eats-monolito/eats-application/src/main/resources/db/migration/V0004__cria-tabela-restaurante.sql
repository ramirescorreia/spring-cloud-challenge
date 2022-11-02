CREATE TABLE restaurante (
  id bigint NOT NULL AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  aprovado BOOLEAN DEFAULT false,
  cnpj varchar(18) NOT NULL,
  descricao varchar(1000) DEFAULT NULL,
  cep varchar(9) NOT NULL,
  endereco varchar(300) NOT NULL,
  taxa_de_entrega_em_reais NUMERIC(19,2) DEFAULT NULL,
  tempo_de_entrega_maximo_em_minutos int DEFAULT NULL,
  tempo_de_entrega_minimo_em_minutos int DEFAULT NULL,
  tipo_de_cozinha_id bigint NOT NULL,
  user_id  bigint DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (tipo_de_cozinha_id) REFERENCES tipo_de_cozinha(id),
  FOREIGN KEY (user_id) REFERENCES USUARIO(id)
) ;
