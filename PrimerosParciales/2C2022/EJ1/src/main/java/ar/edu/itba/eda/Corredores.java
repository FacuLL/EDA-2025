package ar.edu.itba.eda;

public class Corredores {
    private int binarySearch(int[] elements, int key, int izq, int der, int dir) {
        if (izq > der) return der;
        int mid = (der + izq) / 2;
        int actual = elements[mid];
        if (actual == key || (mid == izq && mid == der)) {
            while (mid + dir >= 0 && mid + dir < elements.length && elements[mid+dir] == key) mid+=dir;
            return mid;
        };
        if (actual < key) return binarySearch(elements, key, mid+1, der, dir);
        return binarySearch(elements, key, izq, mid-1, dir);
    }

    public int[] tiemposEntre(int[] tiempos, Pedido[] pedidos) {
        int[] toReturn = new int[pedidos.length];
        for (int i = 0; i < pedidos.length; i++) {
            Pedido pedido = pedidos[i];
            int desdeIndex = binarySearch(tiempos, pedido.desde, 0, tiempos.length-1, -1);
            int hastaIndex = binarySearch(tiempos, pedido.hasta, 0, tiempos.length-1, 1);
            int length = hastaIndex - desdeIndex + 1;
            toReturn[i] = Math.max(length, 0);
        }
        return toReturn;
    }
}