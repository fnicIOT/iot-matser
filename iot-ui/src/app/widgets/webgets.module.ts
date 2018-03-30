import { NgModule } from '@angular/core';
import { PaginationComponent } from './pagination.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [FormsModule,CommonModule,RouterModule
  ],
  declarations: [
    PaginationComponent
  ],
  exports: [
  	PaginationComponent
  ]
})
export class WebgetsModule { }