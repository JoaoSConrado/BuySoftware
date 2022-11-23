package com.magna.buySoftware.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.magna.buySoftware.vo.UsuarioVO;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioControllerTest implements Urls {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	void cadastrarUmUsuario() throws Exception {
		URI uri = new URI(URLUSUARIO);
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setNome("Magna");
		usuarioVO.setCpf("317.347.920-35");
		usuarioVO.setEmail("magna@email.com");
		usuarioVO.setSenha("0123456789");
		HttpEntity<UsuarioVO> entity = new HttpEntity<UsuarioVO>(usuarioVO);
		ResponseEntity<UsuarioVO> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, UsuarioVO.class);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(2)
	void erroAoCadastrarUsuarioPoisNomeNaoPodeEstarVazio() throws URISyntaxException {
		URI uri = new URI(URLUSUARIO);
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setNome("");
		usuarioVO.setCpf("317.347.920-35");
		usuarioVO.setEmail("magna@email.com");
		usuarioVO.setSenha("0123456789");
		HttpEntity<UsuarioVO> entity = new HttpEntity<UsuarioVO>(usuarioVO);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(3)
	void erroAoCadastrarUsuarioPoisCPFNaoPodeEstarVazio() throws URISyntaxException {
		URI uri = new URI(URLUSUARIO);
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setNome("João Conrado");
		usuarioVO.setCpf("");
		usuarioVO.setEmail("magna@email.com");
		usuarioVO.setSenha("0123456789");
		HttpEntity<UsuarioVO> entity = new HttpEntity<UsuarioVO>(usuarioVO);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(4)
	void erroAoCadastrarUsuarioPoisEmailNaoPodeEstarVazio() throws URISyntaxException {
		URI uri = new URI(URLUSUARIO);
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setNome("João Conrado");
		usuarioVO.setCpf("317.347.920-35");
		usuarioVO.setEmail("");
		usuarioVO.setSenha("0123456789");
		HttpEntity<UsuarioVO> entity = new HttpEntity<UsuarioVO>(usuarioVO);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(5)
	void erroAoCadastrarUsuarioPoisSenhaNaoPodeEstarVazio() throws URISyntaxException {
		URI uri = new URI(URLUSUARIO);
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setNome("João Conrado");
		usuarioVO.setCpf("317.347.920-35");
		usuarioVO.setEmail("magna@email.com");
		usuarioVO.setSenha("");
		HttpEntity<UsuarioVO> entity = new HttpEntity<UsuarioVO>(usuarioVO);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(6)
	void erroAoCadastrarUsuarioPoisASenhaEstaInvalida() throws URISyntaxException {
		URI uri = new URI(URLUSUARIO);
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setNome("João Conrado");
		usuarioVO.setCpf("809.905.260-97");
		usuarioVO.setEmail("magnasistemas@email.com");
		usuarioVO.setSenha("1234");
		HttpEntity<UsuarioVO> entity = new HttpEntity<UsuarioVO>(usuarioVO);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	@Order(7)
	void erroCadastrarUmUsuarioComOMesmoCPF() throws URISyntaxException {
		URI uri = new URI(URLUSUARIO);
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setNome("João Conrado");
		usuarioVO.setCpf("317.347.920-35");
		usuarioVO.setEmail("joaoconrado@email.com");
		usuarioVO.setSenha("123123123");
		HttpEntity<UsuarioVO> entity = new HttpEntity<UsuarioVO>(usuarioVO);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(8)
	void erroCadastrarUmUsuarioPoisOCpfEstaInvalido() throws URISyntaxException {
		URI uri = new URI(URLUSUARIO);
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setNome("Magna Sistemas");
		usuarioVO.setCpf("01234567890");
		usuarioVO.setEmail("magna@email.com");
		usuarioVO.setSenha("1010101010");
		HttpEntity<UsuarioVO> entity = new HttpEntity<UsuarioVO>(usuarioVO);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(9)
	void erroCadastrarUmUsuarioComOMesmoEmail() throws URISyntaxException {
		URI uri = new URI(URLUSUARIO);
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setNome("Daniel Santos");
		usuarioVO.setCpf("592.138.790-83");
		usuarioVO.setEmail("magna@email.com");
		usuarioVO.setSenha("1010101010");
		HttpEntity<UsuarioVO> entity = new HttpEntity<UsuarioVO>(usuarioVO);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(10)
	void erroCadastrarUmUsuarioPoisOEmailEstaInvalido() throws URISyntaxException {
		URI uri = new URI(URLUSUARIO);
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setNome("Magna Sistemas");
		usuarioVO.setCpf("429.060.240-15");
		usuarioVO.setEmail("MagnaSistemas");
		usuarioVO.setSenha("1010101010");
		HttpEntity<UsuarioVO> entity = new HttpEntity<UsuarioVO>(usuarioVO);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(11)
	void buscarUsuarioPeloID() throws Exception  {
		URI uri = new URI(URLUSUARIO);
		ResponseEntity<UsuarioVO> responseEntity = restTemplate.exchange(uri + "/1", HttpMethod.GET, null, UsuarioVO.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(12)
	void erroBuscarUsuarioPorUmIDInexistente() throws URISyntaxException  {
		URI uri = new URI(URLUSUARIO);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri + "/999", HttpMethod.GET, null, Object.class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(13)
	void atualizarUsuario() throws Exception  {
		URI uri = new URI(URLUSUARIO);
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setNome("Magna Sistemas");
		usuarioVO.setCpf("319.563.840-14");
		usuarioVO.setEmail("MagnaSistemas@gmail.com");
		usuarioVO.setSenha("12341234");
		HttpEntity<UsuarioVO> entity = new HttpEntity<UsuarioVO>(usuarioVO);
		ResponseEntity<UsuarioVO> responseEntity = restTemplate.exchange(uri + "/1", HttpMethod.PUT, entity, UsuarioVO.class);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(14)
	void erroAtualizarUsuarioPorUmIDInexistente() throws URISyntaxException  {
		URI uri = new URI(URLUSUARIO);
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setNome("Magna Sistemas");
		usuarioVO.setCpf("319.563.840-14");
		usuarioVO.setEmail("MagnaSistemas@gmail.com");
		usuarioVO.setSenha("12341234");
		HttpEntity<UsuarioVO> entity = new HttpEntity<UsuarioVO>(usuarioVO);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri + "/99", HttpMethod.PUT, entity, Object.class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(15)
	void deletarUsuario() throws Exception {
		URI uri = new URI(URLUSUARIO);
		ResponseEntity<UsuarioVO> responseEntity = restTemplate.exchange(uri + "/1", HttpMethod.DELETE, null, UsuarioVO.class);
		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}
	
	@Test
	@Order(16)
	void erroDeletarUsuarioPorUmIDInexistente() throws URISyntaxException {
		URI uri = new URI(URLUSUARIO);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri + "/99", HttpMethod.DELETE, null, Object.class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
}
