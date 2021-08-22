package cn.dizent.rpcfx.demo.common;

import java.math.BigDecimal;

/**
 * @Auther: 布谷
 * @Date: 2021/8/22 21:04
 * @Description:
 */
public class Order {

    private int orderId;

    private String info;

    private BigDecimal amount;

    public Order() {
    }

    public Order(int orderId, String info, BigDecimal amount) {
        this.orderId = orderId;
        this.info = info;
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
