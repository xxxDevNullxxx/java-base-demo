package it.umana.demo.logic;

import it.umana.demo.spi.dto.EXberEsitTypeDTO;
import it.umana.demo.spi.dto.GestioneErroriDTO;
import it.umana.demo.spi.dto.HeaderDTO;
import it.umana.demo.spi.dto.HeaderTecnicoDTO;

/**
 * @author ACME
 * @since 06/06/16
 */
public class HeaderDTOFactory {
    /**
     * Funzione che crea una risposta di default generica, popolata con il caso di errore specifico o sostituita dall'header del msg ricevuto
     *
     * @return HeaderDTO
     */

    public static HeaderDTO createHeaderDTO() {

        HeaderDTO header = new HeaderDTO();

        header.setGestioneErrori(HeaderDTOFactory.createGestioneErroriDTO());

        header.setHeaderTecnicoDTO(HeaderDTOFactory.createHeaderTecnicoDTO());

        return header;
    }

    public static HeaderTecnicoDTO createHeaderTecnicoDTO(){

        HeaderTecnicoDTO headerTecnicoDTO = new HeaderTecnicoDTO();
        String s = " ";
        headerTecnicoDTO.setXbbr_codi_proc(new String(new char[16]).replace("\0", s));
        headerTecnicoDTO.setXbbr_codi_serv(new String(new char[16]).replace("\0", s));
        headerTecnicoDTO.setXbbr_codi_oper(new String(new char[32]).replace("\0", s));
        headerTecnicoDTO.setXbbr_codi_user(new String(new char[8]).replace("\0", s));
        headerTecnicoDTO.setXbbr_codi_term(new String(new char[8]).replace("\0", s));
        headerTecnicoDTO.setXbbr_codi_isti(new String(new char[3]).replace("\0", s));
        headerTecnicoDTO.setXbbr_codi_dipe(new String(new char[5]).replace("\0", s));
        headerTecnicoDTO.setXbbr_codi_uo(new String(new char[6]).replace("\0", s));
        headerTecnicoDTO.setXbbr_codi_prov(new String(new char[8]).replace("\0", s));
        headerTecnicoDTO.setXbbr_codi_cana(new String(new char[5]).replace("\0", s));
        headerTecnicoDTO.setXbbr_sigl_cana(new String(new char[3]).replace("\0", s));
        headerTecnicoDTO.setXbbr_codi_guid(new String(new char[32]).replace("\0", s));
        headerTecnicoDTO.setXbbr_forza_sincrono(false);
        headerTecnicoDTO.setXbbr_dest_proc(new String(new char[8]).replace("\0", s));
        headerTecnicoDTO.setXbbr_filler(new String(new char[365]).replace("\0", s));

        return headerTecnicoDTO;
    }

    public static GestioneErroriDTO createGestioneErroriDTO(){

        GestioneErroriDTO gestioneErrori = new GestioneErroriDTO();

        String Xber_esit_rc = "50";
        String Xber_esit_pref = "OS";
        String Filler = " ";
        String Xber_esit_erro = "500";
        EXberEsitTypeDTO Xber_esit_type = EXberEsitTypeDTO.E;
        String Xber_esit_rife = new String(new char[3]).replace('\0', ' ');
        String Xber_esit_modulo = new String(new char[8]).replace('\0', ' ');
        String Xber_esit_campo = new String(new char[50]).replace('\0', ' ');
        String Xber_esit_desc_s = "Lettura non riuscita";
        String Xber_esit_desc_m = "Errore server in lettura: non è stato possibile leggere alcun contenuto";
        String Xber_esit_desc_l = new String(new char[256]).replace('\0', ' ');
        String Xber_esit_sqlcode = new String(new char[4]).replace('\0', ' ');
        String Xber_esit_sqlca = new String(new char[136]).replace('\0', ' ');
        Boolean Xber_esit_dati = true;
        Boolean Xber_esit_system = false;
        String FinalFiller = new String(new char[31]).replace('\0', ' ');
        gestioneErrori.setXber_esit_rc(Xber_esit_rc);
        gestioneErrori.setXber_esit_pref(Xber_esit_pref);
        gestioneErrori.setXber_esit_filler_01(Filler);
        gestioneErrori.setXber_esit_erro(Xber_esit_erro);
        gestioneErrori.setXber_esit_filler_02(Filler);
        gestioneErrori.setXber_esit_type(Xber_esit_type);
        gestioneErrori.setXber_esit_rife(Xber_esit_rife);
        gestioneErrori.setXber_esit_modulo(Xber_esit_modulo);
        gestioneErrori.setXber_esit_campo(Xber_esit_campo);
        gestioneErrori.setXber_esit_desc_s(Xber_esit_desc_s);
        gestioneErrori.setXber_esit_desc_m(Xber_esit_desc_m);
        gestioneErrori.setXber_esit_desc_l(Xber_esit_desc_l);
        gestioneErrori.setXber_esit_sqlcode(Xber_esit_sqlcode);
        gestioneErrori.setXber_esit_sqlca(Xber_esit_sqlca);
        gestioneErrori.setXber_esit_dati(Xber_esit_dati);
        gestioneErrori.setXber_esit_system(Xber_esit_system);
        gestioneErrori.setXber_esit_filler_03(FinalFiller);

        return gestioneErrori;
    }

}
