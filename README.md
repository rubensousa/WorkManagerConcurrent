# WorkManagerConcurrent

https://issuetracker.google.com/issues/121345393

To reproduce on Android 5.0/5.1, just run the sample and keep pressing "Start workers":

```
java.util.ConcurrentModificationException
        at java.util.WeakHashMap$HashIterator.next(WeakHashMap.java:165)
        at androidx.work.impl.utils.WakeLocks.checkWakeLocks(WakeLocks.java:78)
        at com.github.rubensousa.workmanagerconcurrent.MainActivity$runnable$1.run(MainActivity.kt:17)
        at android.os.Handler.handleCallback(Handler.java:739)
        at android.os.Handler.dispatchMessage(Handler.java:95)
        at android.os.Looper.loop(Looper.java:135)
        at android.app.ActivityThread.main(ActivityThread.java:5221)
        at java.lang.reflect.Method.invoke(Native Method)
        at java.lang.reflect.Method.invoke(Method.java:372)
        at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:899)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:694)
```

The same happens inside WorkManager, but the exception is:

```
Fatal Exception: java.util.ConcurrentModificationException
       at java.util.WeakHashMap$HashIterator.next(WeakHashMap.java:165)
       at androidx.work.impl.utils.WakeLocks.checkWakeLocks(WakeLocks.java:78)
       at androidx.work.impl.background.systemalarm.SystemAlarmService.onAllCommandsCompleted(SystemAlarmService.java:69)
       at androidx.work.impl.background.systemalarm.SystemAlarmDispatcher.dequeueAndCheckForCompletion(SystemAlarmDispatcher.java:217)
       at androidx.work.impl.background.systemalarm.SystemAlarmDispatcher$DequeueAndCheckForCompletion.run(SystemAlarmDispatcher.java:317)
       at android.os.Handler.handleCallback(Handler.java:810)
       at android.os.Handler.dispatchMessage(Handler.java:99)
       at android.os.Looper.loop(Looper.java:189)
       at android.app.ActivityThread.main(ActivityThread.java:5532)
       at java.lang.reflect.Method.invoke(Method.java)
       at java.lang.reflect.Method.invoke(Method.java:372)
       at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:950)
       at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:745)
```
