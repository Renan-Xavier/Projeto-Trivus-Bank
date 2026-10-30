import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json'
  }
});

api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('user');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export const usuarioAPI = {
  listarTodos: () => api.get('/usuarios'),
  obterPorId: (id) => api.get(`/usuarios/${id}`),
  obterPorEmail: (email) => api.get(`/usuarios/email/${email}`),
  criar: (usuario) => api.post('/usuarios', usuario),
  atualizar: (id, usuario) => api.put(`/usuarios/${id}`, usuario),
  deletar: (id) => api.delete(`/usuarios/${id}`)
};

export const categoriaAPI = {
  listarTodos: () => api.get('/categorias'),
  obterPorId: (id) => api.get(`/categorias/${id}`),
  criar: (categoria) => api.post('/categorias', categoria),
  atualizar: (id, categoria) => api.put(`/categorias/${id}`, categoria),
  deletar: (id) => api.delete(`/categorias/${id}`)
};

export const transacaoAPI = {
  listarTodas: () => api.get('/transacoes'),
  obterPorId: (id) => api.get(`/transacoes/${id}`),
  listarPorUsuario: (usuarioId) => api.get(`/transacoes/usuario/${usuarioId}`),
  listarPorTipo: (tipo) => api.get(`/transacoes/tipo/${tipo}`),
  criar: (transacao) => api.post('/transacoes', transacao),
  atualizar: (id, transacao) => api.put(`/transacoes/${id}`, transacao),
  deletar: (id) => api.delete(`/transacoes/${id}`)
};

export default api;
