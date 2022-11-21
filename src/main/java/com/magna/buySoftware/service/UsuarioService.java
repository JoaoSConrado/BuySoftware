package com.magna.buySoftware.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.magna.buySoftware.entity.UsuarioEntity;
import com.magna.buySoftware.repository.UsuarioRepository;
import com.magna.buySoftware.vo.UsuarioVO;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public UsuarioEntity converterUsuarioVOParaUsuarioEntity(UsuarioVO usuarioVO) {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setId(usuarioVO.getId());
		usuarioEntity.setNome(usuarioVO.getNome());
		usuarioEntity.setCpf(usuarioVO.getCpf());
		usuarioEntity.setEmail(usuarioVO.getEmail());
		usuarioEntity.setSenha(usuarioVO.getSenha());
		usuarioEntity.setSoftwares(usuarioVO.getSoftwares());	
		return usuarioEntity;
	}

	public UsuarioVO converterUsuarioEntityParaUsuarioVO(UsuarioEntity usuarioEntity) {
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setId(usuarioEntity.getId());
		usuarioVO.setNome(usuarioEntity.getNome());
		usuarioVO.setCpf(usuarioEntity.getCpf());
		usuarioVO.setEmail(usuarioEntity.getEmail());
		usuarioVO.setSenha(usuarioEntity.getSenha());
		usuarioVO.setSoftwares(usuarioEntity.getSoftwares());	
		return usuarioVO;
	}

	public Page<UsuarioVO> buscarTodosUsuarios(Pageable pageable) {
		return usuarioRepository.findAll(pageable).map(this::converterUsuarioEntityParaUsuarioVO);
	}
	
	public UsuarioVO buscarUsuarioPorId(Long id) {
		Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
		UsuarioEntity usuarioEntity = usuario.get();
		return this.converterUsuarioEntityParaUsuarioVO(usuarioEntity);
	}
	
	public UsuarioVO salvarUsuario(UsuarioVO usuarioVO) {
		UsuarioEntity salvarUsuario = usuarioRepository.save(converterUsuarioVOParaUsuarioEntity(usuarioVO));
		return this.converterUsuarioEntityParaUsuarioVO(salvarUsuario);
	}
	
	public void deletarUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}

	public UsuarioVO atualizarUsuario(Long id, UsuarioVO usuario) {
		Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);
		UsuarioEntity entity = usuarioEntity.get();
		entity.setNome(usuario.getNome());
		entity.setCpf(usuario.getCpf());
		entity.setEmail(usuario.getEmail());
		entity.setSenha(usuario.getSenha());
		UsuarioEntity atualizarUsuario = usuarioRepository.save(usuarioEntity.get());
		return converterUsuarioEntityParaUsuarioVO(atualizarUsuario);
	}
}

