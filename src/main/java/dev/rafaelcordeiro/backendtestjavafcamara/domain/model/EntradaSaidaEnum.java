package dev.rafaelcordeiro.backendtestjavafcamara.domain.model;

public enum EntradaSaidaEnum {
    ENTRADA {
        @Override
        public String toString() {
            return "E";
        }
    }, SAIDA {
        @Override
        public String toString() {
            return "S";
        }
    };
}
