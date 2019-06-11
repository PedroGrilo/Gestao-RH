package gestaorh;

/**
 *
 * @author creep
 */
public enum Categorias {

    /**
     *
     */
    NORMAL,

    /**
     *
     */
    COMERCIAL,

    /**
     *
     */
    GESTOR,

    /**
     *
     */
    MOTORISTA;
	
	
    @Override
    public String toString() {
		switch (this) {
		case NORMAL:
			return "normal";
		case COMERCIAL:
			return "comercial";
		case GESTOR:
			return "gestor";
		case MOTORISTA:
			return "motorista";
		default:
			break;
		}
		return null;
	}
	
	
}
