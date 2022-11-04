INSERT INTO pedido ( data_hora, status, restaurante_id) values ( NOW(), 'ENTREGUE', 1);

INSERT INTO item_do_pedido (quantidade, item_do_cardapio_id, pedido_id) values ( 1, 4, 1);
INSERT INTO item_do_pedido (quantidade, item_do_cardapio_id, pedido_id) values ( 1, 8, 1);
