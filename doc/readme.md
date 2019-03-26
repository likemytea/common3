打包时使用   mvn clean package 报错
需要使用       mvn clean package -DskipTests
安装到本地maven仓库  mvn install -DskipTests
另外eclipse有依赖它的包的项目使用jar包时，需要把eclipse项目中的项目close掉，其它项目才会引用maven仓库中的jar包。