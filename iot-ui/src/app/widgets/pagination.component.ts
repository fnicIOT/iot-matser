import { Component,Input,Output,EventEmitter } from '@angular/core';

@Component ({
	selector:'pagination',
	template:`<ul class="pagination">
			  <li class="page-item"><a class="page-link" [routerLink]="['./']" 
             (click)=changePage(pageParams.pageNum-1)>Prev</a></li>
              <li class="page-item" *ngFor ="let page of pageList">
                <a class="page-link active" [routerLink]="['./']" *ngIf="pageParams.pageNum == page" (click)=changePage(pageParams.pageNum)>{{ page }}</a>
                <a class="page-link" [routerLink]="['./']" *ngIf="pageParams.pageNum != page" (click)=changePage(pageParams.pageNum)>{{ page }}</a>
              </li>
              <li class="page-item"><a class="page-link" [routerLink]="['./']" (click)=changePage(pageParams.pageNum+1)
            >Next</a></li></ul>`
}) 

export class PaginationComponent {

	@Input('pageParams') pageParams;

	@Output() changeCurPage: EventEmitter<number> = new EventEmitter();

  public pageList : any[] = [];

  constructor() {

  }

  ngOnInit() {

    console.log(this.pageParams);
    if( undefined === this.pageParams.count || this.pageParams.count === 0) {
       
       this.pageParams.disabled = true;
    }else {
       let pageCount = this.pageParams.count / this.pageParams.pageSize;

       console.log(pageCount);
       let curPage = this.pageParams.pageNum;

       if( pageCount > 5) {
        if( curPage > 3 && pageCount - curPage > 5) {
          this.pageList.push(curPage - 2);
          this.pageList.push(curPage - 1);
          this.pageList.push(curPage);
          this.pageList.push(curPage + 1);
          this.pageList.push(curPage + 2);
        }else if ( curPage > 3 && pageCount - curPage <= 5) {
          this.pageList.push(pageCount - 4);
          this.pageList.push(pageCount - 3);
          this.pageList.push(pageCount - 2);
          this.pageList.push(pageCount - 1);
          this.pageList.push(pageCount);
        }else {
          for(let i = 1; i <= 5; i++) {
           this.pageList.push(i);
         } 
        }        
       }else {
         for(let i = 1; i <= pageCount; i++) {
           this.pageList.push(i);
         }  
       }
    }
  }

	changePage(pageNum) {
    let vm = this;
    console.log('修改页码', pageNum);
    vm.pageParams.curPage = pageNum;
    vm.changeCurPage.emit(vm.pageParams.curPage);
  }
}