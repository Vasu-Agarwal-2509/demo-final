import { Component } from '@angular/core';
import { AuthDetails } from '../authdetails';
import { DetailsService } from '../details.service';
import { Router } from '@angular/router';
import { CustomerDetails } from '../customer-details';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  authDetail: AuthDetails = new AuthDetails();
  authDetail2: any;
  customerObj: CustomerDetails = new CustomerDetails();
  preferenceList: any[] = [];

  verificationCode: any;
  codeInput: any = false;
  buttonForSendingCode: any = true;
  isUserVerified: any = false;

  constructor(private detailService: DetailsService, private router: Router){}
  
  ngOnInit(){
    this.detailService.getUser().subscribe(data => this.authDetail2 = data);
  }

  verifyCode(){
    this.detailService.verifyEmail(this.verificationCode).subscribe(data => {
      if(data){
        this.isUserVerified = true;
      }
    else{
      alert("Code is invalid");
    }}); 
  }

  addUser(){
    this.customerObj.emailId = this.authDetail.emailid;
    console.log(this.authDetail);

    this.customerObj.preferences = this.preferenceList;
    console.log("Preferences List: " + this.preferenceList);

    this.detailService.addCustomer(this.customerObj).subscribe();
    this.detailService.addCustomerInCart(this.authDetail.emailid).subscribe();
    this.router.navigate(['/login']);
  }

  sendVerificationCode(){
    this.authDetail.roles = "USER";
    this.detailService.sendVerificationCode(this.authDetail).subscribe();
    this.codeInput = true;
    this.buttonForSendingCode = false;
  }

  shoesChecked(event: any){
    if(event.target.checked == true){
      this.preferenceList.push("shoes");
    }
    else{
      // if(this.preferenceList.includes("shoes")){
      //   this.preferenceList.pop("shoes");
      // }
    }
  }

  watchesChecked(event: any){
    if(event.target.checked == true){
      this.preferenceList.push("watch");
    }
    else{
      // if(this.preferenceList.includes("shoes")){
      //   this.preferenceList.pop("shoes");
      // }
    }
  }

  mobileChecked(event: any){
    if(event.target.checked == true){
      this.preferenceList.push("mobile");
    }
    else{
      // if(this.preferenceList.includes("shoes")){
      //   this.preferenceList.pop("shoes");
      // }
    }
  }
}
