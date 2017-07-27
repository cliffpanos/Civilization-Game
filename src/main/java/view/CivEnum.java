package view;
/**
 * This is an enum class that represents all the
 * Civilizations that the user may chose from
 */
public enum CivEnum {

    ANCIENT_EGYPT {
        @Override
        public String toString() {
            return "Ancient Egypt";
        }
    },
    QIN_DYNASTY {
        @Override
        public String toString() {
            return "Qin Dynasty";
        }
    },
    ROMAN_EMPIRE {
        @Override
        public String toString() {
            return "Roman Empire";
        }
    },
    ANCIENT_GREECE { //I am one-quarter Greek :D
        @Override
        public String toString() {
            return "Ancient Greece";
        }
    },
    VENETIAN_FEDERATION {
        @Override
        public String toString() {
            return "Venetian Federation";
        }
    },
    INCAN_EMPIRE {
        @Override
        public String toString() {
            return "Incan Empire";
        }
    }

}
