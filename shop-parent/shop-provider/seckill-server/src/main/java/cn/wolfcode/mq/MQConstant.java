package cn.wolfcode.mq;

/**
 * Created by lanxw
 */
public class MQConstant {
    //订单队列
    public static final String ORDER_CREATE_TOPIC = "ORDER_CREATE_TOPIC";
    //订单结果
    public static final String ORDER_RESULT_TOPIC = "ORDER_RESULT_TOPIC";
    //订单超时取消
    public static final String ORDER_PAY_TIMEOUT_TOPIC = "ORDER_PAY_TIMEOUT_TOPIC";
    //取消本地标识
    public static final String STOCK_OVER_FLAG_TOPIC = "STOCK_OVER_FLAG_TOPIC";
    //延迟消息等级
    public static final int ORDER_PAY_TIMEOUT_DELAY_LEVEL = 13;
    //延时消息发送超时时间
    public static final int MESSAGE_SEND_TIMEOUT = 3000;

}
