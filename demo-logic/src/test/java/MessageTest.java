import it.umana.demo.spi.HostMessageAdapter;
import it.umana.demo.spi.dto.*;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @created ACME
 * @since 26/05/16
 * <p/>
 * Classe di test per progetto DEMO
 */
@Log
public class MessageTest {



    private static final String HEADER = "         E                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             SN                                                                                                                                                                             N                                                                                                                                                                                                                                                                                                                                                                                     ";

    private static final String PAYLOAD = "20231017TESTDEMO";

    private static final String XBBU_BUFF = "0016";


    private static final String CORRECT_MESSAGE = HEADER.concat(XBBU_BUFF).concat(PAYLOAD);
    private static final HostMessageAdapter HOST_MESSAGE_ADAPTER = new HostMessageAdapter();


    /**
     * Testing marshal
     */

    @Test
    public void testingMarshal() {

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setPayload(PAYLOAD);
        messageDTO.setXbbu_buff_l(XBBU_BUFF);
        messageDTO.setHeader(createDefaultHeader());

        System.out.println("==== TESTO MARSHAL HEADER....... ====\n");

        Assertions.assertEquals(HOST_MESSAGE_ADAPTER.marshal(createDefaultHeader()), HEADER);

        System.out.println("==== MARSHAL HEADER ESEGUITA CORRETTAMENTE ====\n");

        String result = HOST_MESSAGE_ADAPTER.marshal(messageDTO);

        System.out.println("==== TESTO MARSHAL MESSAGGIO COMPLETO....... ====\n");

        Assertions.assertEquals(result, CORRECT_MESSAGE);

        System.out.println("==== MARSHAL MESSAGGIO COMPLETO ESEGUITA CORRETTAMENTE ====\n");
    }



    /**
     * Testing unmarshal
     */

    @Test
    public void testingUnmarshal() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO = HOST_MESSAGE_ADAPTER.unmarshal(CORRECT_MESSAGE, messageDTO.getClass());
        String output = HOST_MESSAGE_ADAPTER.marshal(messageDTO);

        System.out.println("==== VALIDO HEADER MESSAGGIO COMPLETO..... ====\n");



        System.out.println("==== VALIDAZIONE HEADER MESSAGGIO COMPLETO ESEGUITA CORRETTAMENTE ====\n");

        System.out.println("==== TESTO UNMARSHAL MESSAGGIO COMPLETO..... ====\n");

        Assertions.assertEquals(output, CORRECT_MESSAGE);

        System.out.println("==== UNMARSHAL MESSAGGIO COMPLETO ESEGUITO CORRETTAMENTE ====\n");
    }

    /**
     * Funzione per creare un headerDTO (corretto) di default
     */

    public HeaderDTO createDefaultHeader() {
        HeaderDTO header = new HeaderDTO();

        GestioneErroriDTO gestioneErrori = new GestioneErroriDTO();

        String Xber_esit_rc = "  ";
        String Xber_esit_pref = "  ";
        String Filler = " ";
        String Xber_esit_erro = "   ";
        EXberEsitTypeDTO Xber_esit_type = EXberEsitTypeDTO.E;
        String Xber_esit_rife = new String(new char[3]).replace('\0', ' ');
        String Xber_esit_modulo = new String(new char[8]).replace('\0', ' ');
        String Xber_esit_campo = new String(new char[50]).replace('\0', ' ');
        String Xber_esit_desc_s = new String(new char[20]).replace('\0', ' ');
        String Xber_esit_desc_m = new String(new char[80]).replace('\0', ' ');
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

        header.setGestioneErrori(gestioneErrori);


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

        header.setHeaderTecnicoDTO(headerTecnicoDTO);
        return header;
       /* ciao = "€£$%&@òçàù§èé";
        String subjectString = Normalizer.normalize(ciao, Normalizer.Form.NFD);
        String resultString = subjectString.replaceAll("[^\\x00-\\x7F]", "");*/
    }


}
