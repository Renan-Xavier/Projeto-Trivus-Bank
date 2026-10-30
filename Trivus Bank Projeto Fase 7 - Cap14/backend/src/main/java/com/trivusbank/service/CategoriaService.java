package com.trivusbank.service;

import com.trivusbank.model.Categoria;
import com.trivusbank.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria criar(Categoria categoria) {
        if (categoriaRepository.findByNome(categoria.getNome()).isPresent()) {
            throw new RuntimeException("Categoria com este nome já existe");
        }

        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Long id, Categoria categoriaAtualizada) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        if (!categoriaExistente.getNome().equals(categoriaAtualizada.getNome()) &&
            categoriaRepository.findByNome(categoriaAtualizada.getNome()).isPresent()) {
            throw new RuntimeException("Categoria com este nome já existe");
        }

        categoriaExistente.setNome(categoriaAtualizada.getNome());
        categoriaExistente.setDescricao(categoriaAtualizada.getDescricao());

        return categoriaRepository.save(categoriaExistente);
    }

    public Categoria obterPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    public void deletar(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        categoria.setAtivo(false);
        categoriaRepository.save(categoria);
    }
}
