package mlog.true123.cn.lib;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

/**
 * Created by junbo on 26/9/2016.
 */

public class MLog {
    private static LEVEL mLevel = LEVEL.V;
    private static boolean isEnable = true;
    private static String defaultMsg = "execute";
    private static String defaultTag = "MLog";
    private static final int stackIndex = 5;
    private static final int v = 0;
    private static final int d = 1;
    private static final int i = 2;
    private static final int w = 3;
    private static final int e = 4;

    private enum LEVEL {
        V("v", v), D("d", d), I("i", i), W("w", w), E("e", e);
        private String name;
        private int id;

        LEVEL(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }


        public int getId() {
            return id;
        }
    }

    private static void printLog(LEVEL level, String tag, String msg) {
        if (!isLoggable(level)) return;
        print(level, tag, getHead() + msg);
    }

    private static void print(LEVEL level, String tag, String msg) {
        switch (level.getId()) {
            case v:
                Log.v(tag, msg);
                break;
            case d:
                Log.d(tag, msg);
                break;
            case i:
                Log.i(tag, msg);
                break;
            case w:
                Log.w(tag, msg);
                break;
            case e:
                Log.e(tag, msg);
                break;

        }
    }

    private static String getHead() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();

        if (elements.length > stackIndex) {
            StackTraceElement stackTraceElement = elements[stackIndex];
            String fileName = stackTraceElement.getFileName();
            String methodName = stackTraceElement.getMethodName();
            int lineNumber = stackTraceElement.getLineNumber();
            return "[ (" + fileName + ":" + lineNumber + ")#" + methodName + " ] ";
        }
        return "";
    }

    public static void v() {
        printLog(LEVEL.V, defaultTag, defaultMsg);
    }

    public static void v(String msg) {
        printLog(LEVEL.V, defaultTag, msg);
    }

    public static void v(String tag, String msg) {
        printLog(LEVEL.V, tag, msg);
    }

    public static void v(Class clazz, String msg) {
        printLog(LEVEL.D, getTag(clazz), msg);
    }

    public static void d() {
        printLog(LEVEL.D, defaultTag, defaultMsg);
    }

    public static void d(String msg) {
        printLog(LEVEL.D, defaultTag, msg);
    }

    public static void d(String tag, String msg) {
        printLog(LEVEL.D, tag, msg);
    }

    public static void d(Class clazz, String msg) {
        printLog(LEVEL.D, getTag(clazz), msg);
    }

    public static void i() {
        printLog(LEVEL.I, defaultTag, defaultMsg);
    }

    public static void i(String msg) {
        printLog(LEVEL.I, defaultTag, msg);
    }

    public static void i(String tag, String msg) {
        printLog(LEVEL.I, tag, msg);
    }

    public static void i(Class clazz, String msg) {
        printLog(LEVEL.I, getTag(clazz), msg);
    }

    public static void w() {
        printLog(LEVEL.W, defaultTag, defaultMsg);
    }

    public static void w(String msg) {
        printLog(LEVEL.W, defaultTag, msg);
    }

    public static void w(String tag, String msg) {
        printLog(LEVEL.W, tag, msg);
    }

    public static void w(Class clazz, String msg) {
        printLog(LEVEL.W, getTag(clazz), msg);
    }

    public static void e() {
        printLog(LEVEL.E, defaultTag, defaultMsg);
    }

    public static void e(String msg) {
        printLog(LEVEL.E, defaultTag, msg);
    }

    public static void e(String tag, String msg) {
        printLog(LEVEL.E, tag, msg);
    }

    public static void e(Class clazz, String msg) {
        printLog(LEVEL.E, getTag(clazz), msg);
    }

    public static void e(Throwable tr) {
        printLog(LEVEL.E, defaultTag, getStackTraceString(tr));
    }

    public static void e(String tag, Throwable tr) {
        printLog(LEVEL.E, tag, getStackTraceString(tr));
    }

    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }

        // This is to reduce the amount of log spew that apps do in the non-error
        // condition of the network being unavailable.
        Throwable t = tr;
        while (t != null) {
            if (t instanceof UnknownHostException) {
                return "";
            }
            t = t.getCause();
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

    private static String getTag(Class clazz) {
        if (clazz != null) {
            if (clazz.isAnonymousClass()) {
                Class superClazz = clazz.getEnclosingClass();
                if (!superClazz.isAnonymousClass()) {
                    return superClazz.getSimpleName();
                }
            } else {
                return clazz.getSimpleName();
            }
        }

        return defaultTag;
    }

    public static void setIsEnable(boolean enable) {
        isEnable = enable;
    }

    public static void setLevel(LEVEL level) {
        mLevel = level;
    }

    private static boolean isLoggable(LEVEL level) {
        return isEnable && level.getId() >= mLevel.getId();
    }
}
