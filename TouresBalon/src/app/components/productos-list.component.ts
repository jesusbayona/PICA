import { Component } from '@angular/core';
import { Router, ActivatedRoute, Params }  from '@angular/router';
import { ProductoService } from '../services/producto.service';
import { Producto } from '../models/producto';

@Component({
    selector: 'productos-list',
    templateUrl: '../views/productos-list.html',
    providers: [ProductoService]
})
export class ProductosListComponent{
    public titulo: string;
    public productos: Producto[];

    constructor(
        private _route: ActivatedRoute,
        private _router: Router,
        private _productoService: ProductoService
    ){
        this.titulo = 'Catalogo de Ofertas';
    }

    ngOnInit(){
        console.log('productos-list.component.ts cargando');

        this._productoService.getProductos().subscribe(
            result =>{
                   if(result.code != 200 ){
                        console.log('omd_1');
                        console.log(result);
                        this.productos = result.data;
                   }else{
                        console.log('omd_2');
                        console.log(result);
                        this.productos = result.productos;
                   }
            },
            error =>{
                console.log('omd_3');
                    console.log(<any>error);
            }

        );
    }

    onAddCart(productoNum,productNombre,precioProduc,cantidad=1,totalCompra,status,idCliente){
        this._productoService.addCart(productoNum,productNombre,precioProduc,cantidad,totalCompra,status,idCliente).subscribe(
            Response=>{
                    if(Response.code == 200){
                        console.log(Response);
                        alert('Se agrego producto al carrito.');

                    }else{
                        alert('No se pudo agregar al carrito.');
                    }
            },
            error => {
                console.log(<any>error);
            }
        );
    }


    
}