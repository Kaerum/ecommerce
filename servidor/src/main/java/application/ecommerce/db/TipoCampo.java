package application.ecommerce.db;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public enum TipoCampo {
    BOOLEAN {
        public Boolean converter(String value) {
            return Boolean.valueOf(value);
        }
    },

    CHAR {
        public Character converter(String value) {
            return value.charAt(0);
        }
    },

    DATE {
        public LocalDateTime converter(String value) {
            LocalDateTime date = null;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                date = LocalDateTime.parse(value, formatter);
            } catch (Exception e) {
                log.info("Não foi possível converter do tipo DATE {}", e.getMessage());
            }
            return date;
        }
    },

    DOUBLE {
        public Double converter(String value) {
            return Double.valueOf(value);
        }
    },

    INTEGER {
        public Integer converter(String value) {
            return Integer.valueOf(value);
        }
    },

    LONG {
        public Long converter(String value) {
            return Long.valueOf(value);
        }
    },

    STRING {
        public String converter(String value) {
            return value;
        }
    };

    public abstract <T> T converter(String value);
}
