import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DepthFS {

String str = ""; // Estado Inicial
String meta = ""; // Estado Final 

    LinkedList<String> Lista; // Lista

    Map<String, Integer> nivel_Prof;


    Map<String, String> stateHistory;

    int nodos = 0; // Contador para generar el nodo
    int limit = 100; // Contador para el límite
    int unique = -1;
    int nuevo_Valor; // Contador para el nivel de profundidad
    int a;

    String Estado_Act; // Estado actual
    boolean solucion = false; 

    DepthFS(String str, String goal) {
        Lista = new LinkedList<String>();
        nivel_Prof = new HashMap<String, Integer>();
        stateHistory = new HashMap<String, String>();
        this.str = str;
        this.meta = goal;
        addToOpenList(str, null); // Añadir raíz
    }

void doSearch (){

while (!Lista.isEmpty()){

Estado_Act = Lista.removeFirst();

if (Estado_Act.equals(meta)){ // Verifica si el estado actual es el estado meta 
solucion = true;
printSolution(Estado_Act);// Imprime las soluciones 
break;

}

else {

if (nivel_Prof.get(Estado_Act) < limit){

a = Estado_Act.indexOf("0");// Obtiene la posición de 0 (casilla blanca)

// Movimientos a la izquierda
while (a != 0 && a != 3 && a != 6){
String sig_Estado = Estado_Act.substring(0,a-1)+"0"+Estado_Act.charAt(a-1)+Estado_Act.substring(a+1); // Cambia la casilla blanca con la de destino
addToOpenList(sig_Estado, Estado_Act); // Añade un nodo a la lista
nodos++;
break;
}

// Movimientos arriba
while (a!=0 && a!=1 && a!=2){
String nextState = Estado_Act.substring(0,a-3)+"0"+Estado_Act.substring(a-2,a)+Estado_Act.charAt(a-3)+Estado_Act.substring(a+1); // Cambia la casilla blanca con la de destino
addToOpenList(nextState, Estado_Act); // Añade un nodo a la lista
nodos++; // Se genera un nodo y se añade al contador 
break;
}

// Movimientos a la derecha
while(a != 2 && a != 5 && a != 8){
String nextState = Estado_Act.substring(0,a)+Estado_Act.charAt(a+1)+"0"+Estado_Act.substring(a+2); // Cambia la casilla blanca con la de destino
addToOpenList(nextState, Estado_Act); // Añade un nodo a la lista
nodos++;
break;
}

// Movimientos abajo
while (a != 6 && a != 7 && a != 8) {
String nextState = Estado_Act.substring(0,a)+Estado_Act.substring(a+3,a+4)+Estado_Act.substring(a+1,a+3)+"0"+Estado_Act.substring(a+4); // Cambia la casilla blanca con la de destino
addToOpenList(nextState, Estado_Act); // Añade un nodo a la lista
nodos++;
break;
}

} // Fin del límite
}

}

if (solucion){
System.out.println("\n SOLUCION ENCONTRADA. ");
}
else{
System.out.println("\n La solución no ha podido ser encontrada, sugerencias:");
System.out.println("\n 1. Intente incrementar el nivel de profundidad ");
System.out.println("2. Quizá es físicamente imposible");
}

}

    private void addToOpenList(String estado_nuevo, String estado_ant) {
        if (!nivel_Prof.containsKey(estado_nuevo)) {
            nuevo_Valor = estado_ant == null ? 0 : nivel_Prof.get(estado_ant) + 1;
            unique++;
            nivel_Prof.put(estado_nuevo, nuevo_Valor);
            Lista.addFirst(estado_nuevo); // Añade un nodo AL INICIO de la lista
            stateHistory.put(estado_nuevo, estado_ant);

        }

    }

void printSolution (String estado_Act){
if (solucion){
System.out.println("\n Solución encontrada en " +nivel_Prof.get(estado_Act)+" paso(s).");
System.out.println("\n Nodos generados "+ nodos);
System.out.println("\n Hojas generadas "+ unique);
}
else{

System.out.println("\n Solución no encontrada!");
System.out.println("\n Limite de profundidad encontrado!");
System.out.println("\n Nodos generados "+ nodos);
System.out.println("\n Hojas generadas"+ unique);
}

String traceState = estado_Act;
while (traceState != null) {
    System.out.println();
    System.out.println(traceState + " en " + nivel_Prof.get(traceState));
    System.out.println();
try{
for(int z=0;z<9;z++){
System.out.print(" " + String.valueOf(traceState.charAt(z)) + " ");
if ((z+1) % 3 == 0){System.out.println();}
}
}
catch (NullPointerException e) {}
traceState = stateHistory.get(traceState);

}

}

}