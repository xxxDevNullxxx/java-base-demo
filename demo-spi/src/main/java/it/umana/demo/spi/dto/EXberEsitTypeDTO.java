package it.umana.demo.spi.dto;

/**
 * Determina la tipologia di esito
 * <pre>
 *     <ul>
 *         <li>I - Info</li>
 *         <li>W - Warning</li>
 *         <li>E - Error</li>
 *     </ul>
 * </pre>
 *
 * @author ACME
 */
public enum EXberEsitTypeDTO {
    /**
     * Info
     */
    I,
    /**
     * Warning
     */
    W,
    /**
     * Error
     */
    E,
    /**
     * Empty
     */
    EMPTY("");
    final String name;
    EXberEsitTypeDTO(String name) { this.name = name; }
    EXberEsitTypeDTO() { this(null); }
    @Override
    public String toString() {
        return name == null ? super.toString() : name;
    }


}
