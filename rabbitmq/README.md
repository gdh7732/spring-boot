1、direct模式
    
    1.1 简单模式 hello
    一个生产者,一个队列,一个消费者,使用默认Exchange
    生产者:只需指定routingKey rabbitTemplate.convertAndSend(routingKey, object);
    消费者:监听该队列 @RabbitListener(queues = "hello")
    
    1.2 工作队列模式work
    一个生产者,一个队列,多个消费者,共享任务,排队消费
    功能：一个生产者，多个消费者，每个消费者获取到的消息唯一，多个消费者只有一个队列
    任务队列：避免立即做一个资源密集型任务，必须等待它完成，而是把这个任务安排到稍后再做。
    我们将任务封装为消息并将其发送给队列。后台运行的工作进程将弹出任务并最终执行作业。
    当有多个worker同时运行时，任务将在它们之间共享。
    生产者:只需指定routingKey;  rabbitTemplate.convertAndSend(routingKey, object);
    消费者:多个消费者监听同一队列 @RabbitListener(queues = "work")
    
    1.3 路由模式 Routing
    说明：生产者发送消息到交换机并且要指定路由key，消费者将队列绑定到交换机时需要指定路由key

2、fanout模式
    
    2.1 发布/订阅模式 Publish/Subscribe
    功能：一个生产者发送的消息会被多个消费者获取。一个生产者、一个交换机、多个队列、多个消费者
    如果消息发送到没有队列绑定的交换机上，那么消息将丢失。
    交换机不能存储消息，消息存储在队列中
    生产者：可以将消息发送到队列或者是交换机。
    消费者：只能从队列中获取消息。
    
3、topic模式

    3.1 topic模式
    队列绑定交换机,并指定队列routing key规则，更具规则来接受生产者发送的消息
    说明：生产者P发送消息到交换机X，type=topic，交换机根据绑定队列的routing key的值进行通配符匹配；
    符号#：匹配一个或者多个词 topic.# 可以匹配 topic.one或者topic.one.cor
    符号*：只能匹配一个词 topic.* 可以匹配 topic.one或者topic.two
    