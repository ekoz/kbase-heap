JVM 浅析和线上系统性问题分析思路
=======================

## JVM内存模型

![JVM内存模型](/heap.png)

## JVM如何判断对象是否可回收
### 引用计数器
### GC Roots
## GC算法
### 标记-清除算法
### 复制算法
### 标记-整理算法
### 分代回收算法
## GC回收器
### Serial收集器
### PerNew收集器
### Parallel Scavenge收集器
### Serial Old收集器
### Parallel Old收集器
### CMS收集器
### G1收集器
## JVM常用配置

### 内存相关
	-Xmn
	-Xms
	-Xmx
	-XX:PermSize
	-XX:MaxPermSize
	-Xss
	-XX:SurvivorRatio=5

### 回收器相关
	-XX:+UseParNewGC 
	-XX:+CMSParallelRemarkEnabled 
	-XX:+UseConcMarkSweepGC

### GC日志相关
	-XX:+PrintGCDetails
	-XX:+HeapDumpOnOutOfMemoryError

### 其他
详见 [JVM系列三:JVM参数设置、分析](https://www.cnblogs.com/redcreen/archive/2011/05/04/2037057.html)

## 线上系统性问题分析思路和常用命令

* df –lh 查询磁盘大小，请重视linux挂载点概念

![磁盘大小](/df.png)

* free –g/-m 查询内存大小
* netstat –an|grep CLOSE_WAIT –c 查询等待关闭连接数，详细指令如下：

		netstat -n | awk '/^tcp/ {++S[$NF]} END {for(a in S) print a, S[a]}'

* ulimit -a / ulimit -u 102400 / ulimit -n 102400

* top

		Shift+p 按CPU占用率倒序
		Shift+m 按内存占用率倒序

* top –H –p pid 通过指定的进程id查看线程id，可以查看到具体哪些线程CPU占用率飙高，将线程id转成16进制，可以在cpu100.txt中找到对应的线程信息
* /home/xiaoi/java/jdk/bin/jstack pid > /tmp/xiaoi/cpu100.txt
* /home/xiaoi/java/jdk/bin/jstat –gcutil pid 3000
* /home/xiaoi/java/jdk/bin/jmap -heap pid

## IBM J9 VM
详见 [垃圾收集策略，第 1 部分](https://www.ibm.com/developerworks/cn/java/j-ibmjava2/)
[垃圾收集策略，第 2 部分](https://www.ibm.com/developerworks/cn/java/j-ibmjava3/)

## JRockit
	/home/Oracle/Middleware/wlserver_12.1/common/bin/commEnv.sh
	/home/Oracle/Middleware/user_projects/domains/srcb/bin/setDomainEnv.sh
