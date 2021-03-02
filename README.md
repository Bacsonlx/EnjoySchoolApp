# # 校园服务类软件 --- 享校

该软件是笔者第一次完成的一个Android项目，包括用户的注册登录、数据的增删改查、通知、广播、文件存储、底部导航栏、WebView等( 虽然都只是涉及一些基础的东西 ，不过UI做得还是挺好看的org)...
虽然比较基础，但还是激发了我对Android开发的兴趣，从一个使用安卓App的用户到一个入门级的安卓开发小白，一步一步的学习，一点一滴的积累使我收获了许多，并对于一款软件的诞生、发展、维护有更多的了解。

该软件主要分为三个功能：**享用、享玩、享吃**。其中享用定为四个方面：**打印空间、洗衣空间、烹饪空间以及学习空间**。打印空间内置附近打印设备的查看以及使用情况，打印设备支持远程操作和自动文档识别调整。洗衣空间内置共享洗衣设备，用户通过app可以直接获取机器运行状态以及当前等待人数和预计等待时间。学习空间提供二手书交易的平台，学生可以通过app进行二手书的出售及购买。烹饪空间需要学生通过app提前预约，烹饪空间提供小厨房服务，结束使用后由系统自动判断是否规范操作。(由于没有具体的数据，所以所有数据均通过SQLite进行增删改查)。 
享玩主要提供校内**新闻资讯**的查看，以及校内活动的发布，学生可以点击新闻跳转至浏览器查看(简单地调用WebView)。享吃模块可以访问附近的校园超市进行**商品的购买**。

( 以下是一些UI的截图， 可以简单看一看)

![image](https://user-images.githubusercontent.com/49552090/109638691-44351e80-7b89-11eb-9fbd-393d8f67f569.png)

**图1登录界面**

![image](https://user-images.githubusercontent.com/49552090/109638726-50b97700-7b89-11eb-87a5-55bfe4ffb8dd.png)

**图2享用界面**

![image](https://user-images.githubusercontent.com/49552090/109638751-5b740c00-7b89-11eb-8bae-e196c3a2243c.png)

**图3享玩界面**

![image](https://user-images.githubusercontent.com/49552090/109638778-63cc4700-7b89-11eb-823f-702d0c56d408.png)

**图4享买界面**

 ![image](https://user-images.githubusercontent.com/49552090/109638804-6a5abe80-7b89-11eb-898d-f6f632333ee0.png)

​**图5个人中心界面**
