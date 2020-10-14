package com.xp.common.constant;

/**
 * @author Climb.Xu
 * @date 2020/9/19 23:35
 */
public class DemandConstant {

    public enum DemandStatus {
        CREATED(0, "新建"),
        ASSIGNED(1, "完成中"),
        FINISH(2, "已完成"),
        CANCEL(3, "取消"),
        AUDIT(4,"待审核")//发布需求 带管理员审核
        ;
        private final int code;
        private final String msg;

        DemandStatus(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
    public enum OrderStatus {
        CREATED(0, "新建"),
        CANCEL(1, "取消"), //发布者取消
        FINISH(2, "已完成"),
        UNFINISHED(3, "未完成"), //接单者未完成或取消
        AUDIT(4,"待审核")
        ;
        private final int code;
        private final String msg;

        OrderStatus(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
