package ar.edu.uai.paradigms.translator.cliente;

import ar.edu.uai.model.cliente.Cliente;
import ar.edu.uai.model.cliente.ClienteCriteria;
import ar.edu.uai.paradigms.dto.cliente.ClienteCriteriaDTO;
import ar.edu.uai.paradigms.dto.cliente.ClienteDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skeith on 20/05/2016.
 */
public class ClienteTranslator
{
    public Cliente translate(ClienteDTO clienteDTO)
    {
        return new Cliente (clienteDTO.getId(),
                clienteDTO.getDni(),
                clienteDTO.getFirst_name(),
                clienteDTO.getLast_name());
    }

    public ClienteDTO translateToDTO(Cliente cliente)
    {
        if (cliente != null)
        {
            return new ClienteDTO( cliente.getId(),
                    cliente.getDni(),
                    cliente.getNombre(),
                    cliente.getApellido());
        }
        return null;
    }

    public List<ClienteDTO> translateToDTO(List<Cliente> clientes) {
        List<ClienteDTO> clienteResponse = new ArrayList<ClienteDTO>();
        for(Cliente i : clientes) {
            ClienteDTO clienteDTO = this.translateToDTO(i);
            if(clienteDTO != null) {
                clienteResponse.add(clienteDTO);
            }
        }
        return clienteResponse;
    }

    public ClienteCriteria translateCriteria(ClienteCriteriaDTO clienteCriteriaDTO) {
        return new ClienteCriteria();
    }
}