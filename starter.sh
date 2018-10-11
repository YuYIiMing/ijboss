echo "app_profiles_active : ${spring_profiles_active}"
date

if [[ ${spring_profiles_active} == 'pre' ]] || [[ ${spring_profiles_active} == 'prod' ]];
then
  java -Djava.security.egd=file:/dev/./urandom -Xms2048m -Xmx2048m -Xmn768M -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -Xrs -Xss256k  -XX:+UseConcMarkSweepGC -XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses -XX:+CMSClassUnloadingEnabled -XX:+CMSScavengeBeforeRemark -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=80 -XX:SurvivorRatio=10 -XX:ErrorFile=/home/hs_err_pid%p.log -Xloggc:/home/gc.log -XX:HeapDumpPath=/home -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintClassHistogramBeforeFullGC -XX:+PrintClassHistogramAfterFullGC -XX:+PrintCommandLineFlags -XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCApplicationStoppedTime -XX:+PrintTenuringDistribution -XX:+PrintHeapAtGC -Duser.timezone=GMT+5.5 -Dspring.profiles.active=${spring_profiles_active} -jar /app.jar
  #java -Duser.timezone=GMT+5.5 -Dspring.profiles.active=${spring_profiles_active} -jar /app.jar
else
  java -Djava.security.egd=file:/dev/./urandom -Xms750m -Xmx750m -XX:MaxNewSize=128M -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xrs -Xss256k  -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:-UseGCOverheadLimit -XX:HeapDumpPath=/home/jvm-dump.hprof -XX:+DisableExplicitGC -Xloggc:/home/gc.log -Duser.timezone=GMT+8 -Dspring.profiles.active=${spring_profiles_active} -jar /app.jar
fi