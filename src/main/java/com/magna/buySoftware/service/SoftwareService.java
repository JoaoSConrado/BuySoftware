package com.magna.buySoftware.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

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
		return softwareRepository.findAll(pageable).map(this::converterSoftwareEntityParaSoftwareVO);
	}

	public SoftwareVO buscarSoftwarePorId(Long id) {
		Optional<SoftwareEntity> software = Optional.of(softwareRepository.findById(id)
				.orElseThrow(() -> new DadosNaoEncontradosException("Software não foi encontrado!")));
		SoftwareEntity softwareEntity = software.get();
		return this.converterSoftwareEntityParaSoftwareVO(softwareEntity);
	}

	public void salvarSoftware(Long id, SoftwareVO softwareVO) {
		Optional<UsuarioEntity> usuario = Optional.of(usuarioRepository.findById(id)
				.orElseThrow(() -> new DadosNaoEncontradosException("O Usuario não foi encontrado!")));
		SoftwareVO software = new SoftwareVO();
		software.setSoftwareDesejado(softwareVO.getSoftwareDesejado());
		software.setValorSoftware(softwareVO.getValorSoftware());
		software.setDesenvolvedor(softwareVO.getDesenvolvedor());
		software.setDataEntrega(softwareVO.getDataEntrega());
		software.setUsuarioEntity(usuario.get());
		software.setDataPedido(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
		softwareRepository.save(converterSoftwareVOParaSoftwareEntity(software));
	}

	public void deletarSoftware(Long id) {
		buscarSoftwarePorId(id);
		softwareRepository.deleteById(id);
	}

	public SoftwareVO atualizarSoftware(Long id, SoftwareVO software) {
		Optional<SoftwareEntity> softwareEntity = Optional.of(softwareRepository.findById(id)
				.orElseThrow(() -> new DadosNaoEncontradosException("Software não foi encontrado!")));
		SoftwareEntity entity = softwareEntity.get();
		entity.setSoftwareDesejado(software.getSoftwareDesejado());
		entity.setValorSoftware(software.getValorSoftware());
		entity.setDesenvolvedor(software.getDesenvolvedor());
		entity.setDataEntrega(software.getDataEntrega());
		SoftwareEntity atualizarSoftware = softwareRepository.save(softwareEntity.get());
		return converterSoftwareEntityParaSoftwareVO(atualizarSoftware);
	}
}
