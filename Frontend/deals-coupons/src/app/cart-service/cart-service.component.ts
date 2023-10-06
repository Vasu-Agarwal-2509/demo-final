import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DetailsService } from '../details.service';
import { Itemdetail } from '../itemdetail';

@Component({
  selector: 'app-cart-service',
  templateUrl: './cart-service.component.html',
  styleUrls: ['./cart-service.component.css']
})
export class CartServiceComponent {

  custId: any;
  cartTotal: any;
  displayTotal: string = "";
  productListObj: Itemdetail[] = [];

  constructor(private route: ActivatedRoute, private detailsService: DetailsService, private router: Router){}
  ngOnInit(){
    this.custId = this.route.snapshot.paramMap.get('emailid');
    this.detailsService.getProductList(this.custId).subscribe(data => this.productListObj = data);
  }

  calculateTotal(){
    this.detailsService.getCartTotal(this.custId).subscribe(data => {this.cartTotal = data; 
                                                  this.displayTotal = "Your cart total is " + data});
  }

  removeProduct(itemId: string){
    this.detailsService.removeProductFromCart(itemId).subscribe();
    this.router.navigateByUrl('/cart', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/cart', this.custId]);
    }); 
  } 
}
// this.route.snapshot.paramMap.get('id')
