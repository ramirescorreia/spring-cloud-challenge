CREATE TABLE pagamento (
    id bigint NOT NULL AUTO_INCREMENT,
    valor decimal(19,2) NOT NULL,
    nome varchar(100) DEFAULT NULL,
    numero varchar(19) DEFAULT NULL,
    expiracao varchar(7) NOT NULL,
    codigo varchar(3) DEFAULT NULL,
    status varchar(255) NOT NULL,
    forma_de_pagamento_id bigint NOT NULL,
    pedido_id bigint NOT NULL,
    PRIMARY KEY (id)
  );
