package com.trivusbank.controller;

import com.trivusbank.model.Transacao;
import com.trivusbank.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
@CrossOrigin(origins = "http://localhost:3000")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public ResponseEntity<List<Transacao>> listarTodas() {
        List<Transacao> transacoes = transacaoService.listarTodas();
        return ResponseEntity.ok(transacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transacao> obterPorId(@PathVariable Long id) {
        try {
            Transacao transacao = transacaoService.obterPorId(id);
            return ResponseEntity.ok(transacao);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Transacao>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<Transacao> transacoes = transacaoService.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(transacoes);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Transacao>> listarPorTipo(@PathVariable String tipo) {
        List<Transacao> transacoes = transacaoService.listarPorTipo(tipo);
        return ResponseEntity.ok(transacoes);
    }

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody Transacao transacao) {
        try {
            Transacao novaTransacao = transacaoService.criar(transacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaTransacao);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Transacao transacaoAtualizada) {
        try {
            Transacao transacao = transacaoService.atualizar(id, transacaoAtualizada);
            return ResponseEntity.ok(transacao);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            transacaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public static class ErrorResponse {
        public String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
