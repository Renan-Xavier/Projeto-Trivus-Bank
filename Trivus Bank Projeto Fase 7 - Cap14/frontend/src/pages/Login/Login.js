import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { usuarioAPI } from '../../services/api';
import './Login.css';

function Login({ onLogin }) {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [erro, setErro] = useState('');
  const [carregando, setCarregando] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setErro('');
    setCarregando(true);

    try {
      const response = await usuarioAPI.obterPorEmail(email);
      const user = response.data;

      if (user.senha !== senha) {
        setErro('Email ou senha incorretos');
        setCarregando(false);
        return;
      }

      onLogin(user);
      navigate('/');
    } catch (error) {
      setErro('Email ou senha incorretos');
      setCarregando(false);
    }
  };

  return (
    <div className="login-container">
      <div className="login-box">
        <h1 className="login-title">🏦 Trivus Bank</h1>
        <p className="login-subtitle">Seu gerenciador financeiro</p>

        <form onSubmit={handleSubmit}>
          {erro && <div className="alert alert-error">{erro}</div>}

          <div className="form-group">
            <label htmlFor="email">Email:</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              placeholder="seu@email.com"
              disabled={carregando}
            />
          </div>

          <div className="form-group">
            <label htmlFor="senha">Senha:</label>
            <input
              type="password"
              id="senha"
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
              required
              placeholder="••••••••"
              disabled={carregando}
            />
          </div>

          <button type="submit" className="login-btn" disabled={carregando}>
            {carregando ? 'Autenticando...' : 'Entrar'}
          </button>
        </form>

        <div className="login-info">
          <p><strong>Credenciais de Teste:</strong></p>
          <p>Email: <code>usuario@trivus.com</code></p>
          <p>Senha: <code>123456</code></p>
        </div>
      </div>
    </div>
  );
}

export default Login;
