package br.com.authentication.component;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Classe de Implementacao para calculo de horas
 * 
 * @author anderson
 */
@Component("calculatesMinutes")
public class CalculationMinutes {

    /**
     * Método para calcular o intervalo de horas entre duas datas
     * 
     * @param horaStart
     * @param horaEnd
     * @return result calculate
     */
    public long calculate(final LocalDateTime horaStart, final LocalDateTime horaEnd) {
	    return ChronoUnit.MINUTES.between(horaStart, horaEnd);
    }
    
}
