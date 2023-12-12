CREATE TABLE clientes(
	cliente_id INT GENERATED ALWAYS AS IDENTITY,
	nome_razao VARCHAR(128) NOT NULL,
	cpf_cnpj VARCHAR(18) UNIQUE NOT NULL,
	endereco VARCHAR (192) NOT NULL,
	telefone VARCHAR (20) NOT NULL,
	data_nascimento TIMESTAMP,
	data_cadastro TIMESTAMP NOT NULL,
	data_alteracao TIMESTAMP,
	flag_excluido BOOLEAN
);