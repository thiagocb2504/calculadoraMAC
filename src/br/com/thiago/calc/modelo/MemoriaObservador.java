package br.com.thiago.calc.modelo;


@FunctionalInterface
public interface MemoriaObservador {

	public void valorAlterado(String novoValor);
}
