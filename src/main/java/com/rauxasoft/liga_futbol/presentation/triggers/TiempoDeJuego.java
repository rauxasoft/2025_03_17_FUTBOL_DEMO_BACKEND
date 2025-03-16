package com.rauxasoft.liga_futbol.presentation.triggers;

import org.springframework.stereotype.Component;

@Component
public class TiempoDeJuego {

	private int minuto = 0;
	
	public void incrementar() {
		minuto += 1;
	}
	
	public void reset() {
		this.minuto = 0;
	}
	
	public int getMinuto() {
		return this.minuto;
	}
}
