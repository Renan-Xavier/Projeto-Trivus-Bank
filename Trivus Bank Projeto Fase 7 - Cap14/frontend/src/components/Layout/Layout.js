import React from 'react';
import { Outlet, Link, useNavigate } from 'react-router-dom';
import './Layout.css';

function Layout({ currentUser, onLogout }) {
  const navigate = useNavigate();

  const handleLogout = () => {
    onLogout();
    navigate('/login');
  };

  return (
    <div className="layout">
      <nav className="navbar">
        <div className="navbar-container">
          <Link to="/" className="navbar-logo">
            <span className="logo-icon-wrapper">
              <svg className="logo-icon" viewBox="0 0 64 64" xmlns="http://www.w3.org/2000/svg" aria-hidden="true">
                <polygon points="32,6 6,58 58,58" fill="rgba(255,255,255,0.2)" />
                <polygon points="32,14 14,50 50,50" fill="rgba(255,255,255,0.5)" />
                <polygon points="32,22 22,42 42,42" fill="#fff" />
              </svg>
            </span>
            <span className="logo-text">Trivus Bank</span>
          </Link>
          <div className="nav-menu">
            <Link to="/" className="nav-link">Home</Link>
            <Link to="/categorias" className="nav-link">Categorias</Link>
            <Link to="/transacoes" className="nav-link">Transações</Link>
          </div>
          <div className="navbar-right">
            <span className="user-info">
              {currentUser && currentUser.nome}
            </span>
            <button onClick={handleLogout} className="logout-btn">
              Sair
            </button>
          </div>
        </div>
      </nav>

      <main className="main-content">
        <Outlet />
      </main>

      <footer className="footer">
        <p>&copy; 2024 Trivus Bank. Todos os direitos reservados.</p>
      </footer>
    </div>
  );
}

export default Layout;
