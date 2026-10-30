package com.trivusbank.service;

import com.trivusbank.model.Transacao;
import com.trivusbank.model.Usuario;
import com.trivusbank.model.Categoria;
import com.trivusbank.repository.TransacaoRepository;
import com.trivusbank.repository.UsuarioRepository;
import com.trivusbank.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Transacao criar(Transacao transacao) {
        Usuario usuario = usuarioRepository.findById(transacao.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Categoria categoria = categoriaRepository.findById(transacao.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        if (!transacao.getTipo().equals("ENTRADA") && !transacao.getTipo().equals("SAIDA")) {
            throw new RuntimeException("Tipo de transação inválido. Use ENTRADA ou SAIDA");
        }

        if (transacao.getTipo().equals("SAIDA")) {
            if (usuario.getSaldo().compareTo(transacao.getValor()) < 0) {
                throw new RuntimeException("Saldo insuficiente para realizar a transação");
            }
            usuario.setSaldo(usuario.getSaldo().subtract(transacao.getValor()));
        } else {
            usuario.setSaldo(usuario.getSaldo().add(transacao.getValor()));
        }

        usuarioRepository.save(usuario);
        transacao.setUsuario(usuario);
        transacao.setCategoria(categoria);

        return transacaoRepository.save(transacao);
    }

    public Transacao atualizar(Long id, Transacao transacaoAtualizada) {
        Transacao transacaoExistente = transacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));

        Usuario usuario = transacaoExistente.getUsuario();

        if (!transacaoAtualizada.getTipo().equals(transacaoExistente.getTipo()) ||
            !transacaoAtualizada.getValor().equals(transacaoExistente.getValor())) {

            if (transacaoExistente.getTipo().equals("SAIDA")) {
                usuario.setSaldo(usuario.getSaldo().add(transacaoExistente.getValor()));
            } else {
                usuario.setSaldo(usuario.getSaldo().subtract(transacaoExistente.getValor()));
            }

            if (transacaoAtualizada.getTipo().equals("SAIDA")) {
                if (usuario.getSaldo().compareTo(transacaoAtualizada.getValor()) < 0) {
                    throw new RuntimeException("Saldo insuficiente para realizar a transação");
                }
                usuario.setSaldo(usuario.getSaldo().subtract(transacaoAtualizada.getValor()));
            } else {
                usuario.setSaldo(usuario.getSaldo().add(transacaoAtualizada.getValor()));
            }

            usuarioRepository.save(usuario);
        }

        transacaoExistente.setTipo(transacaoAtualizada.getTipo());
        transacaoExistente.setValor(transacaoAtualizada.getValor());
        transacaoExistente.setDescricao(transacaoAtualizada.getDescricao());

        return transacaoRepository.save(transacaoExistente);
    }

    public Transacao obterPorId(Long id) {
        return transacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));
    }

    public List<Transacao> listarTodas() {
        return transacaoRepository.findAll();
    }

    public List<Transacao> listarPorUsuario(Long usuarioId) {
        return transacaoRepository.findByUsuarioIdAtivas(usuarioId);
    }

    public void deletar(Long id) {
        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));

        Usuario usuario = transacao.getUsuario();

        if (transacao.getTipo().equals("SAIDA")) {
            usuario.setSaldo(usuario.getSaldo().add(transacao.getValor()));
        } else {
            usuario.setSaldo(usuario.getSaldo().subtract(transacao.getValor()));
        }

        transacao.setAtivo(false);
        usuarioRepository.save(usuario);
        transacaoRepository.save(transacao);
    }

    public List<Transacao> listarPorTipo(String tipo) {
        return transacaoRepository.findByTipo(tipo);
    }
}
