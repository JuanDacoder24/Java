package com.decroly;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SQLAccessMercaDaw miMercaDaw = new SQLAccessMercaDaw();
        List<Producto> listas = miMercaDaw.mostrarLista();

        for (Producto ml : listas) {
            System.out.println(ml);
        }

    }
}