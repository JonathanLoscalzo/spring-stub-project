package ar.edu.uai.paradigms.translator.lote;

import ar.edu.uai.model.lote.Lote;
import ar.edu.uai.model.lote.LoteCriteria;
import ar.edu.uai.paradigms.dto.lote.LoteCriteriaDTO;
import ar.edu.uai.paradigms.dto.lote.LoteDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hal on 18/04/2016.
 */
public class LoteTranslator
{
    public Lote translate(LoteDTO loteDTO)
    {
        return new Lote(loteDTO.getId(),
                        loteDTO.getFechaVencimiento(),
                        loteDTO.getFechaEntrada(),
                        loteDTO.getDetalle());
    }

    public LoteDTO translateToDTO(Lote lote)
    {
        if (lote != null)
        {
            return new LoteDTO( lote.getId(),
                                lote.getFechaVencimiento(),
                                lote.getFechaEntrada(),
                                lote.getDetalle());
        }
        return null;
    }

    public List<LoteDTO> translateToDTO(List<Lote> lotes) {
        List<LoteDTO> loteResponse = new ArrayList<LoteDTO>();
        for(Lote i : lotes) {
            LoteDTO LoteDTO = this.translateToDTO(i);
            if(LoteDTO != null) {
                loteResponse.add(LoteDTO);
            }
        }
        return loteResponse;
    }

    public LoteCriteria translateCriteria(LoteCriteriaDTO loteCriteriaDTO) {
        return new LoteCriteria();
    }
}