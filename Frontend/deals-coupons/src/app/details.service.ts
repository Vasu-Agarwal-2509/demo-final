import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthDetails } from './authdetails';
import { Observable, throwError } from 'rxjs';
import { Itemdetail } from './itemdetail';
import { Cartdetail } from './cartdetail';
import { CustomerDetails } from './customer-details';

@Injectable({
  providedIn: 'root'
})

export class DetailsService {

  
  public authDetailObj: AuthDetails = new AuthDetails();
  public token: any;
  public discountOnCouponCode = 0;
  public isCouponApplied = false;
  public couponCodeToRedeem = "";

  // private urlForRegister: string = "http://localhost:8081/authService/addAuthDetail";
  private urlForGeneratingCode = "http://localhost:8081/authService/sendVerificationCode";
  private urlForVerifyEmail = "http://localhost:8081/authService/verifyAccount/";
  private urlForGetUsername: string = "http://localhost:9090/authService/getAuthDetails";
  private urlForLogin: string = "http://localhost:8081/authService/validate";
  private urlForGetProducts: string = "http://localhost:9090/itemService/getProducts";
  private urlForProductListFromCustId = "http://localhost:9090/apigatewayService/getProductFromId/";
  private urlForCartTotal = "http://localhost:9090/apigatewayService/findCartTotal/";
  private urlForProductFromId = "http://localhost:9090/itemService/getProductById/";
  private urlForAddingProductInCart = "http://localhost:9090/cartService/addProductInCart";
  private urlForRemovingProductFromCart = "http://localhost:9090/cartService/removeProductFromCart/";
  private urlForAddingCustomerInCart = "http://localhost:9090/cartService/addCustomerInCart/";
  private urlForAddingCoupon = "http://localhost:9090/customerService/addCoupon/";
  private urlForGetCoupons = "http://localhost:9090/customerService/getAllCoupons/";
  private urlForAddingCustomerDetail = "http://localhost:8082/customerService/addCustomer";
  private urlForCreateTransaction = "http://localhost:8086/transactionService/createTransaction/";
  private urlForGeneratingToken = "http://localhost:8081/authService/generatetoken";
  private urlForCustomerDetails = "http://localhost:9090/customerService/getCustomer/";
  private urlForUpdatePrice = "http://localhost:9090/customerService/updatePrice/";
  private urlForDiscountOnCouponCode = "http://localhost:9090/itemService/getDiscountOnCouponCode/";
  private urlForDeleteCoupon = "http://localhost:9090/customerService/deleteCoupon/";

  constructor(private http: HttpClient) { }

  sendVerificationCode(authDetail: AuthDetails): Observable<any>{
    this.authDetailObj.emailid = authDetail.emailid;
    return this.http.post<any>(`${this.urlForGeneratingCode}`, authDetail);
  }
  verifyEmail(token: any): Observable<any>{
    return this.http.get<any>(this.urlForVerifyEmail + token);
  }
  getUser(): Observable<AuthDetails>{
    return this.http.get<AuthDetails>(`${this.urlForGetUsername}`);
  }
  validateUser(authDetail: AuthDetails): Observable<any>{
    this.authDetailObj.emailid = authDetail.emailid;
    // let pwd = authDetail.password;
    // const headers = new HttpHeaders({Authorization: 'Basic ' + btoa(uname+":"+pwd)})
    // return this.http.get<any>(this.urlForLogin, {headers, responseType:'text' as 'json'});
    return this.http.post<any>(`${this.urlForLogin}`, authDetail);
  }

  generateToken(authDetail: AuthDetails): Observable<any>{
    return this.http.post<any>(this.urlForGeneratingToken, authDetail);
  }
  getProducts(): Observable<Itemdetail[]>{
    return this.http.get<Itemdetail[]>(this.urlForGetProducts);
  }

  getProductList(custId: string): Observable<Itemdetail[]>{
    return this.http.get<Itemdetail[]>(this.urlForProductListFromCustId + custId);
  }

  getCartTotal(custId: string): Observable<any>{
    return this.http.get<any>(this.urlForCartTotal + custId);
  }

  getProductById(itemId: string): Observable<Itemdetail>{
    let tokenStr = 'Bearer ' + this.token;
    const headers = new HttpHeaders().set("Authorization", tokenStr);
    return this.http.get<Itemdetail>(this.urlForProductFromId + itemId, {headers, responseType:'text' as 'json'});
  }

  addProductInCart(cartObj: Cartdetail): Observable<string>{
    return this.http.post<string>(this.urlForAddingProductInCart, cartObj);
  }

  removeProductFromCart(itemId: string): Observable<any>{
    return this.http.get<any>(this.urlForRemovingProductFromCart + this.authDetailObj.emailid + "/" + itemId);
  }

  addCustomerInCart(customerId: any): Observable<any>{
    return this.http.get<any>(this.urlForAddingCustomerInCart + customerId);
  }

  addCoupon(couponCode: string): Observable<any>{
    return this.http.get<any>(this.urlForAddingCoupon + this.authDetailObj.emailid + "/" + couponCode);
  }

  getCoupons(): Observable<any[]>{
    return this.http.get<any[]>(this.urlForGetCoupons + this.authDetailObj.emailid);
  }

  addCustomer(customerObj: CustomerDetails): Observable<any>{
    return this.http.post<any>(this.urlForAddingCustomerDetail, customerObj);
  }

  currrentUser(){
    return JSON.parse(localStorage.getItem('currentUser')!);
  }

  logOut(){
    return localStorage.removeItem('currentUser');
  }

  createTransaction(amount: any): Observable<any>{
    return this.http.get<any>(this.urlForCreateTransaction + amount);
  }


  // addHeader(){

  // }

  getCustomerDetails(): Observable<any>{
    let tokenStr = 'Bearer ' + this.token;
    const headers = new HttpHeaders().set("Authorization", tokenStr);
    return this.http.get<any>(this.urlForCustomerDetails + this.authDetailObj.emailid, {headers, responseType:'text' as 'json'});
  }

  updatePrice(price: any): Observable<any>{
    return this.http.get<any>(this.urlForUpdatePrice + this.authDetailObj.emailid + "/" + price);
  }

  getDiscountFromCouponCode(couponCode: any): Observable<any>{
    return this.http.get<any>(this.urlForDiscountOnCouponCode + couponCode);
  }

  deleteCoupon(couponCode: any): Observable<any>{
    return this.http.get<any>(this.urlForDeleteCoupon + this.authDetailObj.emailid + "/" + couponCode);
  }
}
