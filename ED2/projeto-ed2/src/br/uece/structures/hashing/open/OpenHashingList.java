package br.uece.structures.hashing.open;
import br.uece.structures.hashing.halfopen.Node;

public class OpenHashingList {
	public final static int SIZE = 7;
	private Node[] vetor;

	public Node[] getVetor(){
		return vetor;
	}
	public OpenHashingList() {
		vetor = new Node[SIZE];
	}

	public void add(int value) {

		int posicao = geraPosicao(value);

		if (vetor[posicao] == null) {
			vetor[posicao] = new Node(value, null);
		} else { // se tiver nao estiver vazio

			Node aux = vetor[posicao];

			vetor[posicao] = new Node(value, aux);

		}

	}

	private int geraPosicao(int value) {
		int position = -1;
		// TODO verificar viabilidade de inserir numeros negativos
		if (value < 0)
			position = value * (-1);
		else
			position = value;

		return position % vetor.length;
	}

	public void remove(int value) {
		int posicao = value % vetor.length;
		Node aux = vetor[posicao];
		if (vetor[posicao].getValue() == value) {
			vetor[posicao] = vetor[posicao].getNext();
		} else {
			while (aux != null) {
				if (aux.getNext().getValue() == value) {
					aux.setNext(aux.getNext().getNext());
					break;
				}
				aux = aux.getNext();
			}
		}

	}

	public int fatorCarga() {
		Node[] aux = vetor;
		int cont = 0;
		for (int i = 0; i < aux.length; i++) {
			cont = carga(vetor[i], cont);
		}
		return cont;
	}

	private int carga(Node node, int maximo) {
		int cont = 0;
		while (node != null) {
			cont++;
			node = node.getNext();
		}
		return Math.max(cont, maximo);
	}

	public int larguraOcupada() {
		int largura = 0;
		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] != null) {
				largura += 1;
			}
		}
		return largura;
	}

	public boolean find(int value) {
		int posicao = geraPosicao(value);
		Node aux = vetor[posicao];
		while(aux!=null) {
			if(value == aux.getValue()) {
				return true;
			}
			aux = aux.getNext();
		}
		return false;
	}
	public int busca(int value) {
		int posicao = geraPosicao(value);
		if(vetor[posicao]!= null && find(value)) {
			return posicao;
		} else {
			return -1;
		}
	
	}
	public int qtdElementos() {
		int qtd = 0;
		for (int i = 0; i < vetor.length; i++) {
			Node aux = vetor[i];
			while (aux != null) {
				qtd += 1;
				aux = aux.getNext();
			}
		}
		return qtd;
	}

	public void imprime() {
		for (int i = 0; i < vetor.length; i++) {
			System.out.printf("Indice " + i + "=> ");
			Node aux = vetor[i];
			while (aux != null) {
				System.out.printf(aux.toString());
				aux = aux.getNext();
			}
			System.out.println("");
		}

	}

}