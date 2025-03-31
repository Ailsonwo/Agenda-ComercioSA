import React, { useState, useEffect } from 'react';
import { createContato, updateContato, getContato } from '../../api/contatoApi';

const ContatoForm = ({ cliente_cpf, contatoId, onSave, onCancel }) => {
  const [contato, setContato] = useState({
    tipo: 'TELEFONE',
    valor: '',
    observacao: '',
    cliente_cpf: cliente_cpf
  });
  const [errors, setErrors] = useState({});

  useEffect(() => {
    if (contatoId) {
      loadContato(contatoId);
    }
  }, [contatoId]);

  const loadContato = async (id) => {
    try {
      const data = await getContato(id);
      setContato({
        ...data.data,
        cliente_cpf: data.data.cliente.cpf
      });
    } catch (error) {
      console.error('Erro ao carregar contato:', error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setContato(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validação
    const newErrors = {};
    if (!contato.tipo) newErrors.tipo = 'Tipo é obrigatório';
    if (!contato.valor) newErrors.valor = 'Valor é obrigatório';
    if (!contato.cliente_cpf) newErrors.cliente_cpf = 'Cliente é obrigatório';

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      return;
    }

    try {
      if (contatoId) {
        await updateContato({
          ...contato,
          id: contatoId
        });
      } else {
        await createContato(contato);
      }

      onSave();
    } catch (error) {
      console.error('Erro ao salvar contato:', error);
      alert(error.message || 'Erro ao salvar contato');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input type="hidden" name="cliente_cpf" value={contato.cliente_cpf} />

      <div>
        <label>Tipo:</label>
        <select
          name="tipo"
          value={contato.tipo}
          onChange={handleChange}
        >
          <option value="TELEFONE">Telefone</option>
          <option value="EMAIL">Email</option>
        </select>
        {errors.tipo && <span className="error">{errors.tipo}</span>}
      </div>

      <div>
        <label>Valor:</label>
        <input
          type="text"
          name="valor"
          value={contato.valor}
          onChange={handleChange}
        />
        {errors.valor && <span className="error">{errors.valor}</span>}
      </div>

      <div>
        <label>Observação:</label>
        <textarea
          name="observacao"
          value={contato.observacao}
          onChange={handleChange}
        />
      </div>

      <button type="submit">Salvar</button>
      <button type="button" onClick={onCancel}>Cancelar</button>
    </form>
  );
};

export default ContatoForm;