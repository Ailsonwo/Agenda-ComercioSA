import React, { useState } from 'react';
import ContatoList from '../components/Contato/ContatoList';
import ContatoForm from '../components/Contato/ContatoForm';

const ContatosPage = ({ cliente_cpf, onBack }) => {
  const [showForm, setShowForm] = useState(false);
  const [editingId, setEditingId] = useState(null);

  const handleEdit = (id) => {
    setEditingId(id);
    setShowForm(true);
  };

  const handleFormSubmit = () => {
    setShowForm(false);
    setEditingId(null);
  };

  const handleCancel = () => {
    setShowForm(false);
    setEditingId(null);
  };

  return (
    <div>
      <button onClick={onBack}>Voltar para Clientes</button>
      <h2>Contatos</h2>

      {showForm ? (
        <ContatoForm
          cliente_cpf={cliente_cpf}
          contatoId={editingId}
          onSave={handleFormSubmit}
          onCancel={handleCancel}
        />
      ) : (
        <>
          <button onClick={() => setShowForm(true)}>Novo Contato</button>
          <ContatoList
            cliente_cpf={cliente_cpf}
            onEdit={handleEdit}
          />
        </>
      )}
    </div>
  );
};

export default ContatosPage;