package middlewares;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Cliente;

public class ValidaRegistrosDeClientes {
	
	public ValidaRegistrosDeClientes() {
		
	}
	
	/**public boolean existeClienteCadastrado() {
		return this.clientes.isEmpty();
	}
	
	public boolean clienteExiste(String identificadorCliente) {
		Cliente clienteVerificado;
		while(this.clientesIterator.hasNext()) {
			//Adicionar polimorfismo.
		}
		return false;
	}
	**/
	public boolean validaFormatoDataNascimento(String dataNascimento) {
		return dataNascimento.matches("^[0-3][0-9]/[0-1][0-9]/\\d{4}$");
	}
	
	public boolean verificaIdade(String dataNascimento) {
		DateTimeFormatter formatoDaData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataNascimentoLocal = LocalDate.parse(dataNascimento, formatoDaData);
		
		return ChronoUnit.YEARS.between(dataNascimentoLocal, LocalDate.now()) >= 18 ? true : false;
	}
	
	public boolean cpfValido(String cpfCliente) {
		Pattern cpfSemDigitos = Pattern.compile("^[0-9]{11}$");
		Pattern cpfComDigitos = Pattern.compile("^[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}$");
		Matcher matcherCpfSemDigitos = cpfSemDigitos.matcher(cpfCliente);
		Matcher matcherCpfComDigitos = cpfComDigitos.matcher(cpfCliente);
		return matcherCpfSemDigitos.find() || matcherCpfComDigitos.find();
	}
	
	public boolean cnpjValido(String cnpjCliente) {
		Pattern cnpjSemDigitos = Pattern.compile("^[0-9]{14}$");
		Pattern cnpjComDigitos = Pattern.compile("^[0-9]{2}.[0-9]{3}.[0-9]{3}/0001-[0-9]{2}$");
		Matcher matcherCnpjSemDigitos = cnpjSemDigitos.matcher(cnpjCliente);
		Matcher matcherCnpjComDigitos = cnpjComDigitos.matcher(cnpjCliente);
		return matcherCnpjSemDigitos.find() || matcherCnpjComDigitos.find();
	}
	
	public String verificaTipoCliente(String identificadorCliente) {
		if(cpfValido(identificadorCliente)) {
			return "Pessoa Física";
		}
		return "Pessoa Jurídica";
	}
	
	public LocalDate dataStringParaLocalDate(String data) {
		DateTimeFormatter formatoDaData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(data, formatoDaData);
	}
}
