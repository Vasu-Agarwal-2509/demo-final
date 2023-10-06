import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CouponsComponent } from './coupons/coupons.component';
import { NewsComponent } from './news/news.component';
import { AboutusComponent } from './aboutus/aboutus.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { CartServiceComponent } from './cart-service/cart-service.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { ProfileComponent } from './profile/profile.component';

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'home/:isUserValid', component: HomeComponent},
  {path: 'coupons', component: CouponsComponent},
  {path: 'news', component: NewsComponent},
  {path: 'aboutus', component: AboutusComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'cart', component: CartServiceComponent},
  {path: 'cart/:emailid', component: CartServiceComponent},
  {path: 'productDetail', component: ProductDetailComponent},
  {path: 'productDetail/:prodId', component: ProductDetailComponent},
  {path: 'profile', component: ProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
