package com.magna.buySoftware.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.magna.buySoftware.entity.SoftwareEntity;
import com.magna.buySoftware.entity.UsuarioEntity;
import com.magna.buySoftware.exception.DadosNaoEncontradosException;
import com.magna.buySoftware.repository.SoftwareRepository;
import com.magna.buySoftware.repository.UsuarioRepository;
import com.magna.buySoftware.vo.SoftwareVO;

@Service
public class SoftwareService {

	private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	SoftwareRepository softwareRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	public SoftwareEntity converterSoftwareVOParaSoftwareEntity(SoftwareVO softwareVO) {
		SoftwareEntity softwareEntity = new SoftwareEntity();
		softwareEntity.setId(softwareVO.getId());
		softwareEntity.setSoftwareDesejado(softwareVO.getSoftwareDesejado());
		softwareEntity.setValorSoftware(softwareVO.getValorSoftware());
		softwareEntity.setDesenvolvedor(softwareVO.getDesenvolvedor());
		softwareEntity.setDataEntrega(softwareVO.getDataEntrega());
		softwareEntity.setUsuarioEntity(softwareVO.getUsuarioEntity());
		softwareEntity.setDataPedido(softwareVO.getDataPedido());
		return softwareEntity;
	}

	public SoftwareVO converterSoftwareEntityParaSoftwareVO(SoftwareEntity softwareEntity) {
		SoftwareVO softwareVO = new SoftwareVO();
		softwareVO.setId(softwareEntity.getId());
		softwareVO.setSoftwareDesejado(softwareEntity.getSoftwareDesejado());
		softwareVO.setValorSoftware(softwareEntity.getValorSoftware());
		softwareVO.setDesenvolvedor(softwareEntity.getDesenvolvedor());
		softwareVO.setDataEntrega(softwareEntity.getDataEntrega());
		softwareVO.setUsuarioEntity(softwareEntity.getUsuarioEntity());
		softwareVO.setDataPedido(softwareEntity.getDataPedido());
		return softwareVO;
	}

	public Page<SoftwareVO> buscarTodosSoftwares(Pageable pageable) {
		logger.info("LISTANDO TODOS OS SOFTWARES CADASTRADOS");
		return softwareRepository.findAll(pageable).map(this::converterSoftwareEntityParaSoftwareVO);
	}

	public SoftwareVO buscarSoftwarePorId(Long id) {
		logger.info("BUSCANDO SOFTWARE POR ID");
		Optional<SoftwareEntity> software = Optional.of(softwareRepository.findById(id)
				.orElseThrow(() -> new DadosNaoEncontradosException("Software não foi encontrado!")));
		SoftwareEntity softwareEntity = software.get();
		logger.info("SOFTWARE COM O ID: {}! ENCONTRADO COM SUCESSO!", id);
		return this.converterSoftwareEntityParaSoftwareVO(softwareEntity);
	}

	public void salvarSoftware(Long id, SoftwareVO softwareVO) {
		logger.info("CADASTRANDO SOFTWARE");
		Optional<UsuarioEntity> usuario = Optional.of(usuarioRepository.findById(id)
				.orElseThrow(() -> new DadosNaoEncontradosException("O Usuario não foi encontrado!")));
		SoftwareVO software = new SoftwareVO();
		software.setSoftwareDesejado(softwareVO.getSoftwareDesejado());
		software.setValorSoftware(softwareVO.getValorSoftware());
		software.setDesenvolvedor(softwareVO.getDesenvolvedor());
		software.setDataEntrega(softwareVO.getDataEntrega());
		software.setUsuarioEntity(usuario.get());
		software.setDataPedido(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
		logger.info("O SOFTWARE DO USUARIO: {}! FOI CADASTRADO COM SUCESSO!", usuario.get().getNome());
		softwareRepository.save(converterSoftwareVOParaSoftwareEntity(software));
	}

	public void deletarSoftware(Long id) {
		logger.info("DELETANDO SOFTWARE");
		buscarSoftwarePorId(id);
		softwareRepository.deleteById(id);
		logger.info("SOFTWARE COM ID: {}! DELETADO COM SUCESSO!", id);
	}

	public SoftwareVO atualizarSoftware(Long id, SoftwareVO software) {
		logger.info("ATUALIZANDO SOFTWARE");
		Optional<SoftwareEntity> softwareEntity = Optional.of(softwareRepository.findById(id)
				.orElseThrow(() -> new DadosNaoEncontradosException("Software não foi encontrado!")));
		SoftwareEntity entity = softwareEntity.get();
		entity.setSoftwareDesejado(software.getSoftwareDesejado());
		entity.setValorSoftware(software.getValorSoftware());
		entity.setDesenvolvedor(software.getDesenvolvedor());
		entity.setDataEntrega(software.getDataEntrega());
		SoftwareEntity atualizarSoftware = softwareRepository.save(softwareEntity.get());
		logger.info("SOFTWARE ATUALIZADO COM SUCESSO!");
		return converterSoftwareEntityParaSoftwareVO(atualizarSoftware);
	}
}
