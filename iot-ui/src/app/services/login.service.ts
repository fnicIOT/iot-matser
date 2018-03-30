import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Http  } from '@angular/http';
import { HttpService } from './http.service';

@Injectable()
export class LoginService extends HttpService {

	public loginUrl : string = '/login';

	public logoutUrl : string = '/logout';

	constructor (public http: Http) { 
		super(http);
	}

	public login(user : User) {
		return this.postReq(this.loginUrl ,{ userName:user.userName, password: user.password });
	}

	public logout() {
		return this.postReq(this.logoutUrl ,{});
	}
}