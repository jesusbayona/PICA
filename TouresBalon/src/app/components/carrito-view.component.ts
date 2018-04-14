import { Component } from '@angular/core';
import { Router, ActivatedRoute, Params }  from '@angular/router';
import { ProductoService } from '../services/producto.service';
import { Carrito } from '../models/carrito';


@Component({
    selector: 'carrito',
    templateUrl: '../views/carrito-view.html',
    providers: [ProductoService]
})
export class CarritoViewComponent{
        public titulo: string;
        public idCliente: number;
        public productos: Carrito[];

        constructor(
            private _route: ActivatedRoute,
            private _router: Router,
            private _productoService: ProductoService
        ){
            this.titulo = "Carrito de compras";
            this.idCliente = 2;
        }

        ngOnInit(){
            console.log('Se ha cargado el componente carrito-view.component.ts');
            this._productoService.getCart(this.idCliente).subscribe(
                result =>{
                       if(result.code != 200 ){
                            console.log('omd_1');
                            console.log(result);
                          
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


        onAddReserva(productoNum,productNombre,precioProduc,cantidad=1,totalCompra,status,idCliente){
            this._productoService.addReserve(this.idCliente).subscribe(
                Response=>{
                        if(Response.code == 200){
                            console.log(Response);
                            alert('¡' + Response.respuesta + '!');
                            this._router.navigate(['/productos']);

    
                        }else{
                            alert('¡Error al registrar reserva!');
                        }
                },
                error => {
                    console.log(<any>error);
                }
            );
        }

}