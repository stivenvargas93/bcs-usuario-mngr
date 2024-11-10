package com.bcsusuariomngr.repository;

import com.bcsusuariomngr.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM usuario u WHERE u.tipoDocumento = ?1 AND u.numeroDocumento = ?2")
    Usuario findByUsuarioLogin(String tipoDocumento, String numeroDocumento);

    Usuario findByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);
}
