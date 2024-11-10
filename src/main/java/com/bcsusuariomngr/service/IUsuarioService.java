package com.bcsusuariomngr.service;

import com.bcsusuariomngr.entity.Usuario;

public interface IUsuarioService {
    Usuario login(String tipoDocumento, String numeroDocumento, String contraseña);
}
