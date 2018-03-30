import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';

@Component({
	selector: 'app-dashboard',
	templateUrl: './full-layout.component.html'
})

export class FullLayoutComponent implements OnInit {

	public disabled: boolean = false;
	public status: { isopen: boolean } = { isopen : false };
	public errorMessage: string;

	constructor(private loginServ : LoginService,private router: Router) {
	   
	     // Content
	}

	public toggled(open: boolean):void {
		console.log('Dropdown is now: ', open);
	}

	public toggledDropdown($event: MouseEvent): void {
		$event.preventDefault();
		$event.stopPropagation();
		this.status.isopen = !this.status.isopen;
	}

	ngOnInit(): void {}

	public logout() {
		this.loginServ.logout().subscribe(res => this.dealResult(res),
                       error =>  this.errorMessage = <any>error);;
	}

	public dealResult(res : any) {

		let rspCode = res.rspCode;
		if('0000' == rspCode) {
			this.router.navigate(['login']);
		}

	}
}