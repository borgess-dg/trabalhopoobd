DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS veiculos;
DROP TABLE IF EXISTS locacoes;
DROP TABLE IF EXISTS veiculos_da_locacao;

CREATE TABLE clientes(
	cliente_id INT GENERATED ALWAYS AS IDENTITY,
	nome_razao VARCHAR(128) NOT NULL,
	cpf_cnpj VARCHAR(18) UNIQUE NOT NULL,
	endereco VARCHAR (192) NOT NULL,
	telefone VARCHAR (20) NOT NULL,
	data_nascimento TIMESTAMP,
	data_cadastro TIMESTAMP NOT NULL,
	data_alteracao TIMESTAMP,
	flag_excluido BOOLEAN,
	PRIMARY KEY(cliente_id)
);

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
	data_alteracao TIMESTAMP,
	PRIMARY KEY(veiculo_id)
);

CREATE TABLE locacoes(
	locacao_id INT GENERATED ALWAYS AS IDENTITY,
	data_inicio TIMESTAMP NOT NULL,
	cliente_id INT NOT NULL,
	data_prevista_devolucao TIMESTAMP NOT NULL,
	data_devolucao TIMESTAMP,
	preco DECIMAL NOT NULL,
	multa DECIMAL NOT NULL,
	status INT NOT NULL,
	PRIMARY KEY(locacao_id),
	CONSTRAINT cliente_id
		FOREIGN KEY(cliente_id)
			REFERENCES clientes(cliente_id)
);

CREATE TABLE veiculos_da_locacao(
	vl_id INT GENERATED ALWAYS AS IDENTITY,
	locacao_id INT,
	veiculo_id INT,
	PRIMARY KEY (vl_id),
	CONSTRAINT locacao_id
		FOREIGN KEY(locacao_id)
			REFERENCES locacoes(locacao_id),
	CONSTRAINT veiculo_id
		FOREIGN KEY(veiculo_id)
			REFERENCES veiculos(veiculo_id)
);