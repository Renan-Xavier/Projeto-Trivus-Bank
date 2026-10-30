import React, { useState, useEffect } from 'react';
import { categoriaAPI } from '../../services/api';
import './CategoriasPage.css';

function CategoriasPage() {
  const [categorias, setCategorias] = useState([]);
  const [formData, setFormData] = useState({
    nome: '',
    descricao: ''
  });
  const [editandoId, setEditandoId] = useState(null);
  const [carregando, setCarregando] = useState(true);
  const [erro, setErro] = useState('');
  const [sucesso, setSucesso] = useState('');

  useEffect(() => {
    carregarCategorias();
  }, []);

  const carregarCategorias = async () => {
    try {
      setCarregando(true);
      const response = await categoriaAPI.listarTodos();
      setCategorias(response.data.filter(c => c.ativo));
      setErro('');
    } catch (error) {
      setErro('Erro ao carregar categorias');
      console.error(error);
    } finally {
      setCarregando(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setErro('');
    setSucesso('');

    try {
      if (editandoId) {
        await categoriaAPI.atualizar(editandoId, formData);
        setSucesso('Categoria atualizada com sucesso!');
      } else {
        await categoriaAPI.criar(formData);
        setSucesso('Categoria criada com sucesso!');
      }

      setFormData({ nome: '', descricao: '' });
      setEditandoId(null);
      carregarCategorias();
    } catch (error) {
      setErro(error.response?.data?.message || 'Erro ao processar categoria');
    }
  };

  const handleEdit = (categoria) => {
    setFormData({
      nome: categoria.nome,
      descricao: categoria.descricao || ''
    });
    setEditandoId(categoria.id);
  };

  const handleDelete = async (id) => {
    if (window.confirm('Tem certeza que deseja deletar esta categoria?')) {
      try {
        await categoriaAPI.deletar(id);
        setSucesso('Categoria deletada com sucesso!');
        carregarCategorias();
      } catch (error) {
        setErro('Erro ao deletar categoria');
      }
    }
  };

  const handleCancel = () => {
    setFormData({ nome: '', descricao: '' });
    setEditandoId(null);
  };

  return (
    <div className="categorias-container">
      <h1>Gerenciamento de Categorias</h1>

      <div className="form-section">
        <h2>{editandoId ? 'Editar Categoria' : 'Nova Categoria'}</h2>
        {erro && <div className="alert alert-error">{erro}</div>}
        {sucesso && <div className="alert alert-success">{sucesso}</div>}

        <form onSubmit={handleSubmit} className="form">
          <div className="form-row">
            <div className="form-group">
              <label>Nome:</label>
              <input
                type="text"
                value={formData.nome}
                onChange={(e) => setFormData({ ...formData, nome: e.target.value })}
                required
              />
            </div>
          </div>

          <div className="form-row">
            <div className="form-group">
              <label>Descrição:</label>
              <textarea
                value={formData.descricao}
                onChange={(e) => setFormData({ ...formData, descricao: e.target.value })}
                rows="4"
              />
            </div>
          </div>

          <div className="form-buttons">
            <button type="submit" className="btn btn-primary">
              {editandoId ? 'Atualizar' : 'Criar'}
            </button>
            {editandoId && (
              <button type="button" onClick={handleCancel} className="btn btn-secondary">
                Cancelar
              </button>
            )}
          </div>
        </form>
      </div>

      <div className="list-section">
        <h2>Lista de Categorias</h2>
        {carregando ? (
          <p>Carregando categorias...</p>
        ) : categorias.length === 0 ? (
          <p className="no-data">Nenhuma categoria encontrada</p>
        ) : (
          <table className="data-table">
            <thead>
              <tr>
                <th>Nome</th>
                <th>Descrição</th>
                <th>Ações</th>
              </tr>
            </thead>
            <tbody>
              {categorias.map((categoria) => (
                <tr key={categoria.id}>
                  <td>{categoria.nome}</td>
                  <td>{categoria.descricao || '-'}</td>
                  <td className="actions">
                    <button
                      onClick={() => handleEdit(categoria)}
                      className="btn btn-small btn-info"
                    >
                      Editar
                    </button>
                    <button
                      onClick={() => handleDelete(categoria.id)}
                      className="btn btn-small btn-danger"
                    >
                      Deletar
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}

export default CategoriasPage;
