package com.baggio.pedidovendabackend.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.baggio.pedidovendabackend.domain.Cliente;
import com.baggio.pedidovendabackend.repositories.ClienteRepository;
import com.baggio.pedidovendabackend.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private EmailService emailService;
	
	private Random random = new Random();
	
	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) {
			throw new ObjectNotFoundException("E-mail não encontrado.");
		}
		
		String newPassword = newPassword();
		cliente.setSenha(encoder.encode(newPassword));
		
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPassword);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for(int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		if(opt == 0) {//gera um digito
			return (char) (random.nextInt(10) + 48); //gera digito de 0 a 9
		}else if(opt == 1) { //gera letra maiusc.
			return (char) (random.nextInt(26) + 65); //gera digitos de A a Z
		}else { // gera letra minusc.
			return (char) (random.nextInt(26) + 97); //gera digitos de a ate z
		}		
	}
	
	
}
