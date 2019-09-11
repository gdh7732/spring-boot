docker run -d -p 9876:9876 -v /data/namesrv/logs:/root/logs -v /data/namesrv/store:/root/store --name rmqnamesrv  rocketmqinc/rocketmq:4.4.0 sh mqnamesrv
docker run -d -p 10911:10911 -p 10909:10909 -v /data/broker/logs:/root/logs -v /data/broker/store:/root/store --name rmqbroker --link rmqnamesrv:namesrv -e "NAMESRV_ADDR=namesrv:9876" rocketmqinc/rocketmq:4.4.0 sh mqbroker
docker run -e "JAVA_OPTS=-Drocketmq.namesrv.addr=127.0.0.1:9876 -Dcom.rocketmq.sendMessageWithVIPChannel=false" -p 8088:8080 -d --name rmq-dashboard styletang/rocketmq-console-ng

docker start rmqnamesrv rmqbroker