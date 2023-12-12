CREATE TABLE veiculos(
	veiculo_id INT GENERATED ALWAYS AS IDENTITY,
	marca VARCHAR(64) NOT NULL,
	modelo VARCHAR(64) NOT NULL,
	placa VARCHAR(8) UNIQUE NOT NULL,
	ano_fabricacao INT NOT NULL,
	ano_modelo INT NOT NULL,
	capacidade_passageiros INT,
	capacidade_carga DECIMAL,
	quantidade_portas INT,
	numero_eixos INT,
	data_cadastro TIMESTAMP NOT NULL,
	data_alteracao TIMESTAMP
);