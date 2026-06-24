/**
 * 学生类 — 练习：类与对象、private封装、this关键字
 *
 * 知识点：
 *  1. private 封装字段，外部不能直接访问
 *  2. 构造方法 + this 区分成员变量和参数
 *  3. 方法返回 boolean / String
 *  4. if-else if-else 多分支判断
 */
public class Student {
    private String name;   // 姓名
    private int score;     // 分数

    // 构造方法：创建对象时初始化 name 和 score
    public Student(String name, int score) {
        this.name = name;    // this.name 是成员变量，name 是参数
        this.score = score;
    }

    // 展示学生信息
    public void showInfo() {
        System.out.println("姓名：" + name);
        System.out.println("分数：" + score);
    }

    // 判断是否及格（>=60）
    public boolean isPass() {
        return score >= 60;
    }

    // 等级评定：优秀(>=90) / 良好(>=75) / 及格(>=60) / 不及格(<60)
    public String getLevel() {
        if (score >= 90) {
            return "优秀";
        } else if (score >= 75) {
            return "良好";
        } else if (score >= 60) {
            return "及格";
        } else {
            return "不及格";
        }
    }

    // ======== 测试入口 ========
    public static void main(String[] args) {
        Student s1 = new Student("张三", 92);
        Student s2 = new Student("李四", 58);
        Student s3 = new Student("王五", 75);

        // 测试3个不同等级的学生
        Student[] students = {s1, s2, s3};
        for (Student s : students) {
            s.showInfo();
            System.out.println("是否及格：" + (s.isPass() ? "是" : "否"));
            System.out.println("等级：" + s.getLevel());
            System.out.println("--------------------");
        }
    }
}
