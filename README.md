# WechatMultiWebview

利用 Xposed 实现微信以前的 `//multiwebview` 功能。

实现很简单：hook `startActivity`，向 `intent` 添加 `FLAG_ACTIVITY_NEW_DOCUMENT` 和 `FLAG_ACTIVITY_MULTIPLE_TASK` 两个 Flag 即可，其中前者使得 `activity` 在新任务中开启，后者使得开启多个 `activity` 时每个 `activity` 都在新的任务中开启，这样可以同时打开多个网页，分别在多个窗口中显示。

本人初学 Android 开发，更是第一次开发 Xposed 模块，代码比较 ugly，还请大家包涵。如有意见建议，望不吝赐教。