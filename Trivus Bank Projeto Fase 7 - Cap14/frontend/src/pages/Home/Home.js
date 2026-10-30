import React, { useState, useEffect } from 'react';
import { transacaoAPI } from '../../services/api';
import './Home.css';

function Home({ currentUser }) {
  const [transacoes, setTransacoes] = useState([]);
  const [resumo, setResumo] = useState({
    totalEntrada: 0,
    totalSaida: 0,
    saldo: currentUser?.saldo || 0
  });
  const [carregando, setCarregando] = useState(true);

  useEffect(() => {
    carregarTransacoes();
  }, []);

  const carregarTransacoes = async () => {
    try {
      const response = await transacaoAPI.listarPorUsuario(currentUser.id);
      setTransacoes(response.data);

      let totalEntrada = 0;
      let totalSaida = 0;

      response.data.forEach(transacao => {
        if (transacao.tipo === 'ENTRADA') {
          totalEntrada += transacao.valor;
        } else {
          totalSaida += transacao.valor;
        }
      });

      setResumo({
        totalEntrada,
        totalSaida,
        saldo: currentUser?.saldo || 0
      });
    } catch (error) {
      console.error('Erro ao carregar transações:', error);
    } finally {
      setCarregando(false);
    }
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
    <div className="home-container">
      <h1>Bem-vindo, {currentUser?.nome}! 👋</h1>

      <div className="dashboard">
        <div className="card card-balance">
          <h3>Saldo Atual</h3>
          <p className="balance-amount">{formatarMoeda(resumo.saldo)}</p>
        </div>

        <div className="card card-income">
          <h3>Total de Entradas</h3>
          <p className="income-amount">{formatarMoeda(resumo.totalEntrada)}</p>
        </div>

        <div className="card card-expense">
          <h3>Total de Saídas</h3>
          <p className="expense-amount">{formatarMoeda(resumo.totalSaida)}</p>
        </div>
      </div>

      <div className="transactions-section">
        <h2>Últimas Transações</h2>
        {carregando ? (
          <p style={{color: '#999'}}>Carregando transações...</p>
        ) : transacoes.length === 0 ? (
          <p className="no-data">Nenhuma transação realizada ainda.</p>
        ) : (
          <div className="transactions-list">
            {transacoes.slice(0, 10).map((transacao) => (
              <div key={transacao.id} className="transaction-item">
                <div className="transaction-info">
                  <div className="transaction-type">{transacao.tipo}</div>
                  <div className="transaction-category">{transacao.categoria?.nome || 'Sem categoria'}</div>
                  <div className="transaction-date">{formatarData(transacao.dataTransacao)}</div>
                </div>
                <div className={`transaction-amount ${transacao.tipo === 'ENTRADA' ? 'entrada' : 'saida'}`}>
                  {transacao.tipo === 'ENTRADA' ? '+' : '-'} {formatarMoeda(transacao.valor)}
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default Home;
