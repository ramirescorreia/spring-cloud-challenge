CREATE TABLE USUARIO (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE(name)
);

CREATE TABLE role (
  authority varchar(255) NOT NULL,
  PRIMARY KEY (authority)
) ;

CREATE TABLE user_authorities (
  user_id bigint NOT NULL,
  authorities_authority varchar(255) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES USUARIO(id),
  FOREIGN KEY (authorities_authority) REFERENCES role(authority)
) ;
