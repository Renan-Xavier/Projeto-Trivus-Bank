import React, { useState, useEffect } from 'react';
import { transacaoAPI, usuarioAPI, categoriaAPI } from '../../services/api';
import './TransacoesPage.css';

function TransacoesPage({ currentUser }) {
  const [transacoes, setTransacoes] = useState([]);
  const [usuarios, setUsuarios] = useState([]);
  const [categorias, setCategorias] = useState([]);
  const [formData, setFormData] = useState({
    usuarioId: currentUser?.id || '',
    categoriaId: '',
    tipo: 'ENTRADA',
    valor: '',
    descricao: ''
  });
  const [editandoId, setEditandoId] = useState(null);
  const [carregando, setCarregando] = useState(true);
  const [erro, setErro] = useState('');
  const [sucesso, setSucesso] = useState('');

  useEffect(() => {
    carregarDados();
  }, []);

  const carregarDados = async () => {
    try {
      setCarregando(true);
      const [transacoesRes, usuariosRes, categoriasRes] = await Promise.all([
        transacaoAPI.listarTodas(),
        usuarioAPI.listarTodos(),
        categoriaAPI.listarTodos()
      ]);

      setTransacoes(transacoesRes.data.filter(t => t.ativo));
      setUsuarios(usuariosRes.data.filter(u => u.ativo));
      setCategorias(categoriasRes.data.filter(c => c.ativo));
      setErro('');
    } catch (error) {
      setErro('Erro ao carregar dados');
      console.error(error);
    } finally {
      setCarregando(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setErro('');
    setSucesso('');

    const data = {
      usuario: { id: parseInt(formData.usuarioId) },
      categoria: { id: parseInt(formData.categoriaId) },
      tipo: formData.tipo,
      valor: parseFloat(formData.valor),
      descricao: formData.descricao
    };

    try {
      if (editandoId) {
        await transacaoAPI.atualizar(editandoId, data);
        setSucesso('Transação atualizada com sucesso!');
      } else {
        await transacaoAPI.criar(data);
        setSucesso('Transação criada com sucesso!');
      }

      setFormData({
        usuarioId: currentUser?.id || '',
        categoriaId: '',
        tipo: 'ENTRADA',
        valor: '',
        descricao: ''
      });
      setEditandoId(null);
      carregarDados();
    } catch (error) {
      setErro(error.response?.data?.message || 'Erro ao processar transação');
    }
  };

  const handleEdit = (transacao) => {
    setFormData({
      usuarioId: transacao.usuario.id,
      categoriaId: transacao.categoria.id,
      tipo: transacao.tipo,
      valor: transacao.valor,
      descricao: transacao.descricao || ''
    });
    setEditandoId(transacao.id);
  };

  const handleDelete = async (id) => {
    if (window.confirm('Tem certeza que deseja deletar esta transação?')) {
      try {
        await transacaoAPI.deletar(id);
        setSucesso('Transação deletada com sucesso!');
        carregarDados();
      } catch (error) {
        setErro('Erro ao deletar transação');
      }
    }
  };

  const handleCancel = () => {
    setFormData({
      usuarioId: currentUser?.id || '',
      categoriaId: '',
      tipo: 'ENTRADA',
      valor: '',
      descricao: ''
    });
    setEditandoId(null);
  };

  const formatarMoeda = (valor) => {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    }).format(valor);
  };

  const formatarData = (dataStr) => {
    return new Date(dataStr).toLocaleDateString('pt-BR', {
      year: 'numeric',
      month: '2-digit',
      day: '2-digit'
    });
  };

  return (
    <div className="transacoes-container">
      <h1>Gerenciamento de Transações</h1>

      <div className="form-section">
        <h2>{editandoId ? 'Editar Transação' : 'Nova Transação'}</h2>
        {erro && <div className="alert alert-error">{erro}</div>}
        {sucesso && <div className="alert alert-success">{sucesso}</div>}

        <form onSubmit={handleSubmit} className="form">
          <div className="form-row">
            <div className="form-group">
              <label>Usuário:</label>
              <select
                value={formData.usuarioId}
                onChange={(e) => setFormData({ ...formData, usuarioId: e.target.value })}
                required
              >
                <option value="">Selecione um usuário</option>
                {usuarios.map((usuario) => (
                  <option key={usuario.id} value={usuario.id}>
                    {usuario.nome} - {formatarMoeda(usuario.saldo)}
                  </option>
                ))}
              </select>
            </div>

            <div className="form-group">
              <label>Categoria:</label>
              <select
                value={formData.categoriaId}
                onChange={(e) => setFormData({ ...formData, categoriaId: e.target.value })}
                required
              >
                <option value="">Selecione uma categoria</option>
                {categorias.map((categoria) => (
                  <option key={categoria.id} value={categoria.id}>
                    {categoria.nome}
                  </option>
                ))}
              </select>
            </div>
          </div>

          <div className="form-row">
            <div className="form-group">
              <label>Tipo:</label>
              <select
                value={formData.tipo}
                onChange={(e) => setFormData({ ...formData, tipo: e.target.value })}
                required
              >
                <option value="ENTRADA">Entrada</option>
                <option value="SAIDA">Saída</option>
              </select>
            </div>

            <div className="form-group">
              <label>Valor:</label>
              <input
                type="number"
                value={formData.valor}
                onChange={(e) => setFormData({ ...formData, valor: e.target.value })}
                step="0.01"
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
                rows="3"
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
        <h2>Lista de Transações</h2>
        {carregando ? (
          <p>Carregando transações...</p>
        ) : transacoes.length === 0 ? (
          <p className="no-data">Nenhuma transação encontrada</p>
        ) : (
          <table className="data-table">
            <thead>
              <tr>
                <th>Data</th>
                <th>Usuário</th>
                <th>Categoria</th>
                <th>Tipo</th>
                <th>Valor</th>
                <th>Descrição</th>
                <th>Ações</th>
              </tr>
            </thead>
            <tbody>
              {transacoes.map((transacao) => (
                <tr key={transacao.id}>
                  <td>{formatarData(transacao.dataTransacao)}</td>
                  <td>{transacao.usuario.nome}</td>
                  <td>{transacao.categoria.nome}</td>
                  <td>
                    <span className={`badge badge-${transacao.tipo.toLowerCase()}`}>
                      {transacao.tipo}
                    </span>
                  </td>
                  <td className={transacao.tipo === 'ENTRADA' ? 'valor-entrada' : 'valor-saida'}>
                    {transacao.tipo === 'ENTRADA' ? '+' : '-'}{formatarMoeda(transacao.valor)}
                  </td>
                  <td>{transacao.descricao || '-'}</td>
                  <td className="actions">
                    <button
                      onClick={() => handleEdit(transacao)}
                      className="btn btn-small btn-info"
                    >
                      Editar
                    </button>
                    <button
                      onClick={() => handleDelete(transacao.id)}
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

export default TransacoesPage;
