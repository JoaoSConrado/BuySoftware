package com.magna.buySoftware.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.magna.buySoftware.entity.UsuarioEntity;
import com.magna.buySoftware.exception.DadosNaoEncontradosException;
import com.magna.buySoftware.exception.JaFoiCadastradoException;
import com.magna.buySoftware.repository.UsuarioRepository;
import com.magna.buySoftware.vo.UsuarioVO;

@Service
public class UsuarioService {

	private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);

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
		logger.info("LISTANDO TODOS OS USUARIOS CADASTRADOS");
		return usuarioRepository.findAll(pageable).map(this::converterUsuarioEntityParaUsuarioVO);
	}

	public UsuarioVO buscarUsuarioPorId(Long id) {
		logger.info("BUSCANDO USUARIO POR ID");
		Optional<UsuarioEntity> usuario = Optional.of(usuarioRepository.findById(id)
				.orElseThrow(() -> new DadosNaoEncontradosException("O Usuario não foi encontrado!")));
		UsuarioEntity usuarioEntity = usuario.get();
		logger.info("USUARIO COM O ID: {}! ENCONTRADO COM SUCESSO", usuario.get().getId());
		return this.converterUsuarioEntityParaUsuarioVO(usuarioEntity);
	}

	public UsuarioVO salvarUsuario(UsuarioVO usuarioVO) {
		logger.info("CADASTRANDO USUÁRIO");
		logger.info("VERIFICANDO SE CPF & EMAIL ESTÃO CADASTRADOS");
		verificaCPF(usuarioVO);
		verificaEmail(usuarioVO);
		UsuarioEntity salvarUsuario = usuarioRepository.save(converterUsuarioVOParaUsuarioEntity(usuarioVO));
		logger.info("USUARIO: {}! CADASTRADO COM SUCESSO!", usuarioVO.getNome());
		return this.converterUsuarioEntityParaUsuarioVO(salvarUsuario);
	}

	public void deletarUsuario(Long id) {
		logger.info("DELETANDO USUÁRIO");
		buscarUsuarioPorId(id);
		usuarioRepository.deleteById(id);
		logger.info("USUÁRIO COM ID: {}! DELETADO COM SUCESSO!", id);
	}

	public UsuarioVO atualizarUsuario(Long id, UsuarioVO usuario) {
		logger.info("ATUALIZANDO USUÁRIO");
		Optional<UsuarioEntity> usuarioEntity = Optional.of(usuarioRepository.findById(id)
				.orElseThrow(() -> new DadosNaoEncontradosException("O Usuario não foi encontrado!")));
		UsuarioEntity entity = usuarioEntity.get();
		entity.setNome(usuario.getNome());
		entity.setCpf(usuario.getCpf());
		entity.setEmail(usuario.getEmail());
		entity.setSenha(usuario.getSenha());
		UsuarioEntity atualizarUsuario = usuarioRepository.save(usuarioEntity.get());
		logger.info("USUÁRIO: {}! ATUALIZADO COM SUCESSO!", usuario.getNome());
		return converterUsuarioEntityParaUsuarioVO(atualizarUsuario);
	}

	private void verificaCPF(UsuarioVO usuario) {
		if (usuarioRepository.findByCpf(usuario.getCpf()).isPresent()) {
			logger.info("ERRO NO CADASTRO: CPF JÁ CADASTRADO!");
			throw new JaFoiCadastradoException("Já existe um Usuario cadastrado com esse CPF!");
		}
	}

	private void verificaEmail(UsuarioVO usuario) {
		if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
			logger.info("ERRO NO CADASTRO: EMAIL JÁ CADASTRADO!");
			throw new JaFoiCadastradoException("Já existe um Usuario cadastrado com esse EMAIL!");
		}
	}

}
