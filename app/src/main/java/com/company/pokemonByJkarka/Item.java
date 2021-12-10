package com.company.pokemonByJkarka;

public class Item {
    String nombre;
    String descripcion;
    int nItems;
    int imagen;
    int precioCompra;
    int precioVenta;

    public Item(String nombre, String descripcion, int nItems, int imagen, int precioCompra, int precioVenta){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nItems = nItems;
        this.imagen = imagen;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }
}
