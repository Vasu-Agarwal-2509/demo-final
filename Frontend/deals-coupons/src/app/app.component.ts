import { Component } from '@angular/core';
import { DetailsService } from './details.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'deals-coupons';
  public isAuthenticated: any = false;

  constructor(private detailService: DetailsService){}
  ngOnInit(){}

  updateAuth(isAuth: any){
    this.isAuthenticated = isAuth;
    console.log("app" + this.isAuthenticated);
  }
}
