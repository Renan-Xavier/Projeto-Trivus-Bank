import React, { useState, useEffect } from 'react';
import { usuarioAPI } from '../../services/api';
import './UsuariosPage.css';

function UsuariosPage() {
  const [usuarios, setUsuarios] = useState([]);
  const [formData, setFormData] = useState({
    nome: '',
    email: '',
    senha: '',
    cpf: ''
  });
  const [editandoId, setEditandoId] = useState(null);
  const [carregando, setCarregando] = useState(true);
  const [erro, setErro] = useState('');
  const [sucesso, setSucesso] = useState('');

  useEffect(() => {
    carregarUsuarios();
  }, []);

  const carregarUsuarios = async () => {
    try {
      setCarregando(true);
      const response = await usuarioAPI.listarTodos();
      setUsuarios(response.data.filter(u => u.ativo));
      setErro('');
    } catch (error) {
      setErro('Erro ao carregar usuários');
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
        await usuarioAPI.atualizar(editandoId, formData);
        setSucesso('Usuário atualizado com sucesso!');
      } else {
        await usuarioAPI.criar(formData);
        setSucesso('Usuário criado com sucesso!');
      }

      setFormData({ nome: '', email: '', senha: '', cpf: '' });
      setEditandoId(null);
      carregarUsuarios();
    } catch (error) {
      setErro(error.response?.data?.message || 'Erro ao processar usuário');
    }
  };

  const handleEdit = (usuario) => {
    setFormData({
      nome: usuario.nome,
      email: usuario.email,
      senha: usuario.senha,
      cpf: usuario.cpf || ''
    });
    setEditandoId(usuario.id);
  };

  const handleDelete = async (id) => {
    if (window.confirm('Tem certeza que deseja deletar este usuário?')) {
      try {
        await usuarioAPI.deletar(id);
        setSucesso('Usuário deletado com sucesso!');
        carregarUsuarios();
      } catch (error) {
        setErro('Erro ao deletar usuário');
      }
    }
  };

  const handleCancel = () => {
    setFormData({ nome: '', email: '', senha: '', cpf: '' });
    setEditandoId(null);
  };

  const formatarMoeda = (valor) => {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    }).format(valor || 0);
  };

  return (
    <div className="usuarios-container">
      <h1>Gerenciamento de Usuários</h1>

      <div className="form-section">
        <h2>{editandoId ? 'Editar Usuário' : 'Novo Usuário'}</h2>
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

            <div className="form-group">
              <label>Email:</label>
              <input
                type="email"
                value={formData.email}
                onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                required
                disabled={!!editandoId}
              />
            </div>
          </div>

          <div className="form-row">
            <div className="form-group">
              <label>Senha:</label>
              <input
                type="password"
                value={formData.senha}
                onChange={(e) => setFormData({ ...formData, senha: e.target.value })}
                required={!editandoId}
              />
            </div>

            <div className="form-group">
              <label>CPF (opcional):</label>
              <input
                type="text"
                value={formData.cpf}
                onChange={(e) => setFormData({ ...formData, cpf: e.target.value })}
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
        <h2>Lista de Usuários</h2>
        {carregando ? (
          <p>Carregando usuários...</p>
        ) : usuarios.length === 0 ? (
          <p className="no-data">Nenhum usuário encontrado</p>
        ) : (
          <table className="data-table">
            <thead>
              <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>CPF</th>
                <th>Saldo</th>
                <th>Ações</th>
              </tr>
            </thead>
            <tbody>
              {usuarios.map((usuario) => (
                <tr key={usuario.id}>
                  <td>{usuario.nome}</td>
                  <td>{usuario.email}</td>
                  <td>{usuario.cpf || '-'}</td>
                  <td>{formatarMoeda(usuario.saldo)}</td>
                  <td className="actions">
                    <button
                      onClick={() => handleEdit(usuario)}
                      className="btn btn-small btn-info"
                    >
                      Editar
                    </button>
                    <button
                      onClick={() => handleDelete(usuario.id)}
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

export default UsuariosPage;
