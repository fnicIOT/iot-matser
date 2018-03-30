import { Component, OnInit,ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { DeviceService } from '../../services/device.service';
import { HttpService } from './http.service';
import { ToastrService } from 'ngx-toastr'; 
import { BaseComponent } from '../base.component'; 
import { EChartOption, ECharts } from 'echarts-ng2';

@Component({
  templateUrl: 'envMonitor.component.html'
})
export class EnvMonitorComponent extends BaseComponent implements OnInit {

	constructor(
    	public router: Router,
    	public deviceServ : DeviceService,
    	public toastrService: ToastrService) {
    	super(toastrService,router);

    }

    @ViewChild('tempChart') tempChart: ECharts;
    @ViewChild('humChart') humChart: ECharts;
    @ViewChild('pm25Chart') pm25Chart: ECharts;

	ngOnInit() : void {

		//setInterval("changeColor()",200);
		this.fontColor = setInterval(()=>{
			this.changeColor();//检测变化
		},300);
		this.queryEnvStat();
	}

	public fontColor : any = {};

	public changeColor(){
	//var color="#f00|#0f0|#00f|#880|#808|#088|yellow|green|blue|gray";
		let color="#3D3D3D|black|gray";
		let colorList = [];
		colorList = color.split("|");

		let rand = Math.random()*colorList.length;
		this.fontColor = colorList[parseInt(rand.toString())];
	}
	

	public queryEnvStat() {
		this.deviceServ.queryEnvStat().subscribe(res => this.dealQueryEnvStat(res),
                       error =>  this.errorMessage = <any>error);
	}

	public dealQueryEnvStat(res : any) {
		res.tempList.forEach( obj => {
			this.tempDataList.push(this.dateFormat2(obj.objectData.timestamp));
			this.tempValueList.push(Math.round(obj.objectData._avg_temperature));
		});

		this.tempChart.setOption(this.temperatureOption);

		res.humList.forEach( obj => {
			this.humDataList.push(this.dateFormat2(obj.objectData.timestamp));
			this.humValueList.push(Math.round(obj.objectData._avg_humidity));
		});

		this.humChart.setOption(this.humidityOption);

		res.pmList.forEach( obj => {
			this.pmDataList.push(this.dateFormat2(obj.objectData.timestamp));
			this.pmValueList.push(Math.round(obj.objectData._avg_pm_2_5));
		});

		this.pm25Chart.setOption(this.pm25Option);

		res.paramList.forEach( obj => {
			if(obj.objectData.room_type == '2') {
				this.hall = obj.objectData;
			} 
		});
	}

	public hall : any = {};
	public tempDataList : Array<any> = new Array<any>();
	public tempValueList : Array<any> = new Array<any>();
	public humDataList : Array<any> = new Array<any>();
	public humValueList : Array<any> = new Array<any>();
	public casDataList : Array<any> = new Array<any>();
	public casValueList : Array<any> = new Array<any>();
	public pmDataList : Array<any> = new Array<any>();
	public pmValueList : Array<any> = new Array<any>();

	public temperatureOption = {
    	title: {
	        text: '温度图'
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            label: {
	                backgroundColor: '#6a7985'
	            }
	        }
	    },
	    // legend: {
	    //     data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
	    // },
	    toolbox: {
	        feature: {
	            saveAsImage: {}
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            interval:20,
	            data : this.tempDataList
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'温度',
	            type:'line',
	            stack: '总量',
	            areaStyle: {normal: {color:'rgb(178, 212, 192)'}},
	            lineStyle: {normal: {color:'rgb(178, 212, 192)'}},
	            data:this.tempValueList
	        },
	        {
	            name:'温度',
	            type:'line',
	            stack: '总量',
	            areaStyle: {normal: {color:'rgb(208, 111, 107)'}},	
	            lineStyle: {normal: {color:'rgb(208, 111, 107)'}},
	            data:[30, 32, 26, 28, 29, 33, 36]
	        },
	        {
	            name:'温度',
	            type:'line',
	            stack: '总量',
	            areaStyle: {normal: {color:'rgb(141, 185, 190)'}},
	            lineStyle: {normal: {color:'rgb(141, 185, 190)'}},
	            data:[20, 22, 36, 38, 39, 23, 26]
	        }
	    ]
	};

	public humidityOption = {
    	title: {
	        text: '湿度图'
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            label: {
	                backgroundColor: '#6a7985'
	            }
	        }
	    },
	    // legend: {
	    //     data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
	    // },
	    toolbox: {
	        feature: {
	            saveAsImage: {}
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : this.humDataList
	        }
	    ],
	    yAxis : [
	        {
	           itype : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'湿度',
	            type:'line',
	            stack: '总量',
	            areaStyle: {normal: {color:'rgb(178, 212, 192)'}},
	            lineStyle: {normal: {color:'rgb(178, 212, 192)'}},
	            data:this.humValueList
	        },
	        {
	            name:'湿度',
	            type:'line',
	            stack: '总量',
	            areaStyle: {normal: {color:'rgb(208, 111, 107)'}},	
	            lineStyle: {normal: {color:'rgb(208, 111, 107)'}},
	            data:[20,30,40,32,35,29,25]
	        },
	        {
	            name:'湿度',
	            type:'line',
	            stack: '总量',
	            areaStyle: {normal: {color:'rgb(141, 185, 190)'}},
	            lineStyle: {normal: {color:'rgb(141, 185, 190)'}},
	            data:[30,20,30,42,45,39,35]
	        }
	    ]
	};

	public pm25Option = {
	    title: {
	        text: 'PM2.5图'
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            label: {
	                backgroundColor: '#6a7985'
	            }
	        }
	    },
	    // legend: {
	    //     data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
	    // },
	    toolbox: {
	        feature: {
	            saveAsImage: {}
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : this.pmDataList
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'PM2.5',
	            type:'line',
	            stack: '总量',
	            areaStyle: {normal: {color:'rgb(178, 212, 192)'}},
	            lineStyle: {normal: {color:'rgb(178, 212, 192)'}},
	            data:this.pmValueList
	        },
	        {
	            name:'PM2.5',
	            type:'line',
	            stack: '总量',
	            areaStyle: {normal: {color:'rgb(208, 111, 107)'}},	
	            lineStyle: {normal: {color:'rgb(208, 111, 107)'}},
	            data:[50,100,70,80,60,50,90]
	        },
	        {
	            name:'PM2.5',
	            type:'line',
	            stack: '总量',
	            areaStyle: {normal: {color:'rgb(141, 185, 190)'}},
	            lineStyle: {normal: {color:'rgb(141, 185, 190)'}},
	            data:[60,50,40,80,60,70,50]
	        }
	    ]
	};
}
