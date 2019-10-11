package br.uece.structures.hashing.halfopen;

import br.uece.hashing.util.Primo;

public class HalfOpenHashingVector {
	private Integer[] vetor;
	public final int SIZE = 7;
	private int size;
	private boolean changeSize;

	public HalfOpenHashingVector() {
		vetor = new Integer[SIZE];
		size = 0;
		changeSize = false;
	}

	public void add(int value) {
		if (size == vetor.length / 2 + 1) {
			size = 0;
			rehashing();
			changeSize = true;
		}
		int posicao = geraPosicao(value);
		if (vetor[posicao] == null) {
			vetor[posicao] = value;
			size++;
		} else {
			vetor[funcaoQuadratica(value)] = value;
			size++;
		}
	}

	private int buscaQuadratica(int value) {

		int tentativa = 1;
		int posicao = -1;

		while (tentativa < (vetor.length / 2) + 1) {
			posicao = (value + (tentativa * tentativa)) % vetor.length;
			if (vetor[posicao] == null) {
			} else if (vetor[posicao] == value) {
				tentativa = vetor.length;
				return posicao;
			}
			tentativa += 1;
		}
		return -1;
	}

	private int funcaoQuadratica(int value) {
		int tentativa = 1;
		int posicao = -1;
		for (int i = 0; i < vetor.length / 2 + 1; i++) {

			posicao = (value + (tentativa * tentativa)) % vetor.length;

			if (vetor[posicao] == null) {
				return posicao;
			} else {
				tentativa++;
			}
		}

		if (tentativa > vetor.length / 2 + 1) {
			System.out.println("Nao foi possivel inserir o elemento " + value);
		}
		return posicao;
	}

	/*
	 * Primeiro metodo para gerar posicao com base no valor e tamanho do vetor
	 */
	private int geraPosicao(int value) {
		return value % vetor.length;
	}

	public void remove(int value) {
		int posicao = busca(value);
		vetor[posicao] = null;
		size--;
	}

	private void rehashing() {
		Integer[] aux = new Integer[vetor.length];
		/*
		 * Salva o vetor principal num temporario
		 */
		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] != null) {
				aux[i] = vetor[i];
			}
		}
		System.out.println("\n");

		int novoValor = Primo.proximoPrimo(vetor.length);
		vetor = new Integer[novoValor];
		for (int i = 0; i < aux.length; i++) {

			if (aux[i] != null) {
				add(aux[i]);
			}
		}
	}

	public int busca(int value) {
		int posicao = geraPosicao(value);

		if (vetor[posicao] != null && vetor[posicao] == value) {
			return posicao;
		} else {
			return buscaQuadratica(value);
		}
	}

	public void imprime() {
		for (int i = 0; i < vetor.length; i++) {
			System.out.println("Indice " + i + "=> " + vetor[i]);
		}

	}

	public Integer[] getVetor() {
		return vetor;
	}

	public boolean getChangeSize() {
		return changeSize;
	}
}
