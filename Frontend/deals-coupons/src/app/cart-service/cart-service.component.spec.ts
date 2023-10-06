import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartServiceComponent } from './cart-service.component';

describe('CartServiceComponent', () => {
  let component: CartServiceComponent;
  let fixture: ComponentFixture<CartServiceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CartServiceComponent]
    });
    fixture = TestBed.createComponent(CartServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
