//package util;
//
//import java.lang.reflect.Method;
//import java.util.concurrent.Callable;
//
//
//
//    class Main {
//
//        public void functionToPass(String message) {
//            String [] split = message.split(" ");
//            for (int i=0; i<split.length; i++)
//                System.out.println(split[i]);
//        }
//
//        public void outerFunction(Object object, Method method, String message) throws Exception {
//            Object[] parameters = new Object[1];
//            parameters[0] = message;
//            method.invoke(object, parameters);
//        }
//
//        public static void main(String[] args) throws Exception{
//            Class[] parameterTypes = new Class[1];
//            parameterTypes[0] = String.class;
//            Method functionToPass = Main.class.getMethod("functionToPass", parameterTypes[0]);
//            Main main = new Main();
//            main.outerFunction(main, functionToPass, "This is the input");
//        }
//    }
//    public class Profiler{
//        public void timeFucntion(Object object, Method method, String... values){
//            System.out.println("This is before");
//            Object[] params = new Object[values.length];
//
//            method.invoke()
//        }
//
//        public void printer(){
//
//        }
//    }
//
