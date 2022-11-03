CREATE TABLE restaurante (
	id bigint NOT NULL AUTO_INCREMENT,
    cep varchar(9) NOT NULL,
    tipo_de_cozinha_id bigint NOT NULL,
    PRIMARY KEY (id)
);
