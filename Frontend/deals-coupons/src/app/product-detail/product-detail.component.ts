import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Itemdetail } from '../itemdetail';
import { DetailsService } from '../details.service';
import { Cartdetail } from '../cartdetail';
// import * as Razorpay from 'razorpay';

declare var Razorpay: any;
@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent {

  itemId: any;
  productObj: Itemdetail = new Itemdetail();
  cartObj: Cartdetail = new Cartdetail();
  productAmount: any;
  totalAmount: any;
  
  constructor(private route: ActivatedRoute, private detailsService: DetailsService, private router: Router){}

  ngOnInit(){
    this.itemId = this.route.snapshot.paramMap.get('prodId');
    this.detailsService.getProductById(this.itemId).subscribe((data: any) => {
      const dataObj = JSON.parse(data);
      this.productObj = dataObj; 
      this.productAmount = dataObj.itemPrice});
    // this.detailsService.getCustomerDetails().subscribe(data => this.totalAmount = data.totalAmount);
  }

  viewCart(itemId: string){
    this.cartObj.customerId = this.detailsService.authDetailObj.emailid;
    this.cartObj.itemId = itemId;
    this.detailsService.addProductInCart(this.cartObj).subscribe();
    this.router.navigate(['/cart', this.detailsService.authDetailObj.emailid]);
  }

  purchaseItem(itemId: string){
    this.detailsService.addCoupon(this.productObj.couponCode).subscribe();
    this.router.navigate(['/coupons']);
  }

  transactionDisplay(){
    if(this.detailsService.isCouponApplied){
      this.productAmount -= this.productAmount * (this.detailsService.discountOnCouponCode / 100);
    }
    this.detailsService.createTransaction(this.productAmount).subscribe(
      (response) => {
        console.log(response);
        if(this.totalAmount >= this.productAmount){
          this.openTransactionModel(response);
        }
        else{
          alert("Not sufficient balance");
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }

  openTransactionModel(response: any){
    var options = {
      order_id: response.order_id,
      key: response.key,
      amount: response.amount,
      currency: response.currency,
      name: 'Vasu',
      description: 'Payment',
      image: 'D:\CaseStudyAngular\deals-coupons\src\assets\s3.jpg',
      handler: (response: any) => {
        this.processResponse(response);
      },
      prefill: {
        name: 'Vasu Ag',
        email: 'vasu@gmail.com',
        contact: '61726781'
      },
      notes:{
        address: 'GHHASKN'
      },
      theme: {
        color: '#F37254'
      }
    };

    var razorPayObject = new Razorpay(options);
    razorPayObject.open();
  }

  processResponse(resp: any){
    console.log(resp);
    this.totalAmount -= this.productAmount;
    this.detailsService.updatePrice(this.totalAmount).subscribe();
    if(this.detailsService.isCouponApplied){
      this.detailsService.deleteCoupon(this.detailsService.couponCodeToRedeem).subscribe();
      this.detailsService.isCouponApplied = false;
      this.detailsService.couponCodeToRedeem = "";
    }
    console.log(this.totalAmount);
  }
}
