import{ Component } from '@angular/core';

@Component({
    selector: 'home',
    templateUrl: '../views/home.html'
})
export class HomeComponent{
    public titulo: string;

    constructor(){
        this.titulo = 'WebApp B2C TouresBalon';
    }

    ngOnInit(){
        console.log('Se ha cargado el comonente home.component.ts');
    }
}