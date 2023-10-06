import { Component } from '@angular/core';
import { DetailsService } from '../details.service';
import { Itemdetail } from '../itemdetail';
import { ActivatedRoute, Router } from '@angular/router';
import { Cartdetail } from '../cartdetail';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  itemDetailList: Itemdetail[] = [];
  cartObj: Cartdetail = new Cartdetail();
  preferencesList: any[] = [];
  isAuthenticated:any;
  constructor(private detailService: DetailsService, private router: Router, private route: ActivatedRoute, private appObj: AppComponent){}
  
  ngOnInit(){
    if(this.appObj.isAuthenticated){
      this.detailService.getCustomerDetails().subscribe((data: any) => {
        const dataObj = JSON.parse(data);
        this.preferencesList = dataObj.preferences;
        this.detailService.getProducts().subscribe(response => {
          for(let pref of this.preferencesList){
            for(let resp of response){
              if(resp.category == pref){
                this.itemDetailList.push(resp);
              }
            }
          }
        });
      });
    }
    else{
      this.detailService.getProducts().subscribe(response => {
        // const dataObj = JSON.parse(response);
        this.itemDetailList = response
      });
    }
    // console.log("home " +this.detailService.isAuthenticated);
    // this.isAuthenticated = this.route.snapshot.paramMap.get('isUserValid');
  }

  viewCart(itemId: string){
    this.cartObj.customerId = this.detailService.authDetailObj.emailid;
    this.cartObj.itemId = itemId;
    this.detailService.addProductInCart(this.cartObj).subscribe();
    this.router.navigate(['/cart', this.detailService.authDetailObj.emailid]);
  }

  displayProductDetails(prodId: string){
    this.router.navigate(['/productDetail', prodId]);
  }
}
