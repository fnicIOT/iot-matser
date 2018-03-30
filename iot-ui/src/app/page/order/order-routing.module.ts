import { NgModule } from '@angular/core';
import { Routes,RouterModule } from '@angular/router';

import { OrderComponent } from './order.component';
import { ShoppingCartComponent } from './shoppingCart.component';
import { PurchaseQosComponent } from './purchaseQos.component';

const routes: Routes = [
  {
    path: 'order',
    component: OrderComponent,
    data: {
      title: '订单'
    }
  },
  {
    path: 'shoppingCart',
    component: ShoppingCartComponent,
    data: {
      title: '购物车'
    }
  },
  {
    path: 'purchase/:id',
    component: PurchaseQosComponent,
    data: {
      title: '购买'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderRoutingModule {}
