package com.bcsusuariomngr.controller;

import com.bcsusuariomngr.entity.Usuario;
import com.bcsusuariomngr.service.IUsuarioService;
import com.bcsusuariomngr.utlis.EncryptionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private EncryptionUtil encryptionUtil;

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody String encryptedData) {
        try {
            String decryptedDataJson = encryptionUtil.decrypt(encryptedData);
            ObjectMapper objectMapper = new ObjectMapper();
            Usuario usuario = objectMapper.readValue(decryptedDataJson, Usuario.class);

            Usuario usuarioLogin = usuarioService.login(usuario.getTipoDocumento(), usuario.getNumeroDocumento(), usuario.getContraseña());
            if (usuarioLogin != null) {
                return new ResponseEntity<>(usuarioLogin, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}