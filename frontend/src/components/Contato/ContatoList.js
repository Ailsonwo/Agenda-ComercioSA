import React, { useState, useEffect } from 'react';
import { getContatosByClienteCpf, deleteContato } from '../../api/contatoApi';

const ContatoList = ({ cliente_cpf, onEdit }) => {
  const [contatos, setContatos] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (cliente_cpf) {
      loadContatos(cliente_cpf);
      console.log("CONTATOS PEGADOS: ", contatos)
    }
  }, [cliente_cpf]);

  const loadContatos = async (cpf) => {
    setLoading(true);
    try {
      const data = await getContatosByClienteCpf(cpf);
      setContatos(data.data);
    } catch (error) {
      console.error('Erro ao carregar contatos:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Tem certeza que deseja excluir este contato?')) {
      try {
        await deleteContato(id);
        loadContatos(cliente_cpf);
      } catch (error) {
        console.error('Erro ao excluir contato:', error);
        alert(error.message || 'Erro ao excluir contato');
      }
    }
  };

  return (
    <div>
      <h3>Contatos do Cliente</h3>

      {loading ? (
        <p>Carregando...</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>Tipo</th>
              <th>Valor</th>
              <th>Observação</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {contatos.map(contato => (
              <tr key={contato.id}>
                <td>{contato.tipo}</td>
                <td>{contato.valor}</td>
                <td>{contato.observacao}</td>
                <td>
                  <button onClick={() => onEdit(contato.id)}>Editar</button>
                  <button onClick={() => handleDelete(contato.id)}>Excluir</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default ContatoList;