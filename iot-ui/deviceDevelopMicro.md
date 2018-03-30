<h3 id='4.5'>Device开发</h3>
<h4 id='4.5.1'>Device开发简介</h4>
1 功能与目的

为了快速开发和业务逻辑相关的设备端程序，提高开发者效率，提高企业产品研发/上线的效率，Fnic_Micro提供了设备接入sdk的lib库和接口c文件。方便开发者将代码添加到正在开发中或者已有的成熟的产品中。	


2 主要功能

序号|功能名称|	详细内容
---|---|---
1.|	通讯加密|	自动将通讯过程中的数据加密解密，保障用户数据安全
2.| 注册认证|	自动跟服务器连接并发送认证
3.|	心跳保持|	自动发送和处理心跳包，可以维持长连接或实时获取设备状态
4.|	消息交互|	与云端、APP端进行消息交互通信
5.|	自动重连|	在网络情况导致tcp连接断开时，自动重新建立tcp连接
6.|	实时消息|	接收云端实时推送的数据
7.|	定时任务|	定时触发自定义任务响应


<h4 id='4.5.2'>开发准备</h4>
<h5 id='4.5.2.1'>开发准备</h5>
1 SDK发布库
fnic发布的设备端SDK为fnic_micro_adpter_*.c fnic_micro_api_*.h和fnic_micro_sdk_lib_*.lib

>注意:
>
>fnic_sdk_lib_*.lib为fnic设备sdk核心库文件，fnic_micro_adpter_*.c为用户实现功能接口文件，fnic_micro_api_*.h为fnic_micro设备端核心功能接口文件

2 开发环境设置

- 设备的MCU需要空余2k+的RAM和2k+的ROM
- 设备支持建立TCP连接能力(不区分wifi和gprs)
- 编译器支持c语言编译,并至少支持C98标准
- 原软件架构提供定时器等能够以固定周期调用函数的能力
- 设计堆栈深度不小于 256 bytes

<h4 id='4.5.3'>开发指南</h4>
<h5 id='4.5.3.1'>添加设备端sdk到工程</h5>
开发者在使用设备端sdk开发设备时，在保证原有工程能正确编译的情况下，只需要将fnic_micro_sdk_lib_*.lib和fnic_micro_adpter_*.c添加到工程中即可。使用IDE作为开发环境的，将fnic_micro_sdk_lib_*.lib和fnic_micro_adpter_*.c添加到工程文件列表中。使用脚本编译开发时，在Makefile中加入 -L fnic_micro_sdk_lib_*.lib,并将fnic_micro_adpter_*.c添加到Makefile中指定的文件夹下即可

<h5 id='4.5.3.2'>修改fnic_micro_adpter_*.c文件实现适配</h5>
1 修改设备连接的服务器的IP地址
```
  char server_ip[4]={112,80,35,14};
```

2 定义设备ID
```
/*********************************************  */
/*  定义设备ID  每个设备对应唯一的Access_Token     */
/*  根据需求定义设置唯一Access_Token  ，不可重复    */
/************************************************/
char  Access_Token[65] = "rXij6G4cWbA4vFYo0sSf";
```

3 实现网络连接初始化功能
```
//网络连接初始化
//如果网络初始化由别处处理，此处可以留空
void Fnic_Network_Init(void)
{
	SIM800_Tcp_Init();
}
```

4 实现获取网络状态功能
```
//获取网络状态
uint8_t Fnic_Check_Net_Status(void)
{
	return FNIC_RET_OK;
}
```

5 实现建立TCP连接功能
```
//建立TCP连接，入参为IP地址和端口号
//返回值 失败:-1  否则返回socket号
int32_t Fnic_Tcp_Connect(char *pIPString,uint16_t port)
{
	
	return SIM800_Tcp_Connect(pIPString,port);

}
```

6 实现关闭TCP连接功能
```
//关闭TCP连接，入参为socket号
//返回值 失败:1 成功 0
uint8_t Fnic_Tcp_Close(uint32_t socket)
{
	uint8_t ret;
	
	ret = SIM800_Tcp_Close();
	
	return ret;
}
```

7 实现关闭TCP数据发送功能
```
//发送TCP数据，入参为socket号，数据指针，数据长度
//返回值 失败:1 成功 0
uint8_t Fnic_Tcp_Send(uint32_t socket,char *pdata,uint16_t len)
{
	uint8_t ret;
	
	ret = SIM800_Tcp_Send(pdata,len);
	
	return ret;
}
```

8 实现关闭TCP数据接收功能
```
//接收TCP数据，入参为socket号，数据指针，数据长度
//返回值 失败:1 成功 0
uint8_t Fnic_Tcp_Recv(uint32_t socket,char *pdata,uint16_t *plen)
{
	uint8_t ret;
	
	ret = SIM800_Tcp_Recv(pdata,plen);
	
	return ret;
}
```

9 向云端发送消息
```
sprintf(meassage,"{\"temperature\": %d, \"humidity\": %d, \"HCHO\": %d.%02d}",temperature,humidity,HCHO / 100,HCHO % 100);
mqtt_publish("v1/devices/me/attributes",meassage);
```

10 接收订阅云端数据
```
memset(meassage,0,sizeof(meassage));
if(++sendflag == 10)
{
	sendflag = 0;
	Mqtt_Publish_Timer(NULL,NULL);
}
else if(mqtt_subscrib_recvice(meassage) == 1)
{
	cJSON *root;
	char *pmethod = NULL;
	int value;
	root = cJSON_Parse(meassage); 
	if(root != NULL)
	{
		pmethod = cJSON_GetObjectItem(root,"method")->valuestring;
		if(0 == strcmp(pmethod,"setValue"))
		{
			value = cJSON_GetObjectItem(root,"params")->valueint;
			printf("cJSON_Parse params Result = %d\r\n",value);
			if(value == 0)
			{
				WORK_LED_SET();
			}
			if(value == 1)
			{
				WORK_LED_CLR();
			}
		}
		
	}
	cJSON_Delete(root);	
	
}
```