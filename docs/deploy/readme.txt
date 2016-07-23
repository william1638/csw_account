制作钥匙：

在装有java环境里
1，切换到DatGen.java目录,比如E目录
2，E:\>javac -d ./ DatGen.java
3，E:\>java DatGen '{"ipList":["127.0.0.1"],"codeList":["802000","802001","802002","802010","802011","802012","802013","802020","802021","802030","802031","802100","802101","802110","802111","802112","802120","802122","802123","802200","802201","802210","802211","802212","802213","802220","802221","802222","802223","802300","802301","802310","802311","802700","802710","802711","802720","802721","802900","802901","802902","802903","802904"]}'

4，拷贝当前目录下的“config.dat”到项目“{项目路径}\src\main\resources”
5，验证：项目跑起来。在浏览器输入“http://IP:端口/项目名/api”，出现版本信息即成功。





部署步骤：
1，切换到本地tomcat部署包所在目录,例如
  cd /Users/myb858/Documents/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp5/wtpwebapps
  
2，打包
  rm -rf account.tar.gz
  tar zcvf account.tar.gz std-account/
  scp -P57652 ./account.tar.gz root@121.43.101.148:/mnt/wwwroot/
  
    scp -P57652 ./std-account.war root@121.43.101.148:/mnt/wwwroot/
  


3，部署
  ssh root@121.43.101.148 -p 57652
  
  cd /mnt/wwwroot/tomcat_STD_account/webapps
  rm -rf std-account.war
  cp ./std-account/WEB-INF/classes/application.properties .
  cp ./std-account/WEB-INF/classes/config.properties .
  rm -rf std-account/
  mv /mnt/wwwroot/std-account.war .

4,起停tomcat_STD_account

  mv -f application.properties ./std-account/WEB-INF/classes/
  mv -f config.properties ./std-account/WEB-INF/classes/
  

  ../bin/shutdown.sh
  ../bin/startup.sh
  
http://121.43.101.148:7102/std-account/api

