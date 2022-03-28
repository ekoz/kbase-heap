# 工作流引擎(flowable)

## flowable-ui

flowable-ui 在6.3.x版本中，主要是 flowable-idm.war 和 flowable-modeler.war 两个包，可以直接次采用 java -jar 启动两个war包，官方推荐是将 war 放进 tomcat 中运行

flowable-ui 6.6.0 版本开始，提供了 flowable-spring-boot-starter-ui-idm 和 flowable-spring-boot-starter-ui-modeler 两个依赖，可以直接用 maven 打包

应用程序发布以后，流程会自动载入到 flowable 中 


act_re_deployment 流程部署表

act_re_procdef 流程定义表

act_ge_bytearray 通用的流程定义和流程资源

act_ge_property 系统相关属性

act_ru_execution 执行表

act_ru_task 正在进行的任务表

act_hi_taskinst 历史任务表

act_ru_variable 正在进行的变量表

act_hi_actinst 历史所有节点表