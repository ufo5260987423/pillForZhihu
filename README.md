# 知乎“药丸”计划技术归约

------

## 0、序章
本序章自称为《知乎“药丸”计划技术归约》（以下简称归约）的纲领性文字，归约的其他部分与本段文字的冲突最终必然以本段文字为准。本序章内部的冲突以在文段中位置靠前的为准。
归约遵循开放的技术原则，即：为了实现一个在技术上和产品上都相当开放的开发计划，欢迎任何人按照归约的规定以任何方式来进行开发。
归约以《药丸论坛筹备》为产品上的指导意见。
## 1、许可证
归约的标的物遵循Apache2.0许可证。本归约一切没有说明的事项以Apache2.0许可证为标准。

## 2、版本号
* 版本号的一切权利依据许可证分配。
* 版本号标准格式为x1.x2.x3-[SNAPSHOT|RELEASE|ALPHA|BETA]。
* x1、x2、x3为十进制整数。x3表示非产品性的、纯粹技术性的版本更迭。x2表示产品的版本更迭。x1表示在产品更迭的基础上的打包更迭，即该版本更迭一般的集合发布之前开发的多种产品特性，该版本号的更迭是向前兼容的。
    * SNAPSHOT表示内部开发版本，只推荐供测试与开发使用。
    * RELEASE表示公开发布的产品版本，可按照相关说明做许可证范围内的一切方式使用。
    * ALPHA表示内部测试版本。
    * BETA表示公开测试版本。

## 3、主要编程语言与包管理
* 使用Java进行开发，JVM选择HotSpot Jdk1.7及以上版本。
* 使用Maven3.1.6以上版本进行包管理。

## 4、对自由开发者的态度
欢迎自由开发者以一切方式参与归约标的产品的开发。
所谓的一切方式，在此指明包括任何的编程语言与任何的产品功能都可以开发。
所谓的欢迎是指，在方便的时候将自有开发者以一切方式开发出来的项目在开发者允许的情况下合并到项目当中去。

## 5、TODO List
求设计师们开发个网页……我已经把接口什么的准备好咯……
页面大概包括：
* 登录与注册页面
* 多个问题与回答的页面
* 单个问题多个回答的页面

要求尽可能的简洁，metro风格我看就很不错。当然，你可以选择任何风格。强制要求只有一条：对于html、css什么的命名必须规，以方便后来者往里面加东西。
