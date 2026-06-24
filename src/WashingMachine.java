/**
 * 洗衣机类 — 练习：类与对象、方法定义、成员变量
 *
 * 知识点：
 *  1. 类 vs 对象：类是图纸，对象是造出来的真机
 *  2. new 关键字在堆内存中创建对象
 *  3. 方法封装行为（洗衣服、停止）
 */
public class WashingMachine {
    private String brand;    // 品牌
    private int capacity;    // 容量（公斤）
    private boolean isRunning; // 是否正在运行

    // 构造方法
    public WashingMachine(String brand, int capacity) {
        this.brand = brand;
        this.capacity = capacity;
        this.isRunning = false;  // 初始状态：停止
    }

    // 洗衣服
    public void wash() {
        if (isRunning) {
            System.out.println(brand + " 洗衣机已经在洗衣服了，别重复启动！");
        } else {
            isRunning = true;
            System.out.println(brand + " 洗衣机（" + capacity + "kg）开始洗衣服...");
        }
    }

    // 停止
    public void stop() {
        if (isRunning) {
            isRunning = false;
            System.out.println(brand + " 洗衣机已停止。");
        } else {
            System.out.println(brand + " 洗衣机本来就没在运行。");
        }
    }

    // ======== 测试入口 ========
    public static void main(String[] args) {
        WashingMachine wm = new WashingMachine("海尔", 10);

        wm.wash();   // 开始洗衣服
        wm.wash();   // 重复启动 → 提示已运行
        wm.stop();   // 停止
        wm.stop();   // 重复停止 → 提示未运行
    }
}
