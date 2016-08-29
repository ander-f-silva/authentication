package br.com.authentication.component;

import java.time.LocalDateTime;

/**
 * Interface para implementar calculo de horas
 * 
 * @author anderson
 */
public interface CalculatesMinutes {
    
    /**
     * Método para calcular o intervalo de horas entre duas datas
     * 
     * @param horaStart
     * @param horaEnd
     * @return result calculate
     */
    long calculate(final LocalDateTime horaStart, final LocalDateTime horaEnd);

}
