制作钥匙：

在装有java环境里
1，切换到DatGen.java目录,比如E目录
2，E:\>javac -d ./ DatGen.java
3，E:\>java DatGen '{"ipList":["127.0.0.1"],"codeList":["702001","702002","702004","702005","702006","702010","702011","702012","702013","702014","702015","702016","702017","702018","702019","702200","702201","702202","702203","702300","702301","702302","702303","702304","702305","702306","702307","702380","702381","702400","702401","702402","702403","702404","702450","702451","702452","702453","702454","702455","702456","702457","702458","702459","702498","702499","702502","702503","702504","702505","702515","702516","702517","702600","702601","702602","702603","702604","702700","702701","702702","702703","702704","702705","702706","702707","702708","702709","702710","702800","702801","702802","702803","702804","702805","702900","702980","702981","702982","702983","702984","702985","702986","702987","702988","7020051","7020052","7020053","7020101","7020102","7020103","7020104","7020105","7020106","7020107","7020108","7020201","7020202","7020203","7020204"]}'

4，拷贝当前目录下的“config.dat”到项目“{项目路径}\src\main\resources”
5，验证：项目跑起来。在浏览器输入“http://IP:端口/项目名/api”，出现版本信息即成功。





部署步骤：
1，切换到本地tomcat部署包所在目录,例如
  cd /Users/myb858/Documents/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp5/wtpwebapps
  
2，打包
  rm -rf account.tar.gz
  tar zcvf account.tar.gz xn-account/
  scp -P54012 ./xn-account.tar.gz root@121.42.196.238:/home/
  scp -P57652 ./xn-account.war root@121.43.101.148:/home/
  scp -P53422 ./xn-account.war root@115.29.140.31:/home/
  
  scp -P 53422 xn-account.war root@115.29.140.31:/home/tomcat_GJS_account_biz/webapps
  scp -P 57652 xn-account.war root@121.43.101.148:/home/tomcat_GJS_account_biz/webapps
  scp -P 54012 xn-account.war root@121.42.196.238:/home/tomcat_GJS_account_biz/webapps
  
3，部署
  ssh root@121.42.196.238 -p 54012
  ssh root@121.43.101.148 -p 57652
  ssh root@115.29.140.31 -p 53422
  
  cd /home/tomcat_GJS_account_biz/webapps
  rm -rf xn-account.war
  cp ./xn-account/WEB-INF/classes/application.properties .
  cp ./xn-account/WEB-INF/classes/config.properties .
  cp ./xn-account/WEB-INF/classes/merchantInfo.properties .
  rm -rf xn-account/
  mv /home/xn-account.war .
  
  mv -f application.properties ./xn-account/WEB-INF/classes/
  mv -f config.properties ./xn-account/WEB-INF/classes/
  mv -f merchantInfo.properties ./xn-account/WEB-INF/classes/
  
4,起停tomcat_develop_account
  ../bin/shutdown.sh
  ../bin/startup.sh
  

http://121.43.101.148:8702/xn-account/api
http://115.29.140.31:8702/xn-account/api

