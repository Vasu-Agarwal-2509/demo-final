import { Component } from '@angular/core';
import { DetailsService } from '../details.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.css']
})
export class CouponsComponent {

  couponList: any[] = [];

  constructor(private detailService: DetailsService, private router: Router){}

  ngOnInit(){
    this.detailService.getCoupons().subscribe(data => this.couponList = data);
  }

  redeemCoupon(coupon: any){
    this.detailService.getDiscountFromCouponCode(coupon).subscribe(data => {this.detailService.discountOnCouponCode = data; 
                                                                            this.detailService.isCouponApplied = true;
                                                                          this.detailService.couponCodeToRedeem = coupon})
    this.router.navigate(['/home']);
  }
}
