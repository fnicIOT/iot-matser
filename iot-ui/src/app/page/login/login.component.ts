import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { LoginService } from '../../services/login.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html'
})

export class LoginComponent implements OnInit {
	private user: User = new User();

	public errorMessage: string;

	private res: any;

	constructor(
		private userServ: UserService,
		private loginServ: LoginService,
		private router: Router ) {	
	     // Content
	}

	public ngOnInit() 
	{
		window.dispatchEvent( new Event('resize') );
	}

	private login() {

		console.log(this.user);


		this.loginServ.login(this.user).subscribe(res => this.dealRsp(res),
                       error =>  this.errorMessage = <any>error);
		
		// if () {
		// 	let user1 = new User( {
		// 		email: 'test@qq.com',
		// 		userName: 'test'
		// 	});

		// 	user1.connected = true;

		// 	this.userServ.setCurrentUser(user1);

		// 	this.router.navigate( ['home'] );
		// }else {

		// }
		// if(1 ===1 ) {
		// 	let user1 = new User( {
		// 		email: 'test@qq.com',
		// 		userName: 'test'
		// 	});

		// 	user1.connected = true;

		// 	this.userServ.setCurrentUser(user1);

		// 	this.router.navigate( ['home'] );
		// }else {

		// }
	}

	private dealRsp(res : any) {
		console.log(res);

		if('0000' === res.rspCode) {
			let user1 = new User( {
				email: res.userEmail,
				userName: res.account,
				token: res.token,
				connected: true
			});

			this.userServ.setCurrentUser(user1);

			this.router.navigate( ['iot/device'] );	
		}else {
			this.errorMessage = res.rspDesc;
		}	
	}
}