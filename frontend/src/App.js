import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ClientesPage from './pages/ClientesPage';
import ContatosPage from './pages/ContatosPage';
import './styles/global.css';

function App() {
  return (
    <Router>
      <div className="app">
        <Routes>
          <Route path="/" element={<ClientesPage />} />
          <Route path="/contatos/:clienteCpf" element={<ContatosPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;