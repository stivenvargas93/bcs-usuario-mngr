package com.bcsusuariomngr.service.impl;

import com.bcsusuariomngr.entity.Usuario;
import com.bcsusuariomngr.repository.UsuarioRepository;
import com.bcsusuariomngr.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Usuario login(String tipoDocumento, String numeroDocumento, String contraseña) {
        Usuario usuario = usuarioRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento);
        if (usuario != null && bCryptPasswordEncoder.matches(contraseña, usuario.getContraseña())) {
            return usuario;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
