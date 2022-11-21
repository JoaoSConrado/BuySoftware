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
		softwareEntity.setDataCriacao(softwareVO.getDataCriacao());
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
		softwareVO.setDataCriacao(softwareEntity.getDataCriacao());
		return softwareVO;
	}

	public Page<SoftwareVO> buscarTodosSoftwares(Pageable pageable) {
		return softwareRepository.findAll(pageable).map(this::converterSoftwareEntityParaSoftwareVO);
	}
	
	public SoftwareVO buscarSoftwarePorId(Long id) {
		Optional<SoftwareEntity> software = softwareRepository.findById(id);
		SoftwareEntity softwareEntity = software.get();
		return this.converterSoftwareEntityParaSoftwareVO(softwareEntity);
	}

	public void salvarSoftware(Long usuarioId, SoftwareVO softwareVO) {
		Optional<UsuarioEntity> usuario = usuarioRepository.findById(usuarioId);		
		SoftwareVO software = new SoftwareVO();
		software.setSoftwareDesejado(softwareVO.getSoftwareDesejado());
		software.setValorSoftware(softwareVO.getValorSoftware());
		software.setDesenvolvedor(softwareVO.getDesenvolvedor());
		software.setDataEntrega(softwareVO.getDataEntrega());
		software.setUsuarioEntity(usuario.get());
		software.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
		softwareRepository.save(converterSoftwareVOParaSoftwareEntity(software));
	}
	
	public void deletarSoftware(Long id) {
		softwareRepository.deleteById(id);
	}
	
	public SoftwareVO atualizarSoftware(Long id, SoftwareVO software) {
		Optional<SoftwareEntity> softwareEntity = softwareRepository.findById(id);
		SoftwareEntity entity = softwareEntity.get();
		entity.setSoftwareDesejado(software.getSoftwareDesejado());
		entity.setValorSoftware(software.getValorSoftware());
		entity.setDesenvolvedor(software.getDesenvolvedor());
		entity.setDataEntrega(software.getDataEntrega());
		SoftwareEntity atualizarSoftware = softwareRepository.save(softwareEntity.get());
		return converterSoftwareEntityParaSoftwareVO(atualizarSoftware);
	}
}

