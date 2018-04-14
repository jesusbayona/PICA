import { Injectable } from '@angular/core';
import {Http,Response, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { Producto } from '../models/producto';
import { Carrito } from '../models/carrito';
import { GLOBAL } from './global';

@Injectable()
export class ProductoService{
    public url: string;

    constructor(
        public _http: Http
    ){
        this.url = GLOBAL.url;
    }

    getProductos(){
         return this._http.get(this.url+'wsTouresBalon/webresources/Productos').map(res => res.json());
         
    }

    addCart(productoNum,productNombre,precioProduc,cantidad=1,totalCompra,status='A',idCliente){
        return this._http.get(this.url+'wsTouresBalon/webresources/quote?productoNum='+productoNum+'&productNombre='+productNombre+'&precioProduc='+precioProduc+'&cantidad='+cantidad+'&totalCompra='+totalCompra+'&status='+status+'&idCliente='+idCliente).map(res => res.json());
    }

    getCart(idCliente){
        return this._http.get(this.url+'wsTouresBalon/webresources/verCarrito?ideCliente='+idCliente).map(res => res.json());
    }


    addReserve(idCliente){
        return this._http.get(this.url+'wsTouresBalon/webresources/reserva?ideCliente='+idCliente).map(res => res.json());
    }
}