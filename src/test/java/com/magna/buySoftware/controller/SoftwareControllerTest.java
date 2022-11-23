package com.magna.buySoftware.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.magna.buySoftware.entity.UsuarioEntity;
import com.magna.buySoftware.repository.UsuarioRepository;
import com.magna.buySoftware.vo.SoftwareVO;
import com.magna.buySoftware.vo.UsuarioVO;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SoftwareControllerTest implements Urls {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UsuarioRepository repository;

	@BeforeAll
	void start() {
		repository.deleteAll();
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setNome("João Conrado");
		usuario.setCpf("475.530.280-37");
		usuario.setEmail("magna@email.com");
		usuario.setSenha("0123456789");
		repository.save(usuario);
	}

	@Test
	@Order(1)
	void cadastrarSoftware() throws Exception {
		URI uri = new URI(URLSOFTWARE);
		SoftwareVO software = new SoftwareVO();
		software.setSoftwareDesejado("Landing Page (HTML, CSS & JavaScript)");
		software.setValorSoftware(5000d);
		software.setDesenvolvedor("João Conrado");
		software.setDataEntrega(LocalDate.of(2023, 07, 18));
		HttpEntity<SoftwareVO> entity = new HttpEntity<SoftwareVO>(software);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri + "/1", HttpMethod.POST, entity,
				Object.class);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	@Order(2)
	void erroAoCadastrarSoftwarePoisSoftwareDesejadoNaoPodeSerVazio() throws Exception {
		URI uri = new URI(URLSOFTWARE);
		SoftwareVO software = new SoftwareVO();
		software.setSoftwareDesejado("");
		software.setValorSoftware(5000d);
		software.setDesenvolvedor("João Conrado");
		software.setDataEntrega(LocalDate.of(2023, 07, 18));
		HttpEntity<SoftwareVO> entity = new HttpEntity<SoftwareVO>(software);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri + "/1", HttpMethod.POST, entity,
				Object.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	@Order(3)
	void erroAoCadastrarSoftwarePoisValorSoftwareNaoPodeSerNulo() throws Exception {
		URI uri = new URI(URLSOFTWARE);
		SoftwareVO software = new SoftwareVO();
		software.setSoftwareDesejado("Landing Page (HTML, CSS & JavaScript)");
		software.setValorSoftware(null);
		software.setDesenvolvedor("João Conrado");
		software.setDataEntrega(LocalDate.of(2023, 07, 18));
		HttpEntity<SoftwareVO> entity = new HttpEntity<SoftwareVO>(software);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri + "/1", HttpMethod.POST, entity,
				Object.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	@Order(4)
	void erroAoCadastrarSoftwarePoisDesenvolvedorNaoPodeSerVazio() throws Exception {
		URI uri = new URI(URLSOFTWARE);
		SoftwareVO software = new SoftwareVO();
		software.setSoftwareDesejado("Landing Page (HTML, CSS & JavaScript)");
		software.setValorSoftware(5000d);
		software.setDesenvolvedor("");
		software.setDataEntrega(LocalDate.of(2023, 07, 18));
		HttpEntity<SoftwareVO> entity = new HttpEntity<SoftwareVO>(software);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri + "/1", HttpMethod.POST, entity,
				Object.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	@Order(5)
	void buscarSoftwarePorID() throws Exception {
		URI uri = new URI(URLSOFTWARE);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri + "/1", HttpMethod.GET, null, Object.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	@Order(6)
	void erroBuscarSoftwarePorUmIDInexistente() throws URISyntaxException {
		URI uri = new URI(URLSOFTWARE);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri + "/999", HttpMethod.GET, null, Object.class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	@Order(7)
	void atualizarSoftware() throws Exception {
		URI uri = new URI(URLSOFTWARE);
		SoftwareVO software = new SoftwareVO();
		software.setSoftwareDesejado("API JAVA - SPRING");
		software.setValorSoftware(10000d);
		software.setDesenvolvedor("Daniel");
		software.setDataEntrega(LocalDate.of(2025, 12, 01));
		HttpEntity<SoftwareVO> entity = new HttpEntity<SoftwareVO>(software);
		ResponseEntity<UsuarioVO> responseEntity = restTemplate.exchange(uri + "/1", HttpMethod.PUT, entity,
				UsuarioVO.class);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	@Order(8)
	void erroAtualizarSoftwarePoisSoftwareDesejadoNaoPodeEstarVazio() throws Exception {
		URI uri = new URI(URLSOFTWARE);
		SoftwareVO software = new SoftwareVO();
		software.setSoftwareDesejado("");
		software.setValorSoftware(10000d);
		software.setDesenvolvedor("Daniel");
		software.setDataEntrega(LocalDate.of(2025, 12, 01));
		HttpEntity<SoftwareVO> entity = new HttpEntity<SoftwareVO>(software);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri + "/1", HttpMethod.PUT, entity, Object.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	@Order(9)
	void erroAtualizarSoftwarePoisValorSoftwareNaoPodeEstarVazio() throws Exception {
		URI uri = new URI(URLSOFTWARE);
		SoftwareVO software = new SoftwareVO();
		software.setSoftwareDesejado("API JAVA - SPRING");
		software.setValorSoftware(null);
		software.setDesenvolvedor("Daniel");
		software.setDataEntrega(LocalDate.of(2025, 12, 01));
		HttpEntity<SoftwareVO> entity = new HttpEntity<SoftwareVO>(software);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri + "/1", HttpMethod.PUT, entity, Object.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	@Order(10)
	void erroAtualizarSoftwarePoisDesenvolvedorNaoPodeEstarVazio() throws Exception {
		URI uri = new URI(URLSOFTWARE);
		SoftwareVO software = new SoftwareVO();
		software.setSoftwareDesejado("API JAVA - SPRING");
		software.setValorSoftware(10000d);
		software.setDesenvolvedor("");
		software.setDataEntrega(LocalDate.of(2025, 12, 01));
		HttpEntity<SoftwareVO> entity = new HttpEntity<SoftwareVO>(software);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri + "/1", HttpMethod.PUT, entity, Object.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	@Order(11)
	void erroAtualizarSoftwarePorUmIDInexistente() throws URISyntaxException {
		URI uri = new URI(URLSOFTWARE);
		SoftwareVO software = new SoftwareVO();
		software.setSoftwareDesejado("API JAVA - SPRING");
		software.setValorSoftware(10000d);
		software.setDesenvolvedor("Daniel");
		software.setDataEntrega(LocalDate.of(2025, 12, 01));
		HttpEntity<SoftwareVO> entity = new HttpEntity<SoftwareVO>(software);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri + "/99", HttpMethod.PUT, entity,
				Object.class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	@Order(12)
	void deletarSoftware() throws Exception {
		URI uri = new URI(URLSOFTWARE);
		ResponseEntity<UsuarioVO> responseEntity = restTemplate.exchange(uri + "/1", HttpMethod.DELETE, null,
				UsuarioVO.class);
		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}

	@Test
	@Order(13)
	void erroDeletarSoftwarePorUmIDInexistente() throws URISyntaxException {
		URI uri = new URI(URLSOFTWARE);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(uri + "/99", HttpMethod.DELETE, null,
				Object.class);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

}
