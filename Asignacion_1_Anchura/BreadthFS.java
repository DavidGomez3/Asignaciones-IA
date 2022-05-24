import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BreadthFS {
	String str = ""; // Estado inicial 
	String meta = ""; // Estado meta

	LinkedList<String> nodeList;// Lista de nodos

	Map<String, Integer> levelDepth;// Guarda el estado actual y su nivel 

	Map<String, String> stateHistory;// Guarda el estado actual y su padre

	int nodes = 0; // Contador de nodos
	int limit = 1000000; // Limite de nodos
	int unique = -1;// -1 Por que añadimos la raiz
	int deep;// Contador para la profundidad
	int a;// Guarda la posicion del 0 o elemento en blanco

	String currState;// Guarda el estado actual 
	boolean solution = false;// nos indica si la solucion existe o no

	BreadthFS(String str, String meta) {
		nodeList = new LinkedList<String>();
		levelDepth = new HashMap<String, Integer>();
		stateHistory = new HashMap<String, String>();
		this.str = str;
		this.meta = meta;
		addToNodeList(str, null);// anade la raiz
	}

	void doSearch() {

		while (!nodeList.isEmpty()) {

			currState = nodeList.removeFirst(); //guarda el nodo que voy a analizar

			if (currState.equals(meta)) { // Verifica si estoy en la solucion 
				solution = true;
				printSolution(currState);// imprime las soluciones
				break;
			}

			if (levelDepth.get(currState) == limit) {// Chequea si supero el limite de nodos
				solution = false;
				printSolution(currState);// imprime soluciones
				break;
			}

			else {

				a = currState.indexOf("0");// Consigue la posicion del 0 

				// mover el 0 a la izquierda
				while (a != 0 && a != 3 && a != 6) {
					String nextState = currState.substring(0, a - 1) + "0" + currState.charAt(a - 1)
							+ currState.substring(a + 1);// cambia de posicion del 0 
					addToNodeList(nextState, currState);// añade el nodo a la lista de nodos
					nodes++;
					break;
				}

				// mover el cero hacia arriba
				while (a != 0 && a != 1 && a != 2) {// if blank not in the very top of row then it able to move up
					String nextState = currState.substring(0, a - 3) + "0" + currState.substring(a - 2, a)
							+ currState.charAt(a - 3) + currState.substring(a + 1);// cambia de posicion del 0
					addToNodeList(nextState, currState);// añade el nodo a la lista de nodos
					nodes++; 
					break;
				}

				// mover a la derecha
				while (a != 2 && a != 5 && a != 8) {// if blank not in the right most column then it able to move right
					String nextState = currState.substring(0, a) + currState.charAt(a + 1) + "0"
							+ currState.substring(a + 2);// cambia de posicion del 0
					addToNodeList(nextState, currState);// añade el nodo a la lista de nodos
					nodes++;
					break;
				}

				// mover hacia abajo
				while (a != 6 && a != 7 && a != 8) {// if blank not in the very bottom row then it able to move down
					String nextState = currState.substring(0, a) + currState.substring(a + 3, a + 4)
							+ currState.substring(a + 1, a + 3) + "0" + currState.substring(a + 4);// cambia de posicion del 0
					addToNodeList(nextState, currState);// añade el nodo a la lista de nodos
					nodes++;
					break;
				}

			}

		}

		if (solution) {
			System.out.println("La solucion existe");
		} else {
			System.out.println("Solucion no encontrada intente: ");
			System.out.println("1. aumentar el limite de nodos ");
			System.out.println("2. quizas no hay solucion posible");
		}

	}

	private void addToNodeList(String newState, String oldState) {
		if (!levelDepth.containsKey(newState)) {
			deep = oldState == null ? 0 : levelDepth.get(oldState) + 1;
			unique++;
			levelDepth.put(newState, deep);
			nodeList.add(newState);// añade el nodo al final de la lista
			stateHistory.put(newState, oldState);
		}

	}

	void printSolution(String currState) {
		if (solution) {
			System.out.println("\nSolucion encontrada en " + levelDepth.get(currState) + " paso(s)");
			System.out.println("\nNodos generado: " + nodes);
			//System.out.println("\nUnique Node generated: " + unique);
		} else {

			System.out.println("Solucion no encontrada!");
			System.out.println("Limite alcanzado!");
			System.out.println("Nodos generados: " + nodes);
			//System.out.println("Unique Node generated: " + unique);
		}

		String traceState = currState;
		while (traceState != null) {
			System.out.println("\nSolucion en " + levelDepth.get(traceState));
			try {
				for (int z = 0; z < 9; z++) {
					System.out.print(" " + String.valueOf(traceState.charAt(z)) + " ");
					if ((z + 1) % 3 == 0) {
						System.out.println();
					}
				}
			} catch (NullPointerException e) {
			}
			traceState = stateHistory.get(traceState);
		}

	}
}