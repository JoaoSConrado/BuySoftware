package com.magna.buySoftware.controller;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magna.buySoftware.service.SoftwareService;
import com.magna.buySoftware.vo.SoftwareVO;

@RestController
@RequestMapping("/software")
@CrossOrigin(origins = "*")
public class SoftwareController {

	@Autowired
	private SoftwareService softwareService;

	@GetMapping
	public ResponseEntity<Page<SoftwareVO>> buscarTodosSoftwares(@ParameterObject @PageableDefault(size = 20) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(softwareService.buscarTodosSoftwares(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SoftwareVO> buscarSoftwarePorId(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(softwareService.buscarSoftwarePorId(id));
	}

	@PostMapping("/{id}")
	public ResponseEntity<SoftwareVO> salvarSoftware(@PathVariable Long id, @RequestBody @Valid SoftwareVO softwareVO) {
		softwareService.salvarSoftware(id, softwareVO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deletarSoftware(@PathVariable Long id) {
		softwareService.deletarSoftware(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SoftwareVO> atualizarSoftware(@PathVariable Long id, @RequestBody @Valid SoftwareVO software) {
		softwareService.atualizarSoftware(id, software);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}

