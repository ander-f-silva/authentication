package io.authentication.component.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import io.authentication.component.CalculatesMinutes;

/**
 * Classe de Implementacao para calculo de horas
 * 
 * @author anderson
 */
@Component("calculatesMinutes")
public class CalculationMinutes implements CalculatesMinutes {

    /**
     * Método para calcular o intervalo de horas entre duas datas
     * 
     * @param horaStart
     * @param horaEnd
     * @return result calculate
     */
    @Override
    public long calculate(final LocalDateTime horaStart, final LocalDateTime horaEnd) {
	return ChronoUnit.MINUTES.between(horaStart, horaEnd);
    }
    
}
