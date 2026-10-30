import React from 'react';
import { Link } from 'react-router-dom';
import './NotFound.css';

function NotFound() {
  return (
    <div className="notfound-container">
      <div className="notfound-content">
        <h1 className="notfound-code">404</h1>
        <h2 className="notfound-title">Página não encontrada</h2>
        <p className="notfound-message">
          Desculpe, a página que você está procurando não existe.
        </p>
        <Link to="/" className="notfound-link">
          Voltar à página inicial
        </Link>
      </div>
    </div>
  );
}

export default NotFound;
