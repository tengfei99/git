cd D:\lishicun\logs
del *.log
cd D:\lishicun\UKBERSEE1
set CLASSPATH=.;%CLASSPATH%;jsch-0.1.41.jar
java LoadLogTool
