import React, { useState } from 'react';
import ClienteList from '../components/Cliente/ClienteList';
import ClienteForm from '../components/Cliente/ClienteForm';
import ContatosPage from './ContatosPage';

const ClientesPage = () => {
  const [showForm, setShowForm] = useState(false);
  const [editingCpf, setEditingCpf] = useState(null);
  const [viewingContatosCpf, setViewingContatosCpf] = useState(null);

  const handleEdit = (cpf) => {
    setEditingCpf(cpf);
    setShowForm(true);
  };

  const handleViewContatos = (cpf) => {
    setViewingContatosCpf(cpf);
  };

  const handleBackToList = () => {
    setViewingContatosCpf(null);
  };

  const handleFormSubmit = () => {
    setShowForm(false);
    setEditingCpf(null);
  };

  const handleCancel = () => {
    setShowForm(false);
    setEditingCpf(null);
  };

  if (viewingContatosCpf) {
    return (
      <ContatosPage
        cliente_cpf={viewingContatosCpf}
        onBack={handleBackToList}
      />
    );
  }

  return (
    <div>
      <h1>Clientes</h1>

      {showForm ? (
        <ClienteForm
          cpf={editingCpf}
          onSave={handleFormSubmit}
          onCancel={handleCancel}
        />
      ) : (
        <>
          <button onClick={() => setShowForm(true)}>Novo Cliente</button>
          <ClienteList
            onEdit={handleEdit}
            onViewContatos={handleViewContatos}
          />
        </>
      )}
    </div>
  );
};

export default ClientesPage;