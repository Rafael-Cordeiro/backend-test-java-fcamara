INSERT INTO estabelecimento (nome, cnpj, endereco, telefone, qtde_vagas_motos, qtde_vagas_carros) VALUES
('Estapar', '12.345.678/0001-90', 'Rua cinco, 454 - São Paulo - SP', '(11) 98765-4321', 20, 15),
('Helbor Parking', '63.103.442/0001-83', 'Rua Dr Ricardo Vilela, 300 - Mogi das Cruzes - SP', '(11) 98765-4321', 25, 15);

INSERT INTO veiculo (marca, modelo, cor, placa, tipo) VALUES
('Fiat', 'Uno Mille 2002', 'Vermelho', 'FEB-8879', 'B'),
('Chevrolet', 'Onix Hatch 2018', 'Branco', 'CEI-3Y22', 'B'),
('Renaut', 'Kwid', 'Preto', 'SSA-2T76', 'B'),
('Yamaha', 'Fazer FZ25 ABS', 'Azul', 'SPO-4L21', 'A'),
('BMW', 'G 310 GS', 'Preto', 'GZW-7546', 'A');

INSERT INTO controle_entrada_saida (entrada_saida, est_id, vei_id, registro) VALUES
(0, 1, 1, '2024-09-10 18:53:06.155936+00'),
(0, 1, 2, '2024-09-10 18:54:01.452741+00'),
(0, 1, 3, '2024-09-10 18:55:01.452741+00'),
(0, 1, 4, '2024-09-10 18:56:06.155936+00'),
(0, 1, 5, '2024-09-10 19:20:06.155936+00'),
(1, 1, 1, '2024-09-10 19:21:06.155936+00'),
(1, 1, 2, '2024-09-10 19:22:06.155936+00'),
(1, 1, 3, '2024-09-10 20:02:06.155936+00'),
(1, 1, 4, '2024-09-10 20:09:06.155936+00'),
(1, 1, 5, '2024-09-10 20:17:06.155936+00'),
(0, 1, 1, '2024-09-10 20:30:06.155936+00'),
(0, 1, 2, '2024-09-10 20:39:06.155936+00'),
(0, 1, 3, '2024-09-10 20:51:06.155936+00');