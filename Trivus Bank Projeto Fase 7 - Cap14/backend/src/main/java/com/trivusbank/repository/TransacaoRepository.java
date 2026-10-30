package com.trivusbank.repository;

import com.trivusbank.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByUsuarioId(Long usuarioId);
    
    @Query("SELECT t FROM Transacao t WHERE t.usuario.id = :usuarioId AND t.ativo = true")
    List<Transacao> findByUsuarioIdAtivas(@Param("usuarioId") Long usuarioId);
    
    List<Transacao> findByTipo(String tipo);
}
