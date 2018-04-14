export class Carrito{
    constructor(
        public nombreproducto: string,
        public descripcionproducto: string,
        public productoid: string,
        public cantidadItem: number,
        public totalcompra: number,
    ){}
}