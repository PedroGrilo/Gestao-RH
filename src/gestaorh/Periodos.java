package gestaorh;

/**
 *
 * @author creep
 */
public enum Periodos {

    /**
     *
     */
    TRIMESTRES,

    /**
     *
     */
    SEMESTRES,

    /**
     *
     */
    ANUAIS;

    @Override
    public String toString() {
        switch (this) {
            case TRIMESTRES:
                return "TRIMESTRES";
            case SEMESTRES:
                return "SEMESTRES";
            case ANUAIS:
                return "ANUAIS";
        }
        return "Periodo não encontrado";
    }
}
