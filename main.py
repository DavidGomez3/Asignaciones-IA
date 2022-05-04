import numpy as np

# def posicion0(nodo):
#     fila,columna = np.where(nodo == 0)
#     posicion0 = [fila,columna]
#     return posicion0

def generar(nodo):
    fila,columna = np.where(nodo == 0)
    posicion0 = [fila,columna]
    print(fila)
    listaNodos.append(nodo)
    #derecha
    if ((posicion0[0] + 1) > 0) and visitado == False:
        nodoGenerado = nodo
        nodoGenerado[posicion0[0:1]][posicion0[0:1]] = [posicion0[0:1 + 1]][posicion0[0:1]]
        nodoGenerado[posicion0[0+1]][posicion0[1]] = 0
        listaNodos.append(nodoGenerado)
    #arriba
    if ((posicion0[1] + 1) > 0) and visitado == False:
        print
    #izquierda
    if ((posicion0[0] - 1) > 0) and visitado == False:
        print
    #abajo
    if ((posicion0[1] - 1) > 0) and visitado == False:
        print

if __name__ == "__main__":

    nodo = np.array([[2,4,8],
                     [0,7,5],
                     [1,6,3]])

    estadoFinal   = np.array([[1,2,3],
                              [8,0,4],
                              [7,6,5]])
    listaNodos = []
    visitado = False

    generar(nodo)