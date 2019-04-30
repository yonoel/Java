package structuralPattern.src;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

// 动态代理，运行时创建的代理，方便的对代理者进行统一处理，而不需要去实现共同的接口
public class BussinessInvocationProxy implements InvocationHandler {
    private Object object;

    public BussinessInvocationProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("i'm dynamic proxy");
        return method.invoke(object, args);
    }
}
