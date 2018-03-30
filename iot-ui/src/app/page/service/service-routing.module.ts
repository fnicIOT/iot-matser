import { NgModule } from '@angular/core';
import { Routes,RouterModule } from '@angular/router';

import { UserComponent } from './user.component';
import { StrategyComponent } from './strategy.component';

const routes: Routes = [
  {
    path: 'user',
    component: UserComponent,
    data: {
      title: '用户'
    }
  },
  {
    path: 'strategy',
    component: StrategyComponent,
    data: {
      title: '策略'
    }
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServiceRoutingModule {}
