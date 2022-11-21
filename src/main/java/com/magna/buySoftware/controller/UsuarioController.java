package com.magna.buySoftware.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magna.buySoftware.service.UsuarioService;
import com.magna.buySoftware.vo.UsuarioVO;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<Page<UsuarioVO>> buscarTodosUsuarios(@PageableDefault(size = 20) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarTodosUsuarios(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioVO> buscarUsuarioPorId(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarUsuarioPorId(id));
	}

	@PostMapping
	public ResponseEntity<UsuarioVO> salvarUsuario(@RequestBody UsuarioVO usuarioVO) {
		usuarioService.salvarUsuario(usuarioVO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deletarUsuario(@PathVariable Long id) {
		usuarioService.deletarUsuario(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioVO> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioVO usuario) {
		usuarioService.atualizarUsuario(id, usuario);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}

