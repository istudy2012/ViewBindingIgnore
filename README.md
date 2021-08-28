# ViewBindingIgnore



简单的类, 用来在xml中默认添加 tools:viewBindingIgnore="true" 属性



## Background

Kotlin Android Extensions 已经被标记为 deprecated，我们应该迁移到ViewBinding

https://android-developers.googleblog.com/2020/11/the-future-of-kotlin-android-extensions.html

https://developer.android.com/topic/libraries/view-binding

打开 ViewBinding

```groovy
android {
    ...
    buildFeatures {
        viewBinding true
    }
}
```

对于较大的项目, xml比较多, 如果一下子把存量的Kotlin synthetics都转换成ViewBinding, 工作量很大

可行的方式是新增的XML使用ViewBinding, 存量的仍保持旧的使用方式, 在项目开发中逐渐替换



但是ViewBinding默认会把所有的xml都生成对应的ViewBinding类， 对于存量的代码是没有用到的

这会损失一些编译时的性能和包大小

因此我们需要一个工具来吧存量的xml都加上ignore flag, 避免转换成对应的Class



详细可以参考具体的实现ViewBindingHandler.kt



## 说明

1. 最好的解决办法是改变ViewBinding默认  tools:viewBindingIgnore为true, 但是不知道怎么调整

   https://cs.android.com/android-studio/platform/tools/adt/idea/+/mirror-goog-studio-main:android/src/com/android/tools/idea/databinding/index/BindingXmlIndex.kt;l=159

2. I first choose use Python and use xml.dom.minidom module, but pycharm and vscode can not code complement

   首选是使用Python，使用xml.dom.minidom module, 但是发现pycharm和vscode无法代码补全, 就放弃了

    https://youtrack.jetbrains.com/issue/PY-46355
