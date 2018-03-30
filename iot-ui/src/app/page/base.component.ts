import { ToastrService } from 'ngx-toastr';  
import { Router } from '@angular/router';

export class BaseComponent {

	public errorMessage: string;

	constructor(public toastrService : ToastrService,public router : Router) {
	    
	}

	// constructor(public router : Router) {
	    
	// }

	public dealRsp(res : any,link : string) {

		let rspCode = res.rspCode;
		let rspDesc = res.rspDesc;
		if(rspCode == '0000') {
			console.log('@@@@@@@@1111');
			this.toastrService.success(rspDesc, null);
			if(link != null) {
				console.log('@@@@@@@@' + link);
				this.router.navigate([link]); 
			}
		}else if (rspCode == '9999') {
			this.toastrService.error(rspDesc,null);
		}else {
			this.toastrService.warning(rspDesc,null);
		}
	    
	}

	public dateFormat(longTypeDate) {
		let dateType = "";  
	    let date = new Date();  
	    date.setTime(longTypeDate); 
	    dateType = date.getFullYear()+"-"+ this.getMonth(date)+"-"+ this.getDay(date);//yyyy-MM-dd格式日期
	    return dateType;
	}

	public dateFormat2(longTypeDate) {
		let dateType = "";  
	    let date = new Date();  
	    date.setTime(longTypeDate); 
	    dateType = this.getMonth(date)+"-"+ this.getDay(date);//yyyy-MM-dd格式日期
	    return dateType;
	}

	//返回 01-12 的月份值   
	public getMonth(date){  
	    let month = 0;  
	    month = date.getMonth() + 1; //getMonth()得到的月份是0-11  

	    return month;  
	}  
	//返回01-30的日期  
	public getDay(date){  
	    let day = "";  
	    day = date.getDate();  

	    return day;  
	}
}