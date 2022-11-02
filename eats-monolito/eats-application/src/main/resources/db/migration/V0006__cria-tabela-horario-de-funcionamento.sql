CREATE TABLE horario_de_funcionamento (
  id bigint NOT NULL AUTO_INCREMENT,
  dia_da_semana varchar(255) NOT NULL,
  horario_de_abertura time NOT NULL,
  horario_de_fechamento time NOT NULL,
  restaurante_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (restaurante_id) REFERENCES restaurante(id)
) ;