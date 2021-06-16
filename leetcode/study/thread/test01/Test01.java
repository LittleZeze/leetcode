package study.thread.test01;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import study.thread.Sleeper;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 保护性暂停
 */

@Slf4j(topic = "c.Test01")
public class Test01 {


    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        for (int i = 0; i < 3; i++) {
            new Poeple().start();
        }
        Sleeper.sleep(1);
        for (Integer id : MailBoxes.getIds()) {
            new Postman(id, "内容" + id).start();
        }

/*      // 线程1等待线程2的下载结果
        new Thread(() -> {
            log.debug("begin");
            Object response = guardedObject.get(2000);
            log.debug("response: {}", response);
        }, "t1").start();

        new Thread(() -> {
            log.debug("begin");
            Sleeper.sleep(1);
            guardedObject.complete(null);
        }, "t2").start();*/

/*        new Thread(() -> {
            log.debug("等待结果");
            // 等待结果
            List<String> list = (List<String>) guardedObject.get();
            log.debug("结果大小：{}", list.size());
        }, "t1").start();

        new Thread(() -> {
            log.debug("执行下载");
            try {
                List<String> list = Downloader.download();
                guardedObject.complete(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "t2").start();*/

    }

}

@Slf4j(topic = "c.People")
class Poeple extends Thread {
    @Override
    public void run() {
        // 收信
        GuardedObject guardedObject = MailBoxes.createGuardedObject();
        log.debug("开始收信 id：{}", guardedObject.getId());
        Object mail = guardedObject.get(5000);
        log.debug("收到信件 id：{}， 内容：{}", guardedObject.getId(), mail);
    }
}

@Slf4j(topic = "c.Postman")
class Postman extends Thread {
    private int id;
    private String mail;

    public Postman(int id, String mail) {
        this.id = id;
        this.mail =mail;
    }

    @Override
    public void run() {
        GuardedObject guardedObject = MailBoxes.getGuardedObject(id);
        log.debug("开始送信 id：{}, 内容：{}", id, mail);
        guardedObject.complete(mail);
    }
}

class MailBoxes {
    private static Map<Integer, GuardedObject> boxes = new ConcurrentHashMap<>();

    public static GuardedObject getGuardedObject(int id) {

        return boxes.remove(id);
    }

    private static int id = 1;
    // 产生唯一的ID, synchronized加在static方法上相当于加在MailBoxes类对象上
    private static synchronized int generateId() {
        return id++;
    }

    public static GuardedObject createGuardedObject() {
        GuardedObject go = new GuardedObject(generateId());
        boxes.put(go.getId(), go);
        return go;
    }

    public static Set<Integer> getIds() {
        return boxes.keySet();
    }
}

@Data
class GuardedObject {

    private int id;
    private Object response;

    public GuardedObject() {

    }

    public GuardedObject(int id) {
        this.id = id;
    }

    // 获取结果
    // timeout 表示要等多久
    public Object get(long timeout) {
        synchronized (this) {
            // 记录开始时间
            long begin = System.currentTimeMillis();
            // 经历时间
            long passedTime = 0;
            while (response == null) {
                // 本轮循环需要等待时间
                long waitTime = timeout - passedTime;
                if (waitTime <= 0) {
                    break;
                }
                try {
                    this.wait(waitTime); // 虚假唤醒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passedTime = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    // 等待结果
    public void complete(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}
